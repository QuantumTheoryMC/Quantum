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
import quantum.api.block.Type;
import quantum.api.model.Model;
import quantum.util.Property;

/**
 * @author link
 */
public class TypeAdapter extends net.minecraft.block.material.Material {


	private final boolean opaque;
	private final float   opacity;

	private final boolean clip;

	private final boolean solid;

	public TypeAdapter(Type type) {
		super(choose((Model.Color) type.get("map-color").value()));

		String resistance = (String) type.get("resistance").value();

		if (resistance != null)
			if (resistance.equals("weak")) setNoPushMobility();
			else if (resistance.equals("strong")) setImmovableMobility();

		if (type.get("flammable").value() == Boolean.TRUE) setBurning();

		Property<?> prop = type.get("mutable");
		if (prop.value() == Boolean.TRUE) setReplaceable();

		if (type.get("tool").value() != null) setRequiresTool();

		prop = type.get("opacity");
		opacity = prop.value() == null ? 1.0f : (Float) prop.value();
		opaque = opacity == 1.0f;

		prop = type.get("clip");
		clip = prop.value() == null || prop.value().equals(Boolean.TRUE);

		prop = type.get("solid");
		solid = prop.value() == null || prop.value().equals(Boolean.TRUE);


	}

	private static MapColor choose(Model.Color color) {
		if (color == null) return MapColor.airColor;
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
			case NONE:
			default:
				return MapColor.airColor;
		}
	}

	@Override
	public boolean isLiquid() {
		return !solid;
	}

	@Override
	public boolean isSolid() {
		return solid;
	}

	@Override
	public boolean blocksLight() {
		return opacity >= 0.95f; // 95% or higher opacity, grass won't grow
	}

	@Override
	public boolean blocksMovement() {
		return clip;
	}

	@Override
	public boolean isOpaque() {
		return opaque;
	}

}
