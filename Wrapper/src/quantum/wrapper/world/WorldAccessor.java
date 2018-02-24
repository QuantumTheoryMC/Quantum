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
 * Created file on 7/22/16 at 2:43 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.world;

import net.minecraft.util.BlockPos;
import quantum.api.block.Block;
import quantum.api.world.BlockEntity;
import quantum.api.world.World;
import quantum.api.world.chunk.Chunk;
import quantum.wrapper.minecraft.block.BlockAdapter;
import quantum.wrapper.minecraft.tileentity.TileEntityAdapter;
import quantum.wrapper.minecraft.world.chunk.ChunkAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author link
 */
public final class WorldAccessor implements World {

	private net.minecraft.world.World world;

	public WorldAccessor(net.minecraft.world.World world) {
		this.world = world;
	}

	public net.minecraft.world.World getWorld() {
		return world;
	}

	public void setWorld(net.minecraft.world.World world) {
		this.world = world;
	}

	@Override
	public String getName() {
		return world.getWorldInfo().getWorldName();
	}

	@Override
	public void setBlock(int x, int y, int z, Block block) {
		world.setBlockState(new BlockPos(x, y, z), BlockAdapter.get(block).getDefaultState());
	}

	@Override
	public void setBlocks(int startX, int endX, int startY, int endY, int startZ, int endZ, List<Block> blocks) {
		// index
		int i = 0;
		// overwrites any blocks
		for (int z = startZ; z <= endZ; z++) {
			for (int y = startY; y <= endY; y++) {
				for (int x = startX; x < endX; x++) {
					setBlock(x, y, z, blocks.get(i));
					i++;
				}
			}
		}
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		return BlockAdapter.get(world.getBlockState(new BlockPos(x, y, z)).getBlock());
	}

	@Override
	public List<Block> getBlocks(int startX, int endX, int startY, int endY, int startZ, int endZ) {
		List<Block> blocks = new ArrayList<>();

		for (int z = startZ; z < endZ; z++) {
			for (int y = startY; y < endY; y++) {
				for (int x = startX; x < endX; x++) {
					blocks.add(getBlock(x, y, z));
				}
			}
		}

		return blocks;
	}

	@Override
	public void setBlockEntity(int x, int y, int z, BlockEntity blockEntity) {
		world.setTileEntity(new BlockPos(x, y, z), new TileEntityAdapter(blockEntity));
	}

	@Override
	public void setBlockEntities(int startX, int endX, int startY, int endY, int startZ, int endZ, List<BlockEntity> blocks) {
		// index
		int i = 0;
		// overwrites any blocks
		for (int z = startZ; z <= endZ; z++) {
			for (int y = startY; y <= endY; y++) {
				for (int x = startX; x < endX; x++) {
					setBlockEntity(x, y, z, blocks.get(i));
					i++;
				}
			}
		}

	}

	@Override
	public BlockEntity getBlockEntity(int x, int y, int z) {
		return new BlockEntityAccessor(world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public List<BlockEntity> getBlockEntities(int startX, int endX, int startY, int endY, int startZ, int endZ) {
		List<BlockEntity> blocks = new ArrayList<>(Math.abs((endX - startX) * (endY - startY) * (endZ - startZ)));

		for (int z = startZ; z <= endZ; z++) {
			for (int y = startY; y <= endY; y++) {
				for (int x = startX; x < endX; x++) {
					blocks.add(getBlockEntity(x, y, z));
				}
			}
		}

		return blocks;
	}

	@Override
	public void setChunk(int x, int y, int z, Chunk chunk) {
		BlockPos pos = new BlockPos(x, y, z);
		world.getChunkFromBlockCoords(pos).addTileEntity(pos, new TileEntityAdapter(chunk.getBlockEntity(x, y, z)));
	}

	@Override
	public void setChunks(int startX, int endX, int startY, int endY, int startZ, int endZ, List<Chunk> chunks) {
		// index
		int i = 0;
		// overwrites any chunks
		for (int z = startZ; z < endZ; z++) {
			for (int y = startY; y < endY; y++) {
				for (int x = startX; x < endX; x++) {
					setChunk(x, y, z, chunks.get(i));
					i++;
				}
			}
		}
	}

	@Override
	public Chunk getChunk(int x, int y, int z) {
		return new ChunkAccessor(world.getChunkFromBlockCoords(new BlockPos(x, y, z)));
	}

	@Override
	public List<Chunk> getChunks(int startX, int endX, int startY, int endY, int startZ, int endZ) {
		List<Chunk> chunks = new ArrayList<>(Math.abs((endX - startX) * (endY - startY) * (endZ - startZ)));
		// overwrites any chunks
		for (int z = startZ; z < endZ; z++) {
			for (int y = startY; y < endY; y++) {
				for (int x = startX; x < endX; x++) {
					chunks.add(getChunk(x, y, z));
				}
			}
		}
		return chunks;
	}

}
