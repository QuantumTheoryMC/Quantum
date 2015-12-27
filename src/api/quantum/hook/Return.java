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
package api.quantum.hook;

import api.quantum.meta.Untested;

/**
 * <p>
 * A Return object represents the object returned by a method. This class is to
 * be used with MethodHook implementations, but can be used elsewhere.
 * </p>
 * <p>
 * This class stores numbers in a field of type double, for performance
 * reasons,
 * and objects in a field of type T. T is guaranteed to never be null.
 * </p>
 *
 * @param <T>
 * 		the type to return if this Method returns an Object.
 * @author link
 */
@Untested
public final class Return<T extends Object> {

	// use double to support all number types.
	private final double NUMBER;
	// if this Object uses an actual Object to return.
	private final T[] OBJECT;

	/**
	 * Creates a new Return object with the specified byte.
	 *
	 * @param number
	 * 		the number to return
	 */
	public Return(byte number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Byte[1];
		OBJECT[0] = (T) Byte.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified short.
	 *
	 * @param number
	 * 		the number to return
	 */
	public Return(short number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Short[1];
		OBJECT[0] = (T) Short.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified int.
	 *
	 * @param number
	 */
	public Return(int number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Integer[1];
		OBJECT[0] = (T) Integer.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified long.
	 *
	 * @param number
	 * 		the number to return
	 */
	public Return(long number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Long[1];
		OBJECT[0] = (T) Long.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified float.
	 *
	 * @param number
	 * 		the number to return
	 */
	public Return(float number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Float[1];
		OBJECT[0] = (T) Float.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified double.
	 *
	 * @param number
	 * 		the number to return
	 */
	public Return(double number) {
		this.NUMBER = number;
		OBJECT = (T[]) new Double[1];
		OBJECT[0] = (T) Double.valueOf(number);
	}

	/**
	 * Creates a new Return object with the specified object.
	 *
	 * @param object
	 * 		the object to return
	 */
	public Return(T object) {
		this.OBJECT = (T[]) new Object[1];
		this.OBJECT[0] = object;
		NUMBER = object instanceof Number ? ((Number) object).doubleValue() : 0;
	}

	/**
	 * Creates a new Return object with the specified object array.
	 *
	 * @param objects
	 * 		the objects to return
	 */
	public Return(T[] objects) {
		OBJECT = objects;
		NUMBER = 0;
	}

	/**
	 * Gets this Return object's object as a byte.
	 *
	 * @return this Return object's object as a byte
	 */
	public byte asByte() {
		return (byte) NUMBER;
	}

	/**
	 * Gets this Return object's object as a short.
	 *
	 * @return this Return object's object as a short
	 */
	public short asShort() {
		return (short) NUMBER;
	}

	/**
	 * Gets this Return object's object as an int.
	 *
	 * @return this Return object's object as an int
	 */
	public int asInteger() {
		return (int) NUMBER;
	}

	/**
	 * Gets this Return object's object as a long.
	 *
	 * @return this Return object's object as a long
	 */
	public long asLong() {
		return (long) NUMBER;
	}

	/**
	 * Gets this Return object's object as a float.
	 *
	 * @return this Return object's object as a float
	 */
	public float asFloat() {
		return (float) NUMBER;
	}

	/**
	 * Gets this Return object's object as a double.
	 *
	 * @return this Return object's object as a double
	 */
	public double asDouble() {
		return NUMBER;
	}

	/**
	 * Gets this Return object's object as an array.
	 *
	 * @return this Return object's object as an array
	 */
	public T[] asArray() {
		return OBJECT;
	}

	/**
	 * Gets this Return object's object.
	 *
	 * @return this Return object's object
	 */
	public T get() {
		return OBJECT[0];
	}

	/**
	 * Gets this Return object's object type.
	 *
	 * @return this Return object's object type
	 */
	public Class<?> getReturnType() {
		return OBJECT.getClass();
	}
}
