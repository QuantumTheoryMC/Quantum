/*
 * The MIT License
 *
 * Copyright 2017 link.
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
 * Created file on 1/2/17 at 1:35 PM.
 *
 * This file is part of Quantum API
 */
package quantum.agent.cm;

import javassist.*;
import quantum.agent.ClassModifier;

/**
 * @author link
 */
public final class CMModelBakery implements ClassModifier {

	private static final String LOAD_MODEL                 = "String var3 = p_177594_1_.getResourcePath();\n" + "if (\"builtin/generated\".equals(var3))\n" + "{\n" + "    return MODEL_GENERATED;\n" + "}\n" + "else if (\"builtin/compass\".equals(var3))\n" + "{\n" + "    return MODEL_COMPASS;\n" + "}\n" + "else if (\"builtin/clock\".equals(var3))\n" + "{\n" + "    return MODEL_CLOCK;\n" + "}\n" + "else if (\"builtin/entity\".equals(var3))\n" + "{\n" + "    return MODEL_ENTITY;\n" + "}\n" + "else if (p_177594_1_.getResourceDomain().startsWith(\"quantum\")) {\n" + "final ModelBlock model = quantum.wrapper.minecraft.block.model.BlockModels.get(p_177594_1_); return model != null ? model : this.loadModel(ModelBakery.MODEL_MISSING);\n" + "}\n" + "else\n" + "{\n" + "    Object var2;\n" + "    if (var3.startsWith(\"builtin/\"))\n" + "    {\n" + "         String var4 = var3.substring(\"builtin/\".length());\n" + "         String var5 = (String)BUILT_IN_MODELS.get(var4);\n" + "         if (var5 == null)\n" + "         {\n" + "              throw new FileNotFoundException(p_177594_1_.toString());\n" + "         }\n" + "              var2 = new StringReader(var5);\n" + "    }\n" + "    else\n" + "    {\n" + "         IResource var9 = this.resourceManager.getResource(this.getModelLocation(p_177594_1_));\n" + "         var2 = new InputStreamReader(var9.getInputStream(), Charsets.UTF_8);\n" + "    }\n" + "    ModelBlock var11;\n" + "            try\n" + "            {\n" + "                ModelBlock var10 = ModelBlock.deserialize((Reader)var2);\n" + "                var10.field_178317_b = p_177594_1_.toString();\n" + "                var11 = var10;\n" + "            }\n" + "            finally\n" + "            {\n" + "                ((Reader)var2).close();\n" + "}\n" + "            return var11;}\n" + "}\n";
	private static final String GET_MODEL_BLOCK_DEFINITION = "ResourceLocation var2 = this.getBlockStateLocation(p_177586_1_);\n" + "        ModelBlockDefinition var3 = (ModelBlockDefinition)this.field_177614_t.get(var2);\n" + "        if (var3 == null)\n" + "{\n" + "if (var2.getResourceDomain().startsWith(\"quantum\")) {\n" + "var3 = quantum.wrapper.minecraft.client.model.BlockModels.get(var2);\n" + "this.field_177614_t.put(var2, var3);\n" + "ArrayList var4 = Lists.newArrayList();\n" + "            try\n" + "            {\n" + "                Iterator var5 = this.resourceManager.getAllResources(var2).iterator();\n" + "                while (var5.hasNext())\n" + "                {\n" + "                    IResource var6 = (IResource)var5.next();\n" + "                    InputStream var7 = null;\n" + "                    try\n" + "                    {\n" + "                        var7 = var6.getInputStream();\n" + "                        ModelBlockDefinition var8 = ModelBlockDefinition.func_178331_a(new InputStreamReader(var7, Charsets.UTF_8));\n" + "                        var4.add(var8);\n" + "                    }\n" + "                    catch (Exception var13)\n" + "                    {\n" + "                        throw new RuntimeException(\"Encountered an exception when loading model definition of \\'\" + p_177586_1_ + \"\\' from: \\'\" + var6.func_177241_a() + \"\\' in resourcepack: \\'\" + var6.func_177240_d() + \"\\'\", var13);\n" + "                    }\n" + "                    finally\n" + "                    {\n" + "                        IOUtils.closeQuietly(var7);\n" + "                    }\n" + "                }\n" + "            }\n" + "            catch (IOException var15)\n" + "            {\n" + "                throw new RuntimeException(\"Encountered an exception when loading model definition of model \" + var2.toString(), var15);\n" + "            }\n" + "            var3 = new ModelBlockDefinition(var4);\n" + "            this.field_177614_t.put(var2, var3);\n" + "        }\n" + "        return var3;\n";

	@Override
	public void modify(String className, CtClass modify) {
		if (className.equals("net/minecraft/client/resources/model/ModelBakery")) {
			try {
				// modify ModelBakery.loadModel
				final ClassPool cp = modify.getClassPool();
				final CtMethod loadModel = modify.getDeclaredMethod("loadModel", new CtClass[]{cp.getCtClass("ResourceLocation")});
				loadModel.setBody(LOAD_MODEL);
				// modify ModelBakery.getModelBlockDefinition
				final CtMethod getModelBlockDefinition = modify.getDeclaredMethod("getModelBlockDefinition");
				getModelBlockDefinition.setBody(GET_MODEL_BLOCK_DEFINITION);
			} catch (NotFoundException e) {
				throw new RuntimeException(e);
			} catch (CannotCompileException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
