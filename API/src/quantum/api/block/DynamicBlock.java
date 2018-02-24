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
 * Created file on 12/31/16 at 10:03 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block;

import quantum.api.block.state.State;
import quantum.api.mod.Mod;

/**
 * Represents a Block with multiple {@link State}s. The nature of
 * Block.States implemented by quantum make it impractical to save the block
 * states in the Block implementation. As such, this is a tag class, and only
 * helps with clarity. There is no implementation difference between this class
 * and AbstractBlock.
 *
 * @author link
 */
public abstract class DynamicBlock extends AbstractBlock {

	protected DynamicBlock(String name, String id, Mod mod, Type type) {
		super(name, id, mod, type);
	}

}
