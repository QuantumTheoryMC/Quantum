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
 *   Created file on ${DATE} at ${TIME}.
 *
 *   This file is part of ${PROJECT_NAME}
 */

/**
 * This package contains various classes which access the actual game classes. The package structure in this package
 * is parallel to the MCP package structure for simplicity. This package contains classes that are either managers or
 * object adapters.
 * <p>
 * An Accessor object is an object that wraps a Minecraft object and makes it accessible to Quantum API (and mods).
 * These objects simply hold a reference to a Minecraft object and implement the appropriate Quantum API interface
 * methods to interact with the Minecraft object as if they were created by the API.
 * </p>
 * <p>
 * An Adapter object is an object that wraps a Quantum object and makes it accessible to Minecraft. These objects simply
 * hold a reference to a Quantum object and implement the appropriate Minecraft methods so that Minecraft can properly
 * interact with API object as if it were created by Minecraft.
 * </p>
 * <p>
 * A Hybrid object is an object that holds the data for a Quantum object or a Minecraft object and is accessible to both
 * Quantum API and the Minecraft platform. These objects simply hold only the data of either a Minecraft object or a
 * Quantum API object. This Hybrid object can then be accessed from either the API or the Minecraft platform. The data
 * must first be converted into an object for the appropriate environment, however, if these Hybrid objects do not
 * directly implement any classes (to prevent method conflicts). Otherwise, a simple cast will suffice.
 * </p>
 * <p>
 * For any kind of object that is required to be transferred between the two environments (the API or Minecraft), it is
 * guaranteed that, given one object, <em>a</em>, and the same object converted to an Adapter/Accessor and retrieved by
 * the API, <em>b</em>, {@code a != b} always holds true and {@code a.equals(b)} is not guaranteed to be true. This sort
 * of scenario may be encountered with the given example code snippet:
 * <p>
 * <blockquote>
 * <pre>
 *         {@code
 *         //...
 *         // our block
 *         Block block = Blocks.get("MyBlock");
 *         // the current world
 *         World world = World.getCurrentWorld();
 *         // where we will store the returned block
 *         Block myBlock;
 *
 *         // set a block
 *         world.setBlock(..., block);
 *         myBlock = world.getBlock(...);
 *
 *          //...
 *          }
 *          </pre>
 * </blockquote>
 * <p>
 * In the above snippet, we set a block in the world and retrieve that same block and store it in a variable named
 * {@code myBlock}. The object stored in myBlock will be guaranteed to be the same object as variable {@code block}.
 * However,
 * </p>
 *
 * @author link
 */
package quantum.wrapper.minecraft;