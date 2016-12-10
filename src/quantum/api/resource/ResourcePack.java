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
 * Created file on 11/23/16 at 4:10 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.resource;

/**
 * Represents a resource pack.
 * <p>
 * A ResourcePack contains a list of named resources which contain varying kinds
 * of objects such as textures, settings, files, references, and more.
 * </p>
 *
 * @author link
 */
public interface ResourcePack {

	static ResourcePack getDefault() {
		return DefaultResourcePack.SINGLETON;
	}

	/**
	 * Retrieves the resource with the given name from this ResourcePack.
	 *
	 * @param name
	 * 		the name of the resource
	 *
	 * @return the resource, or null if a resource with the given name does not
	 * exist
	 */
	Resource get(String name);

	/**
	 * Gets the resolution of this ResourcePack which is the resolution of the
	 * textures for Blocks and Items. The returned value is the width and height
	 * of each texture.
	 *
	 * @return the resolution of Blocks and Items
	 */
	short getResolution();

}
