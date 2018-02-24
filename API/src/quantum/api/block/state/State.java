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
 *   Created file on 3/9/2017 at 9:14 PM.
 *
 *   This file is part of Quantum
 */

package quantum.api.block.state;

import quantum.api.block.Block;
import quantum.api.model.Model;
import quantum.util.Property;
import quantum.util.pair.Pair;

import java.util.List;

/**
 * Represents a possible State of a Block in the {@linkplain
 * quantum.api.world.World World}. A common example is crops, which update
 * their state after several ticks, and simulates "growing".
 */
public interface State {

	@SafeVarargs
	static State getDefault(Block block, Pair<String, Object>... properties) {
		return new DefaultState(block, properties);
	}

	@SafeVarargs
	static State getDefault(Block block, Model model, Pair<String, Object>... properties) {
		return new DefaultState(block, model, properties);
	}


	/**
	 * Gets the cyclic index for this State. This value is used when
	 * calling
	 * {@link #next()}, in order to update the state of a given Block in
	 * the
	 * world.
	 *
	 * @return the cyclic index for this State
	 */
	int getIndex();

	/**
	 * Get the Block that this State belongs to.
	 *
	 * @return the Block that this State belongs to
	 */
	Block getBlock();

	/**
	 * Gets the {@link Model} that this Block.State uses
	 *
	 * @return the Model that this State uses
	 */
	Model getModel();

	/**
	 * Gets the value from the given {@link Property} if this State
	 * contains the given property.
	 * <p>
	 * The actual implementation of a State contains a map properties to
	 * values. The property value is a default value in this case, so it
	 * can
	 * be null: {@code getProperty(new BlockProperty("property_to_get",
	 * null))}
	 * </p>
	 *
	 * @param property
	 * 		the property to retrieve
	 * @param <T>
	 * 		the value held by the Property
	 *
	 * @return the value of the given property contained in this State, or
	 * property.getValue()
	 */
	<T> Property<T> getProperty(String property);

	<V> State setProperty(String name, V value);

	/**
	 * Gets all the properties contained by this Block.State with their
	 * current values.
	 *
	 * @return all properties contained by this Block.State in a List
	 */
	List<Property<?>> getProperties();

	/**
	 * Gets the list of possible states for this state, or if this is the
	 * only possible state, this state is returned as a List.
	 * <p>
	 * Each state has a finite number of possible states which can be
	 * represented for a given Block. The given list is either a logical
	 * list which calculates a state, or has concrete instances. This is
	 * because arbitrarily infinite values are possible, especially for
	 * Integer or Double. It would be impractical to create a possible state
	 * for every integer or double value from beginning to end.
	 * </p>
	 *
	 * @return the list of possible states for this state
	 */
	List<State> getStates();

	/**
	 * Gets the next state from this index, or this state if this is the last
	 * index.
	 *
	 * @return the next state in order
	 */
	State next();

}
