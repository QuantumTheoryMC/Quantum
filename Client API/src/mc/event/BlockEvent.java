/**
 * 
 */
package mc.event;

import java.awt.Event;

import mc.block.Block;

/**
 * <p>
 * A BlockEvent is any event that occurs to a block. It is meant to mimic
 * functionality in net.minecraft.block.Block#onBlock*****() events.
 * </p>
 * 
 * @author Link
 *
 */
public final class BlockEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307231635329578417L;

	/** The location of the passed in block */
	private transient int x, y, z;
	/** The block passed into this BlockEvent */
	private transient Block block;
	/** The event id and argument to pass */
	private transient int evtID, arg;

	/**
	 * Creates a new BlockEvent with an world location, block, id, and argument.
	 * 
	 * @param x
	 *            the x location in the world.
	 * @param y
	 *            the y location in the world.
	 * @param z
	 *            the z location in the world.
	 * @param block
	 *            the block that is in the world.
	 * @param evtId
	 *            the event id of this BlockEvent.
	 * @param arg
	 *            the argument, if applicable, that is passed into this
	 *            BlockEvent.
	 */
	public BlockEvent(int x, int y, int z, Block block, int evtId, int arg) {
		super(block, evtId, arg);
	}

	/**
	 * Gets the x location passed into this BlockEvent object.
	 * 
	 * @return the x location passed into this BlockEvent object.
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Gets the y location passed into this BlockEvent object.
	 * 
	 * @return the y location passed into this BlockEvent object.
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Gets the z location passed into this BlockEvent object.
	 * 
	 * @return the z location passed into this BlockEvent object.
	 */
	public final int getZ() {
		return z;
	}

	/**
	 * Gets the Block passed into this BlockEvent object.
	 * 
	 * @return the Block contained in this BlockEvent.
	 */
	public final Block getBlock() {
		return block;
	}

	/**
	 * Gets the event ID of this BlockEvent object.
	 * 
	 * @return the event ID of this BlockEvent.
	 */
	public final int getID() {
		return evtID;
	}

	/**
	 * Gets the parameter passed into this BlockEvent object.
	 * 
	 * @return the parameter passed into this BlockEvent object.
	 */
	public final int getParam() {
		return arg;
	}
}
