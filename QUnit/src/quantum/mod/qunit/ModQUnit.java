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
 * Created file on 11/9/16 at 7:58 PM.
 *
 * This file is part of Quantum API
 */
package quantum.mod.qunit;


import quantum.Blocks;
import quantum.Quantum;
import quantum.api.block.Block;
import quantum.api.block.BlockBuilder;
import quantum.mod.Mod;
import quantum.mod.qunit.model.CustomModel;
import quantum.model.Model;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author link
 */
public final class ModQUnit implements Mod {

	@Override
	public void load(Quantum quantum) {

		quantum.define(new BlockBuilder().name("testBlock")
		                                 .id("test_block")
		                                 .model(new CustomModel())
		                                 .variant("textBlock")
		                                 .type(new Block.Type(Collections.emptyList()))
		                                 .variantIndex(0)
		                                 .variants(Collections.emptyMap())
		                                 .drops(null) // TODO
		                                 .states(Collections.emptyList())
		                                 .build());
		run(quantum);
	}

	public void run(Quantum quantum) {
		PrintStream out = System.out;
		PrintStream err = System.err;
		out.println("[QUnit] running...");
		Block block;
		try {
			out.println("[QUnit] Begin Section \"Blocks\"");
			block = Blocks.get("test_block");
			out.println("\t[QUnit] quantum.api.block.Blocks.get(String)...pass");
		} catch (Exception e) {
			err.println("[QUnit] Blocks.get(String)...fail");
			err.println("[QUnit] Stacktrace:");
			e.printStackTrace();
			block = null;
		}

		if (block != null) {
			try {
				out.println("\t[QUnit] block.name: \"" + block.getName() + "\"");
				out.println("\t[QUnit] quantum.api.block.Block.getName()... pass");
			} catch (Exception e) {
				err.println("[QUnit] quantum.api.block.Block.getName()... fail");
				err.println("[QUnit] Stacktrace:");
				e.printStackTrace();
			}

			try {
				out.println("[QUnit] Testing BlockBuilder...");
				block = new BlockBuilder().name("custom_block")
				                          .mod(this)
				                          .model(null) // TODO
				                          .states(null) // TODO
				                          .harvestTool(null) // TODO
				                          .type(null) // TODO
				                          .drops(null) // TODO
				                          .variant("custom_block")
				                          .variantIndex(0)
				                          .variants(null) // TODO
				                          .build();
				quantum.define(block);
				Model model = block.getModel();
				out.println("\t[QUnit] quantum.model.Model.getColor(): \"" + model.getColor() + "\"");
				out.println("\t[QUnit] quantum.model.Model.getWidth(): \"" + model.getWidth() + "\"");
				out.println("\t[QUnit] quantum.model.Model.getHeight(): \"" + model.getHeight() + "\"");
				out.println("\t[QUnit] quantum.model.Model.getDepth(): \"" + model.getDepth() + "\"");
				out.println("\t[QUnit] quantum.model.Model.getTextures(): \"" + Arrays.toString(model.getTextures()) + "\"");
				out.println("[QUnit] quantum.model.Model...pass");
			} catch (Exception e) {
				err.println("[QUnit] quantum.model.Model... fail");
				err.println("[QUnit] Stacktrace:");
				e.printStackTrace();
			}

		} else {
			err.println("[QUnit] Section \"Blocks\" failed at initialization");
		}


	}

	@Override
	public void unload(Quantum quantum) {
	}

	@Override
	public String getName() {
		return null;
	}

}
