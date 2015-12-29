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

import api.quantum.log.Logger;
import api.quantum.meta.Untested;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.ClassFile;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.stream.Stream;

/**
 * @author link
 */
@Untested
class UniversalAgent implements java.lang.instrument.ClassFileTransformer {

	private final ClassModifier modifier;

	UniversalAgent(ClassModifier modifier) throws FileNotFoundException {
		this.modifier = modifier;
	}

	@Untested
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classDef, ProtectionDomain domain,
	                        byte[] classfileBuffer) throws IllegalClassFormatException {
		try {
			ClassPool cp = ClassPool.getDefault();
			Logger.getLogger().log(this, "Modifying class '" + className + "'.");
			ClassFile classFile = new ClassFile((DataInputStream) Stream.of(classfileBuffer));
			CtClass ct = cp.makeClass(classFile);
			try {
				return modifier.modify(ct).toBytecode();
			} catch (CannotCompileException e) {
				Logger.getLogger().log(this, e, Logger.Severity.Level.SEVERE, "There is something wrong with the ClassModifier for this type: " + e.getLocalizedMessage());
				return classfileBuffer;
			}
		} catch (IOException ex) {
			Logger.getLogger().log(this, ex, Logger.Severity.Level.SEVERE, "An Unexpected IOException occured: " + ex.getLocalizedMessage());
			return classfileBuffer;
		}

	}

}
