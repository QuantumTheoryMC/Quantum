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
 * Created file on 7/18/16 at 10:39 AM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import quantum.api.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * @author link
 */
public class BlockAdapter extends net.minecraft.block.Block {

	private static final Map<net.minecraft.block.Block, Block> BLOCK_TO_BLOCK = new HashMap<>(blockRegistry
			                                                                                          .getKeys()
			                                                                                          .size(), 0.88f);

	private BlockAdapter(Block block) {
		super(new TypeAdapter(block));
		setDefaultState(new StateAdapter(block.getDefaultState()));
		setUnlocalizedName(block.getName());

	}

	public static BlockAdapter adapt(Block block) {
		// TODO implement per-mod BlockAdapters
		return new BlockAdapter(block);
	}

	public static Block get(net.minecraft.block.Block block) {
		return BLOCK_TO_BLOCK.get(block);
	}

	public static void set(net.minecraft.block.Block key, Block value) {
		BLOCK_TO_BLOCK.put(key, value);
	}
}
