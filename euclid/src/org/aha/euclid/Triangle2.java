
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static org.aha.euclid.math.EuclidMath.len;
import static org.aha.euclid.math.Vectors.pointToString;

import java.io.Serializable;

import org.aha.euclid.math.EuclidMath;

/**
 * <p>
 *   Triangle in 2D.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Triangle2 implements Cloneable, Serializable 
{
  private static final long serialVersionUID=4784985458992442575L;

  protected double m_x0;
  
  protected double m_y0;
  
  protected double m_x1;
  
  protected double m_y1;
  
  protected double m_x2;
  
  protected double m_y2;
  
  /**
   * <p>
   *   Creates triangle {@code (0,0, (1,0), (1,1)}.
   * </p>
   */
  public Triangle2()
  {
    set(0, 0,
        1, 0,
        1, 1);
  }
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Triangle2(Triangle2 o)
  {
    m_x0=o.m_x0;
    m_x1=o.m_x1;
    m_x2=o.m_x2;
    m_y0=o.m_y0;
    m_y1=o.m_y1;
    m_y2=o.m_y2;
  }
  
  public final void set(double x0, double y0, double x1, double y1, double x2, 
    double y2)
  {
    m_x0=x0;
    m_y0=y0;
    
    m_x1=x1;
    m_y1=y1;
    
    m_x2=x2;
    m_y2=y2;
  }
  
  /**
   * <p>
   *   Sets TODO
   * </p>
   * @param p0
   * @param p1
   * @param p2
   */
  public final void set(double[] p0, double[] p1, double[] p2)
  {
    set(p0[0], p0[1], p1[0], p1[1], p2[0], p2[1]);
  }
  
  /**
   * <p>
   *   Gets x coordinate of triangle first point.
   * </p>
   * @return X coordinate.
   */
  public final double x0(){ return m_x0; }
  
  /**
   * <p>
   *   Gets y coordinate of triangle first point.
   * </p>
   * @return Y coordinate.
   */
  public final double y0(){ return m_y0; }
  
  /**
   * <p>
   *   Gets x coordinate of triangle second point.
   * </p>
   * @return X coordinate.
   */
  public final double x1(){ return m_x1; }
  
  /**
   * <p>
   *   Gets y coordinate of triangle second point.
   * </p>
   * @return Y coordinate.
   */
  public final double y1(){ return m_y1; }
  
  /**
   * <p>
   *   Gets x coordinate of triangle third point.
   * </p>
   * @return X coordinate.
   */
  public final double x2(){ return m_x2; }
  
  /**
   * <p>
   *   Gets y coordinate of triangle third point.
   * </p>
   * @return Y coordinate.
   */
  public final double y2(){ return m_y2; }
  
  /**
   * <p>
   *   Gets first point of triangle.
   * </p>
   * @return Point.
   */
  public final double[] getP0(){ return getP0(null); }
  
  /**
   * <p>
   *   Gets first point of triangle.
   * </p>
   * @param p Assigned to point. If {@code null} allocates.
   * @return Point.
   */
  public final double[] getP0(double[] p)
  {
    p=(p==null) ? new double[2] : p;
    p[0]=m_x0;
    p[1]=m_y0;
    return p;
  }
  
  /**
   * <p>
   *   Gets second point of triangle.
   * </p>
   * @return Point.
   */
  public final double[] getP1(){ return getP1(null); }
  
  /**
   * <p>
   *   Gets second point of triangle.
   * </p>
   * @param p Assigned to point. If {@code null} allocates.
   * @return Point.
   */
  public final double[] getP1(double[] p)
  {
    p=(p==null) ? new double[2] : p;
    p[0]=m_x1;
    p[1]=m_y1;
    return p;
  }
  
  /**
   * <p>
   *   Gets third point of triangle.
   * </p>
   * @return Point.
   */
  public final double[] getP2(){ return getP2(null); }
  
  /**
   * <p>
   *   Gets third point of triangle.
   * </p>
   * @param p Assigned to point. If {@code null} allocates.
   * @return Point.
   */
  public final double[] getP2(double[] p)
  {
    p=(p==null) ? new double[2] : p;
    p[0]=m_x2;
    p[1]=m_y2;
    return p;
  }
  
  /**
   * <p>
   *   Computes {@code this} triangle's area.
   * </p>
   * @return Area.
   */
  public final double area()
  { 
    return EuclidMath.area(m_x0, m_y0, m_x1, m_y1, m_x2, m_y2); 
  }
  
  /**
   * <p>
   *   Gets the length of the side from first point of triangle to its second.
   * </p>
   * @return Length.
   */
  public final double length0(){ return len(m_x0, m_y0, m_x1, m_y1); }
  
  /**
   * <p>
   *   Gets the length of the side from second point of triangle to its third.
   * </p>
   * @return Length.
   */
  public final double length1(){ return len(m_x1, m_y1, m_x2, m_y2); }
  
  /**
   * <p>
   *   Gets the length of the side from third point of triangle to its first.
   * </p>
   * @return Length.
   */
  public final double length2(){ return len(m_x2, m_y2, m_x0, m_y0); }
  
  /**
   * <p>
   *   Calculates the length of {@code this} triangle's perimeter.
   * </p>
   * @return Length.
   */
  public final double perimeter(){ return length0()+length1()+length2(); }
  
  /**
   * <p>
   *   Gets the average point of {@code this} triangle.
   * </p>
   * @return Average point.
   */
  public final double[] centroid(){ return centroid(null); }
  
  /**
   * <p>
   *   Gets the average point of {@code this} triangle.
   * </p>
   * @param c Assigned to the average point. If {@code null} allocates.
   * @return Average point.
   */
  public final double[] centroid(double[] c)
  {
    c=(c==null) ? new double[2] : c;
    c[0]=(m_x0+m_x1+m_x2)/3.0;
    c[1]=(m_y0+m_y1+m_y2)/3.0;
    return c;
  }
  
  /**
   * <p>
   *   Calculates the inscribed circle's radius.
   * </p>
   * @return Radius.
   */
  public final double inscribedRadius(){ return (area()/perimeter())*2.0; }
  
  /**
   * <p>
   *   Calculates the circumscribed circle's radius.
   * </p>
   * @return Radius.
   */
  public final double circumscribedRadius()
  {   
    // TODO
    /*
    double e0x=m_x2-m_x1;
    double e0y=m_y2-m_y1;
    double e0z=m_z2-m_z1;
    
    double e1x=m_x0-m_x2;
    double e1y=m_y0-m_y2;
    double e1z=m_z0-m_z2;
    
    double e2x=m_x1-m_x0;
    double e2y=m_y1-m_y0;
    double e2z=m_z1-m_z0;
    
    double d0=dot(-e1x, -e1y, -e1z, e2x, e2y, e2z);
    double d1=dot(-e2x, -e2y, -e2z, e0x, e0y, e0z);
    double d2=dot(-e0x, -e0y, -e0z, e1x, e1y, e1z);
    
    double c0=d1*d2;
    double c1=d2*d0;
    double c2=d0*d1;
    
    double c=c0+c1+c2;
    
    return sqrt((((d0+d1)*(d1+d2)*(d2+d0))/c))/2.0;
    */
    
    return 1.0; // PROTO TODO
  }
  
  /**   
   * <p>
   *   Calculates the ratio of the triangle: Radius of circumscribed circle and 
   *   inscribed circle.
   * </p>
   * @return {@link #circumscribedRadius()}/{@link #inscribedRadius()}.
   */
  public final double ratio(){ return circumscribedRadius()/inscribedRadius(); }
  
  /**
   * <p>
   *   Calculates the inscribed circle.
   * </p>
   * @param s Assigned to circle's center and radius. If {@code null} allocates.
   * @return {@link Circle2}.
   */
  public final Circle2 inscribed(){ return inscribed(null); }
  
  /**
   * <p>
   *   Calculates the inscribed circle.
   * </p>
   * @param s Assigned to circle's center and radius. If {@code null} allocates.
   * @return {@link Circle2}.
   */
  public final Circle2 inscribed(Circle2 s)
  {
    s=(s==null) ? new Circle2() : s;

    // TODO!!!
    /*
    double l12=length0();
    double l23=length1();
    double l31=length2();
    double p=l12+l23+l31;
    double x1=m_x0*l23;
    double y1=m_y0*l23;
    double z1=m_z0*l23;
    double x2=m_x1*l31;
    double y2=m_y1*l31;
    double z2=m_z1*l31;
    double x3=m_x2*l12;
    double y3=m_y2*l12;
    double z3=m_z2*l12;
    
    double cx=(x1+x2+x3)/p;
    double cy=(y1+y2+y3)/p;
    double cz=(z1+z2+z3)/p;
    double r=(area()/p)*2.0;
    
    s.set(cx, cy, cz, r);
    */
    
    return s;
  }
  
  /**
   * <p>
   *   Calculates the circumscribed circle.
   * </p>
   * @param s Assigned to circle's center and radius. If {@code null} allocates.
   * @return {@link Circle2}.
   */
  public final Circle2 circumscribed(){ return circumscribed(null); }
  
  /**
   * <p>
   *   Calculates the circumscribed circle.
   * </p>
   * @param s Assigned to circle's center and radius. If {@code null} allocates.
   * @return {@link Circle2}.
   */
  public final Circle2 circumscribed(Circle2 c)
  {
    c=(c==null) ? new Circle2() : c;
    
    // TODO!!!
    /*
    double e0x=m_x2-m_x1;
    double e0y=m_y2-m_y1;
    double e0z=m_z2-m_z1;
    
    double e1x=m_x0-m_x2;
    double e1y=m_y0-m_y2;
    double e1z=m_z0-m_z2;
    
    double e2x=m_x1-m_x0;
    double e2y=m_y1-m_y0;
    double e2z=m_z1-m_z0;
    
    double d0=dot(-e1x, -e1y, -e1z, e2x, e2y, e2z);
    double d1=dot(-e2x, -e2y, -e2z, e0x, e0y, e0z);
    double d2=dot(-e0x, -e0y, -e0z, e1x, e1y, e1z);
    
    double c0=d1*d2;
    double c1=d2*d0;
    double c2=d0*d1;
    
    double c=c0+c1+c2;
                        
    double r=sqrt((((d0+d1)*(d1+d2)*(d2+d0))/c))/2.0;
    
    double ct2=2.0*c;
    
    double c1pc2=c1+c2;
    double c2pc0=c2+c0;
    double c0pc1=c0+c1;
    
    double cx=(c1pc2*m_x0+c2pc0*m_x1+c0pc1*m_x2)/ct2;
    double cy=(c1pc2*m_y0+c2pc0*m_y1+c0pc1*m_y2)/ct2;
    double cz=(c1pc2*m_z0+c2pc0*m_z1+c0pc1*m_z2)/ct2;
    
    retVal.set(cx, cy, cz, r);
        
    return retVal;
    */
    
    return c;
  }
  
  /**
   * <p>
   *   Computes point's barycentric coordinates related to {@code this} 
   *   triangle.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return Point's barycentric coordinates.          
   */
  public final double[] barycentric(double x, double y)
  {
    return barycentric(x, y, null);
  }
  
  /**
   * <p>
   *   Computes point's barycentric coordinates related to {@code this} 
   *   triangle.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param bc Assigned to the barycentric coordinates of {@code (x,y,z)}. If 
   *           {@code null} allocates.
   * @return Point's barycentric coordinates.          
   */
  public final double[] barycentric(double x, double y, double[] bc)
  {
    double u1;
    double u2;
    double u3;
    double u4;
    
    double v1;
    double v2;
    double v3;
    double v4;
      
    u1=m_x0-m_x2;
    u2=m_x1-m_x2;
    u3=x-m_x0;
    u4=x-m_x2;
      
    v1=m_y0-m_y2;
    v2=m_y1-m_y2;
    v3=y-m_y0;
    v4=y-m_y2;
    
    double denom=v1*u2-v2*u1;
    if (denom==0.0)
    {
      throw new IllegalStateException("denom==0.0");
    }
    
    bc=(bc==null) ? new double[3] : bc;
    
    double oneOverDenom=1.0/denom;
    bc[0]=(v4*u2-v2*u4)*oneOverDenom;
    bc[1]=(v1*u3-v3*u1)*oneOverDenom;
    bc[2]=1.0-bc[0]-bc[1];
    
    return bc;
  }
  
  /**
   * <p>
   *   Tells if point in {@code this} triangle's plane is inside triangle or 
   *   not.
   * </p>
   * <p>
   *   If point is not in plane then this returns {@code true} if the closest
   *   point in plane to given point is inside.
   * </p>
   * <p>
   *   Use this in combination with
   *   {@link #axbyczd(double, double, double)} to learn if points are in plane
   *   and inside triangle.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return {@code true} if {@code (x,y,y)} inside else {@code false}.
   */
  public boolean inside(double x, double y)
  {
    //return EuclidMath.inside(m_x0, m_y0, m_x1, m_y1, m_x2, m_y2, 
    //  x, y, z);
    
    return false; // TODO PROTO
  }
  
  /**
   * <p>
   *   Tells if point in {@code this} triangle's plane is inside triangle or 
   *   not.
   * </p>
   * <p>
   *   If point is not in plane then this returns {@code true} if the closest
   *   point in plane to given point is inside.
   * </p>
   * <p>
   *   Use this in combination with
   *   {@link #axbyczd(double[])} to learn if points are in plane and inside 
   *   triangle.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return {@code true} if {@code (x,y,y)} inside else {@code false}.
   */
  public boolean inside(double[] p)
  {
    //return EuclidMath.inside(m_x0, m_y0, m_x1, m_y1, m_x2, m_y2, 
    //  p[0], p[1], p[2]);
    
    return false; // TODO PROTO
  }
  
  // Object overrides.
  
  @Override
  public String toString() 
  {
    StringBuilder sb=new StringBuilder();
    sb.append(pointToString(m_x0, m_y0)).append(",")
      .append(pointToString(m_x1, m_y1)).append(",")
      .append(pointToString(m_x2, m_y2));
    return sb.toString();
  }
  
  @Override
  public int hashCode()
  {
    long bits=1L;
    bits=31L*bits+((m_x0==0) ? 0 : doubleToLongBits(m_x0));
    bits=31L*bits+((m_y0==0) ? 0 : doubleToLongBits(m_y0));
    bits=31L*bits+((m_x1==0) ? 0 : doubleToLongBits(m_x1));
    bits=31L*bits+((m_y1==0) ? 0 : doubleToLongBits(m_y1));
    bits=31L*bits+((m_x2==0) ? 0 : doubleToLongBits(m_x2));
    bits=31L*bits+((m_y2==0) ? 0 : doubleToLongBits(m_y2));
    return (int)(bits^(bits>>32));    
  }
    
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Triangle2[] THE_EMPTY_ARRAY=new Triangle2[0];
  
}
