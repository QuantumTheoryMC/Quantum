package api.quantum.util.java;

/**
 * @author link
 *         Created on 12/21/15 at 8:54 AM.
 */
public class Serial {

	private Serial() {
	}

	public static long createUID(int customID, Class<?> clazz) {
		return ((long) customID << 32) & (clazz.hashCode());
	}
}
