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
 * Created file on 12/7/16 at 1:01 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.resource.loader;

import quantum.api.model.texture.Image;
import quantum.api.model.texture.Texture;
import quantum.api.resource.TextureResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author link
 */
public class TextureLoader implements ResourceLoader<TextureResource, Texture> {

	protected static Texture.Texel[] toTexels(int[] argb) {
		Texture.Texel[] texels = new Texture.Texel[argb.length];

		for (int i = 0; i < texels.length; i++) {
			texels[i] = Texture.Texel.create(argb[i]);
		}

		return texels;
	}

	@Override
	public Texture load(TextureResource resource) {
		try {
			BufferedImage image = ImageIO.read(new File(resource.getPath()));
			Texture texture;
			final int width = image.getWidth(), height = image.getHeight();

			if (image == null) return null;

			// intentionally make the width and height of the loaded image
			// and texture resource incoherent so that IndexOutOfBoundsException
			// is thrown if the width and height do not match. Also a security
			// feature.
			int[] data = new int[width * height];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), data, 0, 1);
			texture = new Image(image.getWidth(), image.getHeight(), toTexels(data));

			return texture;
		} catch (IOException e) {
			// TODO send to logger
			// Quantum.getLogger().log(e, Severity.WARNING, "failed to load texture from " + resource);
			e.printStackTrace();
			return null;
		} catch (IndexOutOfBoundsException e) {
			// TODO send to logger
			throw new RuntimeException("Failed to load texture: the dimensions of the resource did not match the physical resource", e);
		}
	}


}
