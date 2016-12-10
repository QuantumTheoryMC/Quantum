package quantum.mod;

import quantum.Quantum;
import quantum.api.MinecraftObject;

/**
 * This is the interface all mod main classes are required to implement in
 * order
 * to be a Quantum mod. Otherwise it is invalidated.
 * <p>
 * All Quantum mods run concurrently, but the API is required to be
 * thread-safe, so mods can simply relax and do their thing and mod devs don't
 * have to worry about the stress of concurrency.
 * </p>
 * <p>
 * A call from within a mod, as well as any game tick hooks, are run inside
 * the mod thread. The mods are managed via ModManager(not yet implemented),
 * and
 * contained by
 * {@linkplain quantum.Quantum}. The instance of Quantum passed to the mod is
 * for use of (un)loading, such as (un)registering MinecraftObjects, and saving
 * to configuration files to name a few. Because of this nature, each instance
 * of Quantum has mutable objects that are destroyed (nullified) after the
 * atomic loading and unloading of mods, so saving an instance cannot allow one
 * to register or unregister if it is not time to do so.
 * </p>
 * <p>
 * What defines a mod as a Quantum mod, is a mod that defines a Main-Class in
 * their manifest. In other words, mods loaded by this API are treated as
 * actual
 * programs. Each mod is placed in their own thread, and execute in their own
 * thread, with its thread named {@code "quantum.mod." + mod.getName()}.
 * </p>
 * <p>
 * The only exception to this is a Forge mod. Core mods may or may not be
 * compatible.
 * </p>
 * <p>
 * The {@linkplain #load}() and {@linkplain #unload}() methods will have a
 * timeout of
 * (60,000ms), so if a mod fails to load or unload within the given
 * time, the load/unload is aborted and nothing changes, or any changed state
 * as a result of calling those methods, is restored.
 * Do note that mods are (un)loaded concurrently.
 * </p>
 * <p>
 * (In the future, I may implement a simple AI to recognize changes
 * to Minecraft in a core mod and attempt to create the modification in the
 * actual Forge mod itself as a Quantum API call, since most core mods have
 * no real reason to exist other than there is no way to do something without
 * modifying a part of minecraft because Forge does not provide a way of
 * modifying that otherwise, or the developer chose to do so anyways, core mods
 * and base edits will be unsupported. Quantum
 * API allows changes to be made <em>to
 * the game</em>, but not to minecraft's own implementations internally. For
 * example, you cannot modify how rendering or chunk loading works, but you can
 * change the behavior of a game object such as a block, entity, GUI, etc. I
 * will, however, make extensive optimizations to the game internally,
 * available
 * as an API Extension.)
 * </p>
 *
 * @author link
 * @see Quantum
 */
public interface Mod {

	/**
	 * This method is called by the mod loader to allow the mod to optionally
	 * load anything it specifically needs.
	 * <p>
	 * Mods can be loaded and unloaded at runtime, so it is crucial that if a
	 * mod implements load(), it should also implement unload().
	 * </p>
	 *
	 * @param quantum
	 * 		the quantum instance
	 */
	void load(Quantum quantum);

	/**
	 * This method is called by the mod loader to allow the mod to optionally
	 * unload anything it specifically needed.
	 * <p>
	 * Mods can be unloaded and loaded at runtime, so it is crucial that if a
	 * mod implements unload(), it should also implement load().
	 * </p>
	 * FIXME currently, the mod jar file will still be in memory if a mod is
	 * unloaded. To fix will require a JarClassLoader for each mod loaded
	 * because the CL contains the Class instances. Setting the API's internal
	 * JarClassLoader to null lets GC remove the classes.
	 *
	 * @param quantum
	 * 		the quantum instance
	 */
	void unload(Quantum quantum);

	/**
	 * Gets the name of this mod. Mods are required to implement this.
	 *
	 * @return the name of this mod
	 *
	 * @see MinecraftObject#getID()
	 * @see #toString()
	 */
	String getName();

	/**
	 * Mods are required to implement this method in order to create an
	 * effective debugging String, as well as for readability sake.
	 * <p>
	 * By default, a mod can simply return {@linkplain #getName}
	 * </p>
	 *
	 * @see MinecraftObject#getID()
	 */
	@Override
	String toString();

}