package quantum.agent;

import quantum.api.mod.Mod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * @author link
 */
enum ModLoader {
	;

	public static URLClassLoader load(File jarFile) throws IOException {
		return new URLClassLoader(new URL[]{jarFile.toURI().toURL()});
	}

	public static List<ModManifest> load(Path directory) throws IOException {
		System.out.println("[ModLoader] Loading mods...");
		List<ModManifest> loadedMods = new ArrayList<>(directory.getNameCount());
		File[] jarFiles = directory.toFile().listFiles((file) -> file.isFile() && file.getName().endsWith(".jar"));
		int mods = 0;

		if (jarFiles != null) {
			for (File jarFile : jarFiles) {
				JarFile jar = new JarFile(jarFile, true);
				Attributes attributes = jar.getManifest().getMainAttributes();

				String modClass;

				Mod mod = null;
				URLClassLoader cl = null;
				// TODO print to a logger
				System.out.println("\t[ModLoader] Loading mod: " + attributes.getValue("Name"));
				if ((modClass = attributes.getValue("Mod-Class")) != null && !attributes.containsKey("Main-Class") && !attributes.containsKey("Tweak-Class"))
					try {
						mod = (Mod) (cl = load(jarFile)).loadClass(modClass).newInstance();
						System.out.println("\t\t[ModLoader] Loaded mod class for: " + mod.getName());
						mods++;
					} catch (InstantiationException e) {
						System.err.println("\t\t[ModLoader] Failed to initialize Mod for \"" + jar.getName() + "\": " + e.getLocalizedMessage());
					} catch (IllegalAccessException e) {
						System.err.println("\t\t[ModLoader] The constructor for \"" + modClass + "\" must be public!");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						String modName = attributes.getValue("Name");
						System.err.println("\t\t[ModLoader][mod:" + (modName == null ? "(error)" : modName) + "] Mod class does not exist: " + modClass);
						System.err.println("\t\tThe mod jar file may be corrupt or had been compiled improperly.");
					}

				loadedMods.add(new ModManifest(mod, cl));
			}
		}
		System.out.println("\t[ModLoader] Successfully loaded " + mods + " mod(s)");
		return loadedMods;
	}

	public static ModManifest load(String jar) throws IllegalAccessException, IOException, ClassNotFoundException {
		return load(Paths.get(jar)).get(0);
	}

	static class ModManifest {

		private final Mod            mod;
		private final URLClassLoader cl;

		public ModManifest(final Mod mod, final URLClassLoader cl) {
			this.mod = mod;
			this.cl = cl;
		}

		public Mod getMod() {
			return mod;
		}

		public URLClassLoader getClassLoader() {
			return cl;
		}

	}

}
