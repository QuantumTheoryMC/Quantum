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
 * Created file on 11/6/16 at 5:54 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block;

import quantum.api.item.Item;
import quantum.api.mod.Mod;
import quantum.api.mod.minecraft.ModMinecraft;

/**
 * @author link
 */
public final class Air extends StaticBlock {

	static final Air BLOCK = new Air("air", "air", ModMinecraft.getInstance(), Type.AIR);

	private Air(String name, String id, Mod mod, Type type) {
		super(name, id, mod, type);
	}

	@Override
	public void tick() {
		// do nothing
	}

	@Override
	public Item getHarvestTool() {
		return null;
	}

	@Override
	public int getDamageThreshold() {
		return 0;
	}

	@Override
	public Item getDropItem() {
		return null;
	}

}
