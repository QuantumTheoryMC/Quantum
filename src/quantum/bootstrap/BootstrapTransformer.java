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
 * Created file on 6/20/16 at 5:16 PM.
 *
 * This file is part of Quantum API
 */
package quantum.bootstrap;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author link
 */
class BootstrapTransformer implements java.lang.instrument.ClassFileTransformer {

	private final Map<String, ClassModifier> modifiers;

	BootstrapTransformer() {
		this.modifiers = new HashMap<>();
	}

	BootstrapTransformer(Map<String, ClassModifier> modifiers) {
		this.modifiers = modifiers;
	}

	void addModifier(String className, ClassModifier modifier) {
		modifiers.put(className, modifier);
	}

	void removeModifier(String className) {
		modifiers.remove(className);
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protection, byte[] classFile) throws IllegalClassFormatException {

		if (classFile == null || className == null) return null;

		ClassModifier modifier = modifiers.get(className);

		if (modifier == null) return null;

		try {
			ClassPool cp = ClassPool.getDefault();
			CtClass ct = cp.makeClass((InputStream) Stream.of(classFile));
			modifier.modify(className, ct);
			return ct.toBytecode();
		} catch (IOException | CannotCompileException e) {
			//ignore
			return null;
		}

	}
}
