
//
// 110413 - AH - Checked in.
//

package org.aha.euclid;

/**
 * <p>
 *   Indicates that an operation can not be performed because a vector is of 
 *   length {@code 0}.
 * </p>
 * @author Arne Halvorsen (AH)
 */
@SuppressWarnings("serial")
public final class ZeroLengthVectorException extends RuntimeException
{
  /**
   * <p>
   *   Constructor.
   * </p>
   */
  public ZeroLengthVectorException(){}
  
  /**
   * <p>
   *   Constructor.
   * </p>
   * @param msg Detailed error message.
   */
  public ZeroLengthVectorException(String msg){ super(msg); }
  
}
