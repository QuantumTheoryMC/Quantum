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
 * Created file on 6/28/16 at 8:19 PM.
 *
 * This file is part of Quantum API
 */
package quantum.model;

import quantum.api.resource.Resource;
import quantum.model.texture.Texture;

/**
 * Represents a Model. This interface only provides the dimensions of a Model.
 * The internal data format is determined by the implementations.
 *
 * @author link
 */
public interface Model {

	/**
	 * Gets the width of this Model.
	 *
	 * @return the width of this Model
	 */
	double getWidth();

	/**
	 * Gets the height of this Model.
	 *
	 * @return the height of this Model
	 */
	double getHeight();

	/**
	 * Gets the depth of this Model.
	 *
	 * @return the depth of this Model
	 */
	double getDepth();

	/**
	 * Gets the {@link Color} of this Model as viewed from an aerial view (used
	 * for
	 * maps).
	 *
	 * @return the color of this Model as viewed from a map
	 */
	Color getColor();

	/**
	 * Gets the Textures of this Model
	 *
	 * @return the Textures of this Model
	 */
	Texture[] getTextures();

	/**
	 * Gets the resource associated with this Model.
	 *
	 * @return the resource associated with this Model
	 */
	Resource getResource();

	/**
	 * Represents a color used on the map. Unfortunately, the colors are
	 * limited, since Minecraft is idiotic, and made MapColor's {@code <init>}
	 * private. Blame Mojang&trade;. The last thing I'm going to do is use
	 * reflection to create new MapColor instances so you can have a custom
	 * color for your object show up on the map.
	 * <p>
	 * If using the official Optimizations Extension (TODO), you may define your
	 * own color using a different system.
	 * </p>
	 */
	enum Color {
		RED, BROWN, ORANGE, YELLOW, GREEN, BLUE, LIGHT_BLUE, INDIGO, VIOLET, MAGENTA, BLACK, WHITE
	}

}
