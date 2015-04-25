/**
 * 
 */
package mc.util;

import java.util.Collection;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyHelper;
import net.minecraft.block.properties.PropertyInteger;
import mc.APIObject;

/**
 * @author Link
 *
 */
public class Property<V> extends APIObject {

	private final V v;

	/**
	 * Constructs a new BlockProperty, which is a node of BlockProperties
	 * 
	 * @param v
	 *            the value to put in the property
	 */
	public Property(V v) {
		super("BP:[custom:" + v.getClass().getTypeName() + "]");
		this.v = v;
	}

	private Property(String name, V v) {
		super("BP:[" + name + "]");
		this.v = v;
	}

	public V getValue() {
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.MCObject#format()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IProperty format() {
		return (v instanceof Collection<?> ? toIProperty(getName(), (Property<? extends Collection<?>>) this) : null);
	}

	public static IProperty toIProperty(String name, Property<? extends Collection<?>> prop) {
		class MProperty extends PropertyHelper {

			protected transient Property<? extends Collection<?>> property;

			protected MProperty(String name, Class<?> valueClass, Property<? extends Collection<?>> prop) {
				super(name, valueClass);
				property = prop;
			}

			@Override
			public Collection getAllowedValues() {
				return property.getValue();
			}

			@Override
			public String getName(Comparable value) {
				return value.toString();
			}

		}

		return new MProperty(name, prop.getClass(), prop);
	}

	/**
	 * Converts a PropertyBool to a BlockProperty<Collection<Boolean>>.
	 * 
	 * @param bool
	 *            the PropertyBool to convert
	 * @return a converted PropertyBool as a BlockProperty<Collection<Boolean>>
	 */
	public static Property<Collection<Boolean>> format(PropertyBool bool) {
		return new Property<>(bool.getName(), bool.getAllowedValues());
	}

	/**
	 * Converts a PropertyDirection to a BlockProperty<Collection<Enum<?>>>.
	 * 
	 * @param dir
	 *            the PropertyDirection to convert
	 * @return a converted PropertyDirection as a
	 *         BlockProperty<Collection<Enum<?>>>
	 */
	public static Property<Collection<Enum<?>>> format(PropertyDirection dir) {
		return new Property<>(dir.getName(), dir.getAllowedValues());
	}

	/**
	 * Converts a PropertyEnum to a BlockProperty<Collection<Enum<?>>>.
	 * 
	 * @param en
	 *            the PropertyEnum to convert
	 * @return a converted PropertyEnum as a BlockProperty<Collection<Enum<?>>>
	 */
	public static Property<Collection<Enum<?>>> format(PropertyEnum en) {
		return new Property<>(en.getName(), en.getAllowedValues());
	}

	/**
	 * Converts a PropertyInteger to a BlockProperty<Collection<Enum<?>>>.
	 * 
	 * @param integer
	 *            the PropertyInteger to convert
	 * @return a converted PropertyInteger as a
	 *         BlockProperty<Collection<Enum<?>>>
	 */
	public static Property<Collection<Integer>> format(PropertyInteger integer) {
		return new Property<>(integer.getName(), integer.getAllowedValues());
	}

}
