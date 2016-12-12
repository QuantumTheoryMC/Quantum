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
 * Created file on 12/6/16 at 4:45 PM.
 *
 * This file is part of Quantum API
 */
package quantum.bootstrap.cm;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;
import quantum.bootstrap.ClassModifier;

/**
 * @author link
 */
public final class CMTextureMap implements ClassModifier {

	private static final String FUNC_174942_A = "if (p_174942_1_ == null)\n" + "        {\n" + "            throw new IllegalArgumentException(\"Location cannot be null!\");\n" + "        }\n" + "        else\n" + "        {\n" + "            TextureAtlasSprite var2 = (TextureAtlasSprite)this.mapRegisteredSprites.get(p_174942_1_);\n" + "\n" + "            if (var2 == null)\n" + "            {\n" + "if (var2.getResourceDomain().contains(\"quantum\")) {\n" + "var2 = quantum.wrapper.minecraft.Sprites.get(p_174942_1_);\n" + "} else {\n" + "                var2 = TextureAtlasSprite.func_176604_a(p_174942_1_);\n" + "                this.mapRegisteredSprites.put(p_174942_1_.toString(), var2);\n" + "            }\n" + "\n" + "            return var2;\n" + "        }";

	@Override
	public void modify(String className, CtClass modify) {
		if (className.equals("net/minecraft/client/renderer/texture/TextureMap")) {
			try {
				// replace func_174942_a
				modify.getDeclaredMethod("func_174942_a")
				      .setBody(FUNC_174942_A);
			} catch (CannotCompileException | NotFoundException e) {
				throw new RuntimeException("This version of Minecraft is unsupported: could not modify TextureMap.func_174942_a", e);
			}
		}
	}
}
