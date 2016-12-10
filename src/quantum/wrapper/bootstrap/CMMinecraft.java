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
 * Created file on 12/5/16 at 5:03 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.bootstrap;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import quantum.bootstrap.ClassModifier;

/**
 * @author link
 */
public final class CMMinecraft implements ClassModifier {

	@Override
	public void modify(String className, CtClass modify) {
		String line;
		try {
			CtMethod main = modify.getDeclaredMethod("main");
			// insert Quantum's ResourcePack
			main.insertAt(558, "this.mcDefaultResourcePack = new quantum.wrapper.minecraft.client.resources.QuantumExtendedResourcePack((new ResourceIndex(p_i45547_1_.field_178744_c.field_178759_c, p_i45547_1_.field_178744_c.field_178757_d)).func_152782_a());\n");
			// insert Quantum's ModelManager
			main.insertAt(537, "this.modelManager = new quantum.wrapper.minecraft.client.resources.model.ModelManager(this.textureMapBlocks);");
			// insert Quantum's ResourceManager
			main.insertAt(477, "this.mcResourceManager = new quantum.wrapper.minecraft.client.resources.QuantumResourceManager(this.metadataSerializer_);");
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}
}
