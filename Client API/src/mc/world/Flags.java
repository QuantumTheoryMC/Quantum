/**
 * 
 */
package mc.world;

/**
 * @author Link
 *
 */
public final class Flags {
	
	/** BLOCK_UPDATE flag updates the block state (e.g. Piston moving, Redstone brightness, Falling sand, Bed, Chest, etc.)*/
	public static final int BLOCK_UPDATE = 1;
	/** CLIENT_UPDATE flag updates the clients connected to the server on MP or the client on this machine if in SP. */
	public static final int CLIENT_UPDATE = 2;
	/** RENDER_UPDATE flag applies BLOCK_UPDATE and CLIENT_UPDATE*/
	public static final int RENDER_UPDATE = 3;
	/** NO_RENDER flag does not update the rendered state of a block.*/
	public static final int NO_RENDER = 4;
	
	private Flags() {
	}
	
}
