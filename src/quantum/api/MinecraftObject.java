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
 * Created file on 7/9/16 at 10:41 AM.
 *
 * This file is part of Quantum API
 */
package quantum.api;

/**
 * @author link
 */
public interface MinecraftObject {

	/**
	 * Gets the name of this minecraft object.
	 * <p>
	 * Each minecraft object has a unique name. The name consists of a
	 * namespace, followed by a colon (:), and a lowercase,
	 * underscore-separated
	 * name: namespace:object_name
	 *
	 * The default namespace of all objects is {@code minecraft}, but any mods
	 * loaded by the API which define their own MinecraftObjects receive the
	 * name
	 * of
	 * their
	 * mod as the namespace. For example, if I make a mod MyMod, with name
	 * my_mod, and my_mod defines a MinecraftObject MyBlock with name
	 * my_MinecraftObject, the
	 * resulting ID
	 *
	 * @return the name of this MinecraftObject
	 */
	String getName();

	/**
	 * Gets the non-numeric ID of this MinecraftObject.
	 * <p>
	 * Since 1.8, numerical IDs have been deprecated, and as such, the API only
	 * allows named IDs. A named ID consists of a namespace (default is
	 * minecraft), followed by the name of this MinecraftObject, or a
	 * non-friendly name
	 * of
	 * this MinecraftObject, which is underscore-separated. For example,
	 * AcaciaWood's
	 * name
	 * is "acaciaWood", but the id is "minecraft:acacia_wood". It is also
	 * possible to getProperty it by it's variant id: "minecraft:wood_4"
	 * </p>
	 * <p>
	 * Each minecraft object has a unique ID. The ID consists of a
	 * namespace, followed by a colon (:), and a lowercase,
	 * underscore-separated
	 * name: namespace:object_name
	 *
	 * The default namespace of all objects is {@code minecraft}, but any mods
	 * loaded by the API which define their own MinecraftObjects receive the
	 * name
	 * of
	 * their
	 * mod as the namespace. For example, if I make a mod MyMod, with name
	 * my_mod, and my_mod defines a MinecraftObject My(Block | Entity |
	 * Dimension
	 * | etc.) with name my_MinecraftObject, the
	 * resulting ID would be "my_mod.my_MinecraftObject
	 * </p>
	 *
	 * @return the non-numeric ID of this MinecraftObject
	 */
	String getID();

}
