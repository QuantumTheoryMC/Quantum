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
 * Created file on 11/23/16 at 4:18 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author link
 */
public abstract class AbstractResourcePack implements ResourcePack {

	protected final Map<String, Resource> resources;

	protected AbstractResourcePack() {
		resources = new HashMap<>();
	}

	protected AbstractResourcePack(Map<String, Resource> resources) {
		this.resources = resources;
	}

	/**
	 * Adds the given resource to this AbstractResourcePack
	 *
	 * @param name
	 * 		the name of the resource
	 * @param resource
	 * 		the resource to store
	 */
	protected final void add(String name, Resource resource) {
		resources.putIfAbsent(name, resource);
	}

	/**
	 * Replaces the resource at the given name.
	 *
	 * @param name
	 * 		the name of the resource
	 * @param resource
	 * 		the resource to store
	 */
	protected final void set(String name, Resource resource) {
		resources.put(name, resource);
	}

	/**
	 * Removes the resource at the given name.
	 *
	 * @param name
	 * 		the name of the resource
	 */
	protected final void remove(String name) {
		resources.remove(name);
	}

	@Override
	public final Resource get(String name) {
		return resources.getOrDefault(name, DefaultResourcePack.SINGLETON.get(name));
	}

}
