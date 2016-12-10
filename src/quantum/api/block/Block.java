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
 * Created file on 6/25/16 at 9:04 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.block;

import quantum.api.MinecraftObject;
import quantum.api.block.model.BlockModel;
import quantum.api.item.Item;
import quantum.mod.Mod;
import quantum.model.Model;
import quantum.util.Property;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author link
 */
public interface Block extends MinecraftObject, Serializable {

	int UNBREAKABLE = -1;

	long serialVersionUID = 1L;

	String getName();

	/**
	 * Gets the non-numeric ID of this block.
	 * <p>
	 * Since 1.8, numerical IDs have been deprecated, and as such, the API only
	 * allows named IDs. A named ID consists of a namespace (default is
	 * minecraft), followed by the name of this block, or a non-friendly name
	 * of
	 * this block, which is underscore-separated. For example, AcaciaWood's
	 * name
	 * is "acaciaWood", but the id is "minecraft:acacia_wood". It is also
	 * possible to get it by it's variant id: "minecraft:wood_4"
	 * </p>
	 * <p>
	 * The standard format is {@code namespace + ":" + name.define(" ", "_")}
	 * </p>
	 *
	 * @return the non-numeric ID of this block
	 */
	String getID();

	Mod getMod();

	/**
	 * Gets the width of this block in voxels.
	 * <p>
	 * In this API, voxels represent the ratio of texels (x,&nbsp;y) to pixels
	 * (u,&nbsp;v).
	 * </p>
	 * <p>
	 * Since textures can be any resolution at or higher than 8x8, the voxel
	 * size remains the same <em>relative</em> size. For example, even though
	 * you may use a resource pack that is 512x512, the size of a voxel remains
	 * the same constant ratio, so for our 512x512 textures, the size of a
	 * voxel is 512/8(=64) <em>texels</em>. Continued from the previous
	 * paragraph, this means that one voxel will have 64 texels squished into
	 * it!
	 * </p>
	 *
	 * @return the width of this block in voxels
	 *
	 * @see Model#getWidth()
	 */
	default double getWidth() {
		return getModel().getWidth();
	}

	/**
	 * Gets the height of this block in voxels.
	 * <p>
	 * In this API, voxels represent the ratio of texels (x,&nbsp;y) to pixels
	 * (u,&nbsp;v).
	 * </p>
	 * <p>
	 * Since textures can be any resolution at or higher than 8x8, the voxel
	 * size remains the same <em>relative</em> size. For example, even though
	 * you may use a resource pack that is 512x512, the size of a voxel remains
	 * the same constant ratio, so for our 512x512 textures, the size of a
	 * voxel is 512/8(=64) <em>texels</em>. Continued from the previous
	 * paragraph, this means that one voxel will have 64 texels squished into
	 * it!
	 * </p>
	 *
	 * @return the height of this block in voxels
	 *
	 * @see Model#getHeight()
	 */
	default double getHeight() {
		return getModel().getHeight();
	}

	/**
	 * Gets the depth of this block in voxels.
	 * <p>
	 * In this API, voxels represent the ratio of texels (x,&nbsp;y) to pixels
	 * (u,&nbsp;v).
	 * </p>
	 * <p>
	 * Since textures can be any resolution at or higher than 8x8, the voxel
	 * size remains the same <em>relative</em> size. For example, even though
	 * you may use a resource pack that is 512x512, the size of a voxel remains
	 * the same constant ratio, so for our 512x512 textures, the size of a
	 * voxel is 512/8(=64) <em>texels</em>. Continued from the previous
	 * paragraph, this means that one voxel will have 64 texels squished into
	 * it!
	 * </p>
	 *
	 * @return the depth of this block in voxels
	 *
	 * @see Model#getDepth()
	 */
	default double getDepth() {
		return getModel().getDepth();
	}

	/**
	 * Gets the size of this block in block units.
	 * <p>
	 * In this API, voxels represent the ratio of texels (x,&nbsp;y) to pixels
	 * (u,&nbsp;v).
	 * </p>
	 * <p>
	 * One block unit is 8<sup>3</sup> (512) voxels (3D pixels), and it is 8
	 * because the texture resolution of minecraft is 8-bit. A block unit
	 * is the ratio of voxels<sup>3</sup>, that is, the ratio of texels
	 * (<strong>tex</strong>ture <strong>el</strong>ements) (x,&nbsp;y) to
	 * pixels (u,&nbsp;v). For example, a
	 * block unit of {@code
	 * 0.5d} constitutes (8*0.5d)<sup>3</sup> = 4<sup>3</sup> (= 64) voxels.
	 * </p>
	 * <p>
	 * To make this easier to understand, if we take a look at the grass block,
	 * the absolute size of a voxel is what one of the texels on a face of the
	 * cube is (1/16 of the face).
	 * </p>
	 * <p>
	 * Since textures can be any resolution at or higher than 8x8, the voxel
	 * size remains the same <em>relative</em> size. For example, even though
	 * you may use a resource pack that is 512x512, the size of a voxel remains
	 * the same constant ratio, so for our 512x512 textures, the size of a
	 * voxel is 512/8(=64) <em>texels</em>. Continued from the previous
	 * paragraph, this means that one voxel will have 64 texels squished into
	 * it!
	 * </p>
	 *
	 * @return the size of this block in block units as the diameter of a
	 * circle.
	 */
	default double getSize() {
		return getWidth() * getHeight() * getDepth();
	}

	/**
	 * Gets the {@linkplain State} that this Block is in when it's {@linkplain
	 * quantum.api.world.BlockEntity BlockEntity} is initialized
	 * into the {@linkplain quantum.api.world.World World}.
	 *
	 * @return the State that this Block is in when it's BlockEntity is
	 * initialized
	 */
	State getDefaultState();

	Type getType();

	/**
	 * Gets the {@linkplain Model} that this block uses to be rendered in the
	 * World.
	 * <p>
	 * The {@linkplain #getWidth()}, {@linkplain #getHeight()}, and {@linkplain
	 * #getDepth()} convenience methods call the corresponding methods from
	 * this
	 * block via this method.
	 * </p>
	 *
	 * @return the Model that this block uses to be rendered in the World
	 */
	BlockModel getModel();

	/**
	 * Gets the tool required to harvest this block into an item, or null if
	 * this Block does not require a tool (or can use any tool to harvest).
	 *
	 * @return the tool required to harvest this block into an item
	 */
	Item getHarvestTool();

	/**
	 * Gets the amount of damage it takes to break this block.
	 * <p>
	 * For vanilla Minecraft, this value is 5.0f by default. The
	 * setHardness(float) takes the given value and multiplies it by 5.0f.
	 * Because of this, the API does a simple conversion to make it compatible
	 * with minecraft: {@code setHardness(getBreakThreshold() / 5.0f)}
	 * </p>
	 * <p>
	 * Regardless of which version of Minecraft, each bit of damage dealt to a
	 * given block is cumulative; when it hits or goes beyond this threshold,
	 * it
	 * will break. For example, if a block had a damage threshold of 5, and
	 * punching a block with the hand deals 1 damage, a counter for the damage
	 * an entity does to another entity increases with every punch, so after
	 * getDamageThreshold() / ((Item) hand).getDamage() damage, the block
	 * breaks.
	 * </p>
	 *
	 * @return the amount of damage before this block breaks.
	 */
	int getDamageThreshold();

	/**
	 * Gets the {@linkplain quantum.api.item.Item Item} that is dropped by this
	 * Block when harvested.
	 *
	 * @return the Item dropped by this Block
	 *
	 * @see #getHarvestTool()
	 */
	Item getDrop();

	/**
	 * Gets the variants of this block, including the block itself if
	 * applicable, with the
	 * name of the block (in the format "minecraft:" + block.getVariantName() +
	 * ":" + block.getVariantIndex())  mapped to the variant in a Map.
	 *
	 * @return the variants of this block
	 */
	Map<String, Block> getVariantMap();

	/**
	 * Gets the variants of this block, including the block itself, in the form
	 * of a list.
	 * <p>
	 * Minecraft recognizes variants by index, where each variant in this List
	 * can be accessed in order of variant index.
	 * </p>
	 *
	 * @return the variants of this block
	 */
	List<Block> getVariants();

	/**
	 * Gets the formal variant name of this Block. This usually consists of
	 * appending a number to the name as {@code getName() + "_" +
	 * getVariantName()}.
	 *
	 * @return the variant name of this Block
	 *
	 * @see #getVariantIndex()
	 * @see #getVariantMap()
	 * @see #getVariants()
	 */
	String getVariantName();

	/**
	 * Gets the variant index for this Block. This is the number in the order
	 * of variants for this Block, which is arbitrarily defined by the
	 * implementation.
	 *
	 * @return the variant index for this Block
	 */
	int getVariantIndex();

	/**
	 * Represents a possible State of a Block in the {@linkplain
	 * quantum.api.world.World World}. A common example is crops, which update
	 * their state after several ticks, and simulates "growing".
	 */
	interface State {

		/**
		 * Gets the cyclic index for this State. This value is used when
		 * calling
		 * {@link #next()}, in order to update the state of a given Block in
		 * the
		 * world.
		 *
		 * @return the cyclic index for this State
		 */
		int getIndex();

		/**
		 * Gets the value from the given {@link Property} if this State
		 * contains the given property.
		 * <p>
		 * The actual implementation of a State contains a map properties to
		 * values. The property value is a default value in this case, so it
		 * can
		 * be null: {@code getProperty(new BlockProperty("property_to_get",
		 * null))}
		 * </p>
		 *
		 * @param property
		 * 		the property to retrieve
		 * @param <T>
		 * 		the value held by the Property
		 *
		 * @return the value of the given property contained in this State, or
		 * property.getValue()
		 */
		<T> Property<T> getProperty(String property);

		/**
		 * Gets the list of possible states for this state, or if this is the
		 * only possible state, this state is returned as a List.
		 * <p>
		 * Each state has a finite number of possible states which can be
		 * represented for a given Block. The given list is either a logical
		 * list which calculates a state, or has concrete instances. This is
		 * because arbitrarily infinite values are possible, especially for
		 * Integer or Double. It would be impractical to create a possible state
		 * for every integer or double value from beginning to end.
		 * </p>
		 *
		 * @return the list of possible states for this state
		 */
		List<State> getStates();

		/**
		 * Gets the next state from this index, or 0 if this is the last
		 * index.
		 *
		 * @return the next state in order
		 */
		State next();
	}

	/**
	 * Represents a type of Block, and holds information related to world data
	 * for this Block, such as the sound a Block should make when an entity
	 * moves across it.  Analogous to net.minecraft.block.material.Material
	 */
	class Type {

		public static final  Property<Boolean> INVINCIBLE = new BlockProperty<>("invincible", Boolean.TRUE);
		private static final Map<String, Type> TYPES      = new HashMap<>();

		public static Type get(String name) {
			return TYPES.get(name);
		}

		private Map<String, Property<?>> properties = new HashMap<>();

		/**
		 * Creates a new Type with the specified Properties.
		 *
		 * @param properties
		 * 		the properties
		 */
		public Type(List<Property<?>> properties) {
			properties.forEach((property) -> this.properties.putIfAbsent(property.getName(), property));
		}

		/**
		 * Gets the given {@link Property} for this Type.
		 *
		 * @param property
		 * 		the property to get
		 * @param <V>
		 * 		the value of the Property
		 *
		 * @return the property
		 */
		@SuppressWarnings("unchecked")
		public final <V> Property<V> getProperty(String property) {
			return (Property<V>) properties.get(property);
		}

		protected final <V> void setProperty(Property<V> property) {
			properties.put(property.getName(), property);
		}

		protected final <V> void setProperty(String name, V value) {
			properties.putIfAbsent(name, new BlockProperty<>(name, value));
		}

	}

}
