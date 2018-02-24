/*
 * The MIT License
 *
 * Copyright 2017 link.
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
 * Created file on 1/8/17 at 9:28 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import quantum.api.block.Block;
import quantum.api.block.BlockProperty;
import quantum.api.block.state.State;
import quantum.api.model.Model;
import quantum.util.Property;
import quantum.util.list.LogicalSet;

import java.util.*;
import java.util.function.Function;

/**
 * @author link
 */
@SuppressWarnings({ "InstanceofConcreteClass", "CastToConcreteClass" })
public final class StateHybrid {

	private final Map<String, PropertyHybrid<?>>     properties;
	private final Function<IProperty, Comparable<?>> getValue;
	private final Block                              block;
	private final net.minecraft.block.Block          blockMC;
	private final List<State>                  states;
	private final int                                index;
	private final Model                              model;

	public StateHybrid(State state, net.minecraft.block.Block block) {
		this.block = state.getBlock();
		blockMC = block;
		states = state.getStates();
		index = state.getIndex();
		model = state.getModel();
		properties = state instanceof StateAccessor ? ((StateAccessor) state).hybrid.properties : getProperties(state);
		getValue = (iproperty -> null);
	}

	public StateHybrid(IBlockState state) {
		if (state instanceof StateAccessor) {
			StateHybrid hybrid = ((StateAccessor) state).hybrid;
			blockMC = hybrid.blockMC;
			block = hybrid.block;
			states = hybrid.states;
			index = hybrid.index;
			model = hybrid.model;
			properties = hybrid.properties;
			getValue = hybrid.getValue;
		} else {
			blockMC = state.getBlock();
			block = BlockAdapter.get(blockMC);
			states = getAllStates(state.getBlock());
			index = getIndex(blockMC, state);
			// TODO get the model for an IBlockState
			model = getModel(state);
			//noinspection unchecked
			properties = getProperties(state);
			getValue = iproperty -> {
				PropertyHybrid<?> prop = properties.get(iproperty.getName());
				return prop.type().equals(Comparable.class) ? (Comparable<?>) prop.value() : null;
			};
		}
	}

	public StateHybrid(BlockState state) {
		this(state.getBaseState());
	}

	public StateHybrid(StateHybrid state) {
		block = state.block;
		blockMC = state.blockMC;
		states = state.states;
		index = state.index;
		model = state.model;
		properties = state.properties;
		getValue = state.getValue;
	}

	private StateHybrid(Block block, net.minecraft.block.Block blockMC, List<State> states, int index, Model model, Map<String, PropertyHybrid<?>> properties, Function<IProperty, Comparable<?>> getValue) {
		this.block = block;
		this.blockMC = blockMC;
		this.states = states;
		this.index = index;
		this.model = model;
		this.properties = properties;
		this.getValue = getValue;
	}


	private static Map<String, PropertyHybrid<?>> getProperties(State state) {
		Map<String, PropertyHybrid<?>> properties = new HashMap<>(state.getProperties().size());

		for (Property<?> prop : state.getProperties())
			properties.put(prop.name(), prop instanceof PropertyHybrid ? (PropertyHybrid<?>) prop : new PropertyHybrid<>(prop));

		return properties;
	}

	private static List<State> getAllStates(final net.minecraft.block.Block block) {
		@SuppressWarnings("unchecked") final ImmutableList<IBlockState> allStates = (ImmutableList<IBlockState>) block.getBlockState().getValidStates();
		final List<State> result = new ArrayList<>(allStates.size());

		int currentIndex = 0;
		for (IBlockState state : allStates) {
			//TODO get the model for an IBlockState
			Model model = null;
			Map<String, PropertyHybrid<?>> props = new HashMap<>(state.getProperties().size());

			//noinspection unchecked
			for (Map.Entry<IProperty, Comparable<?>> value : (Set<Map.Entry<IProperty, Comparable<?>>>) state.getProperties().entrySet()) {
				props.put(value.getKey().getName(), value.getKey() instanceof PropertyHybrid ? (PropertyHybrid<?>) value.getKey() : new PropertyHybrid<>(value.getKey(), value.getValue()));
			}

			Function<IProperty, Comparable<?>> getValue = (iproperty -> null);
			result.add(new StateAccessor(new StateHybrid(BlockAdapter.get(block), block, result, currentIndex, model, props, getValue)));
		}

		return result;
	}

	private static int getIndex(final net.minecraft.block.Block block, final IBlockState state) {
		return block.getBlockState().getValidStates().indexOf(state);
	}

	private static Model getModel(IBlockState state) {
		return null;
	}

	private static Map<String, PropertyHybrid<?>> getProperties(IBlockState state) {
		return null;
	}


	public Collection<? extends IProperty> getPropertyNames() {
		//noinspection unchecked
		return properties.values();
	}

	public Comparable<?> getValue(IProperty property) {
		return getValue.apply(property);
	}

	public StateHybrid withProperty(IProperty property, Comparable<?> value) {
		StateHybrid result = new StateHybrid(this);
		result.properties.put(property.getName(), new PropertyHybrid<>(property, value));
		return result;
	}

	public StateHybrid cycleProperty() {
		return next();
	}

	public ImmutableMap<String, Comparable<?>> getPropertiesMC() {
		// TODO optimize
		Map<String, Comparable<?>> props = new HashMap<>(properties.size());

		for (Map.Entry<String, PropertyHybrid<?>> property : properties.entrySet()) {
			Object value = property.getValue().value();
			props.put(property.getKey(), value instanceof Comparable<?> ? (Comparable<?>) value : "null");
		}

		return ImmutableMap.copyOf(props);
	}

	public Collection<? extends Property<?>> getProperties() {
		return properties.values();
	}

	public List<State> getStates() {
		return states;
	}

	public StateHybrid next() {
		return new StateHybrid(states.get(index + 1), blockMC);
	}

	public int getIndex() {
		return index;
	}

	public net.minecraft.block.Block getBlockMC() {
		return blockMC;
	}

	public Block getBlock() {
		return block;
	}

	public Model getModel() {
		return model;
	}

	public <T> Property<T> getProperty(String property) {
		//noinspection unchecked
		return (Property<T>) properties.get(property);
	}

	public <V> StateHybrid setProperty(String name, V value) {
		StateHybrid result = new StateHybrid(this);
		result.properties.put(name, new PropertyHybrid<>(new BlockProperty<>(name, value)));
		return result;
	}


	private static final class PropertyHybrid<V> implements Property<V>, IProperty {

		private final String                    name;
		private final Class<V>                  type;
		private final V                         value;
		// from IProperty
		private final Collection<Comparable<?>> possibleValues;

		public PropertyHybrid(Property<V> property) {
			name = property.name();
			type = property.type();
			value = property.value();
			possibleValues = value instanceof Number ? new LogicalSet<>((object) -> {
				//noinspection unchecked
				return object instanceof Comparable && ((Comparable<Object>) object).compareTo(object) == 0;
			}) : Collections.singleton(null);
		}

		public PropertyHybrid(IProperty property) {
			this(property, null);
		}

		public PropertyHybrid(IProperty property, V value) {
			name = property.getName();
			//noinspection unchecked
			type = property.getValueClass();
			this.value = value;
			//noinspection unchecked
			possibleValues = (Collection<Comparable<?>>) property.getAllowedValues();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Collection<Comparable<?>> getAllowedValues() {
			return possibleValues;
		}

		@Override
		public Class<?> getValueClass() {
			return type;
		}

		@Override
		public String getName(Comparable value) {
			return value instanceof IStringSerializable ? ((IStringSerializable) value).getName() : value.toString();
		}

		@Override
		public String name() {
			return name;
		}

		@Override
		public V value() {
			return value;
		}

		@Override
		public Class<V> type() {
			return type;
		}
	}

}
