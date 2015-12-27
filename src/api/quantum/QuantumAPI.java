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
import api.quantum.meta.WIP;

import java.util.List;
import java.util.Map;

/**
 * @author link
 */
@WIP(description = "A lot of missing runtime information",
     unfinished = {"QuantumAPI.info"})
public class QuantumAPI {

	private static final QuantumAPI info = ModLoader.createAPI();
	private final String apiVersion, mcVersion;
	private final Hook<?>[] mainHooks;
	private final Map<String, List<Hook<?>>> methodHooks;

	@WIP
	QuantumAPI(String apiVersion, String minecraftVersion, Hook<?>[] mainHooks,
	           Map<String, List<Hook<?>>> methodHooks) {
		synchronized (info) {
			this.apiVersion = apiVersion;
			this.mcVersion = minecraftVersion;
			this.mainHooks = mainHooks;
			this.methodHooks = methodHooks;
		}
	}

	public static String getAPIVersion() {
		return info.apiVersion;
	}

	public static String getMinecraftVersion() {
		return info.mcVersion;
	}

	public static Hook<?>[] getMainMethodHooks() {
		return info.mainHooks;
	}

	public static Map<String, List<Hook<?>>> getMethodHooks() {
		return info.methodHooks;
	}

}
