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
 * Created file on 7/20/16 at 9:10 AM.
 *
 * This file is part of Quantum API
 */
package quantum.api.world.chunk;

import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.world.BlockEntity;

/**
 * @author link
 */
public interface Chunk {


	int getX();

	int getZ();

	int getY();

	byte getWidth();

	byte getHeight();

	byte getDepth();

	/**
	 * Gets the number of blocks in this Chunk that are not Air blocks.
	 *
	 * @return the number of non-air blocks in this Chunk
	 */
	short getSize();


	BlockEntity getBlockEntity(int x, int y, int z);

	void setBlockEntity(int x, int y, int z, BlockEntity block);


	Block getBlock(int x, int y, int z);

	void setBlock(int x, int y, int z, Block block);


	State getState(int x, int y, int z);

	void setState(int x, int y, int z, State state);


}
