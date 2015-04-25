/**
 * 
 */
package mc;


/**
 * This is a class that every object in this API uses. It serializes every
 * object and gives each object a name. The serial is the order in which the
 * object was initialized.
 * 
 * If no object is initialized, the serialCount has a default value of 0.
 * 
 * @author Link
 *
 */
public abstract class APIObject {

	/** This object's name */
	protected final String name;

	/**
	 * Creates an MCObject with the specified name.
	 * 
	 * @param name
	 *            the name of this MCObject
	 */
	protected APIObject(String name) {
		this.name = name;
	}

	/**
	 * Gets this object's name.
	 * 
	 * @return the name of this object
	 */
	public String getName() {
		return name;
	}

	public abstract Object format();
}
