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
 * Created file on 7/2/16 at 8:14 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.world;

import net.minecraft.client.Minecraft;
import quantum.api.block.Block;
import quantum.api.world.chunk.Chunk;
import quantum.wrapper.world.WorldAccessor;

import java.util.List;

/**
 * @author link
 */
public interface World {

	enum Vanilla {
		;
		static World current = new WorldAccessor(Minecraft.getMinecraft().theWorld);
	}

	static World getCurrentWorld() {
		//TODO
		return Vanilla.current;
	}

	String getName();

	void setBlockEntity(int x, int y, int z, BlockEntity block);

	void setBlockEntities(int startX, int endX, int startY, int endY, int startZ, int endZ, List<BlockEntity> blocks);

	BlockEntity getBlockEntity(int x, int y, int z);

	List<BlockEntity> getBlockEntities(int startX, int endX, int startY, int endY, int startZ, int endZ);


	Block getBlock(int x, int y, int z);

	void setBlock(int x, int y, int z, Block block);

	List<Block> getBlocks(int startX, int endX, int startY, int endY, int startZ, int endZ);

	void setBlocks(int startX, int endX, int startY, int endY, int startZ, int endZ, List<Block> blocks);


	void setChunk(int x, int y, int z, Chunk chunk);

	void setChunks(int startX, int endX, int startY, int endY, int startZ, int endZ, List<Chunk> chunks);

	Chunk getChunk(int x, int y, int z);

	List<Chunk> getChunks(int startX, int endX, int startY, int endY, int startZ, int endZ);

}
