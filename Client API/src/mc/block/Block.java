/**
 * 
 */
package mc.block;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mc.APIObject;
import mc.block.entity.BlockEntity;
import mc.tile.MapPallette;
import mc.util.Properties;
import mc.util.Property;
import net.minecraft.util.ResourceLocation;

/**
 * <p>
 * This is the primary class for custom Blocks.
 * 
 * 
 * </p>
 * 
 * @author Link
 *
 */
public abstract class Block extends APIObject {

	private static int blockCount = 197;

	protected static final Map<Block, BlockEntity> entities = Collections.unmodifiableMap(new HashMap<>(blockCount));
	static {
		assert Collections.unmodifiableMap(new HashMap<>(blockCount)) != null : "[Error] Block.<clinit>(line:33):[assert:[Fatal: Map containing Block to BlockEntity mappings initialized to null], [Unknown error occured]]";
	}
	/** The Block id */
	protected final int id;
	/** Indicates that this block is opaque or translucent. */
	protected final boolean opaque;
	/** A value of 0 to 255 indicating how much light gets through this block. */
	protected final int opacity;
	/** The amount of light this block emits. */
	protected final int lightEmission;
	/** This is the Block Material. */
	protected final Properties props;
	/** The color of this Block. */
	protected final Color color;
	/** the number of hits with no item that this Block requires to break. */
	protected final float hardness;
	/** the resistance this block has to TNT. */
	protected final float resistance;
	/** the amount that gravity affects particles that come from this Block. */
	protected final float particleGravity;
	/** The width, height, and depth of this Block. */
	protected final double width, height, depth;
	/** The delta offset x, y, and z from a location in a World. */
	protected final double dx, dy, dz;

	{
		incBlockCount();
		id = blockCount;

		// unittest
		assert id == blockCount : "[Error] Block.<init>:[id:[" + id + "] did not match blockCount:[" + blockCount
				+ "]]";
	}

	/**
	 * 
	 * @param props
	 *            unique properties regarding this Block or from another Block
	 * @param color
	 *            the color this Block will be drawn on a {@code Map}
	 * @param opaque
	 *            indicates if this Block is opaque or translucent
	 * @param name
	 *            the name of this Block; used for commands and debugging, and
	 *            as a HashMap Key mechanism.
	 * @param opacity
	 *            the amount of light that goes through this Block.
	 * @param lightEmission
	 *            the amount of light this block emits.
	 * @param hardness
	 *            the number of hits with no item that this Block requires to
	 *            break.
	 * @param resistance
	 *            the resistance this block has to TNT.
	 * @param particleGravity
	 *            the amount that gravity affects particles that come from this
	 *            Block.
	 * @param depth
	 *            the z axis size of the block.
	 * @param width
	 *            the x axis size of the block.
	 * @param height
	 *            the y axis size of the block.
	 * @param dx
	 *            the delta x offset this block renders from a World location.
	 * @param dy
	 *            the delta y offset this block renders from a World location.
	 * @param dz
	 *            the delta z offset this block renders from a World location.
	 * 
	 * @see mc.util.Properties {@code mc.util.Properties }for information
	 *      regarding {@code props}.
	 */
	protected Block(Properties props, Color color, boolean opaque, String name, int opacity, int lightEmission,
			float hardness, float resistance, float particleGravity, double depth, double width, double height,
			double dx, double dy, double dz) {
		super(name);
		this.props = props;
		this.color = color;
		this.opaque = opaque;
		this.hardness = hardness;
		this.resistance = resistance;
		this.particleGravity = particleGravity;
		this.opacity = opacity;
		this.lightEmission = lightEmission;
		this.depth = depth;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;

		this.props.setProperty("?silkTouch", new Property<>(true));
	}

	public Block(Block block) {
		this(block.props, block.color, block.opaque, block.name, block.opacity, block.lightEmission, block.hardness,
				block.resistance, block.particleGravity, block.depth, block.width, block.height, block.dx, block.dy,
				block.dz);
	}

	public static synchronized Block getForID(int blockID) {
		return format((net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObjectById(blockID));
	}

	protected static void register(int blockID, Block block, BlockEntity blockEntity) {
		assert net.minecraft.block.Block.blockRegistry.getIDForObject(block.format()) != blockID : "[Error] Block.register:[The block id "
				+ blockID + "is already registered for block:[id:[" + block.id + ", name:[" + block.name + "]]";
		net.minecraft.block.Block.blockRegistry.register(blockID, new ResourceLocation(block.name), block.format());

		entities.put(block, blockEntity);
	}

	private static void incBlockCount() {
		blockCount++;
	}

	public final double getDeltaX() {
		return dx;
	}

	public final double getDeltaY() {
		return dy;
	}

	public final double getDeltaZ() {
		return dz;
	}

	public final Properties getProperties() {
		return props;
	}

	public final Property<?> getProperty(String name) {
		return props.getProperty(name);
	}

	public final void setProperty(String key, Object val) {
		props.setProperty(key, new Property<>(val));
	}

	public final Color getColor() {
		return color;
	}

	public final int getID() {
		return id;
	}

	/**
	 * Compares another block with this block.
	 * <p>
	 * Returns true if the id of this block is equivalent to the other block.
	 * False otherwise.
	 * </p>
	 * 
	 * @param block
	 *            the other block
	 * @return true if the id of the blocks are equivalent
	 */
	public final boolean compare(Block block) {
		return block.id == id;
	}

	public final Block copy() {
		Block nblock = new Block(this) {
		};
		return nblock;
	}

	@Override
	public final net.minecraft.block.Block format() {

		class MBlock extends net.minecraft.block.Block {

			protected MBlock(Block block) {
				super(block.getProperties().format());
				this.blockHardness = block.hardness;
				this.blockResistance = block.resistance;
				this.fullBlock = block.opaque;
				this.translucent = !fullBlock;
				this.lightOpacity = block.opacity;
				this.lightValue = block.lightEmission;
				this.minX = block.dx;
				this.minY = block.dy;
				this.minZ = block.dz;
				this.maxX = block.width;
				this.maxY = block.height;
				this.maxZ = block.depth;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see net.minecraft.block.Block#canSilkHarvest()
			 */
			@Override
			protected boolean canSilkHarvest() {
				return (boolean) props.getProperty("?silk_touch").getValue();
			}

		}
		return new MBlock(this);
	}

	public static final Block format(net.minecraft.block.Block block) {

		class APIBlock extends Block {
			protected APIBlock(net.minecraft.block.Block block) {
				super(Properties.format(block.getMaterial()), MapPallette.format(block
						.getMapColor(net.minecraft.block.Block.getStateById(net.minecraft.block.Block
								.getIdFromBlock(block)))), block.isFullBlock(), block.getLocalizedName(), block
						.getLightOpacity(), block.getLightValue(), block.getBlockHardness(null, null), block
						.getExplosionResistance(null) * 5, block.blockParticleGravity, block.getBlockBoundsMaxZ(),
						block.getBlockBoundsMaxX(), block.getBlockBoundsMaxY(), block.getBlockBoundsMinX(), block
								.getBlockBoundsMinY(), block.getBlockBoundsMinZ());
			}
		}
		return new APIBlock(block);
	}
}
