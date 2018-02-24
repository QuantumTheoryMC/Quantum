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
 * Created file on 3/21/16 at 1:31 PM.
 *
 * This file is part of Quantum API
 */
package quantum.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import quantum.agent.cm.CMLocale;
import quantum.agent.cm.CMMinecraft;
import quantum.agent.cm.CMModelBakery;
import quantum.agent.cm.CMTextureMap;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * @author link
 */
public enum Bootstrap {
	;

	private static final String QUANTUM = Paths.get(System.getProperty("user.home"), System.getProperty("os.name").contains("windows") ? "AppData\\Roaming\\.minecraft" : ".minecraft", "quantum").toString();

	public static void premain(String args, Instrumentation ins) throws ClassNotFoundException {
		System.out.println("[Bootstrap] Quantum API Java Agent initialized...");
		System.out.println("[Bootstrap] Registering class transformers...");
		Map<String, ClassModifier> modifiers = new HashMap<>(4);

		modifiers.put("net/minecraft/client/resources/Locale", new CMLocale());
		modifiers.put("net/minecraft/client/renderer/texture/TextureMap", new CMTextureMap());
		modifiers.put("net/minecraft/client/Minecraft", new CMMinecraft());
		modifiers.put("net/minecraft/client/resources/model/ModelBakery", new CMModelBakery());

		//modifiers.put("bsu", new CMMinecraft());
		//modifiers.put("cua", new CMTextureMap());
		//modifiers.put("cwf", new CMLocale());
		//modifiers.put("cxh", new CMModelBakery());

		ClassFileTransformer transformer = new BootstrapTransformer(modifiers);

		ins.addTransformer(transformer, true);

		// Inject Quantum into minecraft class path
		Path current = null;
		try {
			// security hack:
			// We add the quantum_util jar file in order to use Quantum.Bridge properly.
			// The Bridge implementation has a security check that checks the caller
			// class with Reflection.getCallerClass() (tagged @CallerSensitive), but
			// @CallerSensitive can only be used with classes loaded by the bootstrap
			// ClassLoader.
			ins.appendToBootstrapClassLoaderSearch(new JarFile((current = Paths.get(QUANTUM, "quantum_util.jar")).toFile(), true));
			ins.appendToSystemClassLoaderSearch(new JarFile((current = Paths.get(QUANTUM, "quantum_api.jar")).toFile(), true));
			ins.appendToSystemClassLoaderSearch(new JarFile((current = Paths.get(QUANTUM, "quantum_wrapper.jar")).toFile(), true));
		} catch (IOException e) {
			throw new RuntimeException("[Bootstrap] Missing dependency \"" + current.getFileName() + "\"\n[Bootstrap] Quantum will not be initialized", e);
		}
		System.out.println("[Bootstrap] Initializing Minecraft...");
	}

	public static void agentmain(String args, Instrumentation ins) throws ClassNotFoundException {
		premain(args, ins);
	}

	//might be used later on
	public static void reloadAgent() throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm;
		String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		vm = VirtualMachine.attach(pid);
		vm.loadAgent(Paths.get(".", "quantum_agent.jar").toString(), "");
		vm.detach();
	}

}
