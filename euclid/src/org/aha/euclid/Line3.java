
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.sqrt;

import static org.aha.euclid.math.EuclidMath.cross0;
import static org.aha.euclid.math.EuclidMath.cross1;
import static org.aha.euclid.math.EuclidMath.cross2;
import static org.aha.euclid.math.EuclidMath.dot;
import static org.aha.euclid.math.EuclidMath.len;
import static org.aha.euclid.math.Comparisons.same;
import static org.aha.euclid.math.Vectors.pointToString;

import java.io.Serializable;

import org.aha.euclid.math.Comparisons;

/**
 * <p>
 *   Represents a line in 3D
 * </p>
 * @author Arne Halvorsen (AH)
 */
public class Line3 implements Cloneable, Serializable
{
  private static final long serialVersionUID=1345352512846466139L;

  private double m_x0=0.0;
  
  private double m_y0=0.0;
  
  private double m_z0=0.0;
  
  private double m_x1=0.0;
  
  private double m_y1=0.0;
  
  private double m_z1=0.0;
  
  /**
   * <p>
   *   Creates line defined by {@code (0, 0, 0}} and {@code (0, 1, 0)}. 
   * </p>
   */
  public Line3(){}
  
  /**
   * <p>
   *   Copy constructor.
   * </p>
   * @param o Object to copy.
   */
  public Line3(Line3 o){ set(o); }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param x0 X coordinate of first point defining line.
   * @param y0 Y coordinate of first point defining line.
   * @param z0 Z coordinate of first point defining line. 
   * @param x1 X coordinate of second point defining line.
   * @param y1 Y coordinate of second point defining line.
   * @param z0 Z coordinate of second point defining line. 
   * @throws IllegalArgumentException If {@code (x0, y0)} same point as 
   *         {@code (x1, y1}}.
   */
  public Line3(double x0, double y0, double z0, double x1, double y1, double z1)
  { 
    set(x0, y0, z0, x1, y1, z1);
  }
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param p0 First point defining line.
   * @param p1 Second point defining line.
   * @throws IllegalArgumentException If {@code p0} same point as {@code p1}.
   */
  public Line3(double[] p0, double[] p1){ set(p0, p1); }
  
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
   *   Gets z coordinate of first point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double z0(){ return m_z0; }
  
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
   *   Gets z coordinate of second point defining {@code this}.
   * </p>
   * @return Coordinate. 
   */
  public final double z1(){ return m_z1; }
  
  /**
   * <p>
   *   Assigns to other.
   * </p>
   * @param o Other.
   */
  public final void set(Line3 o)
  {  
    m_x0=o.m_x0;
    m_y0=o.m_y0;
    m_z0=o.m_z0;
    m_x1=o.m_x1;
    m_y1=o.m_y1;
    m_z1=o.m_z1;
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
  public final void set(double x0, double y0, double z0, double x1, double y1,
    double z1)
  {
    if (same(x0, y0, z0, x1, y1, z1))
    {
      throw new IllegalArgumentException(pointToString(x0, y0, z0)+
        " same point as "+pointToString(x1, y1, z1));
    }
    
    m_x0=x0;
    m_y0=y0;
    m_z0=z0;
    m_x1=x1;
    m_y1=y1;
    m_z1=z1;
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
    if (same(p0, p1))
    {
      throw new IllegalArgumentException(pointToString(p0)+" same point as "+
        pointToString(p1));  
    }
    
    m_x0=p0[0];
    m_y0=p0[1];
    m_z0=p0[2];
    m_x1=p1[0];
    m_y1=p1[1];
    m_z1=p1[2];
  }
  
  /**
   * <p>
   *   Gets first point defining {@code this}.
   * </p>
   * @return Point.
   */
  public final double[] getP0(){ return new double[]{ m_x0, m_y0, m_z0 }; }
  
  /**
   * <p>
   *   Gets first point defining {@code this}.
   * </p>
   * @return Point.
   */
  public final double[] getP1(){ return new double[]{ m_x1, m_y1, m_z1 }; }
  
  /**
   * <p>
   *   Gets second point defining {@code this}.
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
   *   Gets second point defining {@code this}.
   * </p>
   * @param p Assigned to point, if {@code null} allocates.
   * @return Point.
   */
  public final double[] getP1(double[] p)
  {
    p=(p==null) ? new double[3] : p;
    p[0]=m_x1;
    p[1]=m_y1;
    p[2]=m_z1;
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
    p=(p==null) ? new double[3] : p;
    
    p[0]=getPointX(t);
    p[1]=getPointY(t);
    p[2]=getPointZ(t);
    
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
   *   Gets z coordinate for point on line by linear interpolation.
   * </p>
   * @param t Parameter to get point at.
   * @return Z coordinate.
   */
  public final double getPointZ(double t){ return m_z0+t*(m_z1-m_z0); }
  
  /**
   * <p>
   *   Computes length of line segment that defines {@code this}.
   * </p>
   * @return Length.
   */
  public final double length()
  { 
    return len(m_x0, m_y0, m_z0, m_x1, m_y1, m_z1); 
  }
  
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
    double z=m_z0;
    scale(s);
    translate(x-m_x0, y-m_y0, z-m_z0);
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
    double tmp_z=m_z0;
    m_x0=m_x1;
    m_y0=m_y1;
    m_z0=m_z1;
    m_x1=tmp_x;
    m_y1=tmp_y;
    m_z1=tmp_z;   
  }
  
  /**
   * <p>
   *   Gets vector {@code [x1()-x0(), y1()-y0()]}.
   * </p>
   * @return {@code [x1()-x0(), y1()-y0()]}.
   */
  public final double[] getVector()
  { 
    return new double[]{ m_x1-m_x0, m_y1-m_y0, m_z1-m_z0 }; 
  }
  
  /**
   * <p>
   *   Gets vector {@code [x1()-x0(), y1()-y0(), z1()-z0()]}.
   * </p>
   * @param v Assigned to vector. If {@code null} allocates.
   * @return {@code [x1()-x0(), y1()-y0(), z1()-z0()]}.
   */
  public final double[] getVector(double[] v)
  {
    v=(v==null) ? new double[3] : v;
    v[0]=m_x1-m_x0;
    v[1]=m_y1-m_y0;
    v[1]=m_z1-m_z0;
    return v;
  }
  
  /**
   * <p>
   *   Gets the distance to line from given point.
   * </p>
   * @param x Point's x coordinate.
   * @param y Point's y coordinate.
   * @param z Point's z coordinate.  
   * @return Distance.
   */
  public final double distance(double x, double y, double z)
  {
    double wx=x-m_x0;
    double wy=y-m_y0;
    double wz=z-m_z0;
    
    double vx=m_x1-m_x0;
    double vy=m_y1-m_y0;
    double vz=m_z1-m_z0;
    
    double xx=cross0(vx, vy, vz, wx, wy, wz);
    double xy=cross1(vx, vy, vz, wx, wy, wz);
    double xz=cross2(vx, vy, vz, wx, wy, wz);
    return sqrt(xx*xx+xy*xy+xz*xz)/sqrt(vx*vx+vy*vy+vz*vz);
  }
  
  /**
   * <p>
   *   Gets the distance to line from given point.
   * </p>
   * @param p Point.
   * @return Distance.
   */
  public final double distance(double[] p){ return distance(p[0], p[1], p[2]); }
  
  /**
   * <p>
   *   Computes the parameter the closest point on line to the given point.
   * </p>
   * @param x X coordinate to point to find closest point on line to.
   * @param y Y coordinate to point to find closest point on line to.
   * @param z Z coordinate to point to find closest point on line to. 
   * @return Parameter.
   */
  public final double closest(double x, double y, double z)
  {
    double wx=x-m_x0;
    double wy=y-m_y0;
    double wz=z-m_z0;
    
    double vx=m_x1-m_x0;
    double vy=m_y1-m_y0;
    double vz=m_z1-m_z0;
    
    return dot(wx, wy, wz, vx, vy, vz)/dot(vx, vy, vz);
  }
  
  /**
   * <p>
   *   Computes the parameter the closest point on line to the given point.
   * </p>
   * @param p Point.
   * @return Parameter.
   */
  public final double closest(double[] p){ return closest(p[0], p[1], p[2]); }
  
  /**
   * <p>
   *   Computes the the closest point on line to the given point.
   * </p>
   * @param x X coordinate to point to find closest point on line to.
   * @param y Y coordinate to point to find closest point on line to.
   * @param z Z coordinate to point to find closest point on line to. 
   * @param c Assigned to computed point. If {@code null} allocates.
   * @return Point on line closest to {@code (x,y,z)}.
   */
  public final double[] closest(double x, double y, double z, double[] c)
  {
    c=(c==null) ? new double[3] : c;
    double t=closest(x, y, z);
    c[0]=getPointX(t);
    c[1]=getPointY(t);
    c[2]=getPointZ(t);
    return c;
  }
  
  /**
   * <p>
   *   Computes the the closest point on line to the given point.
   * </p>
   * @param p Point to find closest point on line to. 
   * @param c Assigned to computed point. If {@code null} allocates.
   * @return Point on line closest to {@code (x,y,z)}.
   */
  public final double[] closest(double[] p, double[] c)
  {
    c=(c==null) ? new double[3] : c;
    double t=closest(p);
    c[0]=getPointX(t);
    c[1]=getPointY(t);
    c[2]=getPointZ(t);
    return c;
  }
  
  /**
   * <p>
   *   Gets the distance to the line segment defining {@code this} line.
   * </p>
   * @param x X coordinate of point to find distance from.
   * @param y Y coordinate of point to find distance from.
   * @param z Z coordinate of point to find distance from. 
   * @return Distance.
   */
  public final double segmentDistance(double x, double y, double z)
  {
    double t=closest(x, y, z);
    if (t<0.0) return len(x, y, z, m_x0, m_y0, m_z0);
    if (t>1.0) return len(x, y, z, m_x1, m_y1, m_z0);
    return len(x, y, z, getPointX(t), getPointY(t), getPointZ(t));
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
    if (t<0.0) return len(p[0], p[1], p[2], m_x0, m_y0, m_z0);
    if (t>1.0) return len(p[0], p[1], p[2], m_x1, m_y1, m_z1);
    return len(p[0], p[1], p[2], getPointX(t), getPointY(t), getPointZ(t));
  }
  
  /**
   * <p>
   *   Translates line segment (and so line) that defines {@code this}.
   * </p>
   * @param x Translation in x dimension.
   * @param y Translation in y dimension.
   * @param z Translation in z dimension.
   */
  public final void translate(double x, double y, double z)
  { 
    m_x0+=x;
    m_y0+=y;
    m_z0+=z;
    m_x1+=x;
    m_y1+=y;
    m_z1+=z;
  }
  
  /**
   * <p>
   *   Scales uniformly line segment defining {@code this}.  
   * </p>
   * @param s Scale factor.
   */
  public final void scale(double s){ scale(s, s, s); }
     
  /**
   * <p>
   *   Scales line segment defining {@code this}.  
   * </p>
   * @param sx Scale factor in x dimension.
   * @param sy Scale factor in y dimension.
   * @param sz Scale factor in z dimension. 
   */
  public final void scale(double sx, double sy, double sz)
  { 
    m_x0*=sx;
    m_y0*=sy;
    m_z0*=sz;
    m_x1*=sx;
    m_y1*=sy;
    m_z1*=sz;
  }
  
  // Object overrides.
  
  @Override
  public String toString() 
  {
    StringBuilder sb=new StringBuilder();
    
    sb.append("(").append(m_x0).append(",").append(m_y0).append(",")
      .append(m_z0).append("),(").append(m_x1).append(",").append(m_y1)
      .append(",").append(m_z1).append(")");
    
    return sb.toString();
  }
  
  @Override
  public boolean equals(Object o)
  {  
    if (this==o) return true;
    if (o instanceof Line3)
    {
      Line3 l=(Line3)o;
      return Comparisons.equals(m_x0, l.m_x0) && Comparisons.equals(m_y0, l.m_y0) &&
             Comparisons.equals(m_z0, l.m_z0) &&
             Comparisons.equals(m_x1, l.m_x1) && Comparisons.equals(m_y1, l.m_y1) &&
             Comparisons.equals(m_z1, l.m_z1);
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
    bits=31L*bits+((m_x1==0) ? 0 : doubleToLongBits(m_x1));
    bits=31L*bits+((m_y1==0) ? 0 : doubleToLongBits(m_y1));    
    bits=31L*bits+((m_z1==0) ? 0 : doubleToLongBits(m_z1));
    return (int)(bits^(bits>>32));     
  }

  @Override
  public Object clone() 
  {
    try { return super.clone(); }
    catch (CloneNotSupportedException cnx){ throw new Error(); }
  }
  
  /**
   * <p>
   *   The empty array shared.
   * </p>
   */
  public static final Line3[] THE_EMPTY_ARRAY=new Line3[0];
  
}
