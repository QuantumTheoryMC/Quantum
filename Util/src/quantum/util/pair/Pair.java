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
 * Created file on 12/16/16 at 8:05 PM.
 *
 * This file is part of Quantum API
 */
package quantum.util.pair;

/**
 * @author link
 */
public interface Pair<A, B> {

	/**
	 * A helper method that creates pairs from an anonymous class instance which
	 * pairs objects A and B together.
	 *
	 * @param a
	 * 		the first object
	 * @param b
	 * 		the second object
	 * @param <A>
	 * 		the first Object type
	 * @param <B>
	 * 		the second Object type
	 *
	 * @return a pair containing the given objects
	 */
	static <A, B> Pair<A, B> pair(A a, B b) {
		return new Pair<A, B>() {
			@Override
			public A getA() {
				return a;
			}

			@Override
			public B getB() {
				return b;
			}
		};
	}

	A getA();

	B getB();

}
