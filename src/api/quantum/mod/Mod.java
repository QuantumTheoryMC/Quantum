/*
 * The MIT License
 *
 * Copyright 2015 link.
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
 */
package api.quantum.mod;

import java.lang.reflect.Method;

/**
 * <p>
 * Defines a Mod. The interface is made in a way that it can be potentially used
 * by other APIs and understood by mods made with a different API.
 * </p>
 * <p>
 * This is also meant to be the way in which other APIs can interact with other
 * mods, loosely speaking.
 * </p>
 *
 * @author link
 */
public interface Mod extends Runnable {

	/**
	 * Gets the name of this Mod.
	 *
	 * @return the name of this Mod
	 */
	String getName();

	/**
	 * Gets the API this mod belongs to.
	 *
	 * @return the name of the API this mod was made for
	 */
	default String getAPI() {
		return "Quantum";
	}

	/**
	 * Gets the methods which this Mod modifies.
	 *
	 * @return the methods this mod modifies, if any
	 */
	Method[] getModifiedMethods();


	/**
	 * Starts execution of this mod or runs it once.
	 */
	void start();

	/**
	 * Stops execution of this mod or does nothing if it does not loop.
	 */
	void stop();
}
