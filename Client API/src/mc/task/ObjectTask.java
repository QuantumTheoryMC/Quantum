/**
 * 
 */
package mc.task;

import java.util.concurrent.Callable;

/**
 * @author Link
 *
 */
public class ObjectTask<T> implements Callable<T>{

	@SuppressWarnings("unused")
	private ObjectTask() {}
	public ObjectTask(Runnable runnable) {
		synchronized(this) {
			runnable.run();
		}
	}

	@Override
	public synchronized T call() throws Exception {
		return null;
	}

}
