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
package api.quantum.mod;

import api.quantum.log.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * @author link
 */
public abstract class QuantumMod implements Mod {

	/** The name of this Quantum Mod */
	protected final String name;
	protected final Type type;
	protected final Method[] methods;
	protected final boolean useHook;

	private volatile boolean running;
	private volatile long ms;
	private volatile int ns;
	private Thread modThread = new Thread(() -> {
		try {
			while (running) {
				while (!Thread.interrupted()) {
					run();
					Thread.sleep(ms, ns);
				}
			}
		} catch (InterruptedException e) {
			Logger.getLogger().log(this, e, "Hey, don't interrupt this Thread! Use QuantumMod#stop()!");
		}

	});

	protected QuantumMod(boolean useHook, String name, Type type, Method... methods) {
		this.name = name;
		this.type = type;
		this.methods = methods;
		this.useHook = useHook;
	}

	@Override
	public final String getName() {
		return name;
	}

	@NotNull
	@Contract(pure = true)
	@Override
	public final String getAPI() {
		return "Quantum";
	}

	@Override
	public final Method[] getModifiedMethods() {
		return methods;
	}

	@Override
	public final void start() {
		if (running == false) {
			if (!modThread.isAlive()) {
				modThread = new Thread(() -> {
					try {
						while (running) {
							run();
							Thread.sleep(ms, ns);
						}
					} catch (InterruptedException e) {
						Logger.getLogger().log(this, e, "Hey, don't interrupt this Thread! Use QuantumMod#stop()!");
					}

				});
			}
			modThread.setPriority(type.getPriority());
			modThread.start();
		}
	}

	public final void restart() {
		running = false;
		start();
	}

	protected final void setUpdateRate(long ms, int ns) {
		if (ms > 0) {
			this.ms = ms;
		}
		if (ns > 0) {
			this.ns = ns;
		}
	}

	@Override
	public final void stop() {
		// stop it from reiterating
		running = false;
	}

	/**
	 * A Mod Type. Defines the Threading priority of this Thread at startup
	 */
	protected enum Type {
		/**
		 * A Background mod, a mod not meant to really do much, or does not loop
		 * at all.
		 */
		BACKGROUND(Thread.MIN_PRIORITY + 2),
		/**
		 * A Normal mod, a mod that can loop on it's own or in the minecraft
		 * thread.
		 */
		NORMAL(Thread.NORM_PRIORITY),
		/**
		 * An Important mod, aa mod that loops with the lowest latency, though
		 * not guaranteed consistent.
		 */
		IMPORTANT(Thread.MAX_PRIORITY - 2);

		private final int priority;

		Type(int threadPriority) {
			priority = threadPriority;
		}

		int getPriority() {
			return priority;
		}
	}
}
