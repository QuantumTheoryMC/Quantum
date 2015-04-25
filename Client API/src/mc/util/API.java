/**
 * 
 */
package mc.util;

import java.util.HashMap;

import mc.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;

import org.apache.logging.log4j.Logger;

/**
 * @author Link
 *
 */
public final class API {

	private API() {
	}

	/*
	 * section Minecraft 
	 */

	/*
	 * setters
	 */

	/**
	 * Sets the current Dimension the player is in in the current world.
	 * 
	 * @obfuscated true
	 * @param dimension the new dimension to spawn the player.
	 */
	public static void setPlayerDimension(int dimension) {
		getMinecraft().setDimensionAndSpawnPlayer(dimension);
	}

	/*
	 * getters
	 */

	/**
	 * Gets the actual minecraft instance. Mostly used internally within this
	 * API, but also for extensions.
	 * 
	 * @obfuscated true
	 * @return the actual minecraft instance.
	 */
	public static Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	/**
	 * Gets the directory of the installed Minecraft.
	 * 
	 * @obfuscated true
	 * @return the native installation directory of Minecraft.
	 */
	public static String getMinecraftDir() {
		return IntegratedServer.getServer().getDataDirectory().getAbsolutePath();
	}

	/**
	 * Gets the current world's save directory, which is "/saves/<world-name>/".
	 * 
	 * @obfuscated true
	 * @return the current world's save directory
	 */
	public static String getWorldDir() {
		return World.getCurrentWorld().format().getSaveHandler().getWorldDirectoryName();
	}

	/**
	 * Gets the current world the player is in. <br>
	 * <br>
	 * Equivalent to {@code World.getCurrentWorld()}
	 * 
	 * @obfuscated false
	 * @return the current world the player is in.
	 */
	public static World getWorld() {
		return World.getCurrentWorld();
	}

	/**
	 * Returns the player's info as a HashMap<String, String>.<br>
	 * {@code "X-Minecraft-Username"} returns the username,<br>
	 * {@code "X-Minecraft-UUID"} returns the UUID, and<br>
	 * {@code "X-Minecraft-Version"} returns the Minecraft version.<br>
	 * 
	 * @obfuscated true
	 * @return the player info in a HashMap<String, String>
	 */
	public static HashMap<String, String> getPlayerInfo() {
		return (HashMap<String, String>) Minecraft.func_175596_ai();
	}

	/*
	 * section DEBUG
	 */

	/**
	 * Gets the frames per second used in debug mode
	 * 
	 * @obfuscated true
	 * @return the fps used in debug mode
	 */
	public static int getDebugFPS() {
		return Minecraft.func_175610_ah();
	}

	/**
	 * Gets the Minecraft logger
	 *
	 * @obfuscated false
	 * @return the primary Minecraft logger
	 */
	public static Logger getLogger() {

		return org.apache.logging.log4j.LogManager.getLogger();
	}
}
