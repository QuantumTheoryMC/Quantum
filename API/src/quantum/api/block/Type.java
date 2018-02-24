/*
 * The MIT License
 *
 *  Copyright 2017 link.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 *   Created file on $date at $time.
 *
 *   This file is part of Quantum
 */

package quantum.api.block;

import quantum.api.model.Model;
import quantum.util.Property;
import quantum.util.pair.Pair;

import java.util.HashMap;
import java.util.Map;

import static quantum.util.pair.Pair.pair;

/**
 * Represents a type of Block, and holds information related to world data
 * for this Block, such as the sound a Block should make when an entity
 * moves across it.  Analogous to net.minecraft.block.material.Material
 *
 * List of vanilla properties:
 * <table>
 * <thead>
 * <th>Name</th>
 * <th>Values/Type</th>
 * <th>Description</th>
 * </thead>
 * <tbody>
 * <tr>
 * <td>flammable</td>
 * <td>boolean</td>
 * <td>A flag that determines each block for this type can burn
 * when fire is near it</td>
 * </tr>
 * <tr>
 * <td>map-color</td>
 * <td>{@link quantum.api.model.Model.Color}</td>
 * <td>The color that will be displayed on a map for this Block</td>
 * </tr>
 * <tr>
 * <td>resistance</td>
 * <td>
 * String:<br>
 * <ul>
 * <li>weak - this block can be moved by a piston</li>
 * <li>strong - this block cannot be moved by a piston</li>
 * <li>fragile - this block will break if moved by a piston</li>
 * </ul>
 * </td>
 * </tr>
 * </tbody>
 * </table>
 */
public class Type {

	public static final Type AIR   = new Type(pair("name", "air"), pair("flammable", false), pair("map-color", Model.Color.NONE), pair("mutable", true), pair("clip", false), pair("solid", false), pair("resistance", "strong"));
	public static final Type PLANT = new Type(pair("name", "plant"), pair("flammable", true), pair("map-color", Model.Color.GREEN));
	public static final Type STONE = new Type(pair("name", "stone"), pair("flammable", false), pair("map-color", Model.Color.BLACK));
	public static final Type WOOD  = new Type(pair("name", "wood"), pair("flammable", true), pair("map-color", Model.Color.BROWN), pair("clip", true), pair("solid", true));
	public static final Type GLASS = new Type(pair("name", "glass"), pair("flammable", false), pair("opacity", 0.5f), pair("map-color", Model.Color.WHITE), pair("clip", true), pair("solid", true));


	/**
	 * The map of names to properties for this Block.Type
	 */
	private Map<String, Property<?>> properties = new HashMap<>(7);

	/**
	 * Creates a new Type with the specified Properties.
	 *
	 * @param properties
	 * 		the properties
	 */
	public Type(Iterable<Property<?>> properties) {
		properties.forEach((property) -> this.properties.putIfAbsent(property.name(), property));
	}

	/**
	 * Creates a new Type with the given String<->Object pairs as
	 * properties.
	 *
	 * @param properties
	 * 		the pairs which will be converted to properties for this Type
	 */
	@SafeVarargs
	public Type(Pair<String, Object>... properties) {
		if (properties != null && properties.length > 0)
			for (Pair<String, Object> pair : properties) {
				this.properties.putIfAbsent(pair.getA(), new BlockProperty<>(pair.getA(), pair.getB()));
			}
	}

	/**
	 * Gets the given {@link Property} for this Type. A Property is always
	 * guaranteed non-null. When a property does not exist, the value for
	 * the property is simply null.
	 *
	 * @param property
	 * 		the property to get
	 *
	 * @return the property
	 */
	public final Property<?> get(String property) {
		return properties.getOrDefault(property, new BlockProperty<>(property, null));
	}

	/**
	 * Sets the given {@link Property} for this Type.
	 *
	 * @param property
	 * 		the property to set
	 * @param <V>
	 * 		the type of the Property
	 */
	protected final <V> Type set(Property<V> property) {
		properties.put(property.name(), property);
		return this;
	}

	/**
	 * Sets the given {@link Property} for this Type.
	 *
	 * @param name
	 * 		the name of the property to set
	 * @param value
	 * 		the value of the property to set
	 * @param <V>
	 * 		the type of the value of the property to set
	 */
	protected final <V> Type set(String name, V value) {
		properties.putIfAbsent(name, new BlockProperty<>(name, value));
		return this;
	}

}
