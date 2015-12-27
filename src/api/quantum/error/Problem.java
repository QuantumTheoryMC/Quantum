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

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p>
 * A Problem object represents a debugging problem. This could be an Error or
 * an
 * Exception. It could also be a glitch.
 * </p>
 * <p>
 * This object contains detailed information about the object that is being
 * affected, such as the method it is being used in, the object itself, and the
 * status of the object which details what kind of error, exception, or glitch
 * this was.
 *
 * @author link
 */
public class Problem {

	/**
	 * The affected method.
	 */
	private final Method relatedMethod;
	/**
	 * The affected parameter.
	 */
	private final Parameter param;
	/**
	 * The affected object's status.
	 */
	private final Status status;
	/**
	 * The affected object.
	 */
	private final Object problem;

	/**
	 * <p>
	 * Creates a new Problem with the affected object, method, and it's status.
	 * </p>
	 *
	 * @param related
	 * 		the method affected by this problem
	 * @param param
	 * 		the affected parameter of the method
	 * @param status
	 * 		the status of the object and the cause of this problem
	 * @param problem
	 * 		the object which is the problem
	 */
	public Problem(Method related, Parameter param, Status status, Object problem) {
		relatedMethod = related;
		this.status = status;
		this.problem = problem;
		this.param = param;
	}

	/**
	 * <p>
	 * Gets the method affected by this Problem.
	 * </p>
	 *
	 * @return the method affected by this Problem
	 */
	public Method getAffectedMethod() {
		return relatedMethod;
	}

	/**
	 * <p>
	 * Gets the parameter of the affected method that is affected by this
	 * Problem.
	 * </p>
	 *
	 * @return the affected parameter of this Problem
	 */
	public Parameter getAffectedParameter() {
		return param;
	}

	/**
	 * <p>
	 * Gets the affected object's Status. The status is the descriptor of this
	 * problem and details what kind of problem occurred.
	 * </p>
	 *
	 * @return the affected object's status
	 */
	public Status getObjectStatus() {
		return status;
	}

	/**
	 * <p>
	 * Gets the affected object. The object returned by this method is the
	 * object affected by this Problem.
	 *
	 * @return the affected object
	 */
	public Object getAffectedObject() {
		return problem;
	}

	/**
	 * A class representing the state of a problem.
	 *
	 * @author link
	 */
	public static class Status {

		/**
		 * A NULL Status is an object state in which the object reference is
		 * null.
		 */
		public static final Status NULL = new Status("[api]", "null");
		/**
		 * <p>
		 * An ILLEGAL_ARGUMENT is an object state in which the object is
		 * illegally passed to the affected method.
		 * </p>
		 */
		public static final Status ILLEGAL_ARGUMENT = new Status("[api]", "illegal-argument");
		/**
		 * <p>
		 * An INDEX_OUT_OF_BOUNDS is an object state in which the object in
		 * question tried to access an array or Array object at an index that
		 * does not exist.
		 * </p>
		 */
		public static final Status INDEX_OUT_OF_BOUNDS = new Status("[api]", "index-out-of-bounds");
		/**
		 * <p>
		 * A SECURITY_INTERVENTION is an object state in which the object tried
		 * to do an action out of the scope of it's permissions.
		 * </p>
		 */
		public static final Status SECURITY_INTERVENTION = new Status("[api]", "security-intervention");

		/**
		 * The status of the problem
		 */
		private final String status,
		/**
		 * the status's unmodified root without brackets
		 */
		statusRootName,
		/**
		 * The mod that owns this Status type
		 */
		modOwner;

		/**
		 * <p>
		 * Creates a Status from a String resembling the state of an object.
		 * </p>
		 *
		 * @param mod
		 * 		the mod that made this Status comes from
		 * @param status
		 * 		the status name
		 */
		public Status(String mod, String status) {
			this.status = ('<' + mod + ':' + status.trim().intern() + '>').intern();
			statusRootName = status;
			this.modOwner = mod;
		}

		/**
		 * <p>
		 * Creates a localized Status instance made for
		 * </p>
		 *
		 * @param status
		 * @param localized
		 */
		public Status(Status status, String localized) {
			this.status = ('<' +
					               status.statusRootName +
					               status.statusRootName.trim().replace(' ', '-') +
					               '.' +
					               localized +
					               '>');
			statusRootName = (status.statusRootName + '.' + localized).intern();
			this.modOwner = status.modOwner;
		}

		public Status ofMod(String mod) {
			return new Status(mod, this.status);
		}

		public final String getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return status;
		}

		@Override
		public int hashCode() {
			return status.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof Status)) {
				return false;
			}
			return ((Status) obj).status.contentEquals(status);
		}
	}

}
