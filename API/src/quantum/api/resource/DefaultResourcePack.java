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
 * Created file on 11/23/16 at 4:17 PM.
 *
 * This file is part of Quantum API
 */
package quantum.api.resource;

import quantum.api.mod.Mod;
import quantum.api.mod.minecraft.ModMinecraft;

import java.util.HashMap;
import java.util.Map;

/**
 * @author link
 */
final class DefaultResourcePack implements ResourcePack {

	static final DefaultResourcePack SINGLETON = new DefaultResourcePack();

	private static final Map<String, Resource> RESOURCES = new HashMap<>(197);

	static {
		// blocks
		Mod minecraft = ModMinecraft.getInstance();
		add("minecraft:stone", new TextureResource(16, 16, minecraft, "block/stone.png"));
		add("minecraft:grass", new TextureResource(16, 16, minecraft, "block/grass.png"));
		add("minecraft:dirt", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cobblestone", new TextureResource(16, 16, minecraft, "block/cobblestone.png"));
		add("minecraft:planks", new TextureResource(16, 16, minecraft, "block/planks.png"));
		add("minecraft:bedrock", new TextureResource(16, 16, minecraft, "block/bedrock.png"));
		add("minecraft:flowing_water", new TextureResource(16, 16, minecraft, "block/water_still.png"));
		add("minecraft:water", new TextureResource(16, 16, minecraft, "block/water_flowing.png"));
		add("minecraft:flowing_lava", new TextureResource(16, 16, minecraft, "block/lava_flowing.png"));
		add("minecraft:lava", new TextureResource(16, 16, minecraft, "block/lava_still.png"));
		add("minecraft:sand", new TextureResource(16, 16, minecraft, "block/sand.png"));
		add("minecraft:gravel", new TextureResource(16, 16, minecraft, "block/gravel.png"));
		add("minecraft:gold_ore", new TextureResource(16, 16, minecraft, "block/gold_ore.png"));
		add("minecraft:iron_ore", new TextureResource(16, 16, minecraft, "block/iron_ore.png"));
		add("minecraft:coal_ore", new TextureResource(16, 16, minecraft, "block/coal_ore.png"));
		add("minecraft:log", new TextureResource(16, 16, minecraft, "block/log.png"));
		add("minecraft:leaves", new TextureResource(16, 16, minecraft, "block/leaves.png"));
		add("minecraft:sponge", new TextureResource(16, 16, minecraft, "block/sponge.png"));
		add("minecraft:glass", new TextureResource(16, 16, minecraft, "block/glass.png"));
		add("minecraft:lapis_ore", new TextureResource(16, 16, minecraft, "block/lapis_ore.png"));
		add("minecraft:lapis_block", new TextureResource(16, 16, minecraft, "block/lapis_block.png"));
		add("minecraft:dispenser", new TextureResource(16, 16, minecraft, "block/dispenser.png"));
		add("minecraft:sandstone", new TextureResource(16, 16, minecraft, "block/sandstone.png"));
		add("minecraft:noteblock", new TextureResource(16, 16, minecraft, "block/noteblock.png"));
		add("minecraft:bed", new TextureResource(16, 16, minecraft, "block/bed.png"));
		add("minecraft:golden_rail", new TextureResource(16, 16, minecraft, "block/golden_rail.png"));
		add("minecraft:detector_rail", new TextureResource(16, 16, minecraft, "block/detector_rail.png"));
		add("minecraft:sticky_piston", new TextureResource(16, 16, minecraft, "block/sticky_piston.png"));
		add("minecraft:web", new TextureResource(16, 16, minecraft, "block/web.png"));
		add("minecraft:tallgrass", new TextureResource(16, 16, minecraft, "block/tallgrass.png"));
		add("minecraft:deadbush", new TextureResource(16, 16, minecraft, "block/deadbush.png"));
		add("minecraft:piston", new TextureResource(16, 16, minecraft, "block/piston.png"));
		add("minecraft:piston_head", new TextureResource(16, 16, minecraft, "block/piston_head.png"));
		add("minecraft:wool", new TextureResource(16, 16, minecraft, "block/wool.png"));
		add("minecraft:piston_extension", new TextureResource(16, 16, minecraft, "block/piston.png"));
		add("minecraft:yellow_flower", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:red_flower", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:brown_mushroom", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:red_mushroom", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:gold_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:iron_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:double_stone_slab", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_slab", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:brick_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:tnt", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:bookshelf", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:mossy_cobblestone", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:obsidian", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:torch", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:fire", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:mob_spawner", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:oak_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:chest", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:redstone_wire", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:diamond_ore", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:diamond_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:crafting_table", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wheat", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:farmland", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:furnace", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:lit_furnace", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:standing_sign", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wooden_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:ladder", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:rail", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wall_sign", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:lever", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_pressure_plate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:iron_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wooden_pressure_plate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:redstone_ore", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:lit_redstone_ore", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:unlit_redstone_torch", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:redstone_torch", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_button", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:snow_layer", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:ice", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:snow", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cactus", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:clay", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:reeds", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:jukebox", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:pumpkin", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:netherrack", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:soul_sand", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:glowstone", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:portal", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:lit_pumpkin", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cake", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:unpowered_repeater", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:powered_repeater", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stained_glass", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:trapdoor", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:monster_egg", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stonebrick", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:brown_mushroom_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:red_mushroom_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:iron_bars", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:glass_pane", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:melon_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:pumpkin_stem", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:melon_stem", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:vine", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:fence_gate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_brick_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:mycelium", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:waterlily", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:nether_brick", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:nether_brick_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:nether_brick_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:nether_wart", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:enchanting_table", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:brewing_stand", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cauldron", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:end_portal", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:end_portal_frame", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:end_stone", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dragon_egg", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:redstone_lamp", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:lit_redstone_lamp", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:double_wooden_slab", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wooden_slab", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cocoa", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:sandstone_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:emerald_ore", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:ender_chest", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:tripwire_hook", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:tripwire", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:emerald_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:spruce_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:birch_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:jungle_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:command_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:beacon", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:cobblestone_wall", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:flower_pot", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:carrots", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:potatoes", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wooden_button", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:skull", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:anvil", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:trapped_chest", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:light_weighted_pressure_plate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:heavy_weighted_pressure_plate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:unpowered_comparator", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:powered_comparator", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:daylight_detector", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:redstone_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:quartz_ore", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:hopper", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:quartz_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:quartz_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:activator_rail", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dropper", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stained_hardened_clay", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stained_glass_pane", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:leaves2", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:log2", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:acacia_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dark_oak_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:slime", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:barrier", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:iron_trapdoor", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:prismarine", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:sea_lantern", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:hay_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:carpet", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:hardened_clay", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:coal_block", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:packed_ice", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:double_plant", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:standing_banner", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:wall_banner", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:daylight_detector_inverted", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:red_sandstone", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:red_sandstone_stairs", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:double_stone_slab2", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:stone_slab2", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:spruce_fence_gate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:jungle_fence_gate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dark_oak_fence_gate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:acacia_fence_gate", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:spruce_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:birch_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:jungle_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dark_oak_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:acacia_fence", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:spruce_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:birch_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:jungle_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:acacia_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));
		add("minecraft:dark_oak_door", new TextureResource(16, 16, minecraft, "block/dirt.png"));

		// block animations
		add("minecraft:animation:destroy_0", new TextureResource(16, 16, minecraft, "block/destroy_stage_0.png"));
		add("minecraft:animation:destroy_1", new TextureResource(16, 16, minecraft, "block/destroy_stage_1.png"));
		add("minecraft:animation:destroy_2", new TextureResource(16, 16, minecraft, "block/destroy_stage_2.png"));
		add("minecraft:animation:destroy_3", new TextureResource(16, 16, minecraft, "block/destroy_stage_3.png"));
		add("minecraft:animation:destroy_4", new TextureResource(16, 16, minecraft, "block/destroy_stage_4.png"));
		add("minecraft:animation:destroy_5", new TextureResource(16, 16, minecraft, "block/destroy_stage_0.png"));
		add("minecraft:animation:destroy_6", new TextureResource(16, 16, minecraft, "block/destroy_stage_0.png"));
		add("minecraft:animation:destroy_7", new TextureResource(16, 16, minecraft, "block/destroy_stage_0.png"));
		add("minecraft:animation:destroy_8", new TextureResource(16, 16, minecraft, "block/destroy_stage_0.png"));
		add("minecraft:animation:destroy_9", new TextureResource(16, 16, minecraft, "block/destroy_stage_5.png"));
	}

	final byte size = 16;

	private DefaultResourcePack() {
	}

	private static void add(String name, Resource resource) {
		RESOURCES.putIfAbsent(name, resource);
	}

	@Override
	public Resource get(String name) {
		return RESOURCES.get(name);
	}

	@Override
	public short getResolution() {
		return size;
	}
}
