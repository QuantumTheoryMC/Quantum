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
 * Created file on 11/13/16 at 8:52 PM.
 *
 * This file is part of Quantum API
 */
package quantum.util.logger;

import static quantum.util.logger.Logger.Severity.LOG;

/**
 * @author link
 */
public interface Logger {

	void log(Severity severity, Throwable t, String entry);

	void print();

	default void log(Severity severity, Exception e, String entry) {
		log(severity, (Throwable) e, entry);
	}

	default void log(Error e, String entry) {
		log(Severity.ERROR, entry);
	}

	default void log(Severity severity, String entry) {
		log(severity, (Throwable) null, entry);
	}

	default void log(String entry) {
		log(LOG, entry);
	}

	enum Severity {
		LOG, ERROR, WARNING
	}

}

