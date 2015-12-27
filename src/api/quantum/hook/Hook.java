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
package api.quantum.hook;

import api.quantum.meta.Untested;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <R>
 * 		the return type defined by the Return object of run
 * @author link
 */
@Untested
public interface Hook<R> {

	Map<Method, ArrayList<Hook<?>>> HOOKS = new HashMap<>();

	static void addHook(int hookID, Method method, Hook<?> hook) {
		List<Hook<?>> hookList = HOOKS.getOrDefault(method, new ArrayList<>());
		// create new list if needed
		if (hookList == null) {
			HOOKS.put(method, new ArrayList<>(3));
			hookList = HOOKS.get(method);
		}
		hookList.add(hookID, hook);
	}

	static Hook getHook(int hookID, Method method) {
		List<Hook<?>> hookList = HOOKS.getOrDefault(method, new ArrayList<>());
		// create new list if needed
		if (hookList == null) {
			HOOKS.put(method, new ArrayList<>(3));
			hookList = HOOKS.get(method);
		}

		return hookList.get(hookID);
	}

	/**
	 * Runs this Hook. Meant to be run by the API when used in minecraft
	 * methods. It is also meant for use by mods for extensibility.
	 *
	 * @param params
	 * @return
	 */
	Return<R> run(Parameters params);

	/**
	 * The Method this Hook is inserted into.
	 *
	 * @return the method this Hook is inserted into
	 */
	Method getMethod();

}
