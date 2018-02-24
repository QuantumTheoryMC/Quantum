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
 * Created file on 11/26/16 at 7:40 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.model.texture;

/**
 * @author link
 */
public interface Texture {

	/**
	 * Gets the width of this Texture
	 *
	 * @return the width of this Texture
	 */
	int getWidth();

	/**
	 * Gets the height of this Texture
	 *
	 * @return the height of this Texture
	 */
	int getHeight();

	/**
	 * Gets the Texel at the given position in the Texture
	 *
	 * @param index
	 * 		the index of the Texture
	 *
	 * @return the Texel at the given location
	 */
	Texel getTexel(int index);

	/**
	 * Represents a Texture element which has alpha, red, green, and blue
	 * channels.
	 */
	final class Texel {

		private final float r, g, b, a;
		private final int argb;

		/**
		 * Creates a new Texel with the given red, green, and blue values. The
		 * alpha value is set to 1.0f
		 *
		 * @param r
		 * 		the red value as a percentage of 255
		 * @param g
		 * 		the green value as a percentage of 255
		 * @param b
		 * 		the blue value as a percentage of 255
		 */
		public Texel(float r, float g, float b) {
			this(r, g, b, 1.0f);
		}

		/**
		 * Creates a new Texel with the given red, green, blue, and alpha
		 * values.
		 *
		 * @param r
		 * 		the red value as a percentage of 255
		 * @param g
		 * 		the green value as a percentage of 255
		 * @param b
		 * 		the blue value as a percentage of 255
		 * @param a
		 * 		the alpha value as a percentage of 255
		 */
		public Texel(float r, float g, float b, float a) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
			argb = (int) (a * 255) << 24 | (int) (r * 255) << 16 | (int) (g * 255) << 8 | (int) (b * 255);
		}

		public static strictfp Texel create(int argb) {
			return new Texel(getR(argb), getG(argb), getB(argb), getA(argb));
		}

		public static strictfp float getR(int argb) {
			return ((argb >> 16) & 255) / 255;
		}

		public static strictfp float getG(int argb) {
			return ((argb >> 8) & 255) / 255;
		}

		public static strictfp float getB(int argb) {
			return (argb & 255) / 255;
		}

		public static strictfp float getA(int argb) {
			return ((argb >> 24) & 255) / 255;
		}

		/**
		 * Gets the red value of this Texel represented as a percentage of 255.
		 *
		 * @return the red value of this Texel
		 */
		public float getRed() {
			return r;
		}

		/**
		 * Gets the green value of this Texel represented as a percentage of
		 * 255.
		 *
		 * @return the green value of this Texel
		 */
		public float getGreen() {
			return g;
		}

		/**
		 * Gets the blue value of this Texel represented as a percentage of 255.
		 *
		 * @return the blue value of this Texel
		 */
		public float getBlue() {
			return b;
		}

		/**
		 * Gets the alpha value of this Texel represented as a percentage of
		 * 255.
		 *
		 * @return the alpha value of this Texel
		 */
		public float getAlpha() {
			return a;
		}

		/**
		 * Gets the ARGB resultant value of this Texel, with the respective
		 * alpha, red, green, and blue values for each byte.
		 *
		 * @return the ARGB value of this Texel
		 */
		public int getARGB() {
			return argb;
		}

	}

}
