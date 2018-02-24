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
 * Created file on 11/6/16 at 4:11 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.world.chunk;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import quantum.api.block.Blocks;
import quantum.api.world.BlockEntity;
import quantum.wrapper.minecraft.tileentity.TileEntityAdapter;

/**
 * @author link
 */
public class ChunkAdapter extends Chunk {

	private byte size;

	public ChunkAdapter(quantum.api.world.chunk.Chunk chunk) {
		super(Minecraft.getMinecraft().theWorld, chunk.getX(), chunk.getZ());
		BlockPos pos;
		for (int z = 0; z < chunk.getDepth(); z++) {
			for (int y = 0; y < chunk.getHeight(); y++) {
				for (int x = 0; x < chunk.getWidth(); x++) {
					pos = new BlockPos(x, y, z);
					BlockEntity blockEntity = chunk.getBlockEntity(x, y, z);
					addTileEntity(pos, new TileEntityAdapter(blockEntity));
					// increment size if not air
					size += blockEntity.getBlock() != Blocks.AIR ? 1 : 0;
				}
			}
		}
	}

}
