
//
// 110413 - AH - Checked in.
//

package org.aha.euclid.math;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * <p>
 *   Methods of use when calculating in Euclidean space. 
 * </p>
 * @author Arne Halvorsen (AH)
 */
public final class EuclidMath 
{
  private EuclidMath(){} // Utility pattern dictates private constructor.
  
  /**
   * <p>
   *   Gets min value.
   * </p>
   * @param a First value.
   * @param b Second value.
   * @return Min.
   */
  public static double min(double a, double b){ return a<b ? a : b; }
  
  /**
   * <p>
   *   Gets max value.
   * </p>
   * @param a First value.
   * @param b Second value.
   * @return Max.
   */
  public static double max(double a, double b){ return a<b ? b : a; }
  
  /**
   * <p>
   *   Gets min value.
   * </p>
   * @param a First value.
   * @param b Second value.
   * @param c Second value. 
   * @return Min.
   */
  public static double min(double a, double b, double c)
  {
    double min=a;
    if (b<min) min=b;
    if (c<min) return c;
    return min;
  }
  
  /**
   * <p>
   *   Gets max value.
   * </p>
   * @param a First value.
   * @param b Second value.
   * @param c Second value. 
   * @return Max.
   */
  public static double max(double a, double b, double c)
  {
    double max=a;
    if (b>max) max=b;
    if (c>max) return c;
    return max;
  }
  
  /**
   * <p>
   *   Computes the length between two 2D points.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @return Length.
   */
  public static double len(double x0, double y0, double x1, double y1)
  {
    return len(x1-x0, y1-y0);
  }
  
  /**
   * <p>
   *   Computes the length between two 3D points.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param z0 Z coordinate of first point. 
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @param z1 Z coordinate of second point. 
   * @return Length.
   */
  public static double len(double x0, double y0, double z0, double x1, 
    double y1, double z1)
  {
    return len(x1-x0, y1-y0, z1-z0);    
  }
  
  /**
   * <p>
   *   Computes length of a 2D vector.
   * </p>
   * @param u1 First component of vector.
   * @param u2 Second component of vector.
   * @return Length.
   */
  public static double len(double u1, double u2){ return sqrt(dot(u1, u2)); }
  
  /**
   * <p>
   *   Computes length of a 3D vector.
   * </p>
   * @param u1 First component of vector.
   * @param u2 Second component of vector.
   * @param u3 Third component of vector.
   * @return Length.
   */
  public static double len(double u1, double u2, double u3)
  {
    return sqrt(dot(u1, u2, u3));
  }
  
  /**
   * <p>
   *   Computes the dot product u.u of a 2D vector.
   * </p>
   * @param u1 First component of vector.
   * @param u2 Second component of vector.   
   * @return Dot product.
   */
  public static double dot(double u1, double u2){ return u1*u1+u2*u2; }
  
  /**
   * <p>
   *   Computes the dot product of 2D vectors.
   * </p>
   * @param u1 First component of first vector.
   * @param u2 Second component of first vector.
   * @param v1 First component of second vector.
   * @param v2 Second component of second vector.
   * @return Dot product.
   */
  public static double dot(double u1, double u2, double v1, double v2)
  {
    return u1*v1+u2*v2;
  }
  
  /**
   * <p>
   *   Computes the dot product u.u of a 3D vector.
   * </p>
   * @param u1 First component of vector.
   * @param u2 Second component of vector.
   * @param u3 Third component of vector.     
   * @return Dot product.
   */
  public static double dot(double u1, double u2, double u3)
  {
    return u1*u1+u2*u2+u3*u3;
  }
  
  /**
   * <p>
   *   Computes the dot product of 3D vectors.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Third component of first vector. 
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Second component of second vector. 
   * @return Dot product.
   */
  public static double dot(double u0, double u1, double u2, double v0, 
    double v1, double v2)
  {
    return u0*v0+u1*v1+u2*v2;
  }
  
  /**
   * <p>
   *   Computes the first component of the cross product vector.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Third component of first vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Third component of second vector.
   * @return First component of the cross product vector.
   */
  public static double cross0(double u0, double u1, double u2, double v0,
    double v1, double v2)
  {
    return u1*v2-u2*v1;
  }
  
  /**
   * <p>
   *   Computes the second component of the cross product vector.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Third component of first vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Third component of second vector.
   * @return Second component of the cross product vector.
   */
  public static double cross1(double u0, double u1, double u2, double v0,
    double v1, double v2)
  {
    return u2*v0-u0*v2;
  }
  
  /**
   * <p>
   *   Computes the second component of the cross product vector.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Third component of first vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Third component of second vector.
   * @return Third component of the cross product vector.
   */
  public static double cross2(double u0, double u1, double u2, double v0,
    double v1, double v2)
  {
    return u0*v1-u1*v0;  
  }
  
  /**
   * <p>
   *   Computes the <i>signed</i> area times 2 of a 2D triangle.
   * </p>
   * <p>
   *   That the value is signed means this value can be used to decide which
   *   side the third point is on of the line defined by the first and
   *   second point.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @param x2 X coordinate of third point.
   * @param y2 Y coordinate of third point.
   * @return Area of triangle times 2.
   */
  public static double area2(double x0, double y0, double x1, double y1, 
    double x2, double y2)
  {
    return (x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);
  }
  
  /**
   * <p>
   *   Computes the <i>signed</i> area times 2 of a 2D triangle.
   * </p>
   * <p>
   *   That the value is signed means this value can be used to decide which
   *   side the third point is on of the line defined by the first and
   *   second point.
   * </p>
   * @param p0 First point.
   * @param p1 Second point.
   * @param p2 Third point.
   * @return Area of triangle times 2.
   */
  public static double area2(double[] p0, double[] p1, double[] p2)
  {
    return area2(p0[0], p0[1], p1[0], p1[1], p2[0], p2[1]);
  }
  
  /**
   * <p>
   *   Computes the area of a 2D triangle.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @param x2 X coordinate of third point.
   * @param y2 Y coordinate of third point.
   * @return Area.
   */
  public static double area(double x0, double y0, double x1, double y1, 
    double x2, double y2)
  {
    return abs(0.5*area2(x0, y0, x1, y1, x2, y2));
  }
  
  /**
   * <p>
   *   Computes the area of a 3D triangle.
   * </p>
   * @param x0 X coordinate of first point.
   * @param y0 Y coordinate of first point.
   * @param z0 Z coordinate of first point.
   * @param x1 X coordinate of second point.
   * @param y1 Y coordinate of second point.
   * @param z1 Z coordinate of second point.
   * @param x2 X coordinate of third point.
   * @param y2 Y coordinate of third point.
   * @param z2 Z coordinate of third point.
   * @return Area.
   */
  public static double area(double x0, double y0, double z0, double x1,
    double y1, double z1, double x2, double y2, double z2)
  {
    double p1p0x=x1-x0;
    double p1p0y=y1-y0;
    double p1p0z=z1-z0;
    
    double p2p0x=x2-x0;
    double p2p0y=y2-y0;
    double p2p0z=z2-z0;
    
    double wx=cross0(p1p0x, p1p0y, p1p0z, p2p0x, p2p0y, p2p0z);
    double wy=cross1(p1p0x, p1p0y, p1p0z, p2p0x, p2p0y, p2p0z);
    double wz=cross2(p1p0x, p1p0y, p1p0z, p2p0x, p2p0y, p2p0z);
    
    return 0.5*len(wx, wy, wz);    
  }
  
  /**
   * <p>
   *   Tells if a point is inside a triangle.
   * </p>
   * @param ax X coordinate of first point of triangle.
   * @param ay Y coordinate of first point of triangle.
   * @param az Z coordinate of first point of triangle.  
   * @param bx X coordinate of second point of triangle.
   * @param by Y coordinate of second point of triangle.
   * @param bz Z coordinate of second point of triangle. 
   * @param cx X coordinate of third point of triangle.
   * @param cy Y coordinate of third point of triangle.
   * @param cz Z coordinate of third point of triangle.
   * @param px X coordinate of point. 
   * @param py Y coordinate of point.
   * @param pz Z coordinate of point.  
   * @return True if {@code (px, py, pz)} inside.
   */
  public static boolean inside(double ax, double ay, double az, double bx, 
    double by, double bz, double cx, double cy, double cz, double px, double py, 
    double pz)
  {    
    return sameSide(px, py, pz, ax, ay, az, bx, by, bz, cx, cy, cz) && 
           sameSide(px, py, pz, bx, by, bz, ax, ay, az, cx, cy, cz) && 
           sameSide(px, py, pz, cx, cy, cz, ax, ay, az, bx, by, bz);
  }
  
  // Side test inside is implemented in terms of.
  private static boolean sameSide(double p1x, double p1y, double p1z, 
    double p2x, double p2y, double p2z, double ax, double ay, double az, 
    double bx, double by, double bz)
  {
    double ux=bx-ax;
    double uy=by-ay;
    double uz=bz-az;
    
    double vx=p1x-ax;
    double vy=p1y-ay;
    double vz=p1z-az;
    
    double wx=p2x-ax;
    double wy=p2y-ay;
    double wz=p2z-az;
    
    double xx=cross0(ux, uy, uz, vx, vy, vz);
    double xy=cross1(ux, uy, uz, vx, vy, vz);
    double xz=cross2(ux, uy, uz, vx, vy, vz);
    
    double yx=cross0(ux, uy, uz, wx, wy, wz);
    double yy=cross1(ux, uy, uz, wx, wy, wz);
    double yz=cross2(ux, uy, uz, wx, wy, wz);
            
    return dot(xx, xy, xz, yx, yy, yz)>0.0;    
  }
  
}
