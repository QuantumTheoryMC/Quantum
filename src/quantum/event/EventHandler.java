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
package quantum.event;

import quantum.util.Handle;

import java.util.ArrayDeque;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Queue;

/**
 * The EventHandler handles all forms of Events for the Quantum API.
 * <p>
 * Here is an example of a basic (thought not the most efficient) event system:
 * <pre>
 *      {@code
 *      public class MyBlock implements Listener<MyBlockEvent<MyBlockEntity>> {
 *              // ...
 *              @Override
 *              public void invoke(MyBlockEvent event) {
 *                  MyBlockEntity block = event.getObject();
 *                  if(event instanceof MyBlockHitEvent)
 *                      // ...
 *                  else if (\/* ... *\/)
 *                      // ...
 *                  // ...
 *              }
 *          }
 *      }
 * </pre>
 * </p>
 *
 * @author link
 */
public enum EventHandler {
	;

	private static final Map<Handle, Listener<?>> LISTENERS = new IdentityHashMap<>();

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

	/**
	 * <p>
	 * Adds a listener to be handled by the event handler that will be mapped
	 * to the given Handle instance. The internal listener mapping is an {@link
	 * IdentityHashMap}, so any object calling this method should have a
	 * permanent reference to the given Handle, or else the listener cannot be
	 * accessed, and will also cause unintentional resource leaks, and bugs
	 * which could prove deadly.
	 * </p>
	 *
	 * @param handle
	 * 		the handle that points to the listener
	 * @param listener
	 * 		the listener to add
	 */
	public static void add(Handle handle, Listener<?> listener) {
		LISTENERS.put(handle, listener);
	}

	/**
	 * Removes the listener associated with the given handle instance.
	 *
	 * @param handle
	 * 		the handle which points to the listener to remove
	 *
	 * @see #add(Handle, Listener) important handle info
	 */
	public static void remove(Handle handle) {
		LISTENERS.remove(handle);
	}

	/**
	 * Sends the given event to the given handle.
	 * <p>
	 * This method always waits for the internal Queue of event propagation to
	 * finish processing all events before adding the event to the queue. This
	 * creates a happens-before relationship with all subsequent events called,
	 * and the event about to be queued for processing.
	 * </p>
	 *
	 * @param handle
	 * 		the handle to receive the event
	 * @param event
	 * 		the event to send to the handle
	 * @param <E>
	 * 		the type of Event
	 */
	public static <E extends Event<?>> void event(Handle handle, E event) {
		synchronized (EventHandler.class) {
			// if the queue's current result exists...
			while (LISTENER_QUEUE.peek() != null)
				// ...wait for the thread to finish, then add the event to the queue
				try {
					LISTENER_QUEUE.wait(0, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			LISTENER_QUEUE.add(() -> event0(handle, event));
			// let the handler thread continue and allow other threads
			// calling event to continue execution
			LISTENER_QUEUE.notify();
		}
	}

	/**
	 * Sends all the given events to the given handle.
	 *
	 * @param handle
	 * 		the handle to receive the events
	 * @param events
	 * 		the events to give to the handle
	 *
	 * @see #event(Handle, Event)
	 * @see #event(Event, Handle...)
	 */
	public static synchronized void event(Handle handle, Event<?>... events) {
		for (Event<?> event : events) {
			event(handle, event);
		}
	}

	/**
	 * Sends the given event to the given targets.
	 *
	 * @param event
	 * 		the even to send
	 * @param targets
	 * 		the targets to receive the event
	 *
	 * @see #event(Handle, Event)
	 * @see #event(Handle, Event...)
	 */
	public static synchronized void event(Event<?> event, Handle... targets) {
		for (Handle handle : targets) {
			event(handle, event);
		}
	}


	@SuppressWarnings("unchecked")
	private static <E extends Event<?>, L extends Listener<E>> void event0(Handle handle, E event) {
		((L) LISTENERS.getOrDefault(handle, (e) -> System.err.println("[empty listener invoked]: no listener exists for event "
				                                                              .concat(e.toString()))))
				.invoke(event);
	}
}
