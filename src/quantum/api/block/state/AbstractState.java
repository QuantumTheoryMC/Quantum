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
 * Created file on 12/16/16 at 7:41 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block.state;

import quantum.api.block.Block;
import quantum.api.block.BlockProperty;
import quantum.util.Property;
import quantum.util.pair.Pair;
import quantum.util.pair.Pairs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A Skeletal class for Block.State. For the default state, see {@link
 * MonoState}.
 *
 * @author link
 */
public abstract class AbstractState implements Block.State {

	/**
	 * the index of this State into the state cycle
	 */
	protected final int index;

	/**
	 * the list of properties for this state
	 */
	protected final Map<String, Property<?>> properties;

	/**
	 * the list of Block.State variants for this state in order of index
	 */
	protected final List<Block.State> states;

	/**
	 * Creates a new State with the given properties. This constructor is no
	 * different than {@link #AbstractState(Map)}. This merely allows you to
	 * define the properties in a convenient manner that is more readable.
	 *
	 * @param properties
	 * 		the properties for this State
	 */
	@SafeVarargs
	protected AbstractState(Pair<String, Object>... properties) {
		this(Pairs.toMap(properties));
	}

	/**
	 * Creates a new State with the given properties. The index and variants
	 * are
	 * set to their default values of 0 and ArrayList[1], respectively, and this
	 * State is therefore implicitly known to be containing no other variants.
	 *
	 * @param properties
	 * 		the properties for this state
	 */
	protected AbstractState(Map<String, Object> properties) {
		this(0, toProperties(properties), null);
	}

	/**
	 * Creates a new State with the given index and properties. The list of
	 * variants is initialized with only this state since it is implied that
	 * this is the only state, but the List is extensible.
	 *
	 * @param index
	 * 		the index into the list of variants for this state
	 * @param properties
	 * 		the properties for this state
	 */
	protected AbstractState(int index, Map<String, Property<?>> properties) {
		this(index, properties, new ArrayList<>(1));
		states.add(index, this);
	}

	/**
	 * Creates a new State with the given index, properties, and state variants.
	 *
	 * @param index
	 * 		the index for this State into the list of variants for this state.
	 * 		This
	 * 		parameter must not be null.
	 * @param properties
	 * 		the property mapping for this state. If null, a singleton list
	 * 		containing only this state is created.
	 * @param states
	 * 		the variants for this state. This state may be a variant or the
	 * 		default
	 * 		state. This parameter must not be null.
	 */
	protected AbstractState(int index, Map<String, Property<?>> properties, List<Block.State> states) {
		this.index = index;
		this.properties = properties;
		this.states = states != null ? states : Collections.singletonList(this);
	}

	private static Map<String, Property<?>> toProperties(Map<String, Object> properties) {
		Map<String, Property<?>> props = new HashMap<>(properties.size());

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			props.put(entry.getKey(), new BlockProperty<>(entry.getKey(), entry.getValue()));
		}

		return props;
	}

	@Override
	public final int getIndex() {
		return index;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> Property<T> getProperty(String property) {
		return (Property<T>) properties.get(property);
	}

	@Override
	public abstract <V> AbstractState setProperty(String name, V value);

	@Override
	public final List<Property<?>> getProperties() {
		List<Property<?>> list = new ArrayList<>(properties.size());

		list.addAll(properties.entrySet().stream().map((Function<Map.Entry<String, Property<?>>, Property<?>>) Map.Entry::getValue).collect(Collectors.toList()));

		return list;
	}

	@Override
	public final List<Block.State> getStates() {
		return states;
	}

	@Override
	public Block.State next() {
		return states.get(index + 1);
	}
}
