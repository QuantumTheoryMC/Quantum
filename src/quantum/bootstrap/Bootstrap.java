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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import quantum.Quantum;
import quantum.wrapper.bootstrap.CMMinecraft;
import quantum.wrapper.bootstrap.CMTextureMap;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author link
 */
public enum Bootstrap {
	;

	public static void premain(String args, Instrumentation ins) throws ClassNotFoundException {
		System.out.println("[Bootstrap] Quantum API Java Agent initialized...");
		System.out.println("[Bootstrap] Modifying classes...");
		Map<String, ClassModifier> modifiers = new HashMap<>(2);

		modifiers.put(Minecraft.class.getName(), new CMMinecraft());
		modifiers.put(TextureMap.class.getName(), new CMTextureMap());
		//for Quantum Devel
		//modifiers.put("beq", new CMMinecraft());
		//modifiers.put("byv", new CMTextureMap());

		BootstrapTransformer transformer = new BootstrapTransformer(modifiers);

		ins.addTransformer(transformer, true);
		System.out.println("[Bootstrap] Done");
		// add mod jar files to ClassLoader search
		System.out.println("[Bootstrap] Initializing Quantum...");
		Quantum.main();
		System.out.println("[Bootstrap] Successfully initialized Quantum");


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
		vm.loadAgent(Paths.get(Quantum.getMinecraftDir() + "/quantum/quantum_agent.jar")
		                  .toString(), "");
		vm.detach();
	}

}
