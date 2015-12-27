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
 *
 * Created file on 12/25/15 at 7:11 AM.
 *
 * This file is part of Quantum API
 */
package api.quantum.util.java;

/**
 * @author link
 */
public class Value extends Number {

	private final double value;
	private Class<?> type = Double.class;

	public Value(byte value) {
		this((double) value);
		type = Byte.class;
	}

	public Value(short value) {
		this((double) value);
		type = Short.class;
	}

	public Value(int value) {
		this((double) value);
		type = Integer.class;
	}

	public Value(long value) {
		this((double) value);
		type = Long.class;
	}

	public Value(float value) {
		this((double) value);
		type = Float.class;
	}

	public Value(double value) {
		this.value = value;
	}

	@Override
	public synchronized int intValue() {
		return (int) value;
	}

	@Override
	public synchronized long longValue() {
		return (long) value;
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

	@Override
	public double doubleValue() {
		return value;
	}
}
