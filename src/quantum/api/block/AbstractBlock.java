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

import quantum.api.block.model.BlockModel;
import quantum.api.item.Item;
import quantum.mod.Mod;

import java.util.*;
import java.util.stream.Collectors;

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

	/**
	 * Creates a new Block with the given name, id, and mod. All other fields
	 * are initialized with the initializer methods.
	 *
	 * @param name
	 * 		the name of this Block
	 * @param id
	 * 		the id of this Block
	 * @param mod
	 * 		the mod that this Block belongs to
	 */
	protected AbstractBlock(String name, String id, Mod mod) {
		this(name, id, name, 0, mod);
	}

	/**
	 * Creates a new Block with the given name, id, variantName, variantIndex,
	 * and mod. All other fields are initialized with the initializer methods.
	 *
	 * @param name
	 * 		the name of this Block
	 * @param id
	 * 		the id of this Block
	 * @param variantName
	 * 		the variant name of this Block
	 * @param variantIndex
	 * 		the variant index of this Block
	 * @param mod
	 * 		the mod that this Block belongs to
	 */
	protected AbstractBlock(String name, String id, String variantName, int variantIndex, Mod mod) {
		this.name = name;
		this.id = id;
		this.namespace = mod.getName();
		this.variants = variants();
		this.states = states();
		this.variantName = variantName == null || variantName.length() == 0 ? name : variantName;
		this.variantIndex = variantIndex;
		this.variantList = toList(variants);
		this.mod = mod;
		this.type = type();
		this.model = model();
		this.harvestTool = harvestTool();
		this.drop = drop();
	}

	/**
	 * Creates a new Block with the given name, id, variantName, variantIndex,
	 * mod, type, and model. All other fields are initialized with the
	 * initializer methods.
	 *
	 * @param name
	 * 		the name of this Block
	 * @param id
	 * 		the id of this Block
	 * @param variantName
	 * 		the variant name of this Block
	 * @param variantIndex
	 * 		the variant index of this Block
	 * @param mod
	 * 		the mod that this Block belongs to
	 * @param type
	 * 		the type of this Block
	 * @param model
	 * 		the model for this Block
	 */
	protected AbstractBlock(String name, String id, String variantName, int variantIndex, Mod mod, Type type, BlockModel model) {
		this.name = name;
		this.id = id;
		this.namespace = mod.getName();
		this.variants = variants();
		this.states = states();
		this.variantName = variantName == null || variantName.length() == 0 ? name : variantName;
		this.variantIndex = variantIndex;
		this.variantList = toList(variants);
		this.mod = mod;
		this.type = type;
		this.model = model;
		this.harvestTool = harvestTool();
		this.drop = drop();
	}

	/**
	 * Creates a new Block with the given name, id, variantName, variantIndex,
	 * mod, type, model, harvestTool, and drop. All other fields are initialized
	 * with the initializer methods.
	 *
	 * @param name
	 * 		the name of this Block
	 * @param id
	 * 		the id of this Block
	 * @param variantName
	 * 		the variant name of this Block
	 * @param variantIndex
	 * 		the variant index of this Block
	 * @param mod
	 * 		the mod that this Block belongs to
	 * @param type
	 * 		the type of Block
	 * @param model
	 * 		the model for this Block
	 * @param harvestTool
	 * 		the tool used to harvest this Block (i.e. PickAxe, Shovel, Hand, etc.)
	 * @param drop
	 * 		the item that this Block will drop when harvested
	 */
	protected AbstractBlock(String name, String id, String variantName, int variantIndex, Mod mod, Type type, BlockModel model, Item harvestTool, Item drop) {
		this.name = name;
		this.id = id;
		this.namespace = mod.getName();
		this.variants = variants();
		this.states = states();
		this.variantName = variantName == null || variantName.length() == 0 ? name : variantName;
		this.variantIndex = variantIndex;
		this.variantList = toList(variants);
		this.mod = mod;
		this.type = type;
		this.model = model;
		this.harvestTool = harvestTool;
		this.drop = drop;
	}

	/**
	 * Creates a new Block with the given name, id, variantName, variantIndex,
	 * variants, mod, states, type, model, harvestTool, and drop. Initializer
	 * methods do not need to be implemented (and shouldn't be) if using this
	 * constructor.
	 *
	 * @param name
	 * 		the name of this Block
	 * @param id
	 * 		the id of this Block
	 * @param variantName
	 * 		the variant name of this Block
	 * @param variantIndex
	 * 		the variant index of this Block
	 * @param variants
	 * 		the variants for this Block
	 * @param mod
	 * 		the mod that this Block belongs to
	 * @param states
	 * 		the various states for this Block
	 * @param type
	 * 		the type of Block
	 * @param model
	 * 		the model for this Block
	 * @param harvestTool
	 * 		the tool used to harvest this Block
	 * @param drop
	 * 		the item dropped when this Block is harvested
	 */
	protected AbstractBlock(String name, String id, String variantName, int variantIndex, Map<String, Block> variants, Mod mod, List<State> states, Type type, BlockModel model, Item harvestTool, Item drop) {
		this.name = name;
		this.id = id;
		this.namespace = mod.getName();
		this.variants = variants;
		this.states = states;
		this.variantName = variantName == null || variantName.length() == 0 ? name : variantName;
		this.variantIndex = variantIndex;
		this.variantList = toList(variants);
		this.mod = mod;
		this.type = type;
		this.model = model;
		this.harvestTool = harvestTool;
		this.drop = drop;
	}

	protected static List<Block> toList(Map<String, Block> variants) {
		if (variants == null) return Collections.emptyList();

		Set<Map.Entry<String, Block>> entries = variants.entrySet();
		List<Block> blocks = new ArrayList<>(entries.size());

		blocks.addAll(entries.stream().map(Map.Entry::getValue).collect(Collectors.toList()));
		return blocks;
	}

	protected abstract Map<String, Block> variants();

	protected abstract List<State> states();

	protected abstract Item harvestTool();

	protected abstract Item drop();

	protected abstract Type type();

	protected abstract BlockModel model();

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public double getWidth() {
		return 1;
	}

	@Override
	public double getHeight() {
		return 1;
	}

	@Override
	public double getDepth() {
		return 1;
	}

	@Override
	public double getSize() {
		return 1;
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
