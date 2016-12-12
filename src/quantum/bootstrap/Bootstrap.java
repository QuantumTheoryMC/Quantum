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
package quantum.bootstrap;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import quantum.bootstrap.cm.CMLocale;
import quantum.bootstrap.cm.CMMinecraft;
import quantum.bootstrap.cm.CMTextureMap;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * @author link
 */
public enum Bootstrap {
	;

	public static void premain(String args, Instrumentation ins) throws ClassNotFoundException {
		System.out.println("[Bootstrap] Quantum API Java Agent initialized...");
		System.out.println("[Bootstrap] Registering class transformers...");
		Map<String, ClassModifier> modifiers = new HashMap<>(3);

		modifiers.put("net/minecraft/client/resources/Locale", new CMLocale());
		modifiers.put("net/minecraft/client/renderer/texture/TextureMap", new CMTextureMap());
		modifiers.put("net/minecraft/client/Minecraft", new CMMinecraft());

		//for Quantum Source
		//modifiers.put("beq", new CMMinecraft());
		//modifiers.put("byv", new CMTextureMap());

		BootstrapTransformer transformer = new BootstrapTransformer(modifiers);

		ins.addTransformer(transformer, true);
		// Inject Quantum into minecraft class path
		try {
			ins.appendToSystemClassLoaderSearch(new JarFile(Paths.get("..", "Quantum API", "quantum.jar")
			                                                     .toFile(), true));
			ins.appendToSystemClassLoaderSearch(new JarFile(Paths.get("..", "Quantum API", "quantum_api.jar")
			                                                     .toFile(), true));
			ins.appendToSystemClassLoaderSearch(new JarFile(Paths.get("..", "Quantum API", "quantum_wrapper.jar")
			                                                     .toFile(), true));
		} catch (IOException e) {
			System.err.println("[Bootstrap] Missing API jar file or dependencies; Quantum will not be initialized");
		}

		System.out.println("[Bootstrap] Initializing Minecraft...");
	}

	public static void agentmain(String args, Instrumentation ins) throws ClassNotFoundException {
		premain(args, ins);
	}

	//might be used later on
	public static void loadAgent() throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm;
		String pid = ManagementFactory.getRuntimeMXBean()
		                              .getName()
		                              .split("@")[0];
		vm = VirtualMachine.attach(pid);
		vm.loadAgent(Paths.get("..", "Quantum API", "quantum_agent.jar")
		                  .toString(), "");
		vm.detach();
	}

}
