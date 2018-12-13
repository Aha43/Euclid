
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.abs;

import static org.aha.euclid.math.EuclidMath.dot;
import static org.aha.euclid.math.EuclidMath.len;
import static org.aha.euclid.math.Comparisons.zero;
import static org.aha.euclid.math.Vectors.pointToString;

import java.io.Serializable;

import org.aha.euclid.math.Comparisons;

/**
 * <p>
 *   Represents a 2D line.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Line2 implements Cloneable, Serializable
{
  private static final long serialVersionUID=-4999361296259060633L;

  protected double m_x0=0.0;
  
  protected double m_y0=0.0;
  
  protected double m_x1=0.0;
  
  protected double m_y1=1.0;
  
  /**
   * <p>
   *   Creates line defined by {@code (0, 0}} and {@code (0, 1)}. 
   * </p>
   */
  public Line2(){}
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Line2(Line2 o){ set(o); }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param x0 X coordinate of first point defining line.
   * @param y0 Y coordinate of first point defining line.
   * @param x1 X coordinate of second point defining line.
   * @param y1 Y coordinate of second point defining line.
   * @throws IllegalArgumentException If {@code (x0, y0)} same point as 
   *         {@code (x1, y1}}.
   */
  public Line2(double x0, double y0, double x1, double y1)
  { 
    set(x0, y0, x1, y1);
  }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param p0 First point defining line.
   * @param p1 Second point defining line.
   * @throws IllegalArgumentException If {@code p0} same point as {@code p1}.
   */
  public Line2(double[] p0, double[] p1){ set(p0, p1); }
  
  /**
   * <p>
   *   Gets x coordinate of first point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double x0(){ return m_x0; }
  
  /**
   * <p>
   *   Gets y coordinate of first point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double y0(){ return m_y0; }
  
  /**
   * <p>
   *   Gets x coordinate of second point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double x1(){ return m_x1; }
  
  /**
   * <p>
   *   Gets y coordinate of second point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double y1(){ return m_y1; }
  
  /**
   * <p>
   *   Assigns to other.
   * </p>
   * @param o Other.
   */
  public final void set(Line2 o)
  {  
    m_x0=o.m_x0;
    m_y0=o.m_y0;
    m_x1=o.m_x1;
    m_y1=o.m_y1;
  }
  
  /**
   * <p>
   *   Sets points that defines this.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @throws IllegalArgumentException If {@code (x0, y0)} same point as 
   *         {@code (x1, y1}}.       
   */
  public final void set(double x0, double y0, double x1, double y1)
  {
    if (Comparisons.same(x0, y0, x1, y1))
    {
      throw new IllegalArgumentException(pointToString(x0, y0)+
        " same point as "+pointToString(x1, y1));
    }
    
    m_x0=x0;
    m_y0=y0;
    m_x1=x1;
    m_y1=y1;
  }
  
  /**
   * <p>
   *   Sets points that defines {@code this}.
   * </p>
   * @param p0 First point.
   * @param p1 Second point.
   * @throws IllegalArgumentException If {@code p0} same point as {@code p1}. 
   */
  public final void set(double[] p0, double[] p1)
  { 
    if (Comparisons.same(p0, p1))
    {
      throw new IllegalArgumentException(pointToString(p0)+" same point as "+
        pointToString(p1));  
    }
    
    m_x0=p0[0];
    m_y0=p0[1];
    m_x1=p1[0];
    m_y1=p1[1];
  }
  
  /**
   * <p>
   *   Gets first point defining {@code this}.
   * </p>
   * @return Point.
   */
  public final double[] getP0(){ return new double[]{ m_x0, m_y0 }; }
  
  /**
   * <p>
   *   Gets first point defining {@code this}.
   * </p>
   * @return Point.
   */
  public final double[] getP1(){ return new double[]{ m_x1, m_y1 }; }
  
  /**
   * <p>
   *   Gets second point defining {@code this}.
   * </p>
   * @param p Assigned to point, if {@code null} allocates.
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
   *   Gets second point defining {@code this}.
   * </p>
   * @param p Assigned to point, if {@code null} allocates.
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
   *   Gets point on line by linear interpolation.
   * </p>
   * @param t Parameter to get point at.
   * @return Point.
   */
  public final double[] getPoint(double t){ return getPoint(t, null); }
  
  /**
   * <p>
   *   Gets point on line by linear interpolation.
   * </p>
   * @param t Parameter to get point at.
   * @param p Assigned to point, if {@code null} allocated.
   * @return Point.
   */
  public final double[] getPoint(double t, double[] p)
  {
    p=(p==null) ? new double[2] : p;
    
    p[0]=getPointX(t);
    p[1]=getPointY(t);
    
    return p;
  }
  
  /**
   * <p>
   *   Gets x coordinate for point on line by linear interpolation.
   * </p>
   * @param t Parameter to get point at.
   * @return X coordinate.
   */
  public final double getPointX(double t){ return m_x0+t*(m_x1-m_x0); }
  
  /**
   * <p>
   *   Gets y coordinate for point on line by linear interpolation.
   * </p>
   * @param t Parameter to get point at.
   * @return Y coordinate.
   */
  public final double getPointY(double t){ return m_y0+t*(m_y1-m_y0); }
  
  /**
   * <p>
   *   Computes length of line segment that defines {@code this}.
   * </p>
   * @return Length.
   */
  public final double length(){ return len(m_x0, m_y0, m_x1, m_y1); }
  
  /**
   * <p>
   *   Adjust 
   *   {@link #getP1()) second point} that defines line so length of
   *   segment becomes {@code 1.0}.
   * </p>
   */
  public final void normalize()
  {
    double s=1.0/length();
    double x=m_x0;
    double y=m_y0;    
    scale(s);
    translate(x-m_x0, y-m_y0);
  }
  
  /**
   * <p>
   *   Makes 
   *   {@link #getP0() p0} {@code p1} and
   *   {@link #getP1() p1} {@code p0}.
   * </p>
   */
  public final void flip()
  {
    double tmp_x=m_x0;
    double tmp_y=m_y0;
    m_x0=m_x1;
    m_y0=m_y1;
    m_x1=tmp_x;
    m_y1=tmp_y;
  }
  
  /**
   * <p>
   *   Gets vector {@code [x1()-x0(), y1()-y0()]}.
   * </p>
   * @return {@code [x1()-x0(), y1()-y0()]}.
   */
  public final double[] getVector()
  { 
    return new double[]{ m_x1-m_x0, m_y1-m_y0 }; 
  }
  
  /**
   * <p>
   *   Gets vector {@code [x1()-x0(), y1()-y0()]}.
   * </p>
   * @param v Assigned to vector. If {@code null} allocates.
   * @return {@code [x1()-x0(), y1()-y0()]}.
   */
  public final double[] getVector(double[] v)
  {
    v=(v==null) ? new double[2] : v;
    v[0]=m_x1-m_x0;
    v[1]=m_y1-m_y0;
    return v;
  }
  
  /**
   * <p>
   *   Gets the distance to line from given point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return Distance.
   */
  public final double distance(double x, double y)
  { 
    return abs(axbyc(x, y)/len(m_x0, m_y0, m_x1, m_y1)); 
  }
  
  /**
   * <p>
   *   Gets the distance to line from given point.
   * </p>
   * @param p Point.
   * @return Distance.
   */
  public final double distance(double[] p)
  { 
    return abs(axbyc(p)/len(m_x0, m_y0, m_x1, m_y1)); 
  }
  
  /**
   * <p>
   *   Computes the parameter the closest point on line to the given point.
   * </p>
   * @param x X coordinate to point to find closest point on line to.
   * @param y Y coordinate to point to find closest point on line to.
   * @return Parameter.
   */
  public final double closest(double x, double y)
  {
    double wx=x-m_x0;
    double wy=y-m_y0;    
    
    double vx=m_x1-m_x0;
    double vy=m_y1-m_y0;
    
    return dot(wx, wy, vx, vy)/dot(vx, vy);
  }
  
  /**
   * <p>
   *   Computes the parameter the closest point on line to the given point.
   * </p>
   * @param x X coordinate to point to find closest point on line to.
   * @param y Y coordinate to point to find closest point on line to.
   * @return Parameter.
   */
  public final double closest(double[] p){ return closest(p[0], p[1]); }
  
  /**
   * <p>
   *   Computes the the closest point on line to the given point.
   * </p>
   * @param x X coordinate to point to find closest point on line to.
   * @param y Y coordinate to point to find closest point on line to.
   * @param c Assigned to computed point. If {@code null} allocates.
   * @return Point on line closest to {@code (x,y)}.
   */
  public final double[] closest(double x, double y, double[] c)
  {
    c=(c==null) ? new double[2] : c;
    double t=closest(x, y);
    c[0]=getPointX(t);
    c[1]=getPointY(t);
    return c;
  }
  
  /**
   * <p>
   *   Computes the the closest point on line to the given point.
   * </p>
   * @param p Point to find closest point on line to.
   * @return Point on line closest to {@code (x,y)}.
   * @param c Assigned to computed point. If {@code null} allocates. 
   */
  public final double[] closest(double[] p, double[] c)
  {
    c=(c==null) ? new double[2] : c;
    double t=closest(p);
    c[0]=getPointX(t);
    c[1]=getPointY(t);
    return c;
  }
  
  /**
   * <p>
   *   Gets the distance to the line segment defining {@code this} line.
   * </p>
   * @param x X coordinate of point to find distance from.
   * @param y Y coordinate of point to find distance from.
   * @return Distance.
   */
  public final double segmentDistance(double x, double y)
  {
    double t=closest(x, y);
    if (t<0.0) return len(x, y, m_x0, m_y0);
    if (t>1.0) return len(x, y, m_x1, m_y1);
    return len(x, y, getPointX(t), getPointY(t));
  }
  
  /**
   * <p>
   *   Gets the distance to the line segment defining {@code this} line.
   * </p>
   * @param p Point to find distance from.   
   * @return Distance.
   */
  public final double segmentDistance(double[] p)
  {
    double t=closest(p);
    if (t<0.0) return len(p[0], p[1], m_x0, m_y0);
    if (t>1.0) return len(p[0], p[1], m_x1, m_y1);
    return len(p[0], p[1], getPointX(t), getPointY(t));
  }
  
  /**
   * <p>
   *   Translates line segment (and so line) that defines {@code this}.
   * </p>
   * @param x Translation in x dimension.
   * @param y Translation in y dimension.
   */
  public final void translate(double x, double y)
  { 
    m_x0+=x;
    m_y0+=y;
    m_x1+=x;
    m_y1+=y;     
  }
  
  /**
   * <p>
   *   Scales uniformly line segment defining {@code this}.  
   * </p>
   * @param s Scale factor.
   */
  public final void scale(double s){ scale(s, s); }
     
  /**
   * <p>
   *   Scales line segment defining {@code this}.  
   * </p>
   * @param sx Scale factor in x dimension.
   * @param sy Scale factor in y dimension. 
   */
  public final void scale(double sx, double sy)
  { 
    m_x0*=sx;
    m_y0*=sy;
    m_x1*=sx;
    m_y1*=sy;     
  }
  
  /**
   * <p>
   *   Tells if {@code this} is consider to define the same line segment as
   *   {@code o}.
   * </p>
   * @param o Other.
   * @param d Delta used when comparing if end points are the same.
   * @return {@code true} if same segments, {@code false} if not.
   * {@link #sameSegment(Line2)}
   */
  public final boolean sameSegment(Line2 o, double d)
  {
    if (o==this) return true;
    
    return Comparisons.same(m_x0, m_y0, o.m_x0, o.m_y0, d) && 
           Comparisons.same(m_x1, m_y1, o.m_x1, o.m_y1, d);
  }
  
  /**
   * <p>
   *   Tells if {@code this} is consider to define the same line segment as
   *   {@code o}.
   * </p>
   * <p>
   *   Uses
   *   {@link Comparisons#getDelta()} when comparing coordinates.
   * </p>
   * @param o Other.
   * @return {@code true} if same segments, {@code false} if not.
   * @see #sameSegment(Line2, double)
   */
  public final boolean sameSegment(Line2 o)
  {
    if (o==this) return true;
    
    return Comparisons.same(m_x0, m_y0, o.m_x0, o.m_y0) && 
           Comparisons.same(m_x1, m_y1, o.m_x1, o.m_y1);
  }
  
  /**
   * <p>
   *   Tells if {@code o} defines same line as {@code this}: Lines are parallel
   *   and not disjoint.
   * </p>
   * @param o Other.
   * @param d Delta used to decide distance between the two lines are 
   *          {@code 0.0}.
   * @return {@code true} is same line, {@code false} if is not.
   */
  public final boolean same(Line2 o, double d)
  {
    if (o==this) return true;
    
    double a=o.a();
    double b=o.b();
    double c=o.c();
    return zero(a*m_x0+b*m_y0+c, d) && zero(a*m_x1+b*m_y1+c, d); 
  }
  
  /**
   * <p>
   *   Tells if {@code o} defines same line as {@code this}: Lines are parallel
   *   and not disjoint.
   * </p>
   * <p>
   *   Uses
   *   {@link Comparisons#getDelta()} when deciding if distances between the two lines
   *   are {@code 0.0}.
   * </p> 
   * @param o Other.
   * @return {@code true} is same line, {@code false} if is not.
   */
  public boolean same(Line2 o)
  {
    if (o==this) return true;
    
    double a=o.a();
    double b=o.b();
    double c=o.c();
    return zero(a*m_x0+b*m_y0+c) && zero(a*m_x1+b*m_y1+c); 
  }
  
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
    
    sb.append(pointToString(m_x0, m_y0)).append(",")
      .append(pointToString(m_x1, m_y1));
    
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
    return (int)(bits^(bits>>32));     
  }
  
  @Override
  public boolean equals(Object o)
  {  
    if (this==o) return true;
    if (o instanceof Line2)
    {
      Line2 l=(Line2)o;
      return Comparisons.equals(m_x0, l.m_x0) && 
             Comparisons.equals(m_y0, l.m_y0) &&
             Comparisons.equals(m_x1, l.m_x1) && 
             Comparisons.equals(m_y1, l.m_y1);
    }
    return false;    
  }
  
  //////////////////////////////////////////////////////////////////////////////
  // Hyperplane properties.                                                   //
  //////////////////////////////////////////////////////////////////////////////
  
  /**
   * <p>
   *   Computes the <i>a</i> coefficient of the line's implicit equation
   *   <i>ax+by+c=0</i>.
   * </p>
   * @return {@code y0()-y1()}.
   */
  public final double a(){ return m_y0-m_y1; }
  
  /**
   * <p>
   *   Computes the <i>a</i> coefficient of the line's implicit equation
   *   <i>ax+by+c=0</i>.
   * </p>
   * @return {@code x1()-x0()}.
   */
  public final double b(){ return m_x1-m_x0; }
  
  /**
   * <p>
   *   Computes the <i>a</i> coefficient of the line's implicit equation
   *   <i>ax+by+c=0</i>.
   * </p>
   * @return {@code x0()*y1()-x1()*y0()}.
   */
  public final double c(){ return m_x0*m_y1-m_x1*m_y0; }
  
  /**
   * <p>
   *   Evaluates the left side of the line's implicit equation <i>ax+by+c</i> 
   *   at a given point. 
   * </p>
   * <p>
   *   If the line is 
   *   {@link #normalize() normalized} this will be the signed distance to the 
   *   point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return Result, {@code 0} if point on the line.
   */
  public final double axbyc(double x, double y){ return a()*x+b()*y+c(); }
  
  /**
   * <p>
   *   Evaluates the left side of the line's implicit equation <i>ax+by+c</i> 
   *   at a given point. 
   * </p>
   * <p>
   *   If the line is 
   *   {@link #normalize() normalized} this will be the signed distance to the 
   *   point.
   * </p>
   * @param p Point.
   * @return Result, {@code 0} if point on the line.
   */
  public final double axbyc(double[] p){ return axbyc(p[0], p[1]); }
  
  /**
   * <p>
   *   Gets the signed distance to line from given point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @return Signed distance.
   */
  public final double sdistance(double x, double y)
  { 
    return axbyc(x, y)/len(m_x0, m_y0, m_x1, m_y1); 
  }
  
  /**
   * <p>
   *   Gets the signed distance to line from given point.
   * </p>
   * @param p Point.   
   * @return Signed distance.
   */
  public final double sdistance(double[] p)
  { 
    return axbyc(p)/len(m_x0, m_y0, m_x1, m_y1); 
  }
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Line2[] THE_EMPTY_ARRAY=new Line2[0];
  
}
