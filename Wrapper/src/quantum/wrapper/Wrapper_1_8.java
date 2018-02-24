/*
 * The MIT License
 *
 * Copyright 2017 link.
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
 * Created file on 1/18/17 at 6:44 PM.
 *
 * This file is part of Quantum
 */
package quantum.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import quantum.Quantum;
import quantum.Wrapper;
import quantum.agent.ModManager;
import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.block.state.States;
import quantum.api.dimension.Dimension;
import quantum.api.entity.Entity;
import quantum.api.entity.player.PlayerEntity;
import quantum.api.item.Item;
import quantum.api.mod.Mod;
import quantum.api.model.Model;
import quantum.api.model.texture.Texture;
import quantum.api.resource.Resource;
import quantum.api.resource.ResourcePack;
import quantum.api.world.World;
import quantum.wrapper.minecraft.Sprites;
import quantum.wrapper.minecraft.block.BlockAdapter;
import quantum.wrapper.minecraft.client.resources.QResourceLocation;
import quantum.wrapper.minecraft.item.Items;
import quantum.wrapper.minecraft.sprite.QSprite;
import quantum.wrapper.world.WorldAccessor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.client.Minecraft.getMinecraft;

/**
 * @author link
 */
@SuppressWarnings("unchecked")
public class Wrapper_1_8 implements Wrapper, Quantum.Bridge {

	private static final RegistryNamespacedDefaultedByKey BLOCK_REGISTRY = net.minecraft.block.Block.blockRegistry;

	private static final Map<String, Class<? extends net.minecraft.entity.Entity>> ENTITY_NAME_MAP;

	private static final Map<Class<? extends net.minecraft.entity.Entity>, String> ENTITY_CLASS_MAP;

	private static final Map<Integer, Class<? extends net.minecraft.entity.Entity>> ENTITY_ID_MAP;

	private static final Map<Class<? extends net.minecraft.entity.Entity>, Integer> ENTITY_CLASS_ID_MAP;

	private static final Map<String, Integer> ENTITY_NAME_ID_MAP;

	// initialize ENTITY_ maps
	static {
		try {
			Field field1 = EntityList.class.getDeclaredField("stringToClassMapping");
			Field field2 = EntityList.class.getDeclaredField("classToStringMapping");
			Field field3 = EntityList.class.getDeclaredField("idToClassMapping");
			Field field4 = EntityList.class.getDeclaredField("classToIDMapping");
			Field field5 = EntityList.class.getDeclaredField("field_180126_g");

			// enable access to the field references
			field1.setAccessible(true);
			field2.setAccessible(true);
			field3.setAccessible(true);
			field4.setAccessible(true);
			field5.setAccessible(true);

			// retrieve the mappings
			ENTITY_NAME_MAP = (Map<String, Class<? extends net.minecraft.entity.Entity>>) field1.get(EntityList.class);
			ENTITY_CLASS_MAP = (Map<Class<? extends net.minecraft.entity.Entity>, String>) field2.get(EntityList.class);
			ENTITY_ID_MAP = (Map<Integer, Class<? extends net.minecraft.entity.Entity>>) field3.get(EntityList.class);
			ENTITY_CLASS_ID_MAP = (Map<Class<? extends net.minecraft.entity.Entity>, Integer>) field4.get(EntityList.class);
			ENTITY_NAME_ID_MAP = (Map<String, Integer>) field5.get(EntityList.class);

			// disable access to the field references
			field1.setAccessible(false);
			field2.setAccessible(false);
			field3.setAccessible(false);
			field4.setAccessible(false);
			field5.setAccessible(false);
		} catch( NoSuchFieldException | IllegalAccessException e ) {
			throw new RuntimeException("This version of minecraft is not compatible with Quantum", e);
		}
	}

	private static final RegistryNamespaced                   ITEM_REGISTRY = net.minecraft.item.Item.itemRegistry;
	/**
	 * The mangled list of Vanilla and Modded Blocks adapted to Quantum API
	 */
	private static final Map<String, Block>                   BLOCKS        = new HashMap<>(BLOCK_REGISTRY.getKeys().size(), 0.88f);
	/**
	 * The mangled list of Vanilla and Modded Entities adapted to Quantum API
	 */
	private static final Map<String, Class<? extends Entity>> ENTITIES      = new LinkedHashMap<>(ENTITY_ID_MAP.keySet().size(), 0.62f);
	/**
	 * The mangled list of Vanilla and Modded Items adapted to Quantum API
	 */
	private static final Map<String, Item>                    ITEMS         = new HashMap<>(ITEM_REGISTRY.getKeys().size(), 0.33f);
	/**
	 * The mangled list of Vanilla and Modded Dimensions adapted to Quantum API
	 */
	private static final Map<String, Dimension>               DIMENSIONS    = new HashMap<>(3, 1.0f);
	/**
	 * The ModManager for the API
	 */
	private static final ModManager                           MODS          = new ModManager();
	//private static final Map<String, Block> BLOCKS = new HashMap<>(atr.c.getKeys().size(), 0.88f);
	/**
	 * The ModelManager for the API
	 */
	private static final ModelManager MODEL_MANAGER;
	/**
	 * The current ResourcePack
	 */
	private static       ResourcePack resourcePack;

	/**
	 * The first available numeric block ID for quantum
	 */
	private static final int QUANTUM_BLOCK_ID_OFFSET = 2268;
	// block ID counter
	private static       int blockIDCounter          = QUANTUM_BLOCK_ID_OFFSET;

	// initialize MODEL_MANAGER
	static {
		try {
			//Field modelManager = bsu.class.getDeclaredField("aL");
			Field modelManager = Minecraft.class.getDeclaredField("modelManager");
			modelManager.setAccessible(true);

			//MODEL_MANAGER = (ModelManager) modelManager.get(bsu.z());
			MODEL_MANAGER = (ModelManager) modelManager.get(getMinecraft());

			modelManager.setAccessible(false);
		} catch( NoSuchFieldException | IllegalAccessException e ) {
			throw new RuntimeException("This version of minecraft is not compatible with Quantum", e);
		}
	}

	/**
	 * Checks whether the given state's model contains textures. Has a fail-fast behavior.
	 * <p>
	 * This is used by {@link #define(Block)} to determine if a
	 * block's model has any textures or not. This is so that models with no
	 * textures simply reference "missingno" resource location. Going further,
	 * if some textures are resident, then QSprite adds the "missingno" texture
	 * data itself for each frame that is present. (TODO if a texture is
	 * missing,
	 * Quantum warns about this, whether from QSprite or from
	 * registerBlockModel.)
	 * </p>
	 *
	 * @param state the state to check
	 * @return true if some or all textures are resident, false if no textures
	 * are resident
	 */
	private static boolean hasTextures(State state) {
		// there is no state, therefore no model, no textures
		if (state == null)
			return false;
		Model model = state.getModel();
		// there is no model, therefore no textures
		if (model == null)
			return false;
		Texture[] textures = model.getTextures();
		if (textures == null)
			return false;
		// TODO warn that texture array is null (this should never happen)

		// check which textures are null

		// the number of null textures
		int nullCount = 0;
		for (Texture texture : textures) {
			if (texture == null) {
				nullCount++;
			}
		}

		// if no textures are null / if not all textures are null
		return nullCount <= 0 || nullCount < textures.length;

	}

	//*******************************Counters***********************************

	@Override
	public String getName() {
		return "Quantum Wrapper";
	}

	@Override
	public String getVersion() {
		return "1.0b";
	}

	@Override
	public String getMinecraftVersion() {
		return "1.8.0";
	}

	@Override
	public Quantum.Bridge getBridge() {
		return this;
	}

	@Override
	public void loadMods(Path mods, Quantum quantum) throws IllegalAccessException, IOException, ClassNotFoundException {
		MODS.loadAll(mods, quantum);
	}

	@Override
	public void define(Block block) {
		assert block != null : "A Block was null when attempting to define from " + MODS.getLastLoadedMod();
		// increment the block ID counter
		blockIDCounter++;
		// initialize the block (handled by ctor)
		BlockAdapter blockMC = BlockAdapter.create(block, blockIDCounter);
		State        state   = States.get(block);
		//assert state != null : "[Debug] You did not properly map the states to block \"" + block.getID() + '"';
		if (state == null)
			System.out.println("[Quantum][Wrapper][Warning] No states mapped to Block \"" + block.getID() + '"');

		Model    model    = state.getModel();
		Resource resource = state.getModel().getResource();
		Mod      mod      = block.getMod();
		//oa location = resource != null ? new QResourceLocation(resource.getMod(), resource.getPath(), false) : cua.f;
		ResourceLocation location = resource != null ? new QResourceLocation(mod, resource.getPath(), false) : TextureMap.field_174945_f;

		// TODO custom block textures (programmatically)
		//ModelResourceLocation modelMRL = new QModelResourceLocation(resource.getMod(), resource.getPath(), "normal");
		//BlockModels.set(modelMRL, null);

		// if the state's model has textures, register the custom sprites.
		// Minecraft uses a Stitcher, so we have to insert a hook and add
		// sprites to a custom listing. The hook reads the custom sprites
		// from the custom listing.
		if (hasTextures(state))
			Sprites.set(location, new QSprite(block.getName(), model.getTextures()));

		// register block states to models
		//MODEL_MANAGER.c().a(BlockAdapter.get(block), new DefaultStateMapper());
		MODEL_MANAGER.getBlockModelShapes().func_178121_a(blockMC, new DefaultStateMapper());

		// register block item to the item map (does not increment item ID Counter)
		//atr b = (atr) BLOCK_REGISTRY.a(blockIDCounter);
		blockMC.setCreativeTab(CreativeTabs.tabBlock);
		Items.registerBlockItem(blockMC, new ItemBlock(blockMC));
		// register the block with Quantum
		BLOCKS.put(mod.getName().toLowerCase() + ':' + block.getID(), block);

	}

	@Override
	public void define(Item item) {

	}

	@Override
	public void define(Entity entity) {

	}

	@Override
	public void define(Dimension dimension) {

	}

	@Override
	public void define(World world) {

	}

	@Override
	public void redefine(Block block) {

	}

	@Override
	public void redefine(Item item) {

	}

	@Override
	public void redefine(Entity entity) {

	}

	@Override
	public void redefine(Dimension dimension) {

	}

	@Override
	public void redefine(World world) {

	}


	@Override
	public void remove(Block block) {

	}

	@Override
	public void remove(Item item) {

	}

	@Override
	public void remove(Entity entity) {

	}

	@Override
	public void remove(Dimension dimension) {

	}

	@Override
	public void remove(World world) {

	}


	@Override
	public World getCurrentWorld() {
		return new WorldAccessor(Minecraft.getMinecraft().theWorld);
	}

	@Override
	public PlayerEntity getPlayer() {
		return null;
	}

	@Override
	public List<PlayerEntity> getPlayers() {
		return null;
	}

	@Override
	public Block getBlock(String id) {
		return BLOCKS.get(id);
	}

	@Override
	public Item getItem(String id) {
		return null;
	}

	@Override
	public Dimension getDimension(String id) {
		return null;
	}

	@Override
	public void addHook(Object category, Object hook) {

	}


	private enum Hooks {


	}
}
