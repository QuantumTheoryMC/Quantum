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
 * Created file on 1/8/17 at 9:27 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import quantum.api.block.state.State;

import java.util.Hashtable;
import java.util.Map;

/**
 * "Statically" maps all Block.States to UniqueStates.
 * <p>
 * Vanilla Minecraft uses an identity-based map for it's BLOCK_STATE_IDS. As a
 * result, this class is a solution to the problem introduced by Quantum's
 * freedom with Block.States.
 * </p>
 * <p>
 * Normally, in Vanilla Minecraft undefiled by my optimizations in the official
 * Quantum Optimizations Extension (TBD), each block holds a hard reference to
 * all of it's IBlockStates.
 * </p>
 * <p>
 * In Quantum, I give modders the freedom to use multiple instances of
 * Block.States for general usage. This simplifies things when it comes to
 * initialization of Blocks and the performance of Block.States when it comes
 * to
 * cross-mod interaction and general ease-of-use.
 * </p>
 * <p>
 * Because Vanilla Minecraft requires that each blockstate id be mapped to the
 * same blockstate every time, Quantum uses this class to make the leap between
 * duplicate instances of Block.States to their corresponding IBlockState.
 * </p>
 * <p>
 * By "statically", it is meant that every instance of Block.State and it's
 * corresponding internal property values is mapped to the only IBlockState
 * that will ever be used for every kind of Block.State.
 * </p>
 *
 * @author link
 */
public enum BlockStates {
	;

	private static final Map<Entry, UniqueState> STATES = new Hashtable<>();

	public static void add(State state, net.minecraft.block.Block block) {
		STATES.putIfAbsent(Entry.of(state), new UniqueState(state, block));
	}

	public static void remove(State state) {
		STATES.remove(Entry.of(state));
	}


	public static UniqueState get(State state) {
		return STATES.get(Entry.of(state));
	}

	/**
	 * Sets the given state to the given UniqueState implementation
	 *
	 * @param entry
	 * 		a state instance
	 * @param state
	 * 		the unique state
	 */
	public static void set(State entry, UniqueState state) {
		STATES.put(Entry.of(entry), state);
	}

	// a wrapper for Block.States that takes the id of the Block instance
	// and Block.State index and creates a hash whose system is based on
	// 2D -> 1D array mapping arithmetic
	private static final class Entry {

		// the hashCode for the Block.State
		private final int hash;

		private Entry(State state) {
			hash = hash(state);
		}

		// [ 0, 1, 2 ] // 1, 2, 3,
		// [ 3, 4, 5 ] // 4, 5, 6,
		// [ 6, 7, 8 ] // 7, 8, 9
		// index = x + y * width
		//(index = state.block.id + (state.index) * 16)
		//ex: (index =         0  + (     0     ) * 16 = 16) // grass block, but
		// invalid since minecraft does not follow this convention
		private static int hash(State state) {
			return net.minecraft.block.Block.getIdFromBlock(BlockAdapter.get(state.getBlock())) + (state.getIndex() * 16);
		}

		static Entry of(State state) {
			return new Entry(state);
		}

		@Override
		public int hashCode() {
			return hash;
		}

		@Override
		public boolean equals(Object object) {
			return object instanceof Entry && ((Entry) object).hash == hash;
		}
	}
}
