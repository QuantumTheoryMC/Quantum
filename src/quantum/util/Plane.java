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
package quantum.util;

import quantum.meta.WIP;
import net.minecraft.util.EnumFacing;

/**
 * @author link
 */
@WIP(description = "Not yet finished",
     unfinished = {"Method wrappers"})
public enum Plane {

	PLANESET_XXY(Axis.X, Axis.X, Axis.Y),
	PLANESET_XXZ(Axis.X, Axis.X, Axis.Z),
	PLANESET_XYX(Axis.X, Axis.Y, Axis.X),
	PLANESET_XYY(Axis.X, Axis.Y, Axis.Y),
	PLANESET_XYZ(Axis.X, Axis.Y, Axis.Z),
	PLANESET_XZX(Axis.X, Axis.Z, Axis.X),
	PLANESET_XZY(Axis.X, Axis.Z, Axis.Y),
	PLANESET_XZZ(Axis.X, Axis.Z, Axis.Z),
	PLANESET_YXX(Axis.Y, Axis.X, Axis.X),
	PLANESET_YXY(Axis.Y, Axis.X, Axis.Y),
	PLANESET_YXZ(Axis.Y, Axis.X, Axis.Z),
	PLANESET_YYX(Axis.Y, Axis.Y, Axis.X),
	PLANESET_YYZ(Axis.Y, Axis.Y, Axis.Z),
	PLANESET_YZX(Axis.Y, Axis.Z, Axis.X),
	PLANESET_YZY(Axis.Y, Axis.Z, Axis.Y),
	PLANESET_YZZ(Axis.Y, Axis.Z, Axis.Z),
	PLANESET_ZXX(Axis.Z, Axis.X, Axis.X),
	PLANESET_ZXY(Axis.Z, Axis.X, Axis.Y),
	PLANESET_ZXZ(Axis.Z, Axis.X, Axis.Z),
	PLANESET_ZYX(Axis.Z, Axis.Y, Axis.X),
	PLANESET_ZYY(Axis.Z, Axis.Y, Axis.Y),
	PLANESET_ZYZ(Axis.Z, Axis.Y, Axis.Z),
	PLANESET_ZZX(Axis.Z, Axis.Z, Axis.X),
	PLANESET_ZZY(Axis.Z, Axis.Z, Axis.Y),
	XY(PLANESET_XXY),
	XZ(PLANESET_XXZ),
	YX(PLANESET_YYX),
	YZ(PLANESET_YYZ),
	ZX(PLANESET_ZZX),
	ZY(PLANESET_ZZY),
	X(XZ),
	Y(YZ),
	Z(XY),
	HORIZONTAL(EnumFacing.Plane.HORIZONTAL),
	VERTICAL(EnumFacing.Plane.VERTICAL);

	private final Axis first, second, third;

	Plane(Axis first, Axis second, Axis third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	Plane(Plane plane) {
		first = plane.first;
		second = plane.second;
		third = plane.third;
	}

	Plane(EnumFacing.Plane plane) {
		switch (plane) {
			case HORIZONTAL:
				first = Axis.X;
				second = Axis.X;
				third = Axis.Z;
				break;
			case VERTICAL:
				first = Axis.X;
				second = Axis.Y;
				third = Axis.X;
				break;
			default:
				first = Axis.X;
				second = Axis.Y;
				third = Axis.Z;
		}
	}

	public Axis getFirst() {
		return first;
	}

	public Axis getSecond() {
		return second;
	}

	public Axis getThird() {
		return third;
	}
}
