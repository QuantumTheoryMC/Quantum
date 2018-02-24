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
import quantum.api.item.Item;
import quantum.api.mod.Mod;
import quantum.api.model.Model;
import quantum.api.world.tick.Tickable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A Block is an object composed of discrete units called voxels.
 * <p>
 * There is no true standard to voxels, nor does Minecraft maintain any standard for defining a voxel.
 * The Quantum API defines its own standard for voxels.
 * </p>
 * <p>
 * Quantum API defines a <em>voxel</em> as a three-dimensional, discrete, arbitrary, absolute unit. The <em>rendered
 * size</em> of a voxel remains constant, but the ratio of <em>texels</em> (texture-elements or the pixels in a texture)
 * per voxel is <em>proportionally relative</em>. The number of texels that define a voxel is known when the texture
 * size is known, as mentioned above. By default, the Minecraft Resource Pack textures are 16x16 textures (16<sup>2
 * </sup> texels).
 * </p>
 * <p>
 * The default Minecraft Resource Pack is the reference size for a voxel: the way a texel appears on
 * screen with a 16x16 resource pack is the absolute size, look, and feel of a voxel; the ratio of voxels to texels is
 * 1:1. This means that 1 voxel has a <em>resolution</em> of 1 texel. If we used a 64x64 Resource Pack, <em>one voxel
 * would have a</em> resolution <em>of 64 / 16 = 4 texels, but if you compared the voxel to a voxel rendered with 16x16
 * textures, they should be rendered the exact same size.</em>
 * </p>
 * <p>
 * The expression for calculating the resolution of a voxel is {@code texture.width / 16}. Since textures in Minecraft
 * are always (and must be) square, the width or height of the texture can be used in this expression.
 * </p>
 * <p>
 * The Quantum Wrapper won't properly sample a voxel face even with a non-uniform (non-power-of-two) resolution such as
 * two or three by linearly interpolating between the colors to create the "best fit" for the voxel. For example, if a
 * 48x48 texture pack is used, you will have 3 texels to fit on the face of a voxel. There will be texel clipping for
 * these kinds of textures. A Voxel face is sampled as even squares, so if, for example, a voxel in the corner is sampled,
 * you will see one fourth of a texel in the corner, two halves of a texel along the side and adjacent to the fourth of
 * a texel in the corner, and one whole texel in the rest of the space.
 * </p>
 *
 * @author link
 */
public interface Block extends MinecraftObject, Serializable, Tickable {

	// serialVersionUID: uses the integer values of the initials of this class
	// (plus the first super class since this class has only one initial)
	long serialVersionUID = ((long) 'M' | (long) 'O') << 31 | 'B';

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
	@Override
	String getID();

	/**
	 * Gets the Mod that this Block belongs to. For blocks defined by the game, the placeholder,
	 * {@link quantum.api.mod.minecraft.ModMinecraft}, is returned. If this Block was redefined, this method gets
	 * the Mod that redefined this Block.
	 *
	 * @return
	 */
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
	 * @see Model#getWidth()
	 */
	default double getWidth() {
		return 1.0d;
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
	 * @see Model#getHeight()
	 */
	default double getHeight() {
		return 1.0d;
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
	 * @see Model#getDepth()
	 */
	default double getDepth() {
		return 1.0d;
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
	 * Gets the {@link Type} for this Block. This object provides generally
	 * applicable properties to this Block at runtime. Properties are at the
	 * discretion of the implementation and are not mandatory. Any convention is
	 * defined by the community.
	 * <p>
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	Type getType();

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
	 * @see #getHarvestTool()
	 */
	Item getDropItem();

	/**
	 * Gets the number of items dropped when this Block is broken
	 *
	 * @return the number of items dropped when this Block is broken
	 */
	default int getDropCount() {
		return 1;
	}

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

}
