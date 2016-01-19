/*
 * The MIT License
 *
 * Copyright 2015 link.
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
 */
package api.quantum;

import api.quantum.hook.Hook;
import api.quantum.log.Logger;
import api.quantum.meta.WIP;
import api.quantum.mod.QuantumMod;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author link
 */
@WIP(description = "Not working yet.",
     unfinished = {"mod loading"})
enum ModLoader {
	;

	public static void premain(String args, Instrumentation inst) throws ClassNotFoundException, MalformedURLException {
		Logger.getLogger().log(ModLoader.class, "Quantum API Mod Loader as Java Agent successfully initialized");
		inst.addTransformer(new UniversalAgent(net.minecraft.client.main.Main.class, new MCMainClassModifier()));
	}

	private static JarFile loadModule(String pathName) throws IOException {
		// the class loader for the mod
		ClassLoader loader = new URLClassLoader(new URL[]{new File(pathName).toURI().toURL()}, ModLoader.class.getClassLoader());
		// the jar of the mod
		return new JarFile(new File(pathName), false);

	}
	/**
	 * Loads a mod from a java module.
	 *
	 * @param pathName
	 * 		the mod to load
	 * @return the loaded mod
	 * @throws IOException
	 * 		If for some reason the mod file(s) could not be read.
	 */
	static QuantumMod load(String pathName) throws IOException {
		// the class loader for the mod
		ClassLoader loader = new URLClassLoader(new URL[]{new File(pathName).toURI().toURL()}, ModLoader.class.getClassLoader());
		// the jar of the mod
		JarFile jar = new JarFile(new File(pathName), false);
		// the manifest of the jar
		Manifest manifest = jar.getManifest();
		// the names of classes mapped to "Name" attributes
		Map<Object, Object> classes = manifest.getAttributes("Name");
		Logger.getSystemLogger().log(ModLoader.class, "Loaded java module \"" + pathName + "\"");
		// load all the classes from the java module
		classes.forEach((attributeName, attributeValue) -> {
			assert attributeValue instanceof String : "Not an Attributes object, or class Attributes was refactored.";
			try {
				loader.loadClass(((String) attributeName).replace('/', '.'));
			} catch (ClassNotFoundException e) {
				ClassFormatError clFormatErr = new ClassFormatError("This JarFile is corrupted or improperly formatted. Please report this error to http://github.com/QuantumTheoryMC/QuantumAPI");
				Logger.getSystemLogger().log(ModLoader.class, e, Logger.Severity.ERROR, Logger.Severity.Context.INTERNAL, Logger.Severity.Level.FATAL, "Failed to load class \"" + attributeName + "\"");
			} finally {
				try {
					((Closeable) loader).close();
					jar.close();
				} catch (IOException e) {
					//
				}
			}
		});
		return null;
	}

	static List<QuantumMod> loadAll(String folder) {
		//TODO
		return Collections.emptyList();
	}

	static void unload(QuantumAPI api, QuantumMod mod) {
		mod.stop();
		// unload the mod
		// api.removeMod(mod);
		// System.gc();
		// TODO
	}

	static QuantumAPI createAPI() {
		List<Hook<?>> mains = new ArrayList<>(1);
		mains.add(Hook.EMPTY);
		return new QuantumAPI("1a", "1.8", mains, new HashMap<>(1));
	}

	static class LoadResult {

		private final QuantumMod mod;
		private final ClassLoader modClassLoader;

		LoadResult(QuantumMod mod, ClassLoader modClassLoader) {
			this.mod = mod;
			this.modClassLoader = modClassLoader;
		}

		QuantumMod getMod() {
			return mod;
		}

		ClassLoader getModClassLoader() {
			return modClassLoader;
		}
	}
}
