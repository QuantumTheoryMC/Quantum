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
 * Created file on 7/19/16 at 2:07 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.event;

import java.util.*;

/**
 * The EventHandler handles all forms of Events for the Quantum API.
 *
 * @author link
 */
@SuppressWarnings("SuspiciousMethodCalls")
public enum EventHandler {
	;

	private static final Map<Class<? extends Event<?>>, List<Listener<?>>> LISTENERS       = new HashMap<>();
	private static final Map<Event<?>, List<Listener<?>>>                  BOUND_LISTENERS = new HashMap<>();

	private static final    Queue<Runnable> LISTENER_QUEUE = new ArrayDeque<>();
	private static volatile boolean         running        = false;

	private static final Runnable EVENT_HANDLER_LOOP = () -> {
		while (running) {
			synchronized (EventHandler.class) {
				// guard
				while (LISTENER_QUEUE.peek() == null) try {
					LISTENER_QUEUE.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// run event
				LISTENER_QUEUE.poll().run();
				// notify all event() caller threads
				LISTENER_QUEUE.notify();
			}
		}
	};

	private static Thread events = new Thread(EVENT_HANDLER_LOOP);

	static {
		events.setDaemon(true);
		events.setName("event handler");
		events.setPriority(Thread.MAX_PRIORITY);
		events.start();
	}

	public static <T extends Event<?>> void add(Class<T> eventType, Listener<?> listener) {
		if (listener != null) {
			List<Listener<?>> listeners;
			// make sure there is a list for the event type
			if (LISTENERS.containsKey(eventType))
				listeners = LISTENERS.get(eventType);
			else { // new event type; make a list
				listeners = new ArrayList<>(1);
				LISTENERS.put(eventType, listeners);
			}

			listeners.add(listener);
		}
	}

	/**
	 * Removes the given listener from the list of listeners for the specific
	 * event class. This would usually be invoked by an event trigger, but it
	 * can also be manually invoked if a reference to the correct listener is
	 * obtained.
	 *
	 * @param eventType
	 * 		the event type related to the given listener
	 * @param listener
	 * 		the listener to remove
	 */
	@SuppressWarnings("unchecked")
	public static void remove(Class<? extends Event<?>> eventType, Listener<?> listener) {
		//noinspection SuspiciousMethodCalls
		try {
			if (LISTENERS.containsKey(eventType))
				LISTENERS.getOrDefault(eventType, Collections.EMPTY_LIST).remove(listener);
		} catch (UnsupportedOperationException e) {
			// ignore; thrown by EMPTY_LIST
		}
	}

	public static void event(Event<?> event) {
		synchronized (EventHandler.class) {
			// if the queue's current result exists...
			while (LISTENER_QUEUE.peek() != null)
				// ...wait for the thread to finish, then add the event to the queue
				try {
					LISTENER_QUEUE.wait(0, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			if (LISTENERS.containsKey(event.getClass())) {
				LISTENER_QUEUE.add(null);
			}
			// let the handler thread continue and allow other threads
			// calling event to continue execution
			LISTENER_QUEUE.notify();
		}
	}

	public static synchronized void events(Event<?>... events) {
		for (Event<?> event : events) {
			event(event);
		}
	}

}
