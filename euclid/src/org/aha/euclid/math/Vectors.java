
//
// 110413 - AH - Checked in.
//

package org.aha.euclid.math;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.aha.euclid.math.EuclidMath.cross0;
import static org.aha.euclid.math.EuclidMath.cross1;
import static org.aha.euclid.math.EuclidMath.cross2;

/**
 * <p>
 *   Utility methods of use when working with vectors represented using arrays
 *   of {@code double} values.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public final class Vectors 
{
  private Vectors(){} // Utility pattern dictates private constructor.
  
  /**
   * <p>
   *   Finds index of component with minimum value. 
   * </p>
   * @param u Vector.
   * @return Index.
   * @throws IllegalArgumentException If {@code u.length==0}.
   */
  public static int min(double[] u)
  {
    int n=u.length;    
    switch (n)
    {
      case 0 : throw new IllegalArgumentException("u.length==0");
      case 1 : return 0;
      case 2 : return u[0]<u[1] ? 0 : 1;
      case 3 : 
      {
        int idx=0;
        double min=u[0];
        if (u[1]<min){ min=u[1]; idx=1; }
        return u[2]<min ? 2 : idx;
      }
    }
    
    double min=u[0];
    int idx=0;
    for (int i=1; i<n; i++) if (u[i]<min){ min=u[i]; idx=i; }
    return idx;
  }
  
  /**
   * <p>
   *   Gets minimum value.
   * </p>
   * @param u Vector.
   * @return {@code u[min(u)]}.
   */
  public static double minValue(double[] u){ return u[min(u)]; }
  
  /**
   * <p>
   *   Finds index of component with maximum value. 
   * </p>
   * @param u Vector.
   * @return Index.
   * @throws IllegalArgumentException If {@code u.length==0}.
   */
  public static int max(double[] u)
  {
    int n=u.length;    
    switch (n)
    {
      case 0 : throw new IllegalArgumentException("u.length==0");
      case 1 : return 0;
      case 2 : return u[0]<u[1] ? 1 : 0;
      case 3 : 
      {
        int idx=0;
        double max=u[0];
        if (u[1]>max){ max=u[1]; idx=1; }
        return u[2]>max ? 2 : idx;
      }
    }
    
    double max=u[0];
    int idx=0;
    for (int i=1; i<n; i++) if (u[i]>max){ max=u[i]; idx=i; }
    return idx;
  }
  
  /**
   * <p>
   *   Gets maximum value.
   * </p>
   * @param u Vector.
   * @return {@code u[max(u)]}.
   */
  public static double maxValue(double[] u){ return u[max(u)]; }
  
  /**
   * <p>
   *   Gets the index of the component with the minimum absolute value.
   * </p>
   * @param u Vector.
   * @return Index of component with minimum absolute number.
   * @throws IllegalArgumentException If {@code v.length==0}.
   */
  public static int minmag(double[] u)
  {
    int n=u.length;
    
    switch (n)
    {
      case 0 : throw new IllegalArgumentException("v.length==0");
      case 1 : return 0;
      case 2 : return abs(u[0])<abs(u[1]) ? 0 : 1;
      case 3 :
      {
        int rv=0;
        double min=abs(u[0]);
        double c1=abs(u[1]);
        double c2=abs(u[2]);
        if (c1<min)
        {
          rv=1;
          min=c1;
        }
        return c2<min ? 2 : rv;
      }
    }
    
    double min=abs(u[0]);
    int rv=0;
    for (int i=1; i<n; i++)
    {
      double curr=abs(u[i]);
      if (curr<min)
      {
        min=curr;
        rv=i;
      }
    }
    return rv;
  }
  
  /**
   * <p>
   *   Gets the minimum absolute value.
   * </p>
   * @param u Vector.
   * @return {@code u[minmag(u)]}.
   * @throws IllegalArgumentException If {@code v.length==0}.
   */
  public static double minmagValue(double[] u){ return u[minmag(u)]; }
  
  /**
   * <p>
   *   Gets the index of the component with the largest absolute value.
   * </p>
   * @param u Vector.
   * @return Index of component with largest absolute number.
   * @throws IllegalArgumentException If {@code v.length==0}.
   */
  public static int maxmag(double[] u)
  {
    int n=u.length;
    
    switch (n)
    {
      case 0 : throw new IllegalArgumentException("v.length==0");
      case 1 : return 0;
      case 2 : return abs(u[0])<abs(u[1]) ? 1 : 0;
      case 3 :
      {
        int rv=0;
        double max=abs(u[0]);
        double c1=abs(u[1]);
        double c2=abs(u[2]);
        if (c1>max)
        {
          rv=1;
          max=c1;
        }
        return c2>max ? 2 : rv;
      }
    }
    
    double max=abs(u[0]);
    int rv=0;
    for (int i=1; i<n; i++)
    {
      double curr=abs(u[i]);
      if (curr>max)
      {
        max=curr;
        rv=i;
      }
    }
    return rv;
  }
  
  /**
   * <p>
   *   Gets the minimum absolute value.
   * </p>
   * @param u Vector.
   * @return {@code u[minmag(u)]}.
   * @throws IllegalArgumentException If {@code v.length==0}.
   */
  public static double maxmagValue(double[] u){ return u[maxmag(u)]; }
  
  /**
   * <p>
   *   Negate a vector.
   * </p>
   * @param u Vector changed.
   */
  public static void neg(double[] u)
  {
    int n=u.length;
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]=-u[0]; return;
      case 2 : u[0]=-u[0]; u[1]=-u[1]; return;
      case 3 : u[0]=-u[0]; u[1]=-u[1]; u[2]=-u[2]; return;
    }
    
    for (int i=0; i<n; i++) u[i]=-u[i];
  }
  
  /**
   * <p>
   *   Negate a vector.
   * </p>
   * @param u Vector to negate.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}. 
   */
  public static double[] neg(double[] u, double[] w)
  {
    int n=u.length;
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=-u[0]; return w;
      case 2 : w[0]=-u[0]; w[1]=-w[1]; return w;
      case 3 : w[0]=-u[0]; w[1]=-w[1]; w[2]=-w[2]; return w;
    }
    
    for (int i=0; i<n; i++) u[i]=-u[i];    
    return w;
  }
  
  //
  
  /**
   * <p>
   *   Adds a scalar from vector's components.
   * </p>
   * @param u Vector changed.
   * @param s Scalar.
   */
  public static void add(double[] u, double s)
  {
    int n=u.length;
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]+=s; return;
      case 2 : u[0]+=s; u[1]+=s; return;
      case 3 : u[0]+=s; u[1]+=s; u[2]+=s; return;
    }
    
    for (int i=0; i<n; i++) u[i]+=s;
  }
  
  /**
   * <p>
   *   Adds a scalar from vector's components.
   * </p>
   * @param u Vector.
   * @param s Scalar.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}.
   */
  public static double[] add(double[] u, double s, double[] w)
  {
    int n=u.length;
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]+s; return w;
      case 2 : w[0]=u[0]+s; w[1]=u[1]+s; return w;
      case 3 : w[0]=u[0]+s; w[1]=u[1]+s; w[2]=u[2]+s; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]+s;    
    return w;
  }
  
  /**
   * <p>
   *   Adds vector {@code v} to {@code u}.
   * </p>
   * @param u Vector changed.
   * @param v Vector to add to {@code v}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   */
  public static void add(double[] u, double[] v)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }    
    
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]+=v[0]; return;
      case 2 : u[0]+=v[0]; u[1]+=v[1]; return;
      case 3 : u[0]+=v[0]; u[1]+=v[1]; u[2]+=v[2]; return;
    }
    
    for (int i=0; i<n; i++) u[i]+=v[i];
  }
  
  /**
   * <p>
   *   Adds vector {@code v} to {@code u}.
   * </p>
   * @param u Vector changed.
   * @param v Vector to add to {@code v}.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}. 
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}. 
   */
  public static double[] add(double[] u, double[] v, double[] w)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=w.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]+v[0]; return w;
      case 2 : w[0]=u[0]+v[0]; w[1]=u[1]+v[1]; return w;
      case 3 : w[0]=u[0]+v[0]; w[1]=u[1]+v[1]; w[2]=u[2]+v[2]; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]+v[i];
    return w;
  }
  
  /**
   * <p>
   *   Subtracts a scalar from vector's components.
   * </p>
   * @param u Vector changed.
   * @param s Scalar.
   */
  public static void sub(double[] u, double s)
  {
    int n=u.length;
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]-=s; return;
      case 2 : u[0]-=s; u[1]-=s; return;
      case 3 : u[0]-=s; u[1]-=s; u[2]-=s; return;
    }
    
    for (int i=0; i<n; i++) u[i]-=s;
  }
  
  /**
   * <p>
   *   Subtracts a scalar from vector's components.
   * </p>
   * @param u Vector.
   * @param s Scalar.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}.
   */
  public static double[] sub(double[] u, double s, double[] w)
  {
    int n=u.length;
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]-s; return w;
      case 2 : w[0]=u[0]-s; w[1]=u[1]-s; return w;
      case 3 : w[0]=u[0]-s; w[1]=u[1]-s; w[2]=u[2]-s; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]-s;    
    return w;
  }
  
  /**
   * <p>
   *   Subtracts vector {@code v} from {@code u}.
   * </p>
   * @param u Vector changed.
   * @param v Vector to subtract from {@code v}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   */
  public static void sub(double[] u, double[] v)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }    
    
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]-=v[0]; return;
      case 2 : u[0]-=v[0]; u[1]-=v[1]; return;
      case 3 : u[0]-=v[0]; u[1]-=v[1]; u[2]-=v[2]; return;
    }
    
    for (int i=0; i<n; i++) u[i]-=v[i];
  }
  
  /**
   * <p>
   *   Subtracts vector {@code v} from {@code u}.
   * </p>
   * @param u Vector changed.
   * @param v Vector to subtract from {@code v}.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}. 
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}. 
   */
  public static double[] sub(double[] u, double[] v, double[] w)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=w.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]-v[0]; return w;
      case 2 : w[0]=u[0]-v[0]; w[1]=u[1]-v[1]; return w;
      case 3 : w[0]=u[0]-v[0]; w[1]=u[1]-v[1]; w[2]=u[2]-v[2]; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]-v[i];
    return w;
  }
  
  /**
   * <p>
   *   Multiplies a scalar to vector's components.
   * </p>
   * @param u Vector changed.
   * @param s Scalar.
   */
  public static void scale(double[] u, double s)
  {
    int n=u.length;
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]*=s; return;
      case 2 : u[0]*=s; u[1]*=s; return;
      case 3 : u[0]*=s; u[1]*=s; u[2]*=s; return;
    }
    
    for (int i=0; i<n; i++) u[i]*=s;
  }
  
  /**
   * <p>
   *   Multiplies a scalar to vector's components.
   * </p>
   * @param u Vector.
   * @param s Scalar.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}.
   */
  public static double[] scale(double[] u, double s, double[] w)
  {
    int n=u.length;
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]*s; return w;
      case 2 : w[0]=u[0]*s; w[1]=u[1]*s; return w;
      case 3 : w[0]=u[0]*s; w[1]=u[1]*s; w[2]=u[2]*s; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]*s;    
    return w;
  }
  
  /**
   * <p>
   *   Multiplies vector {@code v} components with {@code u} components.
   * </p>
   * @param u Vector changed.
   * @param v Vector to subtract from {@code v}.
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   */
  public static void scale(double[] u, double[] v)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }    
    
    switch (n)
    {
      case 0 : return;
      case 1 : u[0]*=v[0]; return;
      case 2 : u[0]*=v[0]; u[1]*=v[1]; return;
      case 3 : u[0]*=v[0]; u[1]*=v[1]; u[2]*=v[2]; return;
    }
    
    for (int i=0; i<n; i++) u[i]*=v[i];
  }
  
  /**
   * <p>
   *   Multiplies vector {@code v} components with {@code u} components.
   * </p>
   * @param u Vector changed.
   * @param v Vector to multiply with.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}. 
   * @throws IllegalArgumentException If {@code u.length!=v.length}.
   * @throws IllegalArgumentException If {@code w!=null && w.length!=u.length}. 
   */
  public static double[] scale(double[] u, double[] v, double[] w)
  {
    int n=u.length;
    
    if (v.length!=n)
    {
      throw new IllegalArgumentException("u.length!=v.length : "+n+"!="+
        v.length);
    }
    
    w=(w==null) ? (n==0 ? ZERO_DIMENSION_VECTOR : new double[n]) : w;
    
    if (w.length!=n)
    {
      throw new IllegalArgumentException("u.length!=w.length : "+n+"!="+
        w.length);
    }
    
    switch (n)
    {
      case 0 : return w;
      case 1 : w[0]=u[0]*v[0]; return w;
      case 2 : w[0]=u[0]*v[0]; w[1]=u[1]*v[1]; return w;
      case 3 : w[0]=u[0]*v[0]; w[1]=u[1]*v[1]; w[2]=u[2]*v[2]; return w;
    }
    
    for (int i=0; i<n; i++) w[i]=u[i]*v[i];
    return w;
  }
  
  /**
   * <p>
   *   Computes the dot product of two vectors.
   * </p>
   * @param u First vector.
   * @param v Second vector.
   * @return Dot product.
   * @throws IndexOutOfBoundsException If {@code u.length>v.length}. 
   */
  public static double dot(double[] u, double[] v)
  {
    int n=u.length;
    switch (n)
    {
      case 0 : return 0.0;      
      case 1 : return u[0]*v[0];      
      case 2 : return u[0]*v[0]+u[1]*v[1];      
      case 3 : return u[0]*v[0]+u[1]*v[1]+u[2]*v[2];
    }
    
    float rv=0;
    for (int i=0; i<n; i++) rv+=u[i]*v[i];
    return rv;
  }
  
  /**
   * <p>
   *   Computes the length of a vector.
   * </p>
   * @param u Vector.
   * @return Length.
   */
  public static double len(double[] u){ return sqrt(dot(u, u)); }
  
  /**
   * <p>
   *   Computes length between two points.
   * </p>
   * @param u Positional vector for first point.
   * @param v Positional vector for second point.
   * @return Length.
   * @throws IndexOutOfBoundsException If {@code u.length>v.length}. 
   */
  public static double len(double[] u, double[] v)
  { 
    int n=(u==v) ? 0 : u.length;
    
    switch (n)
    {
      case 0 : return 0.0;
      case 1 : return abs(u[0]-v[0]);
      case 2 : return sqrt(EuclidMath.dot(u[0]-v[0], u[1]-v[1]));
      case 3 : return sqrt(EuclidMath.dot(u[0]-v[0], u[1]-v[1], u[2]-v[2]));
    }
    
    double rv=0.0;
    for (int i=0; i<n; i++) 
    {
      double d=u[i]-v[i];
      rv+=d*d;
    }
    
    return sqrt(rv);
  }
  
  /**
   * <p>
   *   Normalize vector.
   * </p>
   * @param u Vector changed.
   */
  public static void norm(double[] u){ scale(u, 1.0/len(u)); }
  
  /**
   * <p>
   *   Computes the cross product of two 3D vectors.
   * </p>
   * @param u First vector.
   * @param v Second vector.
   * @param w Assigned to result, if {@code null} allocates.
   * @return Result: {@code w} or allocated if last parameter {@code null}. 
   * @throws IndexOutOfBoundsException If {@code u.length<3 || v.length<3}.
   * @throws IndexOutOfBoundsException If {@code w!=null && w.length<3}.
   */
  public static double[] cross(double[] u, double[] v)
  { 
    return cross(u, v, null);
  }
  
  /**
   * <p>
   *   Computes the cross product of two 3D vectors.
   * </p>
   * @param u First vector.
   * @param v Second vector.
   * @return Result.
   * @throws IndexOutOfBoundsException If {@code u.length<3 || v.length<3}.         
   */
  public static double[] cross(double[] u, double[] v, double[] w)
  {
    w=(w==null) ? new double[3] : w;
    
    w[0]=cross0(u[0], u[1], u[2], v[0], v[1], v[2]);
    w[1]=cross1(u[0], u[1], u[2], v[0], v[1], v[2]);
    w[2]=cross2(u[0], u[1], u[2], v[0], v[1], v[2]);
    
    return w;
  }
  
  /**
   * <p>
   *   Used to print positional vectors of any dimensions.
   * </p>
   * @param u Vector.
   * @return "(x,y...)".
   */
  public static String pointToString(double... u)
  {
    StringBuilder sb=new StringBuilder("(");
    
    int n=u.length;
    for (int i=0; i<n; i++) 
    {
      if (i>0) sb.append(",");
      sb.append(u[i]);
    }    
    return sb.append(")").toString();
  }
  
  /**
   * <p>
   *   Used to print general vectors.
   * </p>
   * @param u Vector.
   * @return "[u0,u1...]".
   */
  public static String vectorToString(double... u)
  {
    StringBuilder sb=new StringBuilder("[");
    
    int n=u.length;
    for (int i=0; i<n; i++) 
    {
      if (i>0) sb.append(",");
      sb.append(u[i]);
    }    
    return sb.append("]").toString();
  }
  
  /**
   * <p>
   *   The vector of dimension {@code 0} shared.
   * </p>
   */
  public static double[] ZERO_DIMENSION_VECTOR=new double[]{};
  
}
