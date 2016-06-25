package quantum.mod;

/**
 * This is the interface all mod main classes are required to implement in
 * order
 * to be a Quantum mod. Otherwise it is invalidated.
 * <p>
 * All Quantum mods run concurrently, but the API is required to be
 * thread-safe, so mods can simply relax and do their thing and mod devs don't
 * have to worry about the stress of concurrency.
 * \n\n
 * A call from within a mod, as well as any game tick hooks, are run inside
 * the mod thread. The mods are managed via ModManager(not yet implemented), and
 * contained by
 * {@linkplain quantum.Quantum}.
 * </p>
 * <p>
 * What defines a mod as a Quantum mod, is a mod that defines a Main-Class in
 * their manifest. In other words, mods loaded by this API are treated as
 * actual
 * programs. Each mod is placed in their own thread, and execute in their own
 * thread, with the its thread named {@code "quantum.mod." + mod.getName()}.
 * </p>
 * <p>
 * The only exception to this is a Forge mod. Core mods may or may not be
 * compatible.
 * </p>
 * <p>
 * The {@linkplain #load}() and {@linkplain #unload}() methods wil have a fixed
 * amount of time to run (60,000ms), so if a mod fails to load or unload within
 * the given
 * time, the load/unload is aborted and nothing changes, or any changed state
 * as a result of calling those methods, is restored.
 * \n\n
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
 */
public interface Mod extends Runnable {

	/**
	 * This method is called by the mod loader to allow the mod to optionally
	 * load anything it specifically needs.
	 * <p>
	 * Mods can be loaded and unloaded at runtime, so it is crucial that if a
	 * mod implements load(), it should also implement unload().
	 * </p>
	 */
	void load();

	/**
	 * This method is called by the mod loader to allow the mod to optionally
	 * unload anything it specifically needed.
	 * <p>
	 * Mods can be unloaded and loaded at runtime, so it is crucial that if a
	 * mod implements unload(), it should also implement load().
	 * </p>
	 * FIXME currently, the mod jar file will still be in memory if a mod is
	 * unloaded
	 */
	void unload();


	/**
	 * Gets the name of this mod. Mods are required to implement this.
	 *
	 * @return the name of this mod
	 */
	String getName();

	/**
	 * Mods are required to implement this method in order to create an
	 * effective debugging String, as well as for readability sake.
	 * <p>
	 * By default, a mod can simply return {@linkplain #getName}
	 * </p>
	 */
	@Override
	String toString();
}