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
 * Created file on 7/15/16 at 10:26 AM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block;

import quantum.api.mod.Mod;

/**
 * A skeletal implementation of the Block interface.
 * <p>
 * All objects in this class are final, including the methods. If you plan on
 * making a Block type that changes over time, you should implement Block
 * directly.
 * </p>
 *
 * @author link
 */
public abstract class AbstractBlock implements Block {

	protected final String name, id;
	protected final Mod  mod;
	protected final Type type;

	protected AbstractBlock(String name, String id, Mod mod, Type type) {
		this.name = name;
		this.id = id;
		this.mod = mod;
		this.type = type;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final String getID() {
		return id;
	}

	@Override
	public final Mod getMod() {
		return mod;
	}

	@Override
	public final Type getType() {
		return type;
	}

}
