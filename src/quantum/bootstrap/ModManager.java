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
 * Created file on 7/20/16 at 5:02 PM.
 *
 * This file is part of Quantum API
 */
package quantum.bootstrap;

import quantum.Quantum;
import quantum.mod.Mod;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author link
 */
public class ModManager {


	// the class loaders for the mod jar files.
	// in order to unload a mod, the URLClassLoader
	// must be collected by GC.
	private Map<Mod, URLClassLoader> mods;
	private Map<String, Mod>         modNameMap;

	public ModManager() {
		mods = new IdentityHashMap<>();
		modNameMap = new ConcurrentHashMap<>();
	}

	public void loadAll(Path path, Quantum quantum) throws IOException, IllegalAccessException, ClassNotFoundException {
		List<ModLoader.ModManifest> mods = ModLoader.load(path);
		System.out.println("[ModManager]");
		for (ModLoader.ModManifest manifest : mods) {
			Mod mod = manifest.getMod();
			URLClassLoader cl = manifest.getClassLoader();
			try {
				mod.load(quantum);
			} catch (Throwable e) {
				System.err.println("[Quantum][Error] An error was thrown while loading mod \"" + mod + "\":");
				e.printStackTrace();
			}
			this.mods.putIfAbsent(mod, cl);
			modNameMap.putIfAbsent(mod.getName(), mod);
		}
	}

	public void load(String mod, String jar, Quantum quantum) throws IOException, ClassNotFoundException, IllegalAccessException {
		Mod m = ModLoader.load(jar).getMod();
		try {
			m.load(quantum);
		} catch (Throwable e) {
			System.err.println("[Quantum][Error] An error was thrown while loading mod \"" + mod + "\":");
			e.printStackTrace();
		}
	}

	public void unload(String mod, Quantum quantum) {
		Mod m;
		mods.remove(m = modNameMap.get(mod));
		m.unload(quantum);
	}

	public void unload(Mod mod, Quantum quantum) throws IOException {
		unload(mod.getName(), quantum);
	}

	public void unloadAll(Quantum quantum) {
		modNameMap.forEach((name, mod) -> {
			try {
				unload(mod, quantum);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		mods = new IdentityHashMap<>();
		modNameMap = new ConcurrentHashMap<>();
		System.gc();
	}

	public void abort(Mod mod, Quantum quantum) {
		unload(mod.getName(), quantum);
		modNameMap.remove(mod.getName());
		System.gc();
	}

}
