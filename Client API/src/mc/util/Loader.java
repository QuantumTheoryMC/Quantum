/**
 * 
 */
package mc.util;

import java.io.File;

/**
 * @author Link
 *
 */
public abstract class Loader<T, R, W> {

	public Loader() {
	}
	
	public abstract T load(File url);
	
	public abstract R read();
	
	public abstract void write(W w);
}
