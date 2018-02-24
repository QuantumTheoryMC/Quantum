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
 * Created file on 1/8/17 at 9:18 PM.
 *
 * This file is part of Quantum API
 */
package quantum;

import quantum.api.block.Block;
import quantum.api.dimension.Dimension;
import quantum.api.entity.Entity;
import quantum.api.item.Item;
import quantum.api.world.World;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents a Quantum Wrapper which provides an interface into Minecraft
 *
 * @author link
 */
public interface Wrapper {

	String getName();

	String getVersion();

	String getMinecraftVersion();

	Quantum.Bridge getBridge();


	void loadMods(Path mods, Quantum quantum) throws IllegalAccessException, IOException, ClassNotFoundException;


	void define(Block block);

	void define(Item item);

	void define(Entity entity);

	void define(Dimension dimension);

	void define(World world);


	void redefine(Block block);

	void redefine(Item item);

	void redefine(Entity entity);

	void redefine(Dimension dimension);

	void redefine(World world);


	void remove(Block block);

	void remove(Item item);

	void remove(Entity entity);

	void remove(Dimension dimension);

	void remove(World world);


	Block getBlock(String id);

	void addHook(Object category, Object hook);

}
