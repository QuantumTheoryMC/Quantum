/*
 * The MIT License
 *
 * Copyright 2016 link.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created file on 3/21/16 at 1:29 PM.
 *
 * This file is part of Quantum API
 */
package quantum;

import quantum.api.block.Block;
import quantum.api.dimension.Dimension;
import quantum.api.entity.Entity;
import quantum.api.entity.player.PlayerEntity;
import quantum.api.item.Item;
import quantum.api.mod.Mod;
import quantum.api.world.World;
import quantum.api.world.tick.Tickable;
import quantum.util.CallerClass;
import sun.reflect.CallerSensitive;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * @author link
 */
@SuppressWarnings("MethodMayBeStatic")
public final class Quantum {

	/**
	 * The singleton instance of Quantum used for initialization and
	 * uninitialization of mods.
	 */
	private static final Quantum QUANTUM = new Quantum();

	/**
	 * The Minecraft home directory as a Path.
	 */
	private static final Path MC_DIRECTORY = getOS().equals("windows") ? Paths.get(System.getProperty("user.home"), "AppData", "Roaming", ".minecraft") : Paths.get(System.getProperty("user.home"), ".minecraft");

	/**
	 * The Quantum Directory
	 */
	private static final Path QTM_DIRECTORY = Paths.get(MC_DIRECTORY.toFile().getAbsolutePath(), "quantum");

	/**
	 * The Minecraft assets directory as a Path.
	 */
	private static final Path MC_ASSETS = Paths.get(MC_DIRECTORY.toFile().getAbsolutePath(), "assets");


	/**
	 * The Wrapper instance for Quantum.
	 */
	private static Wrapper wrapper = loadWrapper(QUANTUM, Paths.get(System.getProperty("quantum.agent.path", Paths.get(QTM_DIRECTORY.toFile().getAbsolutePath(), "quantum_wrapper.jar").toFile().getAbsolutePath())).toFile());

	// restrict mods from using Quantum outside of loading and unloading mods
	private boolean enabled = false;

	private Quantum() {
	}

	public static void main(String... args) throws ClassNotFoundException {
		// TODO print to logger
		System.out.println("[Quantum] Initializing Quantum...");
		// [init]
		// load these classes early because they use CallerClass.get()
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		cl.loadClass("quantum.api.block.Blocks");
		cl.loadClass("quantum.api.block.BlockBridge");
		cl.loadClass("quantum.api.world.WorldBridge");
		// [/init]
		System.out.println("[Quantum] Searching for mods...");
		Path mods = Paths.get(System.getProperty("user.home"), ".minecraft", "quantum", "mods");
		System.out.println("[Quantum] (in path: \"" + mods + "\")");
		if (mods.toFile().listFiles().length != 0) try {
			QUANTUM.enable();
			wrapper.loadMods(mods, QUANTUM);
			QUANTUM.disable();
		} catch (IOException e) {
			System.err.println("[Quantum]");
		} catch (IllegalAccessException e) {
			System.err.println("[Quantum] A mod constructor is not public: " + e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("[Quantum] A required class is not loaded: " + e.getLocalizedMessage() + '\n' + "[Quantum] The mod jar file may be corrupt.");
		} catch (Exception e) {
			System.err.println("[Quantum] An Exception was thrown during initialization: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Error e) {
			System.err.println("[Quantum] An Error was thrown during initialization: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		else System.out.println("[Quantum]     No mods found...");

		System.out.println("[Quantum] Finished Quantum initialization...");
	}

	/**
	 * Gets the name of the API ("Quantum").
	 *
	 * @return "Quantum"
	 */
	public static String getName() {
		return "Quantum";
	}

	/**
	 * Gets the "formal", or full, name of the Quantum environment, including
	 * the versions of API, wrapper, and minecraft, respectively.
	 *
	 * @return the "formal" name of the Quantum environment
	 */
	public static String getFormalName() {
		return '[' + getName().toLowerCase() + "_(API:" + getVersion() + ")_(W:" + wrapper.getVersion() + ")_(MC:" + getMinecraftVersion() + ")]";
	}

	/**
	 * Gets the version of the current API.
	 *
	 * @return the version of the current API
	 */
	public static String getVersion() {
		return "1.0b";
	}

	/**
	 * Gets the version of the current wrapper.
	 *
	 * @return the version of the current wrapper
	 */
	public static String getWrapperVersion() {
		return wrapper.getVersion();
	}

	/**
	 * Gets the current version of Minecraft
	 *
	 * @return the current version of Minecraft
	 */
	public static String getMinecraftVersion() {
		return wrapper.getMinecraftVersion();
	}

	/**
	 * Gets the OS-dependent ".minecraft" directory for this system.
	 *
	 * @return an absolute ".minecraft" directory
	 */
	public static Path getMinecraftDir() {
		return MC_DIRECTORY;
	}

	/**
	 * Gets the current minecraft assets directory used for ResourcePacks.
	 *
	 * @return the current minecraft assets directory
	 */
	public static Path getMinecraftAssetsDir() {
		return MC_ASSETS;
	}

	/**
	 * Gets the current system's Operating System.
	 *
	 * @return the current system's Operating System
	 */
	private static String getOS() {
		String os = System.getProperty("os.name");
		if (os.contains("windows")) return "windows";
		else if (os.contains("nix")) return "unix";
		else return os;
	}

	/**
	 * Gets the Bridge implementation for Quantum. The Bridge implementation is
	 * provided by the current wrapper. It allows Quantum API to access
	 * Minecraft features, such as the current World, the local player, the
	 * server player list, etc.
	 *
	 * <em>This method is {@link CallerSensitive}</em>, <strong>meaning that the
	 * caller must be in the package {@code quantum.api}</strong>.
	 *
	 * @return the Bridge implementation from the current Wrapper
	 *
	 * @implSpec this method checks the caller class with
	 * {@code Reflection.getCallerClass()} by checking if the class belongs to
	 * package
	 * "quantum.api"
	 */
	public static Bridge getBridge() {
		Class<?> caller = CallerClass.get();
		if (caller.getPackage().getName().startsWith("quantum"))
			return wrapper.getBridge();
		else
			throw new IllegalAccessError("A non-API Class attempted to access the internal Bridge: " + caller.getName());
	}

	private static Wrapper loadWrapper(Quantum quantum, File jarFile) {
		String debugClName = "";
		try {
			JarFile jar = new JarFile(jarFile, true);
			Attributes attributes = jar.getManifest().getMainAttributes();
			String className = debugClName = attributes.getValue("Wrapper-Class");
			Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);

			return (Wrapper) clazz.getConstructor().newInstance();
		} catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
			throw new RuntimeException("Failed to load Quantum Wrapper", e);
		} catch (ClassCastException e) {
			throw new RuntimeException("Failed to load Quantum Wrapper: \"" + debugClName + "\" does not implement Wrapper", e);
		}
	}

	private void enable() {
		enabled = true;
	}

	private void disable() {
		enabled = false;
	}

	/**
	 * Defines a new type of block with the given implementation.
	 * <p>
	 * This method registers a new block with the minecraft block registry, and
	 * in turn adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of mods, this method forces the
	 * internal
	 * list to be updated with the given block regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param block
	 * 		the block to define
	 */
	public void define(Block block) {
		if (enabled) {
			wrapper.define(block);
		}
	}

	/**
	 * Redefines a block implemented by minecraft.
	 * <p>
	 * This method overwrites the block corresponding to this implementation,
	 * and updates the Quantum API internal list.
	 * </p>
	 *
	 * @param block
	 * 		the block to redefine
	 */
	public void redefine(Block block) {
		if (enabled) {
			wrapper.redefine(block);
		}
	}

	/**
	 * Defines a new type of entity with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of MODS, this method forces the
	 * internal
	 * list to be updated with the given entity regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param entity
	 * 		the entity to define
	 */
	public void define(Entity entity) {
		if (enabled) {
			wrapper.define(entity);
		}
	}

	public void redefine(Entity entity) {
		if (enabled) {
			wrapper.redefine(entity);
		}
	}

	/**
	 * Defines a new type of item with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of MODS, this method forces the
	 * internal
	 * list to be updated with the given entity regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param item
	 * 		the item to define
	 */
	public void define(Item item) {
		if (enabled) {
			wrapper.define(item);
		}
	}

	public void redefine(Item item) {
		if (enabled) {
			wrapper.redefine(item);
		}
	}

	/**
	 * Defines a new type of dimension with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 *
	 * @param dimension
	 * 		the dimension to define
	 */
	public void define(Dimension dimension) {
		if (enabled) {
			wrapper.define(dimension);
		}
	}

	public void redefine(Dimension dimension) {
		if (enabled) {
			wrapper.redefine(dimension);
		}
	}

	public void remove(Block block) {
		wrapper.remove(block);
	}

	public void addHook(Mod mod, Tickable hook) {
		// TODO
		//wrapper.addHook(Category.TICK, hook);
	}

	public void removeHook(Mod mod, Tickable hook) {
		// TODO
	}

	public interface Bridge {

		World getCurrentWorld();

		//TODO getServer
		//Server getServer();
		//TODO getClient
		//Client getClient();

		/**
		 * Gets the local PlayerEntity for the current Client.
		 *
		 * @return the local PlayerEntity for the current Client
		 */
		PlayerEntity getPlayer();

		/**
		 * Gets the PlayerEntity List for the current Server for all connected
		 * Clients.
		 *
		 * @return the player list for connected clients to the connected Server
		 */
		List<PlayerEntity> getPlayers();


		/**
		 * Gets the defined Block with the given block ID.
		 *
		 * @param id
		 * 		the ID of the Block
		 *
		 * @return the defined Block for the given ID, or null if it does not
		 * exist
		 */
		Block getBlock(String id);

		/**
		 * Gets the defined Item with the given item ID.
		 *
		 * @param id
		 * 		the item ID
		 *
		 * @return the defined Item for the given item ID, or null if it does
		 * not exist
		 */
		Item getItem(String id);

		/**
		 * Gets the defined Dimension with the given dimension ID. By default,
		 * there are three dimensions: {@code "overworld", "nether", and "end"}
		 *
		 * @param id
		 * 		the dimension ID
		 *
		 * @return the defined Dimension with the given dimension ID, or null if
		 * it does not exist
		 */
		Dimension getDimension(String id);

	}

}
