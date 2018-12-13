
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.abs;

import static org.aha.euclid.math.EuclidMath.len;
import static org.aha.euclid.math.Vectors.pointToString;
import static org.aha.euclid.math.Vectors.vectorToString;

import java.io.Serializable;

import org.aha.euclid.math.Comparisons;

/**
 * <p>
 *   Plane in 3D.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Plane3 implements Cloneable, Serializable 
{
  private static final long serialVersionUID=1552135831353091790L;

  protected double m_a=0.0;
  
  protected double m_b=1.0;
  
  protected double m_c=0.0;
  
  protected double m_x0=0.0;
  
  protected double m_y0=0.0;
  
  protected double m_z0=0.0;
  
  /**
   * <p>
   *   Constructor.
   * </p>
   */
  public Plane3(){}
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Plane3(Plane3 o){ set(o); }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * <p>
   *   The normal {@code this} stores will be {@code [nx, ny, nz]} normalized.
   * </p> 
   * @param x  X coordinate to point in plane that defines {@code this} plane.
   * @param y  Y coordinate to point in plane that defines {@code this} plane.
   * @param z  Z coordinate to point in plane that defines {@code this} plane.
   * @param nx X component to normal vector that defines {@code this} plane.
   * @param ny Y component to normal vector that defines {@code this} plane.
   * @param nz Z component to normal vector that defines {@code this} plane.
   * @throws ZeroLengthVectorException If normal vector of zero length passed. 
   */
  public Plane3(double x, double y, double z, double nx, double ny, double nz)
  {
    set(x, y, z, nx, ny, nz);
  }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * <p>
   *   The normal {@code this} stores will be {@code n} normalized.
   * </p> 
   * @param p Point in plane that defines {@code this} plane.
   * @param n Normal vector that defines {@code this} plane.
   * @throws ZeroLengthVectorException If normal vector of zero length passed. 
   */
  public Plane3(double[] p, double[] n){ set(p, n); }
  
  /**
   * <p>
   *   Assigns to other plane.
   * </p>
   * @param o Other.
   */
  public final void set(Plane3 o)
  { 
    m_x0=o.m_x0; 
    m_y0=o.m_y0; 
    m_z0=o.m_z0; 
    
    m_a=o.m_a;
    m_b=o.m_b;
    m_c=o.m_c;
  }
  
  /**
   * <p>
   *   Sets point in plane that defines {@code this} plane.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.
   */
  public void setP0(double x, double y, double z){ m_x0=x; m_y0=y; m_z0=z; }
  
  /**
   * <p>
   *   Sets point in plane that defines {@code this} plane.
   * </p>
   * @param p Point.
   */
  public void setP0(double[] p){ m_x0=p[0]; m_y0=p[1]; m_z0=p[2]; }
  
  /**
   * <p>
   *   Sets the normal that defines {@code this} plane.
   * </p>
   * <p>
   *   The normal {@code this} stores will be passed vector normalized.
   * </p>
   * @param nx Vector's x component.
   * @param ny Vector's y component.
   * @param nz Vector's z component.
   * @throws ZeroLengthVectorException If vector of zero length passed.
   */
  public void setNormal(double nx, double ny, double nz)
  {
    if (Comparisons.zero3dVector(nx, ny, nz))
    {
      throw new ZeroLengthVectorException();
    }
    
    double l=len(nx, ny, nz);
    m_a=nx/l;
    m_b=ny/l;
    m_c=nz/l;
  }
  
  /**
   * <p>
   *   Sets the normal that defines {@code this} plane.
   * </p>
   * <p>
   *   The normal {@code this} stores will be passed vector normalized.
   * </p>
   * @param n Vector.
   * @throws ZeroLengthVectorException If vector of zero length passed.
   */
  public void setNormal(double[] n){ setNormal(n[0], n[1], n[2]); }
  
  /**
   * <p>
   *   Sets point and normal vector defining {@code this} plane.
   * </p>
   * <p>
   *   The normal {@code this} stores will be {@code [nx, ny, nz]} normalized.
   * </p> 
   * @param x  Point's x coordinate.
   * @param y  Point's y coordinate.
   * @param z  Point's z coordinate.
   * @param nx Normal vector's x component.
   * @param ny Normal vector's y component.
   * @param nz Normal vector's z component.
   * @throws ZeroLengthVectorException If normal vector of zero length passed. 
   */
  public void set(double x, double y, double z, double nx, double ny, double nz)
  {
    setNormal(nx, ny, nz);
    setP0(x, y, z);
  }
  
  /**
   * <p>
   *   Sets point and normal vector defining {@code this} plane.
   * </p>
   * <p>
   *   The normal {@code this} stores will be {@code n} normalized.
   * </p> 
   * @param p Point.
   * @param n Normal vector.
   * @throws ZeroLengthVectorException If normal vector of zero length passed. 
   */
  public void set(double[] p, double[] n){ setNormal(n); setP0(p); }
  
  /**
   * <p>
   *   Get x coordinate of point defining {@code this} plane.
   * </p>
   * @return X coordinate.
   */
  public final double x0(){ return m_x0; }
  
  /**
   * <p>
   *   Get y coordinate of point defining {@code this} plane.
   * </p>
   * @return Y coordinate.
   */
  public final double y0(){ return m_y0; }
  
  /**
   * <p>
   *   Get z coordinate of point defining {@code this} plane.
   * </p>
   * @return Z coordinate.
   */
  public final double z0(){ return m_z0; }
  
  /**
   * <p>
   *   Gets point in plane that defines {@code this} plane.
   * </p>
   * @param p Assigned to point, if {@code null} allocates.
   * @return Point.
   */
  public final double[] getP0(){ return getP0(null); }
  
  /**
   * <p>
   *   Gets point in plane that defines {@code this} plane.
   * </p>
   * @param p Assigned to point, if {@code null} allocates.
   * @return Point.
   */
  public final double[] getP0(double[] p)
  {
    p=(p==null) ? new double[3] : p;
    p[0]=m_x0;
    p[1]=m_y0;
    p[2]=m_z0;
    return p;
  }
  
  /**
   * <p>
   *   Gets normal vector that defines {@code this} plane.
   * </p>
   * @return Normal vector.
   */
  public final double[] getNormal(){ return getNormal(null); }
  
  /**
   * <p>
   *   Gets normal vector that defines {@code this} plane.
   * </p>
   * @param n Assigned to vector, if {@code null} allocates.
   * @return Normal vector.
   */
  public final double[] getNormal(double[] n)
  {
    n=(n==null) ? new double[3] : n;
    n[0]=m_a;
    n[1]=m_b;
    n[2]=m_c;
    return n;
  }
  
  /**
   * <p>
   *   Gets the distance from a point to the plane.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.
   * @return Distance: {@code abs(axbycd(x, y, z))}
   */
  public final double distance(double x, double y, double z)
  { 
    return abs(axbyczd(x, y, z));
  }
  
  /**
   * <p>
   *   Gets the distance from a point to the plane.
   * </p>
   * @param Point.
   * @return Distance: {@code abs(axbycd(p))}
   */
  public final double distance(double[] p){ return abs(axbyczd(p)); }
  
  /**
   * <p>
   *   Finds the closest point in {@code this} plane to given point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.
   * @return closest point in {@code this} to {@code (x,y,z)}.
   */
  public final double[] closest(double x, double y, double z)
  {
    return closest(x, y, z, null);  
  }
  
  /**
   * <p>
   *   Finds the closest point in {@code this} plane to given point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.
   * @param c Assigned to closest point. If {@code null} allocates.
   * @return closest point in {@code this} to {@code (x,y,z)}.
   */
  public final double[] closest(double x, double y, double z, double[] c)
  {
    c=(c==null) ? new double[3] : c;
    double d=axbyczd(x, y, z);
    c[0]=x-m_a*d;
    c[1]=y-m_b*d;
    c[2]=z-m_c*d;
    return c;
  }
  
  /**
   * <p>
   *   Finds the closest point in {@code this} plane to given point.
   * </p>
   * @param p Point.
   * @return closest point in {@code this} to {@code p}.
   */
  public final double[] closest(double[] p){ return closest(p[0], p[1], p[2]); }
  
  /**
   * <p>
   *   Finds the closest point in {@code this} plane to given point.
   * </p>
   * @param p Point.
   * @param c Assigned to closest point. If {@code null} allocates.
   * @return closest point in {@code this} to {@code p}.
   */
  public final double[] closest(double[] p, double[] c)
  { 
    return closest(p[0], p[1], p[2], c); 
  }
  
  // Hyperplane properties.
  
  /**
   * <p>
   *   Gets the <i>a</i> coefficient of the plane's implicit equation
   *   <i>ax+by+cz+d=0</i>.
   * </p>
   * <p>
   *   Is the first component of the planes normal vector.
   * </p>
   * @return a.
   */
  public final double a(){ return m_a; }
  
  /**
   * <p>
   *   Gets the <i>b</i> coefficient of the plane's implicit equation
   *   <i>ax+by+cz+d=0</i>.
   * </p>
   * <p>
   *   Is the second component of the planes normal vector.
   * </p>
   * @return b.
   */
  public final double b(){ return m_b; }
  
  /**
   * <p>
   *   Gets the <i>c</i> coefficient of the plane's implicit equation
   *   <i>ax+by+cz+d=0</i>.
   * </p>
   * <p>
   *   Is the third component of the planes normal vector.
   * </p>
   * @return c.
   */
  public final double c(){ return m_c; }
  
  /**
   * <p>
   *   Gets the constant <i>d</i> of the plane's implicit equation
   *   <i>ax+by+cz+d=0</i>.
   * </p>
   * @return d.
   */
  public final double d(){ return -(m_a*m_x0+m_b*m_y0+m_c*m_z0); }
  
  /**
   * <p>
   *   Evaluates left side of the plane's implicit equation <i>ax+by+cy+d</i> 
   *   at a given point. 
   * </p>
   * <p>
   *   Since the normal vector is normalized this will be the signed distance
   *   from the point to the plane: 
   *   
   *   Distances to all points on same side of plane will have the same sign.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.
   * @return Signed distance, {@code 0} if point in plane
   */
  public final double axbyczd(double x, double y, double z)
  { 
    return m_a*x+m_b*y+m_c*z+d(); 
  }
  
  /**
   * <p>
   *   Evaluates left side of the plane's implicit equation <i>ax+by+cy+d</i> 
   *   at a given point. 
   * </p>
   * <p>
   *   Since the normal vector is normalized this will be the signed distance
   *   from the point to the plane: 
   *   
   *   Distances to all points on same side of plane will have the same sign.
   * </p>
   * @param p Point.
   * @return Signed distance, {@code 0} if point in plane
   */
  public final double axbyczd(double[] p){ return axbyczd(p[0], p[1], p[2]); }
  
  /**
   * <p>
   *   Tells if {@code o} defines the same plane as {@code this}.
   * <p>
   * @param o Other.
   * @return {@code true} if does else {@code false}.
   */
  public boolean same(Plane3 o)
  {
    if (o==this) return true;
    return Comparisons.same(m_a, m_b, m_c, o.m_a, o.m_b, o.m_c) &&
           Comparisons.zero(o.axbyczd(m_x0, m_y0, m_z0));
  }
  
  // Cloneable and Object overrides.
  
  @Override
  public Object clone()
  { 
    try { return super.clone(); }
    catch (CloneNotSupportedException cnsx){ throw new Error(); }    
  }

  @Override
  public boolean equals(Object o)
  {
    if (this==o) return true;
    if (o instanceof Plane3)
    {
      Plane3 p=(Plane3)o;
      return Comparisons.same(m_x0, m_y0, m_z0, p.m_x0, p.m_y0, p.m_z0) &&
             Comparisons.same(m_a, m_b, m_c, p.m_a, p.m_b, p.m_c);
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    long bits=1L;
    bits=31L*bits+((m_x0==0) ? 0 : doubleToLongBits(m_x0));
    bits=31L*bits+((m_y0==0) ? 0 : doubleToLongBits(m_y0));
    bits=31L*bits+((m_z0==0) ? 0 : doubleToLongBits(m_z0));
    bits=31L*bits+((m_a==0) ? 0 : doubleToLongBits(m_a));
    bits=31L*bits+((m_b==0) ? 0 : doubleToLongBits(m_b));
    bits=31L*bits+((m_c==0) ? 0 : doubleToLongBits(m_c));
    return (int)(bits^(bits>>32));    
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("p=").append(pointToString(m_x0, m_y0, m_z0))
      .append(",n=").append(vectorToString(m_a, m_b, m_c));
    return sb.toString();
  }
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Plane3[] THE_EMPTY_ARRAY=new Plane3[0];
  
}
