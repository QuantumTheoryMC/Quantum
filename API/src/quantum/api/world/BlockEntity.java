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
 * Created file on 7/2/16 at 8:10 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.world;

import quantum.api.block.Block;
import quantum.api.block.state.State;

/**
 * A BlockEntity represents a real {@link Block} in a World. The deobfuscated,
 * crowd-sourced named analog is net.minecraft.tileentity.TileEntity.
 * However, after reviewing the sourcecode myself (linktheprogrammer), the
 * developers (Mojang, specifically Markus Persson) actually named it (rightly
 * so) <i>BlockEntity</i>, not <i>TileEntity</i>. The name just stuck. I
 * decided
 * against that and call it by it's real name which is of course {@linkplain
 * BlockEntity ~}.
 * <p>
 * As mentioned above, a BlockEntity represents a Block in the World. For
 * memory
 * efficiency, each block in a chunk in the world is represented by its unique
 * block ID. BlockEntity objects are created on the fly, lazily. They are
 * instantiated whenever you need specific information about a block beyond
 * just its coordinates, or when you are passing information. When they are
 * fully dereferenced (all references to the BlockEntity are null), the rest is
 * up to GC.
 * </p>
 *
 * @author link
 */
public interface BlockEntity {

	/**
	 * The {@linkplain #getLocation() location} indexes for x, y, and z
	 */
	int X = 0, Y = 1, Z = 2;

	/**
	 * Gets the x coordinate for this BlockEntity.
	 *
	 * @return the x coordinate for this BlockEntity
	 */
	double getX();

	/**
	 * Sets the x coordinate for this BlockEntity.
	 *
	 * @param x
	 * 		the x coordinate
	 */
	void setX(double x);

	/**
	 * Gets the y coordinate for this BlockEntity.
	 *
	 * @return the y coordinate for this BlockEntity
	 */
	double getY();

	/**
	 * Sets the y coordinate for this BlockEntity.
	 *
	 * @param y
	 * 		the y coordinate
	 */
	void setY(double y);

	/**
	 * Gets the z coordinate for this BlockEntity.
	 *
	 * @return the z coordinate for this BlockEntity
	 */
	double getZ();

	/**
	 * Sets the z coordinate for this BlockEntity.
	 *
	 * @param z
	 * 		the z coordinate
	 */
	void setZ(double z);

	/**
	 * By default, returns the result of getX(), getY(), and getZ() as an array.
	 *
	 * @return the result of getX(), getY(), and getZ()
	 */
	default double[] getLocation() {
		return new double[]{getX(), getY(), getZ()};
	}

	/**
	 * By default, calls setX(x), setY(y), and setZ(z), respectively.
	 *
	 * @param x
	 * 		the x coordinate
	 * @param y
	 * 		the y coordinate
	 * @param z
	 * 		the z coordinate
	 */
	default void setLocation(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Gets the block that this BlockEntity represents.
	 *
	 * @return the block that this BlockEntity represents
	 */
	Block getBlock();

	/**
	 * Gets the current state of this BlockEntity.
	 *
	 * @return the current state of this BlockEntity
	 */
	State getState();

}
