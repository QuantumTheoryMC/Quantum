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
 * Created file on 11/6/16 at 7:28 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.world.chunk;

import net.minecraft.util.BlockPos;
import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.block.state.States;
import quantum.api.world.BlockEntity;
import quantum.api.world.chunk.Chunk;
import quantum.wrapper.minecraft.block.BlockAdapter;
import quantum.wrapper.minecraft.block.BlockStates;
import quantum.wrapper.minecraft.block.StateAccessor;
import quantum.wrapper.minecraft.tileentity.TileEntityAdapter;
import quantum.wrapper.world.BlockEntityAccessor;

/**
 * @author link
 */
public class ChunkAccessor implements Chunk {

	private final net.minecraft.world.chunk.Chunk chunk;

	public ChunkAccessor(net.minecraft.world.chunk.Chunk chunk) {
		this.chunk = chunk;
	}


	@Override
	public int getX() {
		return chunk.xPosition;
	}

	@Override
	public int getZ() {
		return chunk.zPosition;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public byte getWidth() {
		return 16;
	}

	@Override
	public byte getHeight() {
		return (byte) 256;
	}

	@Override
	public byte getDepth() {
		return (byte) 16;
	}

	@Override
	public short getSize() {
		return (short) (16 * 16 * 256);
	}

	@Override
	public BlockEntity getBlockEntity(int x, int y, int z) {
		return new BlockEntityAccessor(chunk.getWorld().getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public void setBlockEntity(int x, int y, int z, BlockEntity block) {
		chunk.addTileEntity(new BlockPos(x, y, z), new TileEntityAdapter(block));
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		return BlockAdapter.get(chunk.getBlockState(new BlockPos(x, y, z)).getBlock());
	}

	@Override
	public void setBlock(int x, int y, int z, Block block) {
		chunk.setBlockState(new BlockPos(x, y, z), BlockStates.get(States.get(block)));
	}

	@Override
	public State getState(int x, int y, int z) {
		return new StateAccessor(chunk.getBlockState(new BlockPos(x, y, z)));
	}

	@Override
	public void setState(int x, int y, int z, State state) {
		chunk.setBlockState(new BlockPos(x, y, z), BlockStates.get(state));
	}

}
