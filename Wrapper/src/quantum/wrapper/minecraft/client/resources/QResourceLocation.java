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
 * Created file on 12/12/16 at 2:51 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.client.resources;

import net.minecraft.util.ResourceLocation;
import quantum.api.mod.Mod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author link
 */
public class QResourceLocation extends ResourceLocation {

	private final Mod     mod;
	private final boolean jarResource;

	public QResourceLocation(Mod mod, String relativePath, boolean jarResource) {
		super(/* ignored */0, mod.getName(), relativePath);
		this.mod = mod;
		this.jarResource = jarResource;
	}

	public boolean exists() {
		InputStream resource = retrieve();
		try {
			return resource != null && resource.read() != -1;
		} catch (IOException e) {
			return false;
		}
	}

	public InputStream retrieve() {
		try {
			return jarResource ? mod.getClass().getResourceAsStream(getPath().toString()) : new FileInputStream(getPath().toString());
		} catch (IOException e) {
			return null;
		}
	}

	public Path getPath() {
		return !jarResource ? Paths.get(System.getProperty("user.dir"), ".minecraft", "quantum", "mods", mod.getName(), resourcePath) : Paths.get(mod.getName() + ".jar", resourcePath);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
