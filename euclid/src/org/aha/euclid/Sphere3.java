
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.PI;

import static org.aha.euclid.math.Vectors.pointToString;

import java.io.Serializable;

import org.aha.euclid.math.Comparisons;

/**
 * <p>
 *   Sphere in 3D.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Sphere3 implements Cloneable, Serializable
{
  private static final long serialVersionUID=-8180473145923061416L;

  protected double m_x=0.0;
  
  protected double m_y=0.0;
  
  protected double m_z=0.0;
  
  protected double m_r=1.0;
  
  /**
   * <p>
   *   Creates the sphere with center {@code (0,0,0)} and radius {@code 1.0}.
   * </p>
   */
  public Sphere3(){}
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Sphere3(Sphere3 o){ set(o); }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param x Center's x coordinate.
   * @param y Center's y coordinate.
   * @param z Center's z coordinate.
   * @param r Radius.
   */
  public Sphere3(double x, double y, double z, double r){ set(x, y, z, r); }
  
  /**
  * <p>
  *   Assigns to other.
  * </p>
  * @param o Other.
  */
  public final void set(Sphere3 o)
  {
    m_x=o.m_x;
    m_y=o.m_y;
    m_z=o.m_z;
    m_r=o.m_r;
  }
  
  /**
   * <p>
   *   Assigns to new sphere.
   * </p>
   * @param x Center's x coordinate.
   * @param y Center's y coordinate.
   * @param z Center's z coordinate.
   * @param r Radius.
   */
  public final void set(double x, double y, double z, double r)
  {
   m_x=x;
   m_y=y;
   m_z=z;
   m_r=r;
  }
  
  /**
   * <p>
   *   Gets center's x coordinate.
   * </p>
   * @return Coordinate.
   */
  public final double x(){ return m_x; }
  
  /**
   * <p>
   *   Gets center's y coordinate.
   * </p>
   * @return Coordinate.
   */
  public final double y(){ return m_y; }
  
  /**
   * <p>
   *   Gets center's z coordinate.
   * </p>
   * @return Coordinate.
   */
  public final double z(){ return m_z; }
  
  /**
   * <p>
   *   Gets center.
   * </p>
   * @return Center.
   */
  public final double[] getCenter(){ return getCenter(null); }
  
  /**
   * <p>
   *   Gets center.
   * </p>
   * @param c Assigned to center point. If {@code null} allocates.
   * @return Center.
   */
  public final double[] getCenter(double[] c)
  {
    c=(c==null) ? new double[3] : c;
    c[0]=m_x;
    c[1]=m_y;
    c[2]=m_z;
    return c;
  }
  
  /**
   * <p>
   *   Gets radius.
   * </p>
   * @return Radius.
   */
  public final double getRadius(){ return m_r; }
  
  /**
   * <p>
   *   Calculates the length of the circumference of the circle this represents.
   * </p>
   * @return Length.
   */
  public final double circumference(){ return Circle2.circumference(m_r); }
  
  /**
   * <p>
   *   Calculates the area of the circle this represents.
   * </p>
   * @return Area.
   */
  public final double cirlceArea(){ return Circle2.area(m_r); }
  
  /**
   * <p>
   *   Calculates the surface area of {@code this} sphere.
   * </p>
   * @return Area.
   */
  public final double area(){ return area(m_r); }
  
  /**
   * <p>
   *   Calculates the volume of {@code this} sphere.
   * </p>
   * @return Volume.
   */
  public final double volume(){ return volume(m_r); }
  
  // Cloneable and Object overrides.
  
  @Override
  public Object clone() 
  {
    try { return super.clone(); }
    catch (CloneNotSupportedException cnx){ throw new Error(); }
  }
  
  @Override
  public String toString() 
  {
    StringBuilder sb=new StringBuilder();
    sb.append("c=").append(pointToString(m_x, m_y, m_z)).append(",r=")
      .append(m_r);
    return sb.toString();
  }
  
  @Override
  public int hashCode()
  {     
    long bits=1L;
    bits=31L*bits+((m_x==0) ? 0 : doubleToLongBits(m_x));
    bits=31L*bits+((m_y==0) ? 0 : doubleToLongBits(m_y));    
    bits=31L*bits+((m_z==0) ? 0 : doubleToLongBits(m_z));
    bits=31L*bits+((m_r==0) ? 0 : doubleToLongBits(m_r));    
    return (int)(bits^(bits>>32));     
  }
  
  @Override
  public boolean equals(Object o)
  {  
    if (this==o) return true;
    if (o instanceof Sphere3)
    {
      Sphere3 s=(Sphere3)o;
      return Comparisons.equals(m_x, s.m_x) && 
             Comparisons.equals(m_y, s.m_y) &&
             Comparisons.equals(m_z, s.m_z) && 
             Comparisons.equals(m_r, s.m_r);
    }
    return false;    
  }
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Sphere3[] THE_EMPTY_ARRAY=new Sphere3[0];
  
  /**
   * <p>
   *   Calculates the volume of a 3D sphere.
   * </p>
   * @param r Sphere's radius.
   * @return {@code (4.0*PI*r*r*r)/3.0}.
   */
  public static double volume(double r){ return (4.0*PI*r*r*r)/3.0; }
  
  /**
   * <p>
   *   Calculates the surface area of a 3D sphere.
   * </p>
   * @param r Sphere's radius.
   * @return {@code 4.0*PI*r*r}.
   */
  public static double area(double r){ return 4.0*PI*r*r; }
  
}
