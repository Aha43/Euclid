
//
// 110413 - AH - Checked in.
//

package org.aha.euclid.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.aha.euclid.Euclid;
import org.aha.euclid.Line2;

/**
 * <p>
 *   Test 
 *   {@link Euclid} calculations between 2D lines.
 * </p>
 * @author Arne Halvorsen (AH)
 */
public final class EuclidTest_0 
{
  private static final Euclid c_euclid=new Euclid();
  
  private static final Line2 c_line_00_10=new Line2(0, 0, 1, 0);
  
  private static final Line2 c_line_00_01=new Line2(0, 0, 0, 1);
  
  private static final Line2 c_line_00_11=new Line2(0, 0, 1, 1);
  
  private static final Line2 c_line_10_01=new Line2(1, 0, 0, 1);
  
  /**
   * <p>
   *   Constructor.
   * </p>
   */
  public EuclidTest_0(){}
  
  @Test
  public void test1()
  {
    c_euclid.lineLine(c_line_00_10, c_line_00_01);
    
    assertFalse(c_euclid.parallel());
    assertEquals(0.0, c_euclid.getPt(), 1e-6);
    assertEquals(0.0, c_euclid.getQt(), 1e-6);
  }
  
  @Test
  public void test2()
  {
    c_euclid.lineLine(c_line_00_01, c_line_00_10);
    
    assertFalse(c_euclid.parallel());
    assertEquals(0.0, c_euclid.getPt(), 1e-6);
    assertEquals(0.0, c_euclid.getQt(), 1e-6);
  }
  
  @Test
  public void test3()
  {
    c_euclid.lineLine(c_line_00_10, c_line_00_10);
    
    assertTrue(c_euclid.parallel());
  }
  
  @Test
  public void test4()
  {
    c_euclid.lineLine(c_line_00_01, c_line_00_01);
    
    assertTrue(c_euclid.parallel());
  }
  
  @Test
  public void test5()
  {
    c_euclid.lineLine(c_line_00_11, c_line_10_01);
    
    assertFalse(c_euclid.parallel());
    assertEquals(0.5, c_euclid.getPt(), 1e-6);
    assertEquals(0.5, c_euclid.getQt(), 1e-6);
  }
  
  @Test
  public void test6()
  {
    c_euclid.lineLine(c_line_10_01, c_line_00_11);
    
    assertFalse(c_euclid.parallel());
    assertEquals(0.5, c_euclid.getPt(), 1e-6);
    assertEquals(0.5, c_euclid.getQt(), 1e-6);
  }
  
  @Test
  public void test7()
  {
    c_euclid.lineLine(c_line_00_11, c_line_00_11);
    
    assertTrue(c_euclid.parallel());
  }
  
  @Test
  public void test8()
  {
    c_euclid.lineLine(c_line_10_01, c_line_10_01);
    
    assertTrue(c_euclid.parallel());
  }
  
}
