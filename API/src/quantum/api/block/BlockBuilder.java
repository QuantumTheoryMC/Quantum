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

import quantum.api.item.Item;
import quantum.api.mod.Mod;
import quantum.api.world.tick.Tickable;
import quantum.util.pair.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author link
 */
public final class BlockBuilder {

	private String name, id, variantName;
	private Map<String, Block> variantMap;
	private List<Block>        variants;
	private Mod                mod;
	private Type type        = null;
	private Item       harvestTool = null, drop = null;
	private int variantIndex    = 0;
	private int damageThreshold = 5;
	private int dropCount       = 1;
	private Tickable tick;

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

	public final BlockBuilder variants(List<Block> variants) {
		this.variants = variants;
		return this;
	}

	public final BlockBuilder variants(Block... variants) {
		return variants(Arrays.asList(variants));
	}

	public final BlockBuilder variantMap(Map<String, Block> variantMap) {
		this.variantMap = variantMap;
		return this;
	}

	public final BlockBuilder variantMap(Pair<String, Block>... variantMap) {
		if (variantMap != null && variantMap.length > 0) {
			Map<String, Block> result = new HashMap<>(variantMap.length);

			for (Pair<String, Block> variant : variantMap) {
				result.put(variant.getA(), variant.getB());
			}

			this.variantMap = result;
		}

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

	public final BlockBuilder type(Type type) {
		this.type = type;
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

	public final BlockBuilder dropCount(int dropCount) {
		this.dropCount = dropCount;
		return this;
	}

	public final BlockBuilder damageThreshold(int hits) {
		this.damageThreshold = hits;
		return this;
	}

	public final BlockBuilder tick(Tickable tick) {
		this.tick = tick;
		return this;
	}

	@SuppressWarnings("unchecked")
	public Block build() {
		return new AbstractBlock(name, id, mod, type) {
			@Override
			public Item getHarvestTool() {
				return harvestTool;
			}

			@Override
			public int getDamageThreshold() {
				return damageThreshold;
			}

			@Override
			public Item getDropItem() {
				return drop;
			}

			@Override
			public int getDropCount() {
				return dropCount;
			}

			@Override
			public Map<String, Block> getVariantMap() {
				return variantMap;
			}

			@Override
			public List<Block> getVariants() {
				return variants;
			}

			@Override
			public String getVariantName() {
				return variantName;
			}

			@Override
			public int getVariantIndex() {
				return variantIndex;
			}

			@Override
			public void tick() {
				tick.tick();
			}
		};
	}

}
