/*
 * The MIT License
 *
 *  Copyright 2017 link.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 *   Created file on 3/9/2017 at 9:10 PM.
 *
 *   This file is part of Quantum
 */

package quantum.api.block.state;

import quantum.api.block.Block;
import quantum.api.block.BlockProperty;
import quantum.api.model.Model;
import quantum.util.Property;
import quantum.util.pair.Pair;
import quantum.util.pair.Pairs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Root-01
 */
final class DefaultState extends AbstractState {

	private final Block block;
	private final Model model;

	@SafeVarargs
	DefaultState(Block block, Pair<String, Object>... properties) {
		this(block, null, properties);
	}

	@SafeVarargs
	DefaultState(Block block, Model model, Pair<String, Object>... properties) {
		super(0, toProperties(Pairs.toMap(properties)));
		states.add(0, this);
		this.block = block;
		this.model = model;
	}

	private DefaultState(Block block, Model model, Map<String, Property<?>> properties, List<State> states) {
		super(0, properties, states);
		this.block = block;
		this.model = model;
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public <V> DefaultState setProperty(String name, V value) {
		Map<String, Property<?>> properties = new HashMap<>(this.properties);
		properties.put(name, new BlockProperty<>(name, value));
		return new DefaultState(block, model, properties, states);
	}
}
