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
 * Created file on 11/6/16 at 6:18 PM.
 *
 * This file is part of Quantum API
 */
package quantum;

import quantum.api.block.Block;

/**
 * @author link
 */
public enum Blocks {
	;

	public static Block get(String id) {
		return Quantum.getBlock(id);
	}

	static Block AIR;
	static Block STONE;
	static Block GRASS;
	static Block DIRT;
	static Block COBBLE_STONE;
	static Block PLANKS;
	static Block SAPLING;
	static Block BEDROCK;
	static Block FLOWING_WATER;
	static Block WATER;
	static Block FLOWING_LAVA;
	static Block LAVA;
	static Block SAND;
	static Block GRAVEL;
	static Block GOLD_ORE;
	static Block IRON_ORE;
	static Block COAL_ORE;
	static Block LOG;
	static Block LEAVES;
	static Block SPONGE;
	static Block GLASS;
	static Block LAPIS_ORE;
	static Block LAPIS_BLOCK;
	static Block DISPENSER;
	static Block SANDSTONE;
	static Block NOTEBLOCK;
	static Block BED;
	static Block GOLDEN_RAIL;
	static Block DETECTOR_RAIL;
	static Block STICKY_PISTON;
	static Block WEB;
	static Block TALL_GRASS;
	static Block DEAD_BUSH;
	static Block PISTON;
	static Block PISTON_HEAD;
	static Block WOOL;
	static Block PISTON_EXTENSION;
	static Block YELLOW_FLOWER;
	static Block RED_FLOWER;
	static Block BROWN_MUSHROOM;
	static Block RED_MUSHROOM;
	static Block GOLD_BLOCK;
	static Block IRON_BLOCK;
	static Block DOUBLE_STONE_SLAB;
	static Block STONE_SLAB;
	static Block BRICKS;
	static Block TNT;
	static Block BOOK_SHELF;
	static Block MOSSY_COBBLESTONE;
	static Block OBSIDIAN;
	static Block TORCH;
	static Block FIRE;
	static Block MOB_SPAWNER;
	static Block OAK_STAIRS;
	static Block CHEST;
	static Block REDSTONE_DUST;
	static Block DIAMOND_ORE;
	static Block DIAMOND_BLOCK;
	static Block CRAFTING_TABLE;
	static Block WHEAT;
	static Block FARMLAND;
	static Block FURNACE;
	static Block LIT_FURNACE;
	static Block STANDING_SIGN;
	static Block WOODEN_DOOR;
	static Block LADDER;
	static Block RAIL;
	static Block STONE_STAIRS;
	static Block WALL_SIGN;
	static Block LEVER;
	static Block STONE_PRESSURE_PLATE;
	static Block IRON_DOOR;
	static Block WOODEN_PRESSURE_PLATE;
	static Block REDSTONE_ORE;
	static Block LIT_REDSTONE_ORE;
	static Block UNLIT_REDSTONE_TORCH;
	static Block REDSTONE_TORCH;
	static Block STONE_BUTTON;
	static Block SNOW_LAYER;
	static Block ICE;
	static Block SNOW;
	static Block CACTUS;
	static Block CLAY;
	static Block REEDS;
	static Block JUKEBOX;
	static Block FENCE;
	static Block PUMPKIN;
	static Block NETHERRACK;
	static Block SOUL_SAND;
	static Block GLOWSTONE;
	static Block PORTAL;
	static Block LIT_PUMPKIN;
	static Block CAKE;
	static Block UNPOWERED_REPEATER;
	static Block POWERED_REPEATER;
	static Block STAINED_GLASS;
	static Block TRAP_DOOR;
	static Block MONSTER_EGG;
	static Block STONEBRICK;
	static Block BROWN_MUSHROOM_BLOCK;
	static Block RED_MUSHROOM_BLOCK;
	static Block IRON_BARS;
	static Block GLASS_PANE;
	static Block MELON;
	static Block PUMPKIN_STEM;
	static Block MELON_STEM;
	static Block VINE;
	static Block FENCE_GATE;
	static Block BRICK_STAIRS;
	static Block STONE_BRICK_STAIRS;
	static Block MYCELIUM;
	static Block WATERLILY;
	static Block NETHER_BRICK;
	static Block NETHER_BRICK_FENCE;
	static Block NETHER_BRICK_STAIRS;
	static Block NETHER_WART;
	static Block ENCHANTING_TABLE;
	static Block BREWING_STAND;
	static Block CAULDRON;
	static Block END_PORTAL;
	static Block END_PORTAL_FRAME;
	static Block END_STONE;
	static Block DRAGON_EGG;
	static Block REDSTONE_LAMP;
	static Block LIT_REDSTONE_LAMP;
	static Block DOUBLE_WOODEN_SLAB;
	static Block WOODEN_SLAB;
	static Block COCOA;
	static Block SANDSTONE_STAIRS;
	static Block EMERALD_ORE;
	static Block ENDER_CHEST;
	static Block TRIPWIRE_HOOK;
	static Block TRIPWIRE;
	static Block EMERALD_BLOCK;
	static Block SPRUCE_STAIRS;
	static Block BIRCH_STAIRS;
	static Block JUNGLE_STAIRS;
	static Block COMMAND_BLOCK;
	static Block BEACON;
	static Block COBBLESTONE_WALL;
	static Block FLOWER_POT;
	static Block CARROTS;
	static Block POTATOES;
	static Block WOODEN_BUTTON;
	static Block SKULL;
	static Block ANVIL;
	static Block TRAPPED_CHEST;
	static Block LIGHT_WEIGHTED_PRESSURE_PLATE;
	static Block HEAVY_WEIGHTED_PRESSURE_PLATE;
	static Block UNPOWERED_COMPARATOR;
	static Block POWERED_COMPARATOR;
	static Block DAYLIGHT_DETECTOR;
	static Block REDSTONE_BLOCK;
	static Block QUARTZ_ORE;
	static Block HOPPER;
	static Block QUARTZ_BLOCK;
	static Block QUARTZ_STAIRS;
	static Block ACTIVATOR_RAIL;
	static Block DROPPER;
	static Block STAINED_HARDENED_CLAY;
	static Block STAINED_GLASS_PANE;
	static Block LEAVES2;
	static Block LOG2;
	static Block ACACIA_STAIRS;
	static Block DARK_OAK_STAIRS;
	static Block SLIME;
	static Block BARRIER;
	static Block IRON_TRAPDOOR;
	static Block PRISMARINE;
	static Block SEA_LANTERN;
	static Block HAY;
	static Block CARPET;
	static Block HARDENED_CLAY;
	static Block COAL;
	static Block PACKED_ICE;
	static Block DOUBLE_PLANT;
	static Block STANDING_BANNER;
	static Block WALL_BANNER;
	static Block DAYLIGHT_DETECTOR_INVERTED;
	static Block RED_SANDSTONE;
	static Block RED_SANDSTONE_STAIRS;
	static Block RED_SANDSTONE_SLAB2;
	static Block STONE_SLAB2;
	static Block SPRUCE_FENCE_GATE;
	static Block BIRCH_FENCE_GATE;
	static Block JUNGLE_FENCE_GATE;
	static Block DARK_OAK_FENCE_GATE;
	static Block ACACIA_FENCE_GATE;
	static Block SPRUCE_FENCE;
	static Block BIRCH_FENCE;
	static Block JUNGLE_FENCE;
	static Block DARK_OAK_FENCE;
	static Block ACACIA_FENCE;
	static Block SPRUCE_DOOR;
	static Block BIRCH_DOOR;
	static Block JUNGLE_DOOR;
	static Block ACACIA_DOOR;
	static Block DARK_OAK_DOOR;


	public static Block AIR() {
		return AIR;
	}

	public static Block STONE() {
		return STONE;
	}

	public static Block GRASS() {
		return GRASS;
	}

	public static Block DIRT() {
		return DIRT;
	}

	public static Block COBBLE_STONE() {
		return COBBLE_STONE;
	}

	public static Block PLANKS() {
		return PLANKS;
	}

	public static Block SAPLING() {
		return SAPLING;
	}

	public static Block BEDROCK() {
		return BEDROCK;
	}

	public static Block FLOWING_WATER() {
		return FLOWING_WATER;
	}

	public static Block WATER() {
		return WATER;
	}

	public static Block FLOWING_LAVA() {
		return FLOWING_LAVA;
	}

	public static Block LAVA() {
		return LAVA;
	}

	public static Block SAND() {
		return SAND;
	}

	public static Block GRAVEL() {
		return GRAVEL;
	}

	public static Block GOLD_ORE() {
		return GOLD_ORE;
	}

	public static Block IRON_ORE() {
		return IRON_ORE;
	}

	public static Block COAL_ORE() {
		return COAL_ORE;
	}

	public static Block LOG() {
		return LOG;
	}

	public static Block LEAVES() {
		return LEAVES;
	}

	public static Block SPONGE() {
		return SPONGE;
	}

	public static Block GLASS() {
		return GLASS;
	}

	public static Block LAPIS_ORE() {
		return LAPIS_ORE;
	}

	public static Block LAPIS_BLOCK() {
		return LAPIS_BLOCK;
	}

	public static Block DISPENSER() {
		return DISPENSER;
	}

	public static Block SANDSTONE() {
		return SANDSTONE;
	}

	public static Block NOTEBLOCK() {
		return NOTEBLOCK;
	}

	public static Block BED() {
		return BED;
	}

	public static Block GOLDEN_RAIL() {
		return GOLDEN_RAIL;
	}

	public static Block DETECTOR_RAIL() {
		return DETECTOR_RAIL;
	}

	public static Block STICKY_PISTON() {
		return STICKY_PISTON;
	}

	public static Block WEB() {
		return WEB;
	}

	public static Block TALL_GRASS() {
		return TALL_GRASS;
	}

	public static Block DEAD_BUSH() {
		return DEAD_BUSH;
	}

	public static Block PISTON() {
		return PISTON;
	}

	public static Block PISTON_HEAD() {
		return PISTON_HEAD;
	}

	public static Block WOOL() {
		return WOOL;
	}

	public static Block PISTON_EXTENSION() {
		return PISTON_EXTENSION;
	}

	public static Block YELLOW_FLOWER() {
		return YELLOW_FLOWER;
	}

	public static Block RED_FLOWER() {
		return RED_FLOWER;
	}

	public static Block BROWN_MUSHROOM() {
		return BROWN_MUSHROOM;
	}

	public static Block RED_MUSHROOM() {
		return RED_MUSHROOM;
	}

	public static Block GOLD_BLOCK() {
		return GOLD_BLOCK;
	}

	public static Block IRON_BLOCK() {
		return IRON_BLOCK;
	}

	public static Block DOUBLE_STONE_SLAB() {
		return DOUBLE_STONE_SLAB;
	}

	public static Block STONE_SLAB() {
		return STONE_SLAB;
	}

	public static Block BRICKS() {
		return BRICKS;
	}

	public static Block TNT() {
		return TNT;
	}

	public static Block BOOK_SHELF() {
		return BOOK_SHELF;
	}

	public static Block MOSSY_COBBLESTONE() {
		return MOSSY_COBBLESTONE;
	}

	public static Block OBSIDIAN() {
		return OBSIDIAN;
	}

	public static Block TORCH() {
		return TORCH;
	}

	public static Block FIRE() {
		return FIRE;
	}

	public static Block MOB_SPAWNER() {
		return MOB_SPAWNER;
	}

	public static Block OAK_STAIRS() {
		return OAK_STAIRS;
	}

	public static Block CHEST() {
		return CHEST;
	}

	public static Block REDSTONE_DUST() {
		return REDSTONE_DUST;
	}

	public static Block DIAMOND_ORE() {
		return DIAMOND_ORE;
	}

	public static Block DIAMOND_BLOCK() {
		return DIAMOND_BLOCK;
	}

	public static Block CRAFTING_TABLE() {
		return CRAFTING_TABLE;
	}

	public static Block WHEAT() {
		return WHEAT;
	}

	public static Block FARMLAND() {
		return FARMLAND;
	}

	public static Block FURNACE() {
		return FURNACE;
	}

	public static Block LIT_FURNACE() {
		return LIT_FURNACE;
	}

	public static Block STANDING_SIGN() {
		return STANDING_SIGN;
	}

	public static Block WOODEN_DOOR() {
		return WOODEN_DOOR;
	}

	public static Block LADDER() {
		return LADDER;
	}

	public static Block RAIL() {
		return RAIL;
	}

	public static Block STONE_STAIRS() {
		return STONE_STAIRS;
	}

	public static Block WALL_SIGN() {
		return WALL_SIGN;
	}

	public static Block LEVER() {
		return LEVER;
	}

	public static Block STONE_PRESSURE_PLATE() {
		return STONE_PRESSURE_PLATE;
	}

	public static Block IRON_DOOR() {
		return IRON_DOOR;
	}

	public static Block WOODEN_PRESSURE_PLATE() {
		return WOODEN_PRESSURE_PLATE;
	}

	public static Block REDSTONE_ORE() {
		return REDSTONE_ORE;
	}

	public static Block LIT_REDSTONE_ORE() {
		return LIT_REDSTONE_ORE;
	}

	public static Block UNLIT_REDSTONE_TORCH() {
		return UNLIT_REDSTONE_TORCH;
	}

	public static Block REDSTONE_TORCH() {
		return REDSTONE_TORCH;
	}

	public static Block STONE_BUTTON() {
		return STONE_BUTTON;
	}

	public static Block SNOW_LAYER() {
		return SNOW_LAYER;
	}

	public static Block ICE() {
		return ICE;
	}

	public static Block SNOW() {
		return SNOW;
	}

	public static Block CACTUS() {
		return CACTUS;
	}

	public static Block CLAY() {
		return CLAY;
	}

	public static Block REEDS() {
		return REEDS;
	}

	public static Block JUKEBOX() {
		return JUKEBOX;
	}

	public static Block FENCE() {
		return FENCE;
	}

	public static Block PUMPKIN() {
		return PUMPKIN;
	}

	public static Block NETHERRACK() {
		return NETHERRACK;
	}

	public static Block SOUL_SAND() {
		return SOUL_SAND;
	}

	public static Block GLOWSTONE() {
		return GLOWSTONE;
	}

	public static Block PORTAL() {
		return PORTAL;
	}

	public static Block LIT_PUMPKIN() {
		return LIT_PUMPKIN;
	}

	public static Block CAKE() {
		return CAKE;
	}

	public static Block UNPOWERED_REPEATER() {
		return UNPOWERED_REPEATER;
	}

	public static Block POWERED_REPEATER() {
		return POWERED_REPEATER;
	}

	public static Block STAINED_GLASS() {
		return STAINED_GLASS;
	}

	public static Block TRAP_DOOR() {
		return TRAP_DOOR;
	}

	public static Block MONSTER_EGG() {
		return MONSTER_EGG;
	}

	public static Block STONEBRICK() {
		return STONEBRICK;
	}

	public static Block BROWN_MUSHROOM_BLOCK() {
		return BROWN_MUSHROOM_BLOCK;
	}

	public static Block RED_MUSHROOM_BLOCK() {
		return RED_MUSHROOM_BLOCK;
	}

	public static Block IRON_BARS() {
		return IRON_BARS;
	}

	public static Block GLASS_PANE() {
		return GLASS_PANE;
	}

	public static Block MELON() {
		return MELON;
	}

	public static Block PUMPKIN_STEM() {
		return PUMPKIN_STEM;
	}

	public static Block MELON_STEM() {
		return MELON_STEM;
	}

	public static Block VINE() {
		return VINE;
	}

	public static Block FENCE_GATE() {
		return FENCE_GATE;
	}

	public static Block BRICK_STAIRS() {
		return BRICK_STAIRS;
	}

	public static Block STONE_BRICK_STAIRS() {
		return STONE_BRICK_STAIRS;
	}

	public static Block MYCELIUM() {
		return MYCELIUM;
	}

	public static Block WATERLILY() {
		return WATERLILY;
	}

	public static Block NETHER_BRICK() {
		return NETHER_BRICK;
	}

	public static Block NETHER_BRICK_FENCE() {
		return NETHER_BRICK_FENCE;
	}

	public static Block NETHER_BRICK_STAIRS() {
		return NETHER_BRICK_STAIRS;
	}

	public static Block NETHER_WART() {
		return NETHER_WART;
	}

	public static Block ENCHANTING_TABLE() {
		return ENCHANTING_TABLE;
	}

	public static Block BREWING_STAND() {
		return BREWING_STAND;
	}

	public static Block CAULDRON() {
		return CAULDRON;
	}

	public static Block END_PORTAL() {
		return END_PORTAL;
	}

	public static Block END_PORTAL_FRAME() {
		return END_PORTAL_FRAME;
	}

	public static Block END_STONE() {
		return END_STONE;
	}

	public static Block DRAGON_EGG() {
		return DRAGON_EGG;
	}

	public static Block REDSTONE_LAMP() {
		return REDSTONE_LAMP;
	}

	public static Block LIT_REDSTONE_LAMP() {
		return LIT_REDSTONE_LAMP;
	}

	public static Block DOUBLE_WOODEN_SLAB() {
		return DOUBLE_WOODEN_SLAB;
	}

	public static Block WOODEN_SLAB() {
		return WOODEN_SLAB;
	}

	public static Block COCOA() {
		return COCOA;
	}

	public static Block SANDSTONE_STAIRS() {
		return SANDSTONE_STAIRS;
	}

	public static Block EMERALD_ORE() {
		return EMERALD_ORE;
	}

	public static Block ENDER_CHEST() {
		return ENDER_CHEST;
	}

	public static Block TRIPWIRE_HOOK() {
		return TRIPWIRE_HOOK;
	}

	public static Block TRIPWIRE() {
		return TRIPWIRE;
	}

	public static Block EMERALD_BLOCK() {
		return EMERALD_BLOCK;
	}

	public static Block SPRUCE_STAIRS() {
		return SPRUCE_STAIRS;
	}

	public static Block BIRCH_STAIRS() {
		return BIRCH_STAIRS;
	}

	public static Block JUNGLE_STAIRS() {
		return JUNGLE_STAIRS;
	}

	public static Block COMMAND_BLOCK() {
		return COMMAND_BLOCK;
	}

	public static Block BEACON() {
		return BEACON;
	}

	public static Block COBBLESTONE_WALL() {
		return COBBLESTONE_WALL;
	}

	public static Block FLOWER_POT() {
		return FLOWER_POT;
	}

	public static Block CARROTS() {
		return CARROTS;
	}

	public static Block POTATOES() {
		return POTATOES;
	}

	public static Block WOODEN_BUTTON() {
		return WOODEN_BUTTON;
	}

	public static Block SKULL() {
		return SKULL;
	}

	public static Block ANVIL() {
		return ANVIL;
	}

	public static Block TRAPPED_CHEST() {
		return TRAPPED_CHEST;
	}

	public static Block LIGHT_WEIGHTED_PRESSURE_PLATE() {
		return LIGHT_WEIGHTED_PRESSURE_PLATE;
	}

	public static Block HEAVY_WEIGHTED_PRESSURE_PLATE() {
		return HEAVY_WEIGHTED_PRESSURE_PLATE;
	}

	public static Block UNPOWERED_COMPARATOR() {
		return UNPOWERED_COMPARATOR;
	}

	public static Block POWERED_COMPARATOR() {
		return POWERED_COMPARATOR;
	}

	public static Block DAYLIGHT_DETECTOR() {
		return DAYLIGHT_DETECTOR;
	}

	public static Block REDSTONE_BLOCK() {
		return REDSTONE_BLOCK;
	}

	public static Block QUARTZ_ORE() {
		return QUARTZ_ORE;
	}

	public static Block HOPPER() {
		return HOPPER;
	}

	public static Block QUARTZ_BLOCK() {
		return QUARTZ_BLOCK;
	}

	public static Block QUARTZ_STAIRS() {
		return QUARTZ_STAIRS;
	}

	public static Block ACTIVATOR_RAIL() {
		return ACTIVATOR_RAIL;
	}

	public static Block DROPPER() {
		return DROPPER;
	}

	public static Block STAINED_HARDENED_CLAY() {
		return STAINED_HARDENED_CLAY;
	}

	public static Block STAINED_GLASS_PANE() {
		return STAINED_GLASS_PANE;
	}

	public static Block LEAVES2() {
		return LEAVES2;
	}

	public static Block LOG2() {
		return LOG2;
	}

	public static Block ACACIA_STAIRS() {
		return ACACIA_STAIRS;
	}

	public static Block DARK_OAK_STAIRS() {
		return DARK_OAK_STAIRS;
	}

	public static Block SLIME() {
		return SLIME;
	}

	public static Block BARRIER() {
		return BARRIER;
	}

	public static Block IRON_TRAPDOOR() {
		return IRON_TRAPDOOR;
	}

	public static Block PRISMARINE() {
		return PRISMARINE;
	}

	public static Block SEA_LANTERN() {
		return SEA_LANTERN;
	}

	public static Block HAY() {
		return HAY;
	}

	public static Block CARPET() {
		return CARPET;
	}

	public static Block HARDENED_CLAY() {
		return HARDENED_CLAY;
	}

	public static Block COAL() {
		return COAL;
	}

	public static Block PACKED_ICE() {
		return PACKED_ICE;
	}

	public static Block DOUBLE_PLANT() {
		return DOUBLE_PLANT;
	}

	public static Block STANDING_BANNER() {
		return STANDING_BANNER;
	}

	public static Block WALL_BANNER() {
		return WALL_BANNER;
	}

	public static Block DAYLIGHT_DETECTOR_INVERTED() {
		return DAYLIGHT_DETECTOR_INVERTED;
	}

	public static Block RED_SANDSTONE() {
		return RED_SANDSTONE;
	}

	public static Block RED_SANDSTONE_STAIRS() {
		return RED_SANDSTONE_STAIRS;
	}

	public static Block RED_SANDSTONE_SLAB2() {
		return RED_SANDSTONE_SLAB2;
	}

	public static Block STONE_SLAB2() {
		return STONE_SLAB2;
	}

	public static Block SPRUCE_FENCE_GATE() {
		return SPRUCE_FENCE_GATE;
	}

	public static Block BIRCH_FENCE_GATE() {
		return BIRCH_FENCE_GATE;
	}

	public static Block JUNGLE_FENCE_GATE() {
		return JUNGLE_FENCE_GATE;
	}

	public static Block DARK_OAK_FENCE_GATE() {
		return DARK_OAK_FENCE_GATE;
	}

	public static Block ACACIA_FENCE_GATE() {
		return ACACIA_FENCE_GATE;
	}

	public static Block SPRUCE_FENCE() {
		return SPRUCE_FENCE;
	}

	public static Block BIRCH_FENCE() {
		return BIRCH_FENCE_GATE;
	}

	public static Block JUNGLE_FENCE() {
		return JUNGLE_FENCE;
	}

	public static Block DARK_OAK_FENCE() {
		return DARK_OAK_FENCE;
	}

	public static Block ACACIA_FENCE() {
		return ACACIA_FENCE;
	}

	public static Block SPRUCE_DOOR() {
		return SPRUCE_DOOR;
	}

	public static Block BIRCH_DOOR() {
		return BIRCH_DOOR;
	}

	public static Block JUNGLE_DOOR() {
		return JUNGLE_DOOR;
	}

	public static Block ACACIA_DOOR() {
		return ACACIA_DOOR;
	}

	public static Block DARK_OAK_DOOR() {
		return DARK_OAK_DOOR;
	}

}
