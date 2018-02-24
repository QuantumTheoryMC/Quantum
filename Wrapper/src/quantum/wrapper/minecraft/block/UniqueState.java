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

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import quantum.api.block.state.State;

import java.util.Collection;

/**
 * @author link
 */
public class UniqueState implements IBlockState {

	private final StateHybrid hybrid;

	public UniqueState(State state, net.minecraft.block.Block block) {
		this(new StateHybrid(state, block));
	}

	public UniqueState(StateHybrid state) {
		hybrid = state;
	}

	@Override
	public Collection<IProperty> getPropertyNames() {
		//noinspection unchecked
		return (Collection<IProperty>) hybrid.getPropertyNames();
	}

	@Override
	public Comparable<?> getValue(IProperty property) {
		return hybrid.getValue(property);
	}

	@Override
	public IBlockState withProperty(IProperty property, Comparable value) {
		// TODO? map properties and values to block states (not explicitly used by minecraft)
		return null;
	}

	@Override
	public IBlockState cycleProperty(IProperty property) {
		// TODO? map properties and values to block states (not explicitly used by minecraft)
		return null;
	}

	@Override
	public ImmutableMap<String, Comparable<?>> getProperties() {
		//noinspection unchecked
		return hybrid.getPropertiesMC();
	}

	@Override
	public net.minecraft.block.Block getBlock() {
		return hybrid.getBlockMC();
	}
}
