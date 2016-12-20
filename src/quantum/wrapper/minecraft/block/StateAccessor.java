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
 * Created file on 7/25/16 at 1:19 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import quantum.api.block.Block;
import quantum.api.block.BlockProperty;
import quantum.util.Property;
import quantum.util.list.LogicalSet;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author link
 */
public class StateAccessor implements Block.State {

	private int index, next;
	private final List<Block.State>        states;
	private final Map<String, Property<?>> properties;

	public StateAccessor(IBlockState state) {
		properties = properties(state);
		states = states(state);

		index = states.indexOf(this);
		states.set(index, this);

	}

	private StateAccessor(int index, List<Block.State> states, Map<String, Property<?>> properties) {
		this.index = index;
		this.states = states;
		this.properties = properties;
	}

	protected static List<Block.State> states(IBlockState blockState) {
		// TODO optimize
		if (!(blockState instanceof BlockState)) {

			List<Block.State> states = new ArrayList<>(blockState.getProperties().size());

			Map<String, Property<?>> properties = new HashMap<>(states.size());

			IProperty[] props = (IProperty[]) blockState.getProperties().values().toArray();

			int counter = 0;

			StateAccessor current = new StateAccessor(counter, states, properties);
			for (int i = 0; i < states.size(); i++) {
				states.add(i, current);
				for (IProperty prop : props) {
					current.properties.put(prop.getName(), convert(prop, blockState));
				}
				current = new StateAccessor(i + 1, states, current.properties);
			}

			return states;
		}

		@SuppressWarnings("unchecked") ImmutableList<IBlockState> blockStates = blockState instanceof BlockState ? ((BlockState) blockState).getValidStates() : null;
		List<Block.State> states = new ArrayList<>(blockStates.size());

		states.addAll(blockStates.stream().map(StateAccessor::new).collect(Collectors.toList()));

		return states;
	}

	protected static Map<String, Property<?>> properties(IBlockState state) {
		Map<String, Property<?>> result = new HashMap<>(state.getProperties().size(), 0.0f);
		List<IProperty> props = Arrays.asList((IProperty[]) state.getProperties().values().toArray());

		props.forEach((prop) -> {
			Property<?> p = convert(prop, state);
			result.put(p.getName(), p);
		});

		return result;
	}

	static <V> Property<V> convert(IProperty prop, IBlockState state) {
		return new Property<V>() {
			@Override
			public String getName() {
				return prop.getName();
			}

			@Override
			@SuppressWarnings("unchecked")
			public V getValue() {
				return (V) state.getValue(prop);
			}
		};
	}

	static <V> IProperty convert(Property<V> prop) {
		return new IProperty() {
			@Override
			public String getName() {
				return prop.getName();
			}

			@Override
			public Collection<?> getAllowedValues() {
				return new LogicalSet<>((object) -> object.getClass() == getValueClass());
			}

			@Override
			public Class<?> getValueClass() {
				return prop.getType();
			}

			@Override
			public String getName(Comparable value) {
				return value.toString();
			}
		};
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Property<T> getProperty(String property) {
		return (Property<T>) properties.get(property);
	}

	@Override
	public <V> StateAccessor setProperty(String name, V value) {
		Map<String, Property<?>> props = new HashMap<>(properties);
		props.put(name, new BlockProperty<>(name, value));
		return new StateAccessor(index, states, props);
	}

	@Override
	public List<Property<?>> getProperties() {
		List<Property<?>> properties = new ArrayList<>(this.properties.entrySet().size());
		properties.addAll(this.properties.entrySet().stream().map((Function<Map.Entry<String, Property<?>>, Property<?>>) Map.Entry::getValue).collect(Collectors.toList()));
		return properties;
	}

	@Override
	public List<Block.State> getStates() {
		return states;
	}

	@Override
	public Block.State next() {
		return states.get(next++ >= states.size() ? next = 0 : next);
	}
}
