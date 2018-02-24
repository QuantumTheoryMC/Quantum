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
 * Created file on 11/26/16 at 7:35 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block.model;

import quantum.api.model.Cube;
import quantum.api.model.Model;
import quantum.api.model.texture.Texture;
import quantum.api.resource.Resource;

/**
 * @author link
 */
public class BlockModel implements Model {

	/**
	 * The cube model for this BlockModel
	 */
	protected final Cube model;

	/**
	 * The Model.Color for this BlockModel
	 */
	protected final Color color;

	/**
	 * Creates a new BlockModel with the given Model.Color and Cube model.
	 *
	 * @param color
	 * 		the Model.Color of this BlockModel as shown from the map
	 * @param blockModel
	 * 		the Cube that makes this BlockModel
	 */
	public BlockModel(Color color, Cube blockModel) {
		this.color = color;
		this.model = blockModel;
	}

	@Override
	public final double getWidth() {
		return model.getWidth();
	}

	@Override
	public final double getHeight() {
		return model.getHeight();
	}

	@Override
	public final double getDepth() {
		return model.getDepth();
	}

	@Override
	public final Texture[] getTextures() {
		return model.getTextures();
	}

	@Override
	public Resource getResource() {
		return model.getResource();
	}

	@Override
	public final Color getColor() {
		return model.getColor();
	}
}
