/*
 * The MIT License
 *
 * Copyright 2015 link.
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
 */
package ltp.quantum.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines how a Block should work in the World. (Minecraft:
 * net.minecraft.block.material.Material).
 *
 * @author link
 */
public final class BlockType {

	public static final int FREE = 0, BREAK = 1, MOVE = 2;
	/**
	 * The Air BlockType
	 */
	public static final BlockType AIR = new BlockType(new MaterialWrapper(Material.air));
	/**
	 * The Grass BlockType
	 */
	public static final BlockType GRASS = new BlockType(new MaterialWrapper(Material.grass));
	/**
	 * The Dirt BlockType
	 */
	public static final BlockType DIRT = new BlockType(new MaterialWrapper(Material.ground));
	/**
	 * The Wood BlockType
	 */
	public static final BlockType WOOD = new BlockType(new MaterialWrapper(Material.wood));
	/**
	 * The Stone BlockType
	 */
	public static final BlockType STONE = new BlockType(new MaterialWrapper(Material.rock));
	/**
	 * The Iron BlockType
	 */
	public static final BlockType IRON = new BlockType(new MaterialWrapper(Material.iron));
	/**
	 * The Anvil BlockType
	 */
	public static final BlockType ANVIL = new BlockType(new MaterialWrapper(Material.anvil));
	/**
	 * The Water BlockType
	 */
	public static final BlockType WATER = new BlockType(new MaterialWrapper(Material.water));
	/**
	 * The Lava BlockType
	 */
	public static final BlockType LAVA = new BlockType(new MaterialWrapper(Material.lava));
	/**
	 * The Leaves BlockType
	 */
	public static final BlockType LEAVES = new BlockType(new MaterialWrapper(Material.leaves));
	/**
	 * The Plant BlockType
	 */
	public static final BlockType PLANT = new BlockType(new MaterialWrapper(Material.plants));
	/**
	 * The Vine BlockType
	 */
	public static final BlockType VINE = new BlockType(new MaterialWrapper(Material.vine));
	/**
	 * The Sponge BlockType
	 */
	public static final BlockType SPONGE = new BlockType(new MaterialWrapper(Material.sponge));
	/**
	 * The Wool BlockType
	 */
	public static final BlockType WOOL = new BlockType(new MaterialWrapper(Material.cloth));
	/**
	 * The Fire BlockType
	 */
	public static final BlockType FIRE = new BlockType(new MaterialWrapper(Material.fire));
	/**
	 * The Sand BlockType
	 */
	public static final BlockType SAND = new BlockType(new MaterialWrapper(Material.sand));
	/**
	 * The Redstone BlockType
	 */
	public static final BlockType REDSTONE = new BlockType(
			                                                      new MaterialWrapper(Material.circuits));
	/**
	 * The Carpet BlockType
	 */
	public static final BlockType CARPET = new BlockType(new MaterialWrapper(Material.carpet));
	/**
	 * The Glass BlockType
	 */
	public static final BlockType GLASS = new BlockType(new MaterialWrapper(Material.glass));
	/**
	 * The Redstone Light BlockType
	 */
	public static final BlockType REDSTONE_LIGHT = new BlockType(
			                                                            new MaterialWrapper(Material.redstoneLight));
	/**
	 * The Explosives BlockType
	 */
	public static final BlockType EXPLOSIVES = new BlockType(new MaterialWrapper(Material.tnt));
	/**
	 * The Prismarine BlockType
	 */
	public static final BlockType PRISMARINE = new BlockType(new MaterialWrapper(Material.air));
	/**
	 * The Ice BlockType
	 */
	public static final BlockType ICE = new BlockType(new MaterialWrapper(Material.ice));
	/**
	 * The Packed Ice BlockType
	 */
	public static final BlockType PACKED_ICE = new BlockType(
			                                                        new MaterialWrapper(Material.packedIce));
	/**
	 * The Snow BlockType
	 */
	public static final BlockType SNOW = new BlockType(new MaterialWrapper(Material.snow));
	/**
	 * The Snow Block BlockType
	 */
	public static final BlockType SNOW_BLOCK = new BlockType(
			                                                        new MaterialWrapper(Material.craftedSnow));
	/**
	 * The Cactus BlockType
	 */
	public static final BlockType CACTUS = new BlockType(new MaterialWrapper(Material.cactus));
	/**
	 * The Clay BlockType
	 */
	public static final BlockType CLAY = new BlockType(new MaterialWrapper(Material.clay));
	/**
	 * The Vine Fruit BlockType (Pumpkin, Melon, etc.)
	 */
	public static final BlockType VINE_FRUIT = new BlockType(new MaterialWrapper(Material.gourd));
	/**
	 * The Dragon Egg BlockType
	 */
	public static final BlockType DRAGON_EGG = new BlockType(
			                                                        new MaterialWrapper(Material.dragonEgg));
	/**
	 * The Portal BlockType
	 */
	public static final BlockType PORTAL = new BlockType(new MaterialWrapper(Material.portal));
	/**
	 * The Cake BlockType
	 */
	public static final BlockType CAKE = new BlockType(new MaterialWrapper(Material.cake));
	/**
	 * The Cob Web BlockType
	 */
	public static final BlockType COBWEB = new BlockType(new MaterialWrapper(Material.web));
	/**
	 * The Piston BlockType
	 */
	public static final BlockType PISTON = new BlockType(new MaterialWrapper(Material.piston));
	/**
	 * The Barrier (invisible block) BlockType
	 */
	public static final BlockType BARRIER = new BlockType(
			                                                     new MaterialWrapper(Material.barrier));
	private static final Map<Block, BlockType> BLOCK_TYPES = new HashMap<>(64);

	static {
		// add all the vanilla blocks to the list of BlockTypes
	}

	private final MaterialWrapper material;

	BlockType(AbstractBlock block) {
		this(new MaterialWrapper(block.getBlockWrapper().getWrappedBlock().getMaterial()));
	}

	BlockType(Block block, BlockColor color) {
		this(new MaterialWrapper(new Material(color.getMapColor())));
	}

	private BlockType(MaterialWrapper material) {
		this.material = material;
	}

	static void add(Block block, BlockType blockType) {
		BLOCK_TYPES.putIfAbsent(block, blockType);
	}

	public static BlockType get(Block block) {
		return BLOCK_TYPES.getOrDefault(block, AIR);
	}

	public static void add(AbstractBlock block) {
		BLOCK_TYPES.putIfAbsent(block, new BlockType(block));
	}

	public static void add(Block block, BlockColor color) {
		BLOCK_TYPES.putIfAbsent(block, new BlockType(block, color));
	}

	MaterialWrapper getMaterialWrapper() {
		return material;
	}

	public BlockColor getBlockColor() {
		return material.getBlockColor();
	}

	public boolean isOpaque() {
		return material.isOpaque();
	}

	public boolean isClippable() {
		return material.isClippable();
	}

	public boolean isSolid() {
		return material.isSolid();
	}

	public boolean isLiquid() {
		return material.isLiquid();
	}

	public boolean isPhasable() {
		return material.isPhasable();
	}

	public boolean isToolOptional() {
		return material.isToolOptional();
	}

	public boolean isWritable() {
		return material.isWritable();
	}

	public boolean isFlammable() {
		return material.isFlammable();
	}

	public enum BlockColor {

		TRANSPARENT(MapColor.airColor),
		GRASS(MapColor.grassColor),
		SAND(MapColor.sandColor),
		WOOL(MapColor.clothColor),
		EXPLOSIVES(MapColor.tntColor),
		ICE(MapColor.iceColor),
		IRON(MapColor.ironColor),
		LEAVES(MapColor.foliageColor),
		SNOW(MapColor.snowColor),
		CLAY(MapColor.clayColor),
		DIRT(MapColor.dirtColor),
		STONE(MapColor.stoneColor),
		WATER(MapColor.waterColor),
		WOOD(MapColor.woodColor),
		QUARTZ(MapColor.quartzColor),
		MESA_SAND(MapColor.adobeColor),
		MAGENTA(MapColor.magentaColor),
		LIGHT_BLUE(MapColor.lightBlueColor),
		YELLOW(MapColor.yellowColor),
		LIME(MapColor.limeColor),
		PINK(MapColor.pinkColor),
		GRAY(MapColor.grayColor),
		SILVER(MapColor.silverColor),
		CYAN(MapColor.cyanColor),
		PURPLE(MapColor.purpleColor),
		BLUE(MapColor.blueColor),
		BROWN(MapColor.brownColor),
		GREEN(MapColor.greenColor),
		RED(MapColor.redColor),
		BLACK(MapColor.blackColor),
		GOLD(MapColor.goldColor),
		DIAMOND(MapColor.diamondColor),
		LAPIS_LAZULI(MapColor.lapisColor),
		EMERALD(MapColor.emeraldColor),
		OBSIDIAN(MapColor.obsidianColor),
		NETHERRACK(MapColor.netherrackColor);

		MapColor mapColor;

		BlockColor(MapColor mapColor) {
			this.mapColor = mapColor;
		}

		static BlockColor identify(MapColor color) {
			switch (color.colorIndex) {
				case 0:
					return TRANSPARENT;
				case 1:
					return GRASS;
				case 2:
					return SAND;
				case 3:
					return WOOL;
				case 4:
					return EXPLOSIVES;
				case 5:
					return ICE;
				case 6:
					return IRON;
				case 7:
					return LEAVES;
				case 8:
					return SNOW;
				case 9:
					return CLAY;
				case 10:
					return DIRT;
				case 11:
					return STONE;
				case 12:
					return WATER;
				case 13:
					return WOOD;
				case 14:
					return QUARTZ;
				case 15:
					return MESA_SAND;
				case 16:
					return MAGENTA;
				case 17:
					return LIGHT_BLUE;
				case 18:
					return YELLOW;
				case 19:
					return LIME;
				case 20:
					return PINK;
				case 21:
					return GRAY;
				case 22:
					return SILVER;
				case 23:
					return CYAN;
				case 24:
					return PURPLE;
				case 25:
					return BLUE;
				case 26:
					return BROWN;
				case 27:
					return GREEN;
				case 28:
					return RED;
				case 29:
					return BLACK;
				case 30:
					return GOLD;
				case 31:
					return DIAMOND;
				case 32:
					return LAPIS_LAZULI;
				case 33:
					return EMERALD;
				case 34:
					return OBSIDIAN;
				case 35:
					return NETHERRACK;
				default:
					return TRANSPARENT;
			}
		}

		MapColor getMapColor() {
			return mapColor;
		}

	}

	static class MaterialWrapper {

		private final Material material;

		MaterialWrapper(Material material) {
			this.material = material;
		}

		Material getWrappedMaterial() {
			return material;
		}

		public int getPistonMobility() {
			return material.getMaterialMobility();
		}

		public BlockColor getBlockColor() {
			return BlockColor.identify(material.getMaterialMapColor());
		}

		public boolean isOpaque() {
			return material.blocksLight();
		}

		public boolean isClippable() {
			return !material.blocksMovement();
		}

		public boolean isSolid() {
			return material.isSolid();
		}

		public boolean isLiquid() {
			return material.isLiquid();
		}

		public boolean isPhasable() {
			return isSolid() && isLiquid();
		}

		public boolean isToolOptional() {
			return material.isToolNotRequired();
		}

		public boolean isWritable() {
			return material.isReplaceable();
		}

		public boolean isFlammable() {
			return material.getCanBurn();
		}

	}

}
