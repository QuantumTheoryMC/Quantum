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
package ltp.quantum.log;

import ltp.quantum.util.java.Synchronizer;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Stack;

// TODO refactor to make more OOP (Already done in jGUI)
public class Logger implements Thread.UncaughtExceptionHandler {

	protected static final Logger SYSTEM = new Logger(System.in, System.out, System.err);
	protected static final Stack<Entry> STACK = new Stack<>();
	//will use current soon
	private static Logger local = SYSTEM, current;
	private static volatile boolean running = true;
	private static Thread LOGGER = new Thread(() -> {
		while (running) {
			if (!STACK.isEmpty()) {
				Entry e = STACK.pop();
				log(e.ps, e.entry);
			}
		}
	});

	static {
		Thread.setDefaultUncaughtExceptionHandler(SYSTEM);
		Thread.currentThread().setUncaughtExceptionHandler(SYSTEM);
		synchronized (LOGGER) {
			LOGGER.setPriority(Thread.MIN_PRIORITY);
			LOGGER.setName("Logger Message Queue");
			LOGGER.setDaemon(true);
			LOGGER.start();
		}
	}

	protected final PrintStream out, err;
	protected final InputStream in;
	protected final Thread owner;

	public Logger(InputStream in, PrintStream out, PrintStream err) {
		this.in = in;
		this.out = out;
		this.err = err;
		owner = Thread.currentThread();
		local = this;
	}

	public static Logger getLogger() {
		synchronized (local) {
			return local;
		}
	}

	public static void setLogger(Logger l) {
		Synchronizer.lock(local).run(() -> {
			local = l;
		});
	}

	public static Logger getSystemLogger() {
		return SYSTEM;
	}

	public static void stop() {

	}

	public static void restart() {
		synchronized (LOGGER) {
			running = false;

			LOGGER = new Thread(() -> {
				while (running) {
					if (!STACK.isEmpty()) {
						Entry e = STACK.pop();
						log(e.ps, e.entry);
					}
				}
			});

			running = true;

			LOGGER.setPriority(Thread.MIN_PRIORITY);
			LOGGER.setName("Logger Message Queue");
			LOGGER.setDaemon(true);
			LOGGER.start();
		}
	}

	public static void log(Object caller, Throwable t, Severity severity, Severity.Level level, String string) {
		Synchronizer.lock(local).run(() -> {
			local.log(caller, t, severity, Severity.Context.EXTERNAL, level, string);
		});
	}

	protected static void log(PrintStream out, String string) {
		Synchronizer.lock(out).run(() -> {
			LocalDateTime time = LocalDateTime.now();
			out.print("[" + time.getHour() + ":" + time.getMinute() +
					          ":"
					          + time.getSecond() + "]" + string
					          + '\n');
		});
	}

	/**
	 * Gets the InputStream for this Logger
	 *
	 * @return the InputStream for this Logger
	 */
	public InputStream getInputStream() {
		synchronized (in) {
			return in;
		}
	}

	/**
	 * Gets the OUT PrintStream
	 *
	 * @return the OUT PrintStream
	 */
	public PrintStream getOutputStream() {
		synchronized (out) {
			return out;
		}
	}

	/**
	 * Gets the ERROR PrintStream
	 *
	 * @return the ERROR PrintStream
	 */
	public PrintStream getErrorStream() {
		synchronized (err) {
			return err;
		}
	}

	/**
	 * Gets the Thread that owns this Logger
	 *
	 * @return the Thread that owns this Logger
	 */
	public Thread getOwner() {
		return owner;
	}

	/**
	 * <p>
	 * Logs a String to the internal OUT PrintStream with the specified
	 * Severity
	 * and Context.
	 * </p>
	 *
	 * @param caller
	 * 		the caller of this method
	 * @param t
	 * 		the Throwable that was thrown, or null
	 * @param severity
	 * 		the Severity
	 * @param context
	 * 		the Severity Context
	 * @param level
	 * 		the Severity Level
	 * @param string
	 * 		the String to log
	 */
	public void log(Object caller, Throwable t, Severity severity, Severity.Context context, Severity.Level level,
	                String string) {
		if (t == null) {
			STACK.push(new Entry(out, "[" + context.name() + "::" + caller.getClass().getSimpleName() + "]["
					                          + severity.name() + "] " + string));
		} else {
			STACK.push(new Entry(err,
					                    "[" +
							                    context.name() +
							                    "::Thread(" +
							                    owner.getName() +
							                    ")::" +
							                    caller.getClass().getName() +
							                    "]["
							                    +
							                    level.name() +
							                    ' ' +
							                    severity.name() +
							                    "][" +
							                    t.getClass().getSimpleName() +
							                    "] " +
							                    string
							                    +
							                    ": " +
							                    t.getLocalizedMessage()));
		}

		if (t != null)
			synchronized (err) {
				t.printStackTrace(err);
			}
	}

	public void log(Object caller, Throwable t, Severity.Level level, String string) {
		log(caller, t, Severity.ERROR, Severity.Context.INTERNAL, level, string);
	}

	public void log(Object caller, Throwable t, String string) {
		log(caller, t, Severity.Level.FATAL, string);
	}

	public void log(Object caller, Severity severity, Severity.Level level, String string) {
		log(caller, null, severity, Severity.Context.INTERNAL, level, string);
	}

	public void log(Object caller, Severity severity, String string) {
		log(caller, severity, null, string);
	}

	public void log(Object caller, String string) {
		log(caller, Severity.LOG, string);
	}

	public void log(Object caller, Object print) {
		log(caller, print.toString());
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		log(t, e, Severity.ERROR, Severity.Context.UNKNOWN, Severity.Level.FATAL, e.getLocalizedMessage());
	}

	public enum Severity {
		WARNING,
		ERROR,
		LOG;

		public enum Context {
			INTERNAL,
			EXTERNAL,
			UNKNOWN
		}

		public enum Level {
			SEVERE,
			FATAL
		}

	}

	private final class Entry {

		PrintStream ps;
		String entry;

		Entry(PrintStream ps, String entry) {
			this.ps = ps;
			this.entry = entry;
		}
	}

}
