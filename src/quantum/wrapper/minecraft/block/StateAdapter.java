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
 * Created file on 11/8/16 at 7:34 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import quantum.api.block.Block;
import quantum.util.Property;

import java.util.*;

/**
 * @author link
 */
public class StateAdapter implements IBlockState {

	private final Block.State state;

	public StateAdapter(Block.State state) {
		this.state = state;
	}

	@Override
	public Collection<IProperty> getPropertyNames() {
		Set<IProperty> properties = new HashSet<>(state.getProperties().size());

		for (Property<?> property : state.getProperties()) {
			properties.add(StateAccessor.convert(property));
		}
		return properties;
	}

	@Override
	public Comparable<?> getValue(IProperty property) {
		return (Comparable<?>) StateAccessor.convert(state.getProperty(property.getName()));
	}

	@Override
	public IBlockState withProperty(IProperty property, Comparable value) {
		state.setProperty(property.getName(), value);
		return this;
	}

	@Override
	public IBlockState cycleProperty(IProperty property) {
		return new StateAdapter(state.next());
	}

	@Override
	public ImmutableMap<String, IProperty> getProperties() {
		Map<String, IProperty> properties = new HashMap<>(state.getProperties().size());

		for (Property<?> property : state.getProperties()) {
			properties.put(property.getName(), StateAccessor.convert(property));
		}

		return ImmutableMap.copyOf(properties);
	}

	@Override
	public net.minecraft.block.Block getBlock() {
		return BlockAdapter.adapt((Block) state.getProperty("block"));
	}
}
