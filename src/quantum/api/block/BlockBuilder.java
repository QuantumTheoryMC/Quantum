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
 * Created file on 11/6/16 at 5:56 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block;

import quantum.api.block.model.BlockModel;
import quantum.api.item.Item;
import quantum.mod.Mod;

import java.util.List;
import java.util.Map;

/**
 * @author link
 */
public final class BlockBuilder {

	private String name, id, variantName;
	private Map<String, Block> variants;
	private Mod                mod;
	private List<Block.State>  states;
	private Block.Type         type;
	private BlockModel         model;
	private Item               harvestTool, drop;
	private int variantIndex;

	public BlockBuilder() {
	}

	public final BlockBuilder name(String name) {
		this.name = name;
		return this;
	}

	public final BlockBuilder id(String id) {
		this.id = id;
		return this;
	}

	public final BlockBuilder variant(String variantName) {
		this.variantName = variantName;
		return this;
	}

	public final BlockBuilder variants(Map<String, Block> variants) {
		this.variants = variants;
		return this;
	}

	public final BlockBuilder variantIndex(int variantIndex) {
		this.variantIndex = variantIndex;
		return this;
	}

	public final BlockBuilder mod(Mod mod) {
		this.mod = mod;
		return this;
	}

	public final BlockBuilder states(List<Block.State> states) {
		this.states = states;
		return this;
	}

	public final BlockBuilder type(Block.Type type) {
		this.type = type;
		return this;
	}

	public final BlockBuilder model(BlockModel model) {
		this.model = model;
		return this;
	}

	public final BlockBuilder harvestTool(Item harvestTool) {
		this.harvestTool = harvestTool;
		return this;
	}

	public final BlockBuilder drops(Item drop) {
		this.drop = drop;
		return this;
	}

	@SuppressWarnings("unchecked")
	public Block build() {
		return new AbstractBlock(name, id, variantName, variants, mod, states, type, model, harvestTool, drop, variantIndex) {
		};
	}

}
