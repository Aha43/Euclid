
//
// 110413 - AH - Checked in.
//

package org.aha.euclid.math;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.abs;

/**
 * <p>
 *   Methods of use when comparing double precision scalars or vectors.
 * </p>
 * @author Arne Halvorsen (aha42)
 */
public final class Comparisons 
{   
  private Comparisons(){} // Utility pattern dictates private constructor.
  
  /**
   * <p>
   *   The default delta used by method not accepting a delta parameter unless
   *   overridden with
   *   {@link #setDelta(double)}.
   * </p>
   */
  public static final double DEFAULT_DELTA=1e-6;
  
  private static double c_d=DEFAULT_DELTA;
  
  /**
   * <p>
   *   Sets delta used by methods not accepting a delta parameter.
   * </p>
   * <p>
   *   Default value is 
   *   {@link #DEFAULT_DELTA}.
   * </p>
   * @param d Delta.
   * @throws IllegalArgumentException If {@code d<0.0}. 
   */
  public static synchronized void setDelta(double d)
  {
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    c_d=d;
  }
  
  /**
   * <p>
   *   Gets delta used by methods not accepting a delta parameter.
   * </p>
   * <p>
   *   Default value is 
   *   {@link #DEFAULT_DELTA}.
   * </p> 
   * @return Delta. 
   */
  public static synchronized double getDelta(){ return c_d; }
  
  /**
   * <p>
   *   Tells if two numbers are to be considered same.
   * </p>
   * @param a One number.
   * @param b Second number.
   * @param d Delta.
   * @return {@code abs(a-b)<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}.
   */
  public static boolean same(double a, double b, double d)
  {
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    return a==b || abs(a-b)<=d;
  }
  
  /**
   * <p>
   *   Tells if two floating point numbers are to be considered same.
   * </p>
   * @param a One number.
   * @param b Other number.
   * @return {@code abs(a-b)<=d} where {@code d=}{@link Comparisons#getDelta()}.
   */
  public static boolean same(double a, double b){ return abs(a-b)<=c_d; }
  
  /**
   * <p>
   *   Tells if two 2D vectors are to be considered same.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param d  Delta.
   * @return {@code same(u0, v0, d) && same(u1, v1, d)}.
   */
  public static boolean same(double u0, double u1, double v0, double v1, 
    double d)
  {
    return same(u0, v0, d) && same(u1, v1, d);
  }
  
  /**
   * <p>
   *   Tells if two 2D vectors are to be considered same.
   * </p>
   * <p>
   *   Uses delta
   *   {@link #getDelta()}.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @return {@code same(u0, v0) && same(u1, v1)}.
   */
  public static boolean same(double u0, double u1, double v0, double v1)
  {
    return same(u0, v0) && same(u1, v1);
  }
  
  /**
   * <p>
   *   Tells if two 3D vectors are to be considered same.
   * </p>
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Second component of third vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Second component of third vector. 
   * @param d  Delta.
   * @return same(u0, v0, d) && same(u1, v1, d) && same(u2, v2, d).
   */
  public static boolean same(double u0, double u1, double u2, double v0, 
    double v1, double v2, double d)
  {
    return same(u0, v0, d) && same(u1, v1, d) && same(u2, v2, d);
  }
  
  /**
   * <p>
   *   Tells if two 3D vectors are to be considered same.
   * </p>
   * <p>
   *   Uses delta
   *   {@link #getDelta()}.
   * </p> 
   * @param u0 First component of first vector.
   * @param u1 Second component of first vector.
   * @param u2 Second component of third vector.
   * @param v0 First component of second vector.
   * @param v1 Second component of second vector.
   * @param v2 Second component of third vector. 
   * @return same(u0, v0) && same(u1, v1) && same(u2, v2).
   */
  public static boolean same(double u0, double u1, double u2, double v0, 
    double v1, double v2)
  {
    return same(u0, v0) && same(u1, v1) && same(u2, v2);
  }
  
  /**
   * <p>
   *   Tells if two vectors are to be considered same.
   * </p>
   * <p>
   *   Note that it is an error to compare vectors of different dimensions, this
   *   method does not return {@code false} in that case but throws an
   *   {@link IllegalArgumentException}.
   * </p>
   * <p>
   *   Vectors of dimension {@code 0} returns {@code true}.
   * </p>
   * @param u One vector.
   * @param v Other vector.
   * @param d Delta.
   * @return {@code true} if {@code u.length==v.length==0} or if for all 
   *         {@code i} {@code abs(u[i]-v[i])<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}. 
   */
  public static boolean same(double[] u, double[] v, double d)
  {
    if (u==null)
    {
      throw new NullPointerException("u");
    }
    if (v==null)
    {
      throw new NullPointerException("v");
    }
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    if (u==v) return true;
    
    int n=u.length;
    
    if (n!=v.length)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    switch (n)
    {
      case 0 : return true;
      case 1 : return  u[0]==v[0] || abs(u[0]-v[0])<=d;
      case 2 : return (u[0]==v[0] || abs(u[0]-v[0])<=d) && 
                      (u[1]==v[1] || abs(u[1]-v[1])<=d);
      case 3 : return (u[0]==v[0] || abs(u[0]-v[0])<=d) && 
                      (u[1]==v[1] || abs(u[1]-v[1])<=d) && 
                      (u[2]==v[2] || abs(u[2]-v[2])<=d);
    }
    
    for (int i=0; i<n; i++) if (abs(u[i]-v[i])>d) return false;
    return true;
  }
  
  /**
   * <p>
   *   Tells if two vectors are to be considered same.
   * </p>
   * <p>
   *   Note that it is an error to compare vectors of different dimensions, this
   *   method does not return {@code false} in that case but throws an
   *   {@link IllegalArgumentException}.
   * </p>
   * <p>
   *   Vectors of dimension {@code 0} returns {@code true}.
   * </p>
   * @param u One vector.
   * @param v Other vector.
   * @return {@code true} {@code true} if {@code u.length==v.length==0} or if 
   *         for all {@code i} {@code abs(u[i]-v[i])<=d} where 
   *         {@code d=}{@link Comparisons#getDelta()}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}. 
   */
  public static boolean same(double[] u, double[] v)
  {
    if (u==null)
    {
      throw new NullPointerException("u");
    }
    if (v==null)
    {
      throw new NullPointerException("v");
    }
    
    if (u==v) return true;
    
    int n=u.length;
    
    if (n!=v.length)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    double d=c_d;
    
    switch (n)
    {
      case 0 : return true;
      case 1 : return  u[0]==v[0] || abs(u[0]-v[0])<=d;
      case 2 : return (u[0]==v[0] || abs(u[0]-v[0])<=d) && 
                      (u[1]==v[1] || abs(u[1]-v[1])<=d);
      case 3 : return (u[0]==v[0] || abs(u[0]-v[0])<=d) && 
                      (u[1]==v[1] || abs(u[1]-v[1])<=d) && 
                      (u[2]==v[2] || abs(u[2]-v[2])<=d);
    }
    
    for (int i=0; i<n; i++) if (abs(u[i]-v[i])>d) return false;
    return true;
  }
  
  /**
   * <p>
   *   Tells if a number is to be considered the {@code 0.0}.
   * </p>
   * @param a Number.
   * @param d Delta.
   * @return {@code abs(a)<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}. 
   */
  public static boolean zero(double a, double d)
  {
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    return a==0 || abs(a)<=d;
  }
  
  /**
   * <p>
   *   Tells if a number is to be considered the {@code 0.0}.
   * </p>
   * @param a Number.
   * @return {@code abs(a)<=d} where {@code d=}{@link Comparisons#getDelta()}.
   */
  public static boolean zero(double a){ return a==0 || abs(a)<=c_d; }
  
  /**
   * <p>
   *   Tells if a 2D vector's length is to be considered {@code 0.0}.
   * </p>
   * @param u0 Vector's first component.
   * @param u1 Vector's second component. 
   * @param d Delta.
   * @return {@code abs(u0)<=d && abs(u1)<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}. 
   */
  public static boolean zero2dVector(double u0, double u1, double d)
  {
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    return (u0==0 || abs(u0)<=d) && (u1==0 || abs(u1)<=d); 
  }
  
  /**
   * <p>
   *   Tells if a 2D vector's length is to be considered {@code 0.0}.
   * </p>
   * @param u0 Vector's first component.
   * @param u1 Vector's second component. 
   * @return {@code abs(u0)<=d && abs(u1)<=d} where 
   *         {@code d=}{@link Comparisons#getDelta()}.       
   */
  public static boolean zero2dVector(double u0, double u1)
  {
    return (u0==0 || abs(u0)<=c_d) && (u1==0 || abs(u1)<=c_d); 
  }
  
  //
  
  /**
   * <p>
   *   Tells if a 2D vector's length is to be considered {@code 0.0}.
   * </p>
   * @param u0 Vector's first component.
   * @param u1 Vector's second component. 
   * @param u2 Vector's third component. 
   * @param d Delta.
   * @return {@code abs(u0)<=d && abs(u1)<=d && abs(u2)<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}. 
   */
  public static boolean zero3dVector(double u0, double u1, double u2, double d)
  {
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    return (u0==0 || abs(u0)<=d) && (u1==0 || abs(u1)<=d) && 
           (u2==0 || abs(u2)<=d); 
  }
  
  /**
   * <p>
   *   Tells if a 2D vector's length is to be considered {@code 0.0}.
   * </p>
   * @param u0 Vector's first component.
   * @param u0 Vector's second component. 
   * @return {@code abs(u0)<=d && abs(u1)<=d} where 
   *         {@code d=}{@link Comparisons#getDelta()}.
   */
  public static boolean zero3dVector(double u0, double u1, double u2)
  {
    return (u0==0 || abs(u0)<=c_d) && (u1==0 || abs(u1)<=c_d) && 
           (u2==0 || abs(u2)<=c_d); 
  }
  
  /**
   * <p>
   *   Tells if a vector's length is to be considered {@code 0.0}.
   * </p>
   * <p>
   *   Vectors of dimension {@code 0} returns {@code true}.
   * </p>
   * @param u Vector.
   * @param d Delta.
   * @return {@code true} {@code u.length==0} or if for all {@code i} 
   *         {@code abs(u[i])<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}. 
   */
  public static boolean zero(double[] u, double d)
  {
    if (u==null)
    {
      throw new NullPointerException("u");
    }
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    int n=u.length;
    
    switch (n)
    {
      case 0 : return true;
      case 1 : return  u[0]==0 || abs(u[0])<=d;
      case 2 : return (u[0]==0 || abs(u[0])<=d) && 
                      (u[1]==0 || abs(u[1])<=d);
      case 3 : return (u[0]==0 || abs(u[0])<=d) && 
                      (u[1]==0 || abs(u[1])<=d) && 
                      (u[2]==0 || abs(u[2])<=d);
    }
    
    for (int i=0; i<n; i++) if (abs(u[i])>d) return false;
    return true;
  }
  
  /**
   * <p>
   *   Tells if a vector's length is to be considered {@code 0.0}.
   * </p>
   * <p>
   *   Vectors of dimension {@code 0} returns {@code true}.
   * </p>
   * @param u Vector.
   * @return {@code true} {@code u.length==0} or if for all {@code i} 
   *         {@code abs(u[i])<=d} where {@code d=}{@link Comparisons#getDelta()}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}. 
   */
  public static boolean zero(double[] u)
  {
    if (u==null)
    {
      throw new NullPointerException("u");
    }
    
    int n=u.length;
    
    double d=c_d;
    
    switch (n)
    {
      case 0 : return true;
      case 1 : return  u[0]==0 || abs(u[0])<=d;
      case 2 : return (u[0]==0 || abs(u[0])<=d) && 
                      (u[1]==0 || abs(u[1])<=d);
      case 3 : return (u[0]==0 || abs(u[0])<=d) && 
                      (u[1]==0 || abs(u[1])<=d) && 
                      (u[2]==0 || abs(u[2])<=d);
    }
    
    for (int i=0; i<n; i++) if (abs(u[i])>d) return false;
    return true;
  }
  
  /**
   * <p>
   *   Tells if a number is to be considered to be {@code 1.0}.
   * </p>
   * @param a Number.
   * @param d Delta.
   * @return {@code abs(1.0-a)<=d}.
   * @throws IllegalArgumentException If {@code d<0.0}. 
   */
  public static boolean one(double a, double d)
  { 
    if (d<0.0)
    {
      throw new IllegalArgumentException("d<0.0 : "+d);
    }
    
    return abs(1.0-a)<=d; 
  }
  
  /**
   * <p>
   *   Tells if a number is to be considered to be {@code 1.0}.
   * </p>
   * @param a Number.
   * @param d Delta.
   * @return {@code abs(1.0-a)<=d} where {@code d=}{@link Comparisons#getDelta()}. 
   */
  public static boolean one(double a){ return abs(1.0-a)<=c_d; }
  
  /**
   * <p>
   *   Provides the same equal test for primitive {@code double} as 
   *   {@link Double#equals(Object)}.
   * </p>
   * @param a First value.
   * @param b Second value.
   * @return Double.doubleToLongBits(a)==Double.doubleToLongBits(b).
   */
  public static boolean equals(double a, double b)
  {
    return doubleToLongBits(a)==doubleToLongBits(b);
  }
  
}
