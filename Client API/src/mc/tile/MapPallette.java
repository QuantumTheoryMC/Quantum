/**
 * 
 */
package mc.tile;

import static net.minecraft.block.material.MapColor.adobeColor;
import static net.minecraft.block.material.MapColor.airColor;
import static net.minecraft.block.material.MapColor.blackColor;
import static net.minecraft.block.material.MapColor.blueColor;
import static net.minecraft.block.material.MapColor.brownColor;
import static net.minecraft.block.material.MapColor.clayColor;
import static net.minecraft.block.material.MapColor.clothColor;
import static net.minecraft.block.material.MapColor.cyanColor;
import static net.minecraft.block.material.MapColor.diamondColor;
import static net.minecraft.block.material.MapColor.dirtColor;
import static net.minecraft.block.material.MapColor.emeraldColor;
import static net.minecraft.block.material.MapColor.foliageColor;
import static net.minecraft.block.material.MapColor.goldColor;
import static net.minecraft.block.material.MapColor.grassColor;
import static net.minecraft.block.material.MapColor.grayColor;
import static net.minecraft.block.material.MapColor.greenColor;
import static net.minecraft.block.material.MapColor.iceColor;
import static net.minecraft.block.material.MapColor.ironColor;
import static net.minecraft.block.material.MapColor.lapisColor;
import static net.minecraft.block.material.MapColor.lightBlueColor;
import static net.minecraft.block.material.MapColor.limeColor;
import static net.minecraft.block.material.MapColor.magentaColor;
import static net.minecraft.block.material.MapColor.netherrackColor;
import static net.minecraft.block.material.MapColor.obsidianColor;
import static net.minecraft.block.material.MapColor.pinkColor;
import static net.minecraft.block.material.MapColor.purpleColor;
import static net.minecraft.block.material.MapColor.quartzColor;
import static net.minecraft.block.material.MapColor.redColor;
import static net.minecraft.block.material.MapColor.sandColor;
import static net.minecraft.block.material.MapColor.silverColor;
import static net.minecraft.block.material.MapColor.snowColor;
import static net.minecraft.block.material.MapColor.stoneColor;
import static net.minecraft.block.material.MapColor.tntColor;
import static net.minecraft.block.material.MapColor.waterColor;
import static net.minecraft.block.material.MapColor.woodColor;
import static net.minecraft.block.material.MapColor.yellowColor;

import java.awt.Color;
import java.util.HashMap;

import mc.block.Block;
import net.minecraft.block.material.MapColor;

/**
 * @author Link
 *
 */
public final class MapPallette {

	private static final HashMap<Block, Color> blockColors = new HashMap<>(64, 0.5f);
	
	public static final Color AIR = toColor(airColor);
	public static final Color GRASS = toColor(grassColor);
	public static final Color SAND = toColor(sandColor);
	public static final Color CLOTH = toColor(clothColor);
	public static final Color TNT = toColor(tntColor);
	public static final Color ICE = toColor(iceColor);
	public static final Color IRON = toColor(ironColor);
	public static final Color LEAVES = toColor(foliageColor);
	public static final Color SNOW = toColor(snowColor);
	public static final Color CLAY = toColor(clayColor);
	public static final Color DIRT = toColor(dirtColor);
	public static final Color STONE = toColor(stoneColor);
	public static final Color WATER = toColor(waterColor);
	public static final Color WOOD = toColor(woodColor);
	public static final Color QUARTZ = toColor(quartzColor);
	public static final Color TERRA_COTTA = toColor(adobeColor);
	public static final Color MAGENTA = toColor(magentaColor);
	public static final Color LIGHT_BLUE = toColor(lightBlueColor);
	public static final Color YELLOW = toColor(yellowColor);
	public static final Color LIME = toColor(limeColor);
	public static final Color PINK = toColor(pinkColor);
	public static final Color GRAY = toColor(grayColor);
	public static final Color SILVER = toColor(silverColor);
	public static final Color CYAN = toColor(cyanColor);
	public static final Color PURPLE = toColor(purpleColor);
	public static final Color BLUE = toColor(blueColor);
	public static final Color BROWN = toColor(brownColor);
	public static final Color GREEN = toColor(greenColor);
	public static final Color RED = toColor(redColor);
	public static final Color BLACK = toColor(blackColor);
	public static final Color GOLD = toColor(goldColor);
	public static final Color DIAMOND = toColor(diamondColor);
	public static final Color LAPIS = toColor(lapisColor);
	public static final Color EMERALD = toColor(emeraldColor);
	public static final Color OBSIDIAN = toColor(obsidianColor);
	public static final Color NETHERRACK = toColor(netherrackColor);

	public static void addColor(final Block block) {
		blockColors.putIfAbsent(block, block.getColor());
	}

	public static Color getColor(final Block block) {
		return blockColors.getOrDefault(block, new Color(0, 0, 0));
	}

	public static Color getColorValue(final Block block) {
		return getColor(block);
	}
	
	private static Color toColor(MapColor mc) {
		return new Color(mc.colorValue);
	}
	
	public static Color format(MapColor mc) {
		return toColor(mc);
	}
}
