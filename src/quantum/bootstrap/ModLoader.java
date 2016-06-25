package quantum.bootstrap;

import quantum.mod.Mod;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 *
 * @author link
 */
enum ModLoader {
	;

	public static void load(Instrumentation instrumentation, String jarFile) throws IOException {
		instrumentation.appendToSystemClassLoaderSearch(new JarFile(new File(jarFile), true));
	}

	public static void load(Instrumentation ins, Path directory) throws IOException {
		File[] jars = directory.toFile().listFiles((file) -> {
			if (file.isFile()) {
				boolean safe = false;
				if (file.getName().endsWith(".jar")) {
					try (JarFile jar = new JarFile(file, true)) {

						// security check the jar
						final Attributes attrs = jar.getManifest().getMainAttributes();

						// check that there is a Main-Class (see API spec: mod loaded through main)
						// we don't want to load Forge mods yet :)
						safe = !(attrs.get("Main-Class") == null) && attrs.get("Tweak-Class") == null;
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				}
				return safe;
			}
			return false;
		});
		if (jars != null)
			for (File jar : jars) {
				System.out.println(jar);
				load(ins, jar.getAbsolutePath());
			}
	}

	public static void unload(Instrumentation ins, Mod mod) {
		// TODO
	}

}
