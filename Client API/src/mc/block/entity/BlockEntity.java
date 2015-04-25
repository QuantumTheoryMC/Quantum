/**
 * 
 */
package mc.block.entity;

import mc.APIObject;
import mc.block.Block;
import mc.world.World;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

/**
 * <p>
 * A BlockEntity is the name of the original Minecraft source class
 * "BlockEntity". TileEntity is the BlockEntity class, but MCP was wrong from
 * the beginning :P.
 * 
 * This class is used to render any block you create.
 * </p>
 * 
 * @author Link
 *
 */
public abstract class BlockEntity extends APIObject {

	/** The Block representing this BlockEntity */
	protected final Block block;
	/** The World location of this BlockEntity */
	protected int x, y, z;

	/**
	 * Creates a BlockEntity.
	 * 
	 * @param block
	 *            the type of BlockEntity this is
	 * @param x
	 *            the world x of this BlockEntity
	 * @param y
	 *            the world y of this BlockEntity
	 * @param z
	 *            the world z of this BlockEntity
	 */
	public BlockEntity(Block block, int x, int y, int z) {
		super(block.getName());
		this.block = block;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets this BlockEntity's block object.
	 * 
	 * @return a Block representing this BlockEntity
	 */
	public final Block type() {
		return block;
	}

	/**
	 * Gets the x location of this BlockEntity
	 * 
	 * @return the x location of this BlockEntity
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Gets the y location of this BlockEntity
	 * 
	 * @return the y location of this BlockEntity
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Gets the z location of this BlockEntity
	 * 
	 * @return the z location of this BlockEntity
	 */
	public final int getZ() {
		return z;
	}

	/**
	 * Formats this BlockEntity to a TileEntity
	 * 
	 * @return this BlockEntity as a TileEntity
	 */
	@Override
	public final TileEntity format() {
		class MTileEntity extends TileEntity {
			protected MTileEntity() {
				this.blockType = block.format();
				this.pos = new BlockPos(x, y, z);
				this.worldObj = World.getCurrentWorld().format();
				this.tileEntityInvalid = false;
			}

		}
		return new MTileEntity();
	}
	
	public static final BlockEntity format(TileEntity tileEntity) {
		class MBlockEntity extends BlockEntity {
			protected MBlockEntity(TileEntity te) {
				super(Block.format(te.getBlockType()), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
			}
			
		}
		
		MBlockEntity mbe = new MBlockEntity(tileEntity);
		return mbe;
	}

}
