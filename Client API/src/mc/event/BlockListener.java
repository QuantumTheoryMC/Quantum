/**
 * 
 */
package mc.event;

/**
 * @author Link
 *
 */
public interface BlockListener {

	/**
	 * Called whenever a BlockEvent occurs.
	 * 
	 * @param evt the event containing information about the event.
	 */
	public void blockEvent(BlockEvent evt);
}
