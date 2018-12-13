
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static java.lang.System.arraycopy;
import static org.aha.euclid.math.Vectors.dot;
import static org.aha.euclid.math.Vectors.len;
import static org.aha.euclid.math.Vectors.norm;
import static org.aha.euclid.math.Vectors.scale;

import java.io.Serializable;
import java.util.Arrays;

import org.aha.euclid.math.Comparisons;

/**
 * <p>
 *   Represents a hyper plane of dimension {@code d}'s implicit equation: 
 *   {@code n*p+c=0} where {@code n} is a vector of dimension {@code d} and
 *   {@code c} is a constant.
 *   
 *   The hyper plane of dimension {@code 1} is a point, of dimension {@code 2}
 *   a line in 2D and the hyper plane of dimension {@code 3} is a plane in 3D.  
 * </p>
 * <p>
 *   This class provides faster signed distance computation, testing if points
 *   are on the same side of the plane or if is in the plane than using 
 *   {@link Line2} and 
 *   {@link Plane3} since those objects does not hold the implicit equation:
 * </p>
 * <p>  
 *   If 
 *   {@link #eva(double)},
 *   {@link #eva(double, double)},
 *   {@link #eva(double, double, double)} or
 *   {@link #eva(double[])} returns value that is considered the same as 
 *   {@code 0.0} the point is in the plane.
 * </p>
 * <p>  
 *   If for two points the value returned by the above methods has the same sign
 *   they are on the same side of the plane. 
 * </p>
 * <p>
 *   If the vector {@code n} of the length {@code 1.0} the equation is said to
 *   be normalized and the absolute value returned by the above methods is the
 *   distance from the point to the plane. 
 * </p>
 * <p>
 *   This is mutable but only assignment to a hyperplane of the same dimension
 *   is legal.
 * </p>
 * <p>
 *   Note that the support for 1D huperplane is needed for completeness, it has
 *   no practical use.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public final class Hyperplane implements Cloneable, Serializable 
{
  private static final long serialVersionUID=-8717135832124782814L;

  private final double[] m_n;
  
  private double m_c=0.0;
 
  /**
   * <p>
   *   Creates hyperplane of dimension 2 initialized to the normalized implicit
   *   equation for the 2D line {@code x=0}.
   * </p>
   */
  public Hyperplane(){ this(2); }
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Hyperplane(Hyperplane o){ m_n=o.n(); m_c=o.c(); }
  
  /**
   * <p>
   *   Creates hyperplane of given dimension where the vector
   *   {@link #n() n} has first component set to {@code 1.0} and the rest (if
   *   {@code d>0}) set to {@code 0} and the constant 
   *   {@link #c() c} set to 0.0.
   *   
   *   Note that this is a normalized form.
   * </p>
   * @param d Dimension.
   * @throws IllegalArgumentException If {@code d<1}.
   */
  public Hyperplane(int d)
  {
    if (d<=0)
    {
      throw new IllegalArgumentException("d<1 : "+d);
    }
    
    m_n=new double[d];
    m_n[0]=1.0;
  }
  
  /**
   * <p>
   *   Creates normalized plane of dimension {@code 1}, a point in 1D.
   * </p>
   * @param x Coordinate of the 1D point.
   */
  public Hyperplane(double x){ m_n=new double[]{ 1.0 }; m_c=-x; }
  
  /**
   * <p>
   *   Creates normalized plane of dimension {@code 2}, a line in 2D.
   * </p>
   * @param x0 X coordinate of first point defining line.
   * @param y0 Y coordinate of first point defining line. 
   * @param x1 X coordinate of second point defining line.
   * @param y1 Y coordinate of second point defining line. 
   */
  public Hyperplane(double x0, double y0, double x1, double y1)
  {
    m_n=new double[2];
    set(x0, y0, x1, y1);
  }
  
  /**
   * <p>
   *   Creates normalized hyperplane of dimension {@code 2}, a line in 2D if 
   *   passed vectors of length {@code 2} or a normalized hyperplane of 
   *   dimension {@code 3} if passed vectors of length {@code 3}.    
   * </p>
   * @param u Either a point defining a 2D line or the normal to a 3D plane.
   * @param v Either second point defining a 2D line or a point in a 3D plane.
   * @throws IllegalArgumentException If {@code u.length!=2 || u.length!=3}.
   */
  public Hyperplane(double[] u, double[] v)
  { 
    m_n=new double[u.length]; 
    set(u, v);
  }
  
  /**
   * <p>
   *   Creates normalized hyperplane of dimension {@code 3}, a plane in 3D.
   * </p>
   * <p>
   *   Passed normal vector needs not be normalized.
   * </p>
   * @param nx Plane normal x component.
   * @param ny Plane normal y component.
   * @param nz Plane normal z component.
   * @param px Point in plane x component.   
   * @param py Point in plane y component.
   * @param pz Point in plane z component.  
   */
  public Hyperplane(double nx, double ny, double nz, double px, double py,
    double pz)
  {
    m_n=new double[3];
    set(nx, ny, nz, px, py, pz);
  }
  
  /**
   * <p>
   *   Creates normalized hyperplane of dimension {@code 3}, a plane in 3D.
   * </p>
   * @param p Plane.
   */
  public Hyperplane(Plane3 p){ m_n=new double[3]; set(p); }
  
  /**
   * <p>
   *   Generic constructor.
   * </p>
   * <p>
   *   If hyperplane need to be in normalized form it is up to client to make
   *   sure parameters defines plane normalized, this does not normalize.
   * </p>
   * @param c Constant.
   * @param n Vector.
   * @throws IllegalArgumentException If {@code n.length==0}.
   */
  public Hyperplane(double c, double... n)
  {
    if (n.length==0)
    {
      throw new IllegalArgumentException("n.length==0");
    }
    
    m_n=n.clone();
    m_c=c;
  }
  
  /**
   * <p>
   *   Assigns to other.
   * </p
   * @param o Other.
   */
  public void set(Hyperplane o)
  {
    int n=o.m_n.length;
    if (m_n.length!=o.m_n.length)
    {
      throw new IllegalArgumentException("this is of dimension : "+m_n.length+
        ", p is of dimension : "+n);
    }
    
    arraycopy(m_n, 0, o.m_n, 0, n);
    m_c=o.m_c;
  }
  
  /**
   * <p>
   *   Assigns {@code this} to a new 2D hyperplane, a 2D line.
   * </p>
   * <p>
   *   This hyperplane will be normalized.
   * </p>
   * @param x0 X coordinate of first point defining 2D line.
   * @param y0 Y coordinate of first point defining 2D line.
   * @param x1 X coordinate of second point defining 2D line.
   * @param y1 Z coordinate of second point defining 2D line.
   * @throws IllegalArgumentException If {@code this} not of dimension 
   *         {@code 2}.
   */
  public void set(double x0, double y0, double x1, double y1)
  {
    if (m_n.length!=2)
    {
      throw new IllegalArgumentException("this of dimension: "+m_n.length);  
    }
    
    m_n[0]=y0-y1;
    m_n[1]=x1-x0;
    m_c=x0*y1-x1*y0;
    double l=len(m_n);
    double s=1.0/l;
    scale(m_n, s);
    m_c*=s;
  }
  
  /**
   * <p>
   *   Assigns {@code this} to a new 2D hyperplane, a 2D line.
   * </p>
   * <p>
   *   This hyperplane will be normalized.
   * </p> 
   * @param l Line.
   * @throws IllegalArgumentException If {@code this} not of dimension 
   *         {@code 2}. 
   */
  public void set(Line2 l){ set(l.x0(), l.y0(), l.x1(), l.y1()); }
  
  /**
   * <p>
   *   Assigns {@code this} to a new 3D hyperplane, a plane in 3D. 
   * </p>
   * <p>
   *   This hyperplane will be normalized.
   * </p> 
   * @param nx X component to normal defining the plane.
   * @param ny Y component to normal defining the plane.
   * @param nz Z component to normal defining the plane.
   * @param px X coordinate to point in plane defining the plane.
   * @param py Y coordinate to point in plane defining the plane.
   * @param pz Z coordinate to point in plane defining the plane.
   * @throws IllegalArgumentException If {@code this} not of dimension 
   *         {@code 3}. 
   */
  public void set(double nx, double ny, double nz, double px, double py,
    double pz)
  {
    if (m_n.length!=3)
    {
      throw new IllegalArgumentException("this of dimension: "+m_n.length);  
    }
    
    m_n[0]=nx;
    m_n[1]=ny;
    m_n[2]=nz;
    norm(m_n);
    m_c=-(m_n[0]*px+m_n[1]*py+m_n[2]*pz);
  }
  
  /**
   * <p>
   *   Assigns {@code this} to a new hyperplane of dimension {@code 2}, a line 
   *   in 2D if passed vectors of length {@code 2} or a hyperplane of dimension 
   *   {@code 3} if passed vectors of length {@code 3}.    
   * </p>
   * <p>
   *   This hyperplane will be normalized.
   * </p> 
   * @param u Either a point defining a 2D line or the normal to a 3D plane.
   * @param v Either second point defining a 2D line or a point in a 3D plane.
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   * @throws IllegalArgumentException If {@code u.length!=2 || u.length!=3}.
   * @throws IllegalArgumentException If {@code this} of not correct dimension.
   */
  public void set(double[] u, double[] v)
  {
    int n=u.length;
    
    if (n!=v.length)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    switch (n)
    {
      case 2 : set(u[0], u[1], v[0], v[1]); return;
      case 3 : set(u[0], u[1], u[2], v[0], v[1], v[2]); return;
    }
    
    throw new IllegalArgumentException("u.length="+u.length);
  }
  
  /**
   * <p>
   *   Assigns {@code this} to a new 3D hyperplane, a plane in 3D. 
   * </p>
   * <p>
   *   This hyperplane will be normalized.
   * </p> 
   * @param p Plane.
   * @throws IllegalArgumentException If {@code this} not of dimension 
   *         {@code 3}. 
   */
  public void set(Plane3 p)
  { 
    if (m_n.length!=3)
    {
      throw new IllegalArgumentException("this of dimension: "+m_n.length);  
    }
    
    m_n[0]=p.a(); 
    m_n[1]=p.b(); 
    m_n[2]=p.c(); 
    m_c=p.d();
  }
  
  /**
   * <p>
   *   Gets the dimension of {@code this} hyperplane.
   * </p>
   * @return Dimension.
   */
  public int getDimension(){ return m_n.length; }
  
  /**
   * <p>
   *   Gets the vector {@code n} of {@code this} hyperplane's implicit equation
   *   {@code n*p+c}.
   * </p>
   * @return Vector.
   */
  public double[] n(){ return n(null); }
  
  /**
   * <p>
   *   Gets the vector {@code n} of {@code this} hyperplane's implicit equation
   *   {@code n*p+c}.
   * </p>
   * @param n Assigned to vector, if {@code null} allocates.
   * @return Vector.
   * @throws IndexOutOfBoundsException If 
   *         {@code n!=null && n.length<getDimension()}.
   */
  public double[] n(double[] n)
  {
    int d=m_n.length;
    n=(n==null) ? new double[d] : n;
    arraycopy(m_n, 0, n, 0, d);
    return n;
  }
  
  /**
   * <p>
   *   Gets the constant {@code c} of {@code this} hyperplane's implicit 
   *   equation {@code n*p+c}.
   * </p>
   * @return Constant.
   */
  public double c(){ return m_c; }
  
  /**
   * <p>
   *   Evaluates {@code n*p+c} for the hyperplane of dimension {@code 1}, a 1D
   *   point).
   * </p>
   * @param x Coordinate for the point in 1D to evaluate at.
   * @return {@code x+c} where {@code c} is the constant that can be fetched by
   *         {@link Hyperplane#c()}.
   */
  public double eva(double x){ return x+m_c; }
  
  /**
   * <p>
   *   Evaluates {@code n*p+c} for the hyperplane of dimension {@code 2} (a 2D
   *   line).
   * </p>
   * @param x X coordinate for the point in 2D to evaluate at.
   * @param y Y coordinate for the point in 2D to evaluate at. 
   * @return {@code n[0]*x+n[1]*y+c} where {@code n} is the vector that can be
   *         fetched by
   *         {@link Hyperplane#n()} and {@code c} is the constant that can be 
   *         fetched by
   *         {@link Hyperplane#c()}.
   */
  public double eva(double x, double y){ return m_n[0]*x+m_n[1]*y+m_c; }
  
  /**
   * <p>
   *   Evaluates {@code n*p+c} for the hyperplane of dimension {@code 3} (a 3D
   *   line).
   * </p>
   * @param x X coordinate for the point in 3D to evaluate at.
   * @param y Y coordinate for the point in 3D to evaluate at. 
   * @param z Z coordinate for the point in 3D to evaluate at. 
   * @return {@code n[0]*x+n[1]*y+n[2]*z+c} where {@code n} is the vector that 
   *         can be fetched by
   *         {@link Hyperplane#n()} and {@code c} is the constant that can be 
   *         fetched by
   *         {@link Hyperplane#c()}.
   */
  public double eva(double x, double y, double z)
  { 
    return m_n[0]*x+m_n[1]*y+m_n[2]*z+m_c; 
  }
  
  /**
   * <p>
   *   Evaluates {@code n*p+c} for {@code this} hyperplane.
   * </p>
   * @param p The point in to evaluate at.
   * @return {@code n*p+c} where {@code n} is the vector that 
   *         can be fetched by
   *         {@link Hyperplane#n()} and {@code c} is the constant that can be 
   *         fetched by
   *         {@link Hyperplane#c()}.
   */
  public double eva(double[] p)
  {
    int n=p.length;
    switch (n)
    {
      case 0 : throw new IllegalArgumentException("p.length==0");
      case 1 : return p[0]+m_c;
      case 2 : return m_n[0]*p[0]+m_n[1]*p[1]+m_c;
      case 3 : return m_n[0]*p[0]+m_n[1]*p[1]+m_n[2]*p[2]+m_c;
    }
    
    return dot(m_n, p)+m_c;
  }
    
  @Override
  public String toString() 
  {
    StringBuilder sb=new StringBuilder();
    
    int n=m_n.length;
    for (int i=0; i<n; i++)
      sb.append(m_n[i]).append("*c").append(i).append("+");
    sb.append(m_c);
    
    return sb.toString();
  }
  
  @Override
  public Object clone() 
  {
    try { return super.clone(); }
    catch (CloneNotSupportedException cnx){ throw new Error(); }
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (o==this) return true;
    if (o instanceof Hyperplane)
    {
      Hyperplane h=(Hyperplane)o;
      return Comparisons.equals(m_c, h.m_c) && Arrays.equals(m_n, h.m_n);
    }
    return false;
  }
  
  @Override
  public int hashCode()
  {
    long bits=1L;
    int n=m_n.length;
    for (int i=0; i<n; i++)
    {
      double curr=m_n[i];
      bits=31L*bits+((curr==0) ? 0 : doubleToLongBits(curr));
    }
    bits=31L*bits+((m_c==0) ? 0 : doubleToLongBits(m_c));
    return (int)(bits^(bits>>32));
  } 
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public final static Hyperplane[] THE_EMPTY_ARRAY=new Hyperplane[0];
  
}
