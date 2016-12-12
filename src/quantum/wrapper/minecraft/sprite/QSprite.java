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
 * Created file on 12/6/16 at 5:58 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.sprite;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import quantum.model.texture.Texture;

import java.util.Arrays;
import java.util.List;

/**
 * @author link
 */
public class QSprite extends TextureAtlasSprite {

	public QSprite(String iconName, Texture[] frames) {
		super(iconName);
		boolean hasFrames = frames[0] != null;
		setIconWidth(hasFrames ? frames[0].getWidth() : 16);
		setIconHeight(hasFrames ? frames[0].getHeight() : 16);
		setFramesTextureData(list(convertAll(frames)));
	}

	//[0 0 0 0] index = 9, x=3, y=3; x = index % width, y = index - (x * height)
	//[0 0 0 0]
	//[0 0 0 0]
	private static int[][] convert(Texture frame) {
		if (frame == null) return Minecraft.getMinecraft()
		                                   .getTextureMapBlocks()
		                                   .func_174944_f()
		                                   .getFrameTextureData(0);
		int[][] texture = new int[frame.getWidth()][frame.getHeight()];
		int width = frame.getWidth();

		for (int y = 0; y < frame.getHeight(); y++) {
			for (int x = 0; x < width; x++) {
				texture[x][y] = frame.getTexel(x + (y * width)).getARGB();
			}
		}

		return texture;
	}

	private static int[][][] convertAll(Texture[] frames) {
		int[][][] frameSet = new int[frames.length][frames[0].getWidth()][frames[0]
				                                                                  .getHeight()];
		// copy data into frame array
		int i = 0;
		for (Texture texture : frames) {
			// convert texture to 2D array of texels
			frameSet[i] = convert(texture);
			i++;
		}

		return frameSet;
	}

	private static List<int[][]> list(int[][][] frames) {
		return Arrays.asList(frames);
	}


}
