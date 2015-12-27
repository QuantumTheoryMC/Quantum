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
package api.quantum.error;

import api.quantum.log.Logger.Severity;
import api.quantum.util.java.Serial;

/**
 * <p>
 * Thrown when an unexpected Error occurs. This follows the same rules as
 * Error.
 * This is usually thrown by the API itself when it encounters an unexpected
 * error. It might be thrown when a part of minecraft is incompatible with the
 * API.
 * </p>
 *
 * @author link
 */
public class UnexpectedError extends QuantumError {

	private static final long serialVersionUID = Serial.createUID(QuantumError.class.hashCode(), UnexpectedError.class);

	public UnexpectedError(Object creator, String message) {
		super(creator, message);
	}

	public UnexpectedError(Object creator, Severity severity, Severity.Level level, String message) {
		super(creator, severity, level, message);
	}

}
