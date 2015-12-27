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
package api.quantum;

import api.quantum.block.AbstractBlock;
import api.quantum.block.Block;
import api.quantum.log.Logger;

import java.util.Random;

/**
 * @author link
 */
public final class Test {

	public static void main(String... args) {
		Block block = AbstractBlock.getBlock("minecraft:dirt");
		Logger.getLogger().log(Test.class, "Are stats enabled: " + block.areStatsEnabled());
		Logger.getLogger().log(Test.class, "Can Update: " + block.canUpdate());
		Logger.getLogger().log(Test.class, "Ambient Occlusion: " + block.getAmbientOcclusion());
		Logger.getLogger().log(Test.class, "BlockColor: " + block.getBlockColor());
		Logger.getLogger().log(Test.class, "BlockType: " + block.getBlockType());
		Logger.getLogger().log(Test.class, "Brightness: " + block.getBrightness(0, 0, 0));
		Logger.getLogger().log(Test.class, "Drop Count: " + block.getDropCount(new Random()));
		Logger.getLogger().log(Test.class, "Full Name" + block.getFullName());
		Logger.getLogger().log(Test.class, "Hardness : " + block.getHardness());
	}
}
