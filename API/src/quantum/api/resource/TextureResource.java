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
 * Created file on 11/23/16 at 5:01 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.resource;

import quantum.api.mod.Mod;
import quantum.api.resource.loader.TextureLoader;

/**
 * A TextureResource is a resource that represents a physical texture. It may be
 * stored on disc or in memory. The actual {@link TextureLoader} determines how
 * a Texture is to be loaded by a resource.
 *
 * @author link
 */
public class TextureResource extends AbstractResource {

	/**
	 * The width and height of this TextureResource
	 */
	protected final int width, height;

	/**
	 * Creates a new TextureResource with the given width and height.
	 *
	 * @param width
	 * 		the width of the physical texture
	 * @param height
	 * 		the height of the physical texture
	 * @param mod
	 * 		the mod that this texture belongs to
	 * @param path
	 * 		the path of the physical texture
	 */
	public TextureResource(int width, int height, Mod mod, String path) {
		super(mod, path);
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the width of the texture represented by this TextureResource.
	 *
	 * @return the width of the physical texture
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the texture represented by this TextureResource.
	 *
	 * @return the height of the physical texture
	 */
	public final int getHeight() {
		return height;
	}

}
