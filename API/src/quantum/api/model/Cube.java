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
 * Created file on 11/26/16 at 7:36 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.model;


import quantum.api.model.texture.Texture;
import quantum.api.resource.Resource;

/**
 * A Cube has 6 sides, each with their own Texture.
 *
 * @author link
 */
public final class Cube implements Model {

	/**
	 * The Model.Color of this Cube
	 */
	private final Color color;

	/**
	 * The sides of the Cube
	 */
	private final Texture top, north, east, south, west, bottom;

	/**
	 * The resource for this Cube, if any
	 */
	private final Resource resource;

	/**
	 * Creates a new cube with all 6 given side texture
	 *
	 * @param top
	 * 		the top texture
	 * @param north
	 * 		the north texture
	 * @param east
	 * 		the east texture
	 * @param south
	 * 		the south texture
	 * @param west
	 * 		the west texture
	 * @param bottom
	 * 		the bottom texture
	 */
	public Cube(Color color, Resource resource, Texture top, Texture north, Texture east, Texture south, Texture west, Texture bottom) {
		this.color = color;
		this.resource = resource;
		this.top = top;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
		this.bottom = bottom;
	}

	/**
	 * Gets the top Texture used for this Cube
	 *
	 * @return the top Texture used for this Cube
	 */
	public Texture getTop() {
		return top;
	}

	/**
	 * Gets the north Texture used for this Cube
	 *
	 * @return the north Texture used for this Cube
	 */
	public Texture getNorth() {
		return north;
	}

	/**
	 * Gets the east Texture used for this Cube
	 *
	 * @return the east Texture used for this Cube
	 */
	public Texture getEast() {
		return east;
	}

	/**
	 * Gets the south Texture used for this Cube
	 *
	 * @return the south Texture used for this Cube
	 */
	public Texture getSouth() {
		return south;
	}

	/**
	 * Gets the west Texture used for this Cube
	 *
	 * @return the west Texture used for this Cube
	 */
	public Texture getWest() {
		return west;
	}

	/**
	 * Gets the bottom Texture used for this Cube
	 *
	 * @return the bottom Texture used for this Cube
	 */
	public Texture getBottom() {
		return bottom;
	}

	/**
	 * Gets the Texture for the given Side.
	 *
	 * @return the Texture for the given Side
	 */
	public Texture getTexture(Face face) {
		switch (face) {
			case TOP:
				return top;
			case BOTTOM:
				return bottom;
			case NORTH:
				return north;
			case EAST:
				return east;
			case SOUTH:
				return south;
			case WEST:
				return west;
			default:
				throw new RuntimeException("[Quantum] Either I forgot to update this method (getTexture(Side)) or \"side\" is null");
		}
	}

	@Override
	public final Texture[] getTextures() {
		return new Texture[]{top, north, east, south, west, bottom};
	}

	@Override
	public Resource getResource() {
		return resource;
	}

	@Override
	public double getWidth() {
		return 1.0d;
	}

	@Override
	public double getHeight() {
		return 1.0d;
	}

	@Override
	public double getDepth() {
		return 1.0d;
	}

	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Represents a given side of the Cube
	 *
	 * @see Cube#getTop()
	 * @see Cube#getNorth()
	 * @see Cube#getEast()
	 * @see Cube#getSouth()
	 * @see Cube#getWest()
	 * @see Cube#getBottom()
	 * @see Cube#getTexture(Face)
	 */
	public enum Face {
		TOP, BOTTOM, NORTH, EAST, SOUTH, WEST
	}
}
