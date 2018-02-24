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

import net.minecraft.block.state.IBlockState;
import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.model.Model;
import quantum.util.Property;

import java.util.List;

/**
 * @author link
 */
public class StateAccessor implements State {

	final StateHybrid hybrid;

//	public StateAccessor(IBlockState state) {
//		properties = properties(state);
//		states = states(state);
//		block = BlockAdapter.get(state.getBlock());
//		index = states.indexOf(this);
//		states.set(index, this);
//
//	}
//
//	StateAccessor(int index, Block block, List<Block.State> states, Map<String, Property<?>> properties) {
//		this.index = index;
//		this.block = block;
//		this.states = states;
//		this.properties = properties;
//	}
//
//	protected static List<Block.State> states(IBlockState blockState) {
//		List<Block.State> states = new ArrayList<>(blockState.getProperties().size());
//
//		Map<String, Property<?>> properties = new HashMap<>(states.size());
//
//		IProperty[] props = (IProperty[]) blockState.getProperties().values().toArray();
//
//		int counter = 0;
//		StateAccessor current = new StateAccessor(counter, BlockAdapter.get(blockState.getBlock()), states, properties);
//		for (int i = 0; i < states.size(); i++) {
//			states.add(i, current);
//			for (IProperty prop : props) {
//				current.properties.put(prop.getName(), convert(prop, blockState));
//			}
//			current = new StateAccessor(i + 1, current.getBlock(), states, current.properties);
//		}
//
//		return states;
//	}
//
//	protected static Map<String, Property<?>> properties(IBlockState state) {
//		Map<String, Property<?>> result = new HashMap<>(state.getProperties().size(), 0.0f);
//		List<IProperty> props = Arrays.asList((IProperty[]) state.getProperties().values().toArray());
//
//		props.forEach((prop) -> {
//			Property<?> p = convert(prop, state);
//			result.put(p.name(), p);
//		});
//
//		return result;
//	}
//
//	static <V> Property<V> convert(IProperty prop, IBlockState state) {
//		return new Property<V>() {
//			@Override
//			public String name() {
//				return prop.getName();
//			}
//
//			@Override
//			@SuppressWarnings("unchecked")
//			public V value() {
//				return (V) state.getValue(prop);
//			}
//		};
//	}
//
//	static <V> IProperty convert(Property<V> prop) {
//		Class<?> valueClass = prop.value() instanceof Cardinality ? EnumFaceing.class : prop.type();
//		return new PropertyHelper(prop.name(), valueClass) {
//
//			@Override
//			public Collection<?> getAllowedValues() {
//				return Collections.singletonList(prop.value()); // used by implementation classes; unnecessary
//			}
//
//			@Override
//			public String getName(Comparable value) {
//				return value.toString();
//			}
//
//			@Override
//			public boolean equals(Object object) {
//				return object == this || (object != null && getClass() == object.getClass() && valueClass.equals(((PropertyHelper) object).getValueClass()));
//			}
//
//		};
//	}

	public StateAccessor(IBlockState state) {
		this(new StateHybrid(state));
	}

	StateAccessor(StateHybrid hybrid) {
		this.hybrid = hybrid;
	}

	@Override
	public int getIndex() {
		return hybrid.getIndex();
	}

	@Override
	public Block getBlock() {
		return hybrid.getBlock();
	}

	@Override
	public Model getModel() {
		return hybrid.getModel();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Property<T> getProperty(String property) {
		return hybrid.getProperty(property);
//		return (Property<T>) properties.get(property);
	}

	@Override
	public <V> StateAccessor setProperty(String name, V value) {
		return new StateAccessor(hybrid.setProperty(name, value));
//		Map<String, Property<?>> props = new HashMap<>(properties);
//		props.put(name, new BlockProperty<>(name, value));
//		return new StateAccessor(index, block, states, props);
	}

	@Override
	public List<Property<?>> getProperties() {
		//noinspection unchecked
		return (List<Property<?>>) hybrid.getProperties();
//		List<Property<?>> properties = new ArrayList<>(this.properties.entrySet().size());
//		properties.addAll(this.properties.entrySet().stream().map((Function<Map.Entry<String, Property<?>>, Property<?>>) Map.Entry::getValue).collect(Collectors.toList()));
//		return properties;
	}

	@Override
	public List<State> getStates() {
		return hybrid.getStates();
	}

	@Override
	public State next() {
		return new StateAccessor(hybrid.next());
//		return states.get(next++ >= states.size() ? next = 0 : next);
	}

}
