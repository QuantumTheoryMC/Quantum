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
 * Created file on 12/5/16 at 8:40 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.client.resources.model;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.IRegistry;

import java.lang.reflect.Field;

/**
 * @author link
 */
public final class QModelManager extends ModelManager {

	private IRegistry models = getModelRegistry(this);

	public QModelManager(TextureMap p_i46082_1_) {
		super(p_i46082_1_);
	}

	private static IRegistry getModelRegistry(ModelManager manager) {
		try {
			Field modelRegistry = ModelManager.class.getDeclaredField("modelRegistry");

			modelRegistry.setAccessible(true);
			IRegistry models = (IRegistry) modelRegistry.get(manager);
			modelRegistry.setAccessible(false);

			return models;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("This version of minecraft is unsupported: unable to retrieve model registry", e);
		}
	}

	public void addModel(QModel model) {
		models.putObject(model.getLocation(), model.getModel());
	}

	public void addModels(QModel[] models) {
		for (QModel model : models) {
			addModel(model);
		}
	}

	public static final class QModel {

		private final ModelResourceLocation location;
		private final IBakedModel           model;

		public QModel(ModelResourceLocation location, IBakedModel model) {
			this.location = location;
			this.model = model;
		}

		public static QModel build(ModelResourceLocation location, IBakedModel model) {
			return new QModel(location, model);
		}

		ModelResourceLocation getLocation() {
			return location;
		}

		IBakedModel getModel() {
			return model;
		}
	}


}
