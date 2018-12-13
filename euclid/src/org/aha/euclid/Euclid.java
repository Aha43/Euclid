
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

import static org.aha.euclid.math.EuclidMath.cross0;
import static org.aha.euclid.math.EuclidMath.cross1;
import static org.aha.euclid.math.EuclidMath.cross2;
import static org.aha.euclid.math.EuclidMath.dot;
import static org.aha.euclid.math.Comparisons.zero;

/**
 * <p>
 *   Object that calculates the closest point between lines, intersection point
 *   between line and plane and the intersection line between two planes.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public final class Euclid 
{
  private double m_pt;
  
  private double m_qt;
  
  private boolean m_parallel;
  
  private boolean m_disjoint;
  
  /**
   * <p>
   *   Creates not initialized relation, use
   *   {@link #lineLine(Line2, Line2)},
   *   {@link #lineLine(Line3, Line3)}
   *   {@link #planeLine(Plane3, Line3)}
   *   {@link #planePlane(Plane3, Plane3)} or
   *   {@link #planePlane(Plane3, Plane3, Line3)} to perform a calculation.
   * </p>
   */
  public Euclid(){}
  
  /**
   * <p>
   *   Creates by performing 
   *   {@link #lineLine(Line2, Line2)}.
   * </p>
   * <p>
   *   See
   *   {@link #lineLine(Line2, Line2)} for additional information.
   * </p>
   * @param p First line.
   * @param q Second line.
   * @see #lineLine(Line2, Line2) 
   * @see #parallel()
   * @see #getPt()
   * @see #getQt()   
   */
  public Euclid(Line2 p, Line2 q){ lineLine(p, q); }
  
  /**
   * <p>
   *   Creates by performing 
   *   {@link #lineLine(Line3, Line3)}.
   * </p>
   * <p>
   *   See
   *   {@link #lineLine(Line3, Line3)} for additional information.
   * </p>
   * @param p First line.
   * @param q Second line.
   * @see #lineLine(Line3, Line3) 
   * @see #parallel()
   * @see #getPt()
   * @see #getQt()   
   */
  public Euclid(Line3 p, Line3 q){ lineLine(p, q); }
  
  /**
   * <p>
   *   Creates by performing 
   *   {@link #planeLine(Plane3, Line3)}.
   * </p>
   * <p>
   *   See
   *   {@link #planeLine(Plane3, Line3)}.
   * </p>
   * @param p Plane.
   * @param l Line.
   * @return {@code true} if line and plane not parallel else {@code false}.
   * @see #planeLine(Plane3, Line3)  
   * @see #parallel()
   * @see #disjoint()
   * @see #getPt()   
   */
  public Euclid(Plane3 p, Line3 l){ planeLine(p, l); }
  
  /**
   * <p>
   *   Creates by performing
   *   {@link #planePlane(Plane3, Plane3)}.
   * </p>
   * <p>
   *   See
   *   {@link #planePlane(Plane3, Plane3)}.
   * </p>
   * @param p One plane.
   * @param q Other plane.
   * @see #planePlane(Plane3, Plane3)
   * @see #parallel()
   * @see #disjoint()
   */
  public Euclid(Plane3 p, Plane3 q){ planePlane(p, q); }
  
  /**
   * <p>
   *   Gets the parameter of intersection on first line in last intersection
   *   calculation involving a line.
   * </p>
   * <p>
   *   Not defined if no such calculation has been performed or parallel case.
   * </p>
   * @return Parameter.
   */
  public double getPt(){ return m_pt; }
  
  /**
   * <p>
   *   Gets the parameter of intersection on second line in last intersection
   *   calculation involving two lines.
   * </p>
   * <p>
   *   Not defined if no such calculation has been performed or parallel case.
   * </p>
   * @return Parameter.
   */
  public double getQt(){ return m_qt; }
  
  /**
   * <p>
   *   Provides additional information of performed calculations:
   * </p>
   * <ol>
   *   <li>
   *     Tells if lines passed to last call to
   *     {@link #lineLine(Line3, Line3)},
   *     {@link #lineLine(Line3, Line3, Line3)} are parallel.
   *   </li>
   *   <li>
   *     Tells if plane and line passed to last call to
   *     {@link #planeLine(Plane3, Line3)},
   *     {@link #planeLine(Plane3, Line3, double[])} are parallel.
   *   </li>
   *   <li>
   *     Tells if the planes passed to last call to
   *     {@link #planePlane(Plane3, Plane3, Line3)} are parallel.
   *   </li>
   * </ol>
   * <p>
   *   Not defined if none of the above methods has been performed.
   * </p> 
   * @return True if parallel, false if not.    
   */
  public boolean parallel(){ return m_parallel; }
  
  /**
   * <p>
   *   Provides additional information of performed calculations when objects
   *   are found to be parallel:
   * </p>
   * <ol>
   *   <li>
   *     Tells if parallel plane and line passed to last call to
   *     {@link #planeLine(Plane3, Line3)},
   *     {@link #planeLine(Plane3, Line3, double[])} are disjoint or not.
   *   </li>
   *   <li>
   *     Tells if parallel planes passed to last call to
   *     {@link #planePlane(Plane3, Plane3, Line3)} are disjoint or not.
   *   </li>
   * </ol> 
   * <p>
   *   Not defined if none of the above methods has been performed.
   * </p>
   * @return True if disjoint, false if not.    
   */
  public boolean disjoint(){ return m_disjoint; }
  
  /**
   * <p>
   *   Computes the parameter
   *   {@link #getPt()} so that it defines the point on the first line given
   *   closest to the second line given and the parameter
   *   {@link #getQt()} so that it defines the point on the second line given
   *   closest to the first line given.
   * </p>
   * <p>
   *   Since lines are in 2D then unless lines are parallel the points on the
   *   two lines calculated will be the same: where the lines intersect.
   * </p>
   * @param p First line.
   * @param q Second line.
   * @return {@code true} if lines intersects, {@code false} if is parallel.
   * @see #parallel()
   * @see #getPt()
   * @see #getQt()
   */
  public boolean lineLine(Line2 p, Line2 q)
  {
    double px0=p.x0();
    double py0=p.y0();    
    
    double px1=p.x1();
    double py1=p.y1();    
    
    double qx0=q.x0();
    double qy0=q.y0();    
    
    double qx1=q.x1();
    double qy1=q.y1();    
    
    double ux=px1-px0;
    double uy=py1-py0;    
    
    double vx=qx1-qx0;
    double vy=qy1-qy0;    
    
    double wx=px0-qx0;
    double wy=py0-qy0;        
      
    double a=dot(ux, uy);
    double b=dot(ux, uy, vx, vy);
    double c=dot(vx, vy);
    double d=dot(ux, uy, wx, wy);
    double e=dot(vx, vy, wx, wy);
    
    double acmbs=a*c-b*b;
      
    if (zero(acmbs)) // parallel line case.
    {
      m_pt=0.0;
      m_qt=d/b;
      m_parallel=true;
    }
    else
    {
      m_pt=(b*e-c*d)/acmbs;
      m_qt=(a*e-b*d)/acmbs;
      m_parallel=false;
    }
    
    return !m_parallel;
  }
  
  /**
   * <p>
   *   Computes the parameter
   *   {@link #getPt()} so that it defines the point on the first line given
   *   closest to the second line given and the parameter
   *   {@link #getQt()} so that it defines the point on the second line given
   *   closest to the first line given.
   * </p>
   * <p>
   *   The parameters will also be defined for the parallel case.
   * </p>
   * <p>
   *   In 3D non parallel lines may not intersect, check if the closest points
   *   are the same to learn in the non parallel case if the lines intersect or
   *   in the parallel case that they are not disjoint.
   * </p>
   * @param p First line.
   * @param q Second line.
   * @param {@code true} if not parallel else {@code false}. 
   * @see #parallel()
   * @see #getPt()
   * @see #getQt()
   */
  public boolean lineLine(Line3 p, Line3 q)
  {
    double px0=p.x0();
    double py0=p.y0();
    double pz0=p.z0();
    
    double px1=p.x1();
    double py1=p.y1();
    double pz1=p.z1();
    
    double qx0=q.x0();
    double qy0=q.y0();
    double qz0=q.z0();
    
    double qx1=q.x1();
    double qy1=q.y1();
    double qz1=q.z1();
    
    double ux=px1-px0;
    double uy=py1-py0;
    double uz=pz1-pz0;
    
    double vx=qx1-qx0;
    double vy=qy1-qy0;
    double vz=qz1-qz0;
    
    double wx=px0-qx0;
    double wy=py0-qy0;
    double wz=pz0-qz0;    
      
    double a=dot(ux, uy, uz);
    double b=dot(ux, uy, uz, vx, vy, vz);
    double c=dot(vx, vy, vz);
    double d=dot(ux, uy, uz, wx, wy, wz);
    double e=dot(vx, vy, vz, wx, wy, wz);
    
    double acmbs=a*c-b*b;
      
    if (zero(acmbs)) // parallel line case.
    {
      m_pt=0.0;
      m_qt=d/b;
      m_parallel=true;
    }
    else
    {
      m_pt=(b*e-c*d)/acmbs;
      m_qt=(a*e-b*d)/acmbs;
      m_parallel=false;
    }
    
    return !m_parallel;
  }
  
  /**
   * <p>
   *   Computes the parameter
   *   {@link #getPt()} so that it defines the point on the given line where it 
   *   intersects the given plane.
   * </p>
   * <p>
   *   If plane and line are parallel no unique intersection point exists and
   *   {@link #parallel()} returns {@code true}, parameter is then undefined.
   * </p>
   * <p>
   *   If parallel and line also is in plane
   *   {@link #disjoint()} returns {@code false}.
   * </p>
   * <p>
   *   If not parallel the method
   *   {@link #getPt()} can be used to fetch the parameter defining the
   *   intersection point, if this is in the range [0, 1] the line segment
   *   intersects the plane.
   * </p>
   * @param p Plane.
   * @param l Line.
   * @return {@code true} if line and plane not parallel else {@code false}.  
   * @see #parallel()
   * @see #disjoint()
   * @see #getPt()   
   */  
  public boolean planeLine(Plane3 p, Line3 l)
  {    
    m_parallel=false;
    m_disjoint=true;
    
    double nx=p.a();
    double ny=p.b();
    double nz=p.c();
    
    double n0x=p.x0();
    double n0y=p.y0();
    double n0z=p.z0();
    
    double px0=l.x0();
    double py0=l.y0();
    double pz0=l.z0();
    
    double ux=l.x1()-px0;
    double uy=l.y1()-py0;
    double uz=l.z1()-pz0;
    
    double wx=px0-n0x;
    double wy=py0-n0y;
    double wz=pz0-n0z;        
    
    double d=dot(nx, ny, nz, ux, uy, uz);
    double n=-dot(nx, ny, nz, wx, wy, wz);
    
    if (zero(d))
    {
      m_parallel=true;      
      if (zero(n)) m_disjoint=false;
      return false;      
    }
    
    m_pt=n/d;     
    
    return true;
  }
  
  /**
   * <p>
   *   Computes the line where two planes intersects.
   * </p>
   * <p>
   *   If planes are parallel no line is computed and
   *   {@link #parallel()} returns true and also if not disjoint
   *   {@link #disjoint()} returns false.
   * </p> 
   * @param p One plane.
   * @param q Other plane.
   * @return Line or {@code null} if is the parallel case.
   * @see #parallel()
   * @see #disjoint()
   */  
  public Line3 planePlane(Plane3 p, Plane3 q){ return planePlane(p, q, null); }
  
  /**
   * <p>
   *   Computes the line where two planes intersects.
   * </p>
   * <p>
   *   If planes are parallel no line is computed and
   *   {@link #parallel()} returns true and also if not disjoint
   *   {@link #disjoint()} returns false.
   * </p> 
   * @param p One plane.
   * @param q Other plane.
   * @param l Set to line of intersection, unassigned in parallel. 
   *          If {@code null} and there it is not the parallel case allocates.
   * @return Line or {@code null} if is the parallel case.
   * @see #parallel()
   * @see #disjoint()
   */  
  public Line3 planePlane(Plane3 p, Plane3 q, Line3 l)
  {    
    double p2x=p.x0();
    double p2y=p.y0();
    double p2z=p.z0();
    
    double xx=p.a();
    double xy=p.b();
    double xz=p.c();
    
    double q1x=q.x0();
    double q1y=q.y0();
    double q1z=q.z0();
    
    double yx=q.a();
    double yy=q.b();
    double yz=q.c();
    
    double ux=cross0(xx, xy, xz, yx, yy, yz);
    double uy=cross1(xx, xy, xz, yx, yy, yz);
    double uz=cross2(xx, xy, xz, yx, yy, yz);
    
    double ax=Math.abs(ux);
    double ay=Math.abs(uy);
    double az=Math.abs(uz);
    
    m_parallel=false;
    m_disjoint=true;
    
    if (zero(ax+ay+az))
    {
      m_parallel=true;
      
      double vdx=q1x-p2x;
      double vdy=q1y-p2y;
      double vdz=q1z-p2z;
          
      if (zero(dot(xx, xy, xz, vdx, vdy, vdz))) m_disjoint=false;      
      
      return null;
    }
    
    int maxc;
    if (ax>ay)
    {
      if (ax>az) maxc=1;
      else       maxc=3;
    }
    else
    {
      if (ay>az) maxc=2;
      else       maxc=3;
    }
    
    double d1=p.d();
    double d2=q.d();
    
    double p1x;
    double p1y;
    double p1z;
    
    switch (maxc)
    {
      case 1 :
        p1x=0.0;        
        p1y=(d2*xz-d1*yz)/ux;
        p1z=(d1*yy-d2*xy)/ux;
      break;
      
      case 2 :        
        p1x=(d1*yz-d2*xz)/uy;
        p1y=0.0;
        p1z=(d2*xx-d1*yx)/uy;
      break;
      
      case 3 :
        p1x=(d2*xy-d1*yy)/uz;        
        p1y=(d1*yx-d2*xx)/uz;
        p1z=0.0;
      break;
      
      default : throw new Error("maxc="+maxc);
    }
    
    l=(l==null) ? new Line3() : l;
    l.set(p1x, p1y, p1z, p1x+ux, p1y+uy, p1z+uz);
    return l;
  }
  
}
