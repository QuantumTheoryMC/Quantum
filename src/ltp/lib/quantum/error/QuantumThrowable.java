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
package ltp.lib.quantum.error;

import ltp.lib.quantum.log.Logger;
import ltp.lib.quantum.util.java.Serial;

/**
 * This class is base class for all Throwables in the API. If you want to make
 * your own Throwables, it is required that you extend QuantumError or
 * QuantumException in order to show the most useful debugging information.
 *
 * @author link
 */
@Untested
public class QuantumThrowable extends Throwable {

	private static final long serialVersionUID = Serial.createUID(QuantumAPI.class.hashCode(), QuantumThrowable.class);
	/**
	 * The thrower of this Throwable
	 */
	private final Object thrower;

	/**
	 * The problems that caused this Throwable
	 */
	private final Problem[] problems;

	/**
	 * <p>
	 * Creates a new QuantumThrowable with the specified thrower and message.
	 * </p>
	 *
	 * @param thrower
	 * 		The object that threw this Throwable
	 * @param message
	 * 		The message of this Throwable
	 * @param problems
	 * 		The affected objects of this Throwable
	 */
	public QuantumThrowable(Object thrower, String message, Problem... problems) {
		this(thrower, message, null, problems);
	}

	/**
	 * <p>
	 * Creates a new QuantumThrowable with the specified thrower, message, and
	 * cause.
	 * </p>
	 *
	 * @param thrower
	 * 		The object that threw this Throwable
	 * @param message
	 * 		The message of this Throwable
	 * @param cause
	 * 		The cause of this Throwable
	 * @param problems
	 * 		The affected objects of this Throwable
	 */
	public QuantumThrowable(Object thrower, String message, Throwable cause, Problem... problems) {
		super(message, cause);
		this.thrower = thrower;
		this.problems = problems;
	}

	private static String createMessage(Problem problem) {
		return "[api.quantum.error].Problem:[method=\"" +
				       problem.getAffectedMethod().toGenericString() +
				       "\", object=\"" +
				       problem.getAffectedObject() +
				       "\", status=\"" +
				       problem.getObjectStatus().getStatus() +
				       "\", param=\"" +
				       problem.getAffectedParameter().getName() +
				       "\"]; ";
	}

	/**
	 * <p>
	 * Gets the thrower of this Throwable. The thrower is the object that threw
	 * this Throwable.
	 * </p>
	 *
	 * @return the thrower of this Throwable
	 */
	public final Object getThrower() {
		return thrower;
	}

	/**
	 * <p>
	 * Gets the problems of this Throwable. The Problems are objects that
	 * contain an affected object, the affected method, and the status of the
	 * affected object.
	 * </p>
	 *
	 * @return the problems of this Throwable
	 */
	public final Problem[] getProblems() {
		return problems;
	}

	/**
	 * <p>
	 * Prints this Throwable to the specified logger for convenience.
	 * </p>
	 *
	 * @param logger
	 * 		the logger to print to
	 */
	public void print(Logger logger) {
		Logger.Severity.Context context = thrower.getClass().getPackage().getName().contains("api.quantum") ?
				                                  Logger.Severity.Context.INTERNAL :
				                                  Logger.Severity.Context.EXTERNAL;
		Logger.Severity.Level level = problems.length > 1 ?
				                              Logger.Severity.Level.FATAL :
				                              Logger.Severity.Level.SEVERE;
		for (Problem problem : problems) {
			logger.log(thrower, this, Logger.Severity.ERROR, context, level, createMessage(problem));
		}
	}
}
