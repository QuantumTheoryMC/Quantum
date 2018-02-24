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
 * Created file on 7/15/16 at 10:30 AM.
 *
 * This file is part of Quantum API
 */
package quantum.api.mod.minecraft;

import quantum.Quantum;
import quantum.api.mod.Mod;
import quantum.util.CallerClass;

/**
 * A representational mod that exists only to represent Minecraft and it's
 * namespace.
 *
 * @author link
 */
public final class ModMinecraft implements Mod {

	private static final ModMinecraft SINGLETON = new ModMinecraft();

	public static ModMinecraft getInstance() {
		Class<?> caller = CallerClass.get();
		if ((caller).getPackage().getName().startsWith("quantum.api"))
			return SINGLETON;
		else
			throw new IllegalAccessError("A non-API class tried to get the ModMinecraft instance: " + caller.getName());
	}

	@Override
	public void load(Quantum quantum) {
	}

	@Override
	public void unload(Quantum quantum) {
	}

	@Override
	public String getName() {
		return "minecraft";
	}
}
