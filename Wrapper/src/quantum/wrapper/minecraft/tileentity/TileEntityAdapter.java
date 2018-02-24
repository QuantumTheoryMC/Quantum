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
 * Created file on 11/5/16 at 7:11 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import quantum.api.block.Block;
import quantum.api.world.BlockEntity;
import quantum.wrapper.minecraft.block.BlockAdapter;

/**
 * @author link
 */
public class TileEntityAdapter extends TileEntity {

	public TileEntityAdapter(BlockEntity blockEntity) {
		this(new BlockPos(blockEntity.getX(), blockEntity.getY(), blockEntity.getZ()), blockEntity.getBlock());
	}

	public TileEntityAdapter(BlockPos pos, Block block) {
		this.blockType = BlockAdapter.get(block);
		this.pos = pos;
		this.worldObj = Minecraft.getMinecraft().theWorld;
	}

}