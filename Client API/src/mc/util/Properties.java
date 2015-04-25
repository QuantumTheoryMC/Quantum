/**
 * 
 */
package mc.util;

import java.util.Collection;
import java.util.HashMap;

import mc.APIObject;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;

/**
 * @author Link
 *
 */
public class Properties extends APIObject {

	protected HashMap<String, Property<?>> nodes = new HashMap<>();

	public Properties(String name) {
		super("Properties:[" + name + "]");
	}

	public synchronized Property<?> getProperty(String key) {
		return nodes.get(key);
	}

	/**
	 * Gets the specified property if not defined in the internal HashMap.
	 * 
	 * @param key
	 *            the property to retrieve
	 * @return the Property mapped to key
	 */
	public synchronized Property<?> getPropIfndef(String key) {
		return nodes.getOrDefault(key, new Property<>(new Object()));
	}

	/**
	 * Overwrites a property.
	 * 
	 * @param key
	 *            the property to overwrite
	 * @param val
	 *            the new property
	 */
	public synchronized void setProperty(String key, Property<?> val) {
		nodes.put(key, val);
	}

	/**
	 * Adds a property if it is absent.
	 * 
	 * @param key
	 *            the property mapping
	 * @param val
	 *            the property
	 */
	public synchronized void addProperty(String key, Property<?> val) {
		nodes.putIfAbsent(key, val);
	}

	/**
	 * Removes a property.
	 * 
	 * @param key
	 *            the property to remove
	 * @return the removed property
	 */
	public synchronized Property<?> removeProperty(String key) {
		return nodes.remove(key);
	}

	/**
	 * Copies a property from one node to another. It does not delete the
	 * original.
	 * 
	 * @param key
	 *            the original property
	 * @param dest
	 *            the destination to copy to
	 */
	public synchronized void copyProperty(String key, String dest) {
		nodes.put(dest, nodes.get(key));
	}

	/**
	 * Gets the properties held by this BlocKProperties object.
	 * 
	 * @return the properties
	 */
	public synchronized Property<?>[] getProperties() {
		return nodes.values().toArray(new Property<?>[nodes.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.MCObject#format()
	 */
	@Override
	public Material format() {

		class MBlockProperties extends Material {

			protected MBlockProperties(Properties props) {
				super((MapColor) props.getProperty("block.mapColor").getValue());
			}

		}
		
		MBlockProperties mbp = new MBlockProperties(this);
		return mbp;
	}

	@SuppressWarnings("unchecked")
	public IProperty[] toIProperties(String... target) {
		IProperty[] iprops = new IProperty[nodes.size()];
		int i = 0;
		for (Property<?> prop : nodes.values().toArray(new Property<?>[nodes.size()])) {
			iprops[i] = Property.toIProperty(target[i], (Property<? extends Collection<?>>)prop);
			i++;
		}
		return iprops;
	}

	/**
	 * Converts a {@link net.minecraft.block.material.Material} to a
	 * {@code BlockProperties} object.
	 * 
	 * @param mat
	 *            the Material to convert
	 * @return the converted material
	 */
	public static Properties format(Material mat) {
		return new Properties("[" + Integer.toString(mat.hashCode()).substring(0, 4) + "] "
				+ mat.toString().split("^@$")[1]) {

		};
	}
}
