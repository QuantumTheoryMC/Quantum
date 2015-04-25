/**
 * 
 */
package mc.util;

/**
 * @author Link
 *
 */
public interface Action extends Runnable {

	public abstract void doAction();
	
	@Override
	public default void run() {
		doAction();
	}
	
}
