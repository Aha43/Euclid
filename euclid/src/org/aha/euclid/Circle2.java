
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
 *   Circle in 2D.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Circle2 implements Cloneable, Serializable
{
  private static final long serialVersionUID=7167212696176721812L;

  protected double m_x=0.0;
  
  protected double m_y=0.0;
  
  protected double m_r=1.0;
  
  /**
   * <p>
   *   Creates the circle with center {@code (0,0)} and radius {@code 1.0}.
   * </p>
   */
  public Circle2(){}
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Circle2(Circle2 o){ set(o); }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param x Center's x coordinate.
   * @param y Center's y coordinate.
   * @param r Radius.
   */
  public Circle2(double x, double y, double r){ set(x, y, r); }
  
  /**
  * <p>
  *   Assigns to other.
  * </p>
  * @param o Other.
  */
  public final void set(Circle2 o){ m_x=o.m_x; m_y=o.m_y; m_r=o.m_r; }
  
  /**
   * <p>
   *   Assigns to new circle.
   * </p>
   * @param x Center's x coordinate.
   * @param y Center's y coordinate.
   * @param r Radius.
   */
  public final void set(double x, double y, double r){ m_x=x; m_y=y; m_r=r; }
  
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
    c=(c==null) ? new double[2] : c;
    c[0]=m_x;
    c[1]=m_y;
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
   *   Calculates the length of the circumference.
   * </p>
   * @return Length.
   */
  public final double circumference(){ return circumference(m_r); }
  
  /**
   * <p>
   *   Calculates the area.
   * </p>
   * @return Area.
   */
  public final double area(){ return area(m_r); }
  
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
    sb.append("c=").append(pointToString(m_x, m_y)).append(",r=").append(m_r);
    return sb.toString();
  }
  
  @Override
  public int hashCode()
  {     
    long bits=1L;
    bits=31L*bits+((m_x==0) ? 0 : doubleToLongBits(m_x));
    bits=31L*bits+((m_y==0) ? 0 : doubleToLongBits(m_y));    
    bits=31L*bits+((m_r==0) ? 0 : doubleToLongBits(m_r));    
    return (int)(bits^(bits>>32));     
  }
  
  @Override
  public boolean equals(Object o)
  {  
    if (this==o) return true;
    if (o instanceof Circle2)
    {
      Circle2 s=(Circle2)o;
      return Comparisons.equals(m_x, s.m_x) && 
             Comparisons.equals(m_y, s.m_y) &&
             Comparisons.equals(m_r, s.m_r);
    }
    return false;    
  }
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Circle2[] THE_EMPTY_ARRAY=new Circle2[0];
  
  /**
   * <p>
   *   Calculates the circumference of a circle.
   * </p>
   * @param r Circle's radius.
   * @return {@code 2.0*PI*r}.
   */
  public static double circumference(double r){ return 2.0*PI*r; }
  
  /**
   * <p>
   *   Calculates the area of a circle.
   * </p>
   * @param r Circle's area.
   * @return {@code PI*r*r}.
   */
  public static double area(double r){ return PI*r*r; }
  
}
