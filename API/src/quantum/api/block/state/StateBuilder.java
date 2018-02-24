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
 * Created file on 12/31/16 at 10:51 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block.state;

import quantum.api.block.Block;
import quantum.api.block.BlockProperty;
import quantum.api.model.Model;
import quantum.util.Property;
import quantum.util.pair.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author link
 */
public class StateBuilder {

	protected int index = 0;
	protected Block block;
	protected Model model;
	protected Map<String, Property<?>> properties = new HashMap<>(1);
	protected List<State>        states     = new ArrayList<>(1);

	/**
	 * Sets the index to the given value for this State.
	 *
	 * @param index
	 * 		the index for this State
	 *
	 * @return this StateBuilder
	 */
	public final StateBuilder index(int index) {
		this.index = index;
		return this;
	}

	/**
	 * Sets the properties for this State.
	 *
	 * @param properties
	 * 		the properties for this State
	 *
	 * @return this StateBuilder
	 */
	public final StateBuilder properties(Map<String, Property<?>> properties) {
		this.properties = properties;
		return this;
	}

	/**
	 * Adds the given property to the list of properties for this StateBuilder.
	 *
	 * @param name
	 * 		the name of the property
	 * @param value
	 * 		the value of the property
	 * @param <V>
	 * 		-ignore-
	 *
	 * @return this StateBuilder
	 */
	public final <V> StateBuilder property(String name, V value) {
		properties.put(name, new BlockProperty<>(name, value));
		return this;
	}

	/**
	 * Sets the properties for this State with the given properties in the form
	 * of a name->value pair varargs array.
	 *
	 * @param properties
	 * 		the properties for this State
	 *
	 * @return this StateBuilder
	 */
	@SuppressWarnings("unchecked")
	public StateBuilder properties(Pair<String, Object>... properties) {
		if (properties != null && properties.length > 0)
			for (Pair<String, Object> pair : properties)
				this.properties.put(pair.getA(), new BlockProperty<>(pair.getA(), pair.getB()));
		return this;
	}

	/**
	 * Sets the possible States for this State.
	 *
	 * @param states
	 * 		the possible States for this State
	 *
	 * @return this StateBuilder
	 */
	public StateBuilder states(List<State> states) {
		this.states = states;
		return this;
	}

	/**
	 * Adds the given State to the list of possible States for this State.
	 *
	 * @param state
	 * 		the State to add to the list of possible States for this State
	 *
	 * @return this StateBuilder
	 */
	public StateBuilder state(State state) {
		states.add(state.getIndex(), state);
		return this;
	}

	/**
	 * Sets the given States as the list of possible States for this State.
	 *
	 * @param states
	 * 		the list of possible States for this State
	 *
	 * @return this StateBuilder
	 */
	public StateBuilder states(State... states) {
		if (states != null && states.length > 0)
			for (State state : states)
				state(state);

		return this;
	}

	public StateBuilder block(Block block) {
		this.block = block;
		return this;
	}

	public StateBuilder model(Model model) {
		this.model = model;
		return this;
	}

	/**
	 * Creates a Block state with all the initialized variables in this
	 * StateBuilder.
	 *
	 * @return
	 */
	public final State build() {
		class State extends AbstractState {
			private final Block block;
			private final Model model;

			State(StateBuilder builder) {
				super(builder.index, builder.properties, builder.states);
				this.block = builder.block;
				this.model = builder.model;
			}

			State(State state, String name, Object value) {
				super(state.index, state.properties, state.states);
				block = state.block;
				model = state.model;
				properties.put(name, new BlockProperty<>(name, value));
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
			public <V> State setProperty(String name, V value) {
				return new State(this, name, value);
			}
		}
		return new State(this);
	}

}
