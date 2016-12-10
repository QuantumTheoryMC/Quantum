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

import org.jetbrains.annotations.Nullable;
import quantum.api.block.model.BlockModel;
import quantum.api.item.Item;
import quantum.mod.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	protected final String name, namespace, id, variantName;
	protected final Map<String, Block> variants;
	protected final List<Block>        variantList;
	protected final Mod                mod;
	protected final List<State>        states;
	protected final Type               type;
	protected final BlockModel         model;
	protected final Item               harvestTool, drop;
	protected final int variantIndex;


	protected AbstractBlock(String name, String id, @Nullable String variantName, Map<String, Block> variants, Mod mod, List<State> states, Type type, BlockModel model, Item harvestTool, Item drop, int variantIndex) {
		this.name = name;
		this.id = id;
		this.namespace = mod.getName();
		this.variantName = variantName == null || variantName.length() == 0 ? name : variantName;
		this.variants = variants;
		this.variantList = Arrays.asList((Block[]) variants.entrySet()
		                                                   .toArray());
		this.mod = mod;
		this.states = states;
		this.type = type;
		this.model = model;
		this.harvestTool = harvestTool;
		this.drop = drop;
		this.variantIndex = variantIndex;
	}



	@Override
	public final String getName() {
		return name;
	}

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public double getDepth() {
		return 0;
	}

	@Override
	public double getSize() {
		return 0;
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
	public final State getDefaultState() {
		return states.get(0);
	}

	@Override
	public final Type getType() {
		return type;
	}

	@Override
	public final BlockModel getModel() {
		return model;
	}

	@Override
	public final Item getHarvestTool() {
		return harvestTool;
	}

	@Override
	public int getDamageThreshold() {
		return 0;
	}

	@Override
	public final Item getDrop() {
		return drop;
	}

	@Override
	public final Map<String, Block> getVariantMap() {
		return variants;
	}

	@Override
	public final List<Block> getVariants() {
		return variantList;
	}

	@Override
	public final String getVariantName() {
		return variantName;
	}

	@Override
	public final int getVariantIndex() {
		return variantIndex;
	}
}
