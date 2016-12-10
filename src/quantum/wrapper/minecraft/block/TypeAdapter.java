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
 * Created file on 7/18/16 at 12:03 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import net.minecraft.block.material.MapColor;
import quantum.api.block.Block;
import quantum.model.Model;

/**
 * @author link
 */
public class TypeAdapter extends net.minecraft.block.material.Material {

	public TypeAdapter(Block block) {
		super(choose(block.getModel().getColor()));
	}

	private static MapColor choose(Model.Color color) {
		switch (color) {
			case RED:
				return MapColor.redColor;
			case BROWN:
				return MapColor.brownColor;
			case ORANGE:
				return MapColor.clothColor;
			case YELLOW:
				return MapColor.yellowColor;
			case GREEN:
				return MapColor.greenColor;
			case BLUE:
				return MapColor.blueColor;
			case LIGHT_BLUE:
				return MapColor.lightBlueColor;
			case MAGENTA:
			case INDIGO:
				return MapColor.magentaColor;
			case VIOLET:
				return MapColor.purpleColor;
			case BLACK:
				return MapColor.blackColor;
			case WHITE:
				return MapColor.snowColor;
			default:
				return MapColor.airColor;
		}
	}

}
