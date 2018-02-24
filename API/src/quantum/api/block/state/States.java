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
 * Created file on 12/30/16 at 8:27 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block.state;

import quantum.api.block.Block;
import quantum.util.pair.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A static map class containing the mappings of blocks to states
 *
 * @author link
 */
public enum States {
	;

	private static final Map<Block, State> STATE_MAP = new HashMap<>();

	/**
	 * Maps the given Block to the given State.
	 *
	 * @param block
	 * 		the block to map
	 * @param state
	 * 		the state to map to the given block
	 */
	public static void map(Block block, State state) {
		STATE_MAP.put(block, state);
	}

	/**
	 * Maps the given block to the given states
	 *
	 * @param block
	 * 		the block to map
	 * @param states
	 * 		the states to map to the given block
	 */
	public static void map(Block block, State... states) {
		if (states == null || states.length == 0)
			throw new IllegalArgumentException("No states given: " + Arrays.toString(states));

		for (State state : states) {
			map(block, state);
		}
	}

	@SafeVarargs
	public static void map(Pair<Block, State>... blockStatePairs) {
		if (blockStatePairs == null || blockStatePairs.length == 0)
			throw new IllegalArgumentException("No block<->state pairs: " + Arrays.toString(blockStatePairs));

		for (Pair<Block, State> pair : blockStatePairs) {
			map(pair.getA(), pair.getB());
		}
	}


	/**
	 * Gets the State associated with the given Block.
	 *
	 * @param block
	 * 		the Block to retrieve a State for
	 *
	 * @return the State associated with the given Block
	 */
	public static State get(Block block) {
		return STATE_MAP.get(block);
	}

}
