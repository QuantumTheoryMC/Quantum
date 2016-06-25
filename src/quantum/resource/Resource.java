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
package quantum.resource;

import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.WeakHashMap;

/**
 * <p>
 * A Resource is an object that can be loaded from the disk and used in
 * Minecraft. This could be an image or a model, or any resource.
 * </p>
 *
 * @param <T>
 * 		the type that this Resource represents
 * @author link
 */
public class Resource<T> {

	/**
	 * The list of resources. Over time these can be GC'd.
	 */
	private static final WeakHashMap<String, Resource<?>> RESOURCES = new WeakHashMap<>();

	/**
	 * The actual ResourceLocation instance.
	 */
	private final ResourceLocation resource;
	/**
	 * The file this Resource represents.
	 */
	private final File file;
	/**
	 * What the type of object is that this Resource represents.
	 */
	private final Class<T> type;

	/**
	 * <p>
	 * Creates a new Resource with the specified type and name.
	 *
	 * @param type
	 * 		the type of object this Resource represents
	 * @param file
	 * 		the actual file resource, or null if this Resource doesn't
	 * 		have a file.
	 * @param name
	 * 		the name of this resource
	 */
	public Resource(Class<T> type, File file, String name) {
		this.type = type;
		this.file = file;
		resource = new ResourceLocation(name);

	}

	/**
	 * <p>
	 * Creates a new Resource with the specified type, mod, and name.
	 *
	 * @param type
	 * 		the type of object this Resource represents
	 * @param file
	 * 		the actual file resource, or null if this Resource doesn't
	 * 		have a file.
	 * @param mod
	 * 		the mod this Resource belongs to
	 * @param name
	 * 		the name of this Resource
	 */
	public Resource(Class<T> type, File file, String mod, String name) {
		this.type = type;
		this.file = file;
		resource = new ResourceLocation(mod, name);
	}

	public static void addResource(Resource<?> resource) {
		RESOURCES.putIfAbsent(resource.getNamespace() + resource.getName(), resource);
	}

	public static Resource<?> getResource(String mod, String name) {
		return RESOURCES.get(mod + name);
	}

	public String getNamespace() {
		return resource.getResourceDomain();
	}

	public String getName() {
		return resource.getResourcePath();
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return resource.toString();
	}

	@Override
	public int hashCode() {
		return resource.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Resource) {
			return ((Resource<?>) object).resource.equals(resource);
		}
		return false;
	}
}
