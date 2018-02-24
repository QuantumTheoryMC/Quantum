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
 * Created file on 7/22/16 at 3:00 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.world;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.world.BlockEntity;
import quantum.wrapper.minecraft.block.BlockAdapter;
import quantum.wrapper.minecraft.block.StateAccessor;

/**
 * @author link
 */
public final class BlockEntityAccessor implements BlockEntity {


	private final TileEntity blockEntity;

	public BlockEntityAccessor(TileEntity tileEntity) {
		this.blockEntity = tileEntity;
	}

	@Override
	public double getX() {
		return blockEntity.getPos().getX();
	}

	@Override
	public void setX(double x) {
		blockEntity.setPos(new BlockPos(x, getY(), getZ()));
	}

	@Override
	public double getY() {
		return blockEntity.getPos().getY();
	}

	@Override
	public void setY(double y) {
		blockEntity.setPos(new BlockPos(getX(), y, getZ()));
	}

	@Override
	public double getZ() {
		return blockEntity.getPos().getZ();
	}

	@Override
	public void setZ(double z) {
		blockEntity.setPos(new BlockPos(getX(), getY(), z));
	}

	@Override
	public void setLocation(double x, double y, double z) {
		blockEntity.setPos(new BlockPos(x, y, z));
	}

	@Override
	public Block getBlock() {
		return BlockAdapter.get(blockEntity.getBlockType());
	}

	@Override
	public State getState() {
		return new StateAccessor(blockEntity.getWorld().getBlockState(blockEntity.getPos()));
	}
}
