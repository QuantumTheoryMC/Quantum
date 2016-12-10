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

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import quantum.api.block.Block;
import quantum.util.Property;

import java.util.*;

/**
 * @author link
 */
public class StateAccessor implements Block.State {

	private       int                      index;
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
		List<Block.State> states = new ArrayList<>(blockState.getProperties()
		                                                     .size());
		Map<String, Property<?>> properties = new HashMap<>(states.size());
		IProperty[] props = (IProperty[]) blockState.getProperties()
		                                            .values()
		                                            .toArray();

		int counter = 0;

		StateAccessor current = new StateAccessor(counter, states, properties);
		for (int i = 0; i < states.size(); i++) {
			states.add(i, current);
			for (IProperty prop : props) {
				current.properties.put(prop.getName(), convert(prop));
			}
			current = new StateAccessor(i + 1, states, current.properties);
		}

		return states;

	}

	protected static Map<String, Property<?>> properties(IBlockState state) {
		Map<String, Property<?>> result = new HashMap<>(state.getProperties()
		                                                     .size(), 0.0f);
		List<IProperty> props = Arrays.asList((IProperty[]) state.getProperties()
		                                                         .values()
		                                                         .toArray());

		props.forEach((prop) -> {
			Property<?> p = convert(prop);
			result.put(p.getName(), p);
		});

		return result;
	}

	static <V> Property<V> convert(IProperty prop) {
		return new Property<V>() {
			@Override
			public String getName() {
				return prop.getName();
			}

			@Override
			public V getValue() {
				return null;
			}
		};
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Property<T> getProperty(String property) {
		return (Property<T>) properties.get(property);
	}

	@Override
	public List<Block.State> getStates() {
		return states;
	}

	@Override
	public Block.State next() {
		return states.get(index++ >= states.size() ? index = 0 : index);
	}
}
