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
package quantum.bootstrap.cm;

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
		if (className.equals("net/minecraft/client/Minecraft")) try {
			// insert Quantum's ResourcePack
			modify.getDeclaredConstructors()[0].insertAt(558, true, "this.mcDefaultResourcePack = new quantum.wrapper.minecraft.client.resources.QuantumExtendedResourcePack((new net.minecraft.client.resources.ResourceIndex(p_i45547_1_.field_178744_c.field_178759_c, p_i45547_1_.field_178744_c.field_178757_d)).func_152782_a());\n");

			CtMethod startGame = modify.getDeclaredMethod("startGame");
			// insert Quantum's ResourceManager
			startGame.insertAt(477, true, "this.mcResourceManager = new quantum.wrapper.minecraft.client.resources.QuantumResourceManager(this.metadataSerializer_);");
			// insert Quantum's ModelManager
			startGame.insertAt(537, true, "this.modelManager = new quantum.wrapper.minecraft.client.resources.model.QModelManager(this.textureMapBlocks);");
			// initialize Quantum mods
			startGame.insertAfter("quantum.Quantum.main(new String[0]);");
		} catch (CannotCompileException | NotFoundException e) {
			e.printStackTrace();
		}
	}
}
