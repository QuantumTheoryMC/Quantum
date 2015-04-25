/**
 * 
 */
package mc.world;

import java.util.Locale;

import mc.block.Block;
import mc.block.State;
import mc.entity.Entity;
import mc.task.Task;
import mc.task.TaskQueue;
import mc.util.API;
import mc.util.Settings;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;

/**
 * @author Link
 *
 */
public class World {

	private static World current = null;
	protected String folderName;
	protected String name;
	protected WorldClient worldClient;

	{
		worldClient = Minecraft.getMinecraft().theWorld;
	}

	public World() {
		load(this);
	}

	public World(String folder) {
		this.folderName = folder.toLowerCase(Locale.ENGLISH).replace(" ", "_").replace("*", "_");
		this.name = folder;
		load(this);
	}

	public static final World getCurrentWorld() {
		return current;
	}

	public String getName() {
		return name;
	}

	public String getFolderName() {
		return folderName;
	}

	/**
	 * Unloads the world by calling
	 * {@code API.getMinecraft().loadWorld((WorldClient)null)}.<br>
	 * <p>
	 * The loadWorld() method in Minecraft unloads the current world, then loads
	 * the given world. It would be inefficient to use this unload method in
	 * conjunction with another loadWorld() method, whether it is the load
	 * method in this class, in Minecraft, or a user-defined world loader.
	 * 
	 * This should only be called if absolutely necessary. Do not use this with
	 * {@code load(World world)} for the sake of all that is pure.
	 * </p>
	 * 
	 * @obfuscated true
	 */
	public static final void unload() {
		API.getMinecraft().loadWorld((WorldClient) null, "Unloading world " + current.name);
	}
	
	protected final static void load(World world) {
		load(world, "Loading world " + world.name);
		current = world;
	}

	protected static final void load(World world, String message) {
		API.getMinecraft().loadWorld(world.worldClient, message);
		current = null;
	}

	public void addBlock(int x, int y, int z, int blockID) {
		TaskQueue.add(new Task() {
			@Override
			public synchronized void run() {
				worldClient.setBlockState(new BlockPos(x, y, z), Block.getForID(blockID).format().getDefaultState());
			}
		});
	}

	/**
	 * Adds a block with an explicit state which it finds in the block's
	 * Properties . The property for the state is "state" and of the type
	 * {@link State}
	 * 
	 * @param x
	 *            the x location of the block
	 * @param y
	 *            the y location of the block
	 * @param z
	 *            the z location of the block
	 * @param block
	 *            the block to add
	 * @param flag
	 *            a Flag stating the kind of update
	 */
	public void addBlockExplicitly(int x, int y, int z, Block block, int flag) {
		BlockState state = ((State) block.getProperties().getProperty("state").getValue()).format();
		// debug
		assert state instanceof IBlockState : "[Error] World:[Property \"state\" undefined in " + block.getName();
		worldClient.setBlockState(new BlockPos(x, y, z), (IBlockState) state, flag);
	}

	/**
	 * Spawn an entity into this World.
	 * 
	 * @param x
	 *            the x spawn point
	 * @param y
	 *            the y spawn point
	 * @param z
	 *            the z spawn point
	 * @param entity
	 *            the entity to spawn
	 */
	public void addEntity(double x, double y, double z, Entity entity) {
		TaskQueue.add(new Task() {
			@Override
			public synchronized void run() {
				worldClient.spawnEntityInWorld(entity.format());
			}
		});
	}

	public void remBlock(double x, double y, double z, boolean drop) {
		TaskQueue.add(new Task() {
			@Override
			public synchronized void run() {
				worldClient.destroyBlock(new BlockPos(x, y, z), drop);

			}
		});
	}

	/**
	 * @obfuscated true
	 */
	public void tick() {
		worldClient.tick();
	}

	/**
	 * Gets the current render distance.
	 * 
	 * @return the current render distance
	 */
	public static float getRenderDistance() {
		return Settings.getRenderDistance();
	}

	public net.minecraft.world.World format() {
		return worldClient;
	}
}