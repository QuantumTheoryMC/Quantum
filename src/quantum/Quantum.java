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
 * Created file on 3/21/16 at 1:29 PM.
 *
 * This file is part of Quantum API
 */
package quantum;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import quantum.api.block.Block;
import quantum.api.block.model.BlockModel;
import quantum.api.dimension.Dimension;
import quantum.api.entity.Entity;
import quantum.api.item.Item;
import quantum.api.resource.Resource;
import quantum.api.resource.ResourcePack;
import quantum.api.world.tick.Tickable;
import quantum.bootstrap.ModManager;
import quantum.mod.Mod;
import quantum.model.texture.Texture;
import quantum.wrapper.entity.EntityAdapter;
import quantum.wrapper.minecraft.Sprites;
import quantum.wrapper.minecraft.block.BlockAdapter;
import quantum.wrapper.minecraft.block.StateAdapter;
import quantum.wrapper.minecraft.client.resources.CustomStateMap;
import quantum.wrapper.minecraft.client.resources.QResourceLocation;
import quantum.wrapper.minecraft.item.Items;
import quantum.wrapper.minecraft.sprite.QSprite;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static net.minecraft.block.Block.blockRegistry;
import static net.minecraft.client.Minecraft.getMinecraft;
import static net.minecraft.item.Item.itemRegistry;

/**
 * @author link
 */
public final class Quantum {

	/**
	 * The singleton instance of Quantum used for initialization and
	 * de-initialization of mods.
	 */
	private static final Quantum QUANTUM = new Quantum();

	/**
	 * The Minecraft home directory as a Path.
	 */
	private static final Path MC_DIRECTORY = getOS().equals("windows") ? Paths.get(System.getProperty("user.dir"), "AppData", "Roaming", ".minecraft") : Paths.get(System.getProperty("user.dir"), ".minecraft");

	/**
	 * The Minecraft assets directory as a Path.
	 */
	private static final String MC_ASSETS = MC_DIRECTORY + "/assets";

	/**
	 * The mangled list of Vanilla and Modded Blocks adapted to Quantum API
	 */
	private static final Map<String, Block> BLOCKS = new HashMap<>(Vanilla.BLOCK_COUNT, 0.88f);

	/**
	 * The mangled list of Vanilla and Modded Entities adapted to Quantum API
	 */
	private static final Map<String, Class<? extends Entity>> ENTITIES = new LinkedHashMap<>(Vanilla.ENTITY_COUNT, 0.62f);

	/**
	 * The mangled list of Vanilla and Modded Items adapted to Quantum API
	 */
	private static final Map<String, Item> ITEMS = new HashMap<>(Vanilla.Item.ITEM_COUNT, 0.33f);

	/**
	 * The mangled list of Vanilla and Modded Dimensions adapted to Quantum API
	 */
	private static final Map<String, Dimension> DIMENSIONS = new HashMap<>(Vanilla.DIMENSION_COUNT, 1.0f);

	/**
	 * The ModManager for the API
	 */
	private static final ModManager MODS = new ModManager();

	/**
	 * The Tick hooks which are updated before each game tick
	 */
	private static final List<Tickable> TICK_HOOKS = new ArrayList<>();

	/**
	 * The current ResourcePack
	 */
	private static ResourcePack resourcePack;

	// restrict mods from using Quantum outside of loading and unloading mods
	private boolean enabled = false;

	private Quantum() {
	}

	public static void main(String... args) {
		// TODO print to logger
		System.out.println("[Quantum] Initializing Quantum...");
		// [init]
		resourcePack = ResourcePack.getDefault();
		// [/init]
		System.out.println("[Quantum] Searching for mods...");
		Path mods = Paths.get("/", "home", "link", ".minecraft", "quantum", "mods");
		System.out.println("[Quantum] (path: \"" + mods + "\")");
		if (mods.toFile().listFiles().length != 0) try {
			QUANTUM.enable();
			MODS.loadAll(mods, QUANTUM);
			QUANTUM.disable();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("[Quantum] An Exception was thrown during initialization: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Error e) {
			System.err.println("[Quantum] An Error was thrown during initialization: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		else System.out.println("[Quantum]     No mods found...");

		System.out.println("[Quantum] Finished Quantum initialization...");
	}


	private void enable() {
		enabled = true;
	}

	private void disable() {
		enabled = false;
	}


	/**
	 * Defines a new type of block with the given implementation.
	 * <p>
	 * This method registers a new block with the minecraft block registry, and
	 * in turn adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of mods, this method forces the
	 * internal
	 * list to be updated with the given block regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param block
	 * 		the block to define
	 */
	public void define(Block block) {
		if (enabled) {
			// register the block
			BlockAdapter adapter = BlockAdapter.adapt(block);
			Vanilla.blockIDCounter++;
			blockRegistry.register(Vanilla.blockIDCounter, new QResourceLocation(block.getMod(), block.getID(), false), adapter);
			// register the Model held by the Block
			registerBlockModel(block);
			// register the block with Quantum
			BLOCKS.put(block.getMod().getName().toLowerCase() + ":" + block.getID(), block);
			BlockAdapter.set(adapter, block);
		}
	}

	/**
	 * Redefines a block implemented by minecraft.
	 * <p>
	 * This method overwrites the block corresponding to this implementation,
	 * and updates the Quantum API internal list.
	 * </p>
	 *
	 * @param block
	 * 		the block to redefine
	 */
	public void redefine(Block block) {
		if (enabled) {
			// force the block definition by using putObject instead of register
			blockRegistry.putObject(new QResourceLocation(block.getMod(), block.getID(), false), BlockAdapter.adapt(block));
			// register the Model held by the Block
			registerBlockModel(block);
			// register the block with Quantum
			BLOCKS.put(block.getID(), block);
		}
	}

	private void registerBlockModel(Block block) {
		// register default state block model
		Resource resource = block.getModel().getResource();
		ResourceLocation location = resource != null ? new QResourceLocation(resource.getMod(), resource.getPath(), false) : TextureMap.field_174945_f;

		if (areTexturesResident(block))
			Sprites.set(location, new QSprite(block.getName(), block.getModel().getTextures()));
		// register block states to models
		Vanilla.Block.MODEL_MANAGER.getBlockModelShapes().func_178121_a((net.minecraft.block.Block) blockRegistry.getObject(new ResourceLocation(block.getMod().getName().toLowerCase(), block.getID())), block.getDefaultState() == null ? new CustomStateMap((state) -> new ModelResourceLocation(block.getMod().getName().toLowerCase() + ":" + block.getID(), null)) : new StateMap.Builder().func_178442_a(extract(block)).func_178439_a('_' + block.getID()).build());
		// register block item to the item map (does not increment item ID Counter)
		net.minecraft.block.Block b = (net.minecraft.block.Block) blockRegistry.getObjectById(Vanilla.blockIDCounter);
		b.setCreativeTab(CreativeTabs.tabBlock);
		Items.registerBlockItem(b, new ItemBlock(b));
	}

	private static IProperty[] extract(Block block) {
		return new ArrayList<>(new StateAdapter(block.getDefaultState()).getProperties().values()).toArray(new IProperty[0]);
	}

	/**
	 * Checks whether the given block's model contains textures. Has a
	 * fail-fast
	 * behavior.
	 * <p>
	 * This is used by {@link #registerBlockModel(Block)} to determine if a
	 * block's model has any textures or not. This is so that models with no
	 * textures simply reference "missingno" resource location. Going further,
	 * if some textures are resident, then QSprite adds the "missingno" texture
	 * data itself for each frame that is present. TODO if a texture is
	 * missing,
	 * Quantum warns about this, whether from QSprite or from
	 * registerBlockModel.
	 * </p>
	 *
	 * @param block
	 * 		the block to check
	 *
	 * @return true if some or all textures are resident, false if no textures
	 * are resident
	 */
	private static boolean areTexturesResident(Block block) {
		BlockModel model = block.getModel();
		// there is no model, therefore no textures
		// for Quantum Source
		// assert model != null : "[Quantum] Quantum has detected a null model for block \" + block.getName() + \"";
		if (model == null) return false;
		Texture[] textures = model.getTextures();
		// for Quantum Source
		// assert textures != null : "[Quantum] Quantum has detected that LinkTheProgrammer dun goof'd, or someone broke the terms & conditions: BlockModel should not return a null Texture array!";
		if (textures == null) return false;
		// TODO warn that texture array is null (this should never happen)

		// check which textures are null

		// the number of null textures
		int nullCount = 0;
		for (Texture texture : textures) {
			if (texture == null) {
				nullCount++;
			}
		}

		// if no textures are null
		// if not all textures are null
		return nullCount <= 0 || nullCount < textures.length;

	}


	private void unregisterBlockModel(Block block) {
		// unregister block model
		Sprites.set(new ModelResourceLocation(block.getModel().getResource().getPath(), block.getName()), null);
		// unregister block states to models
		// TODO test this
		Vanilla.Block.MODEL_MANAGER.getBlockModelShapes().func_178121_a((net.minecraft.block.Block) blockRegistry.getObject(block.getID()), null);
	}

	/**
	 * Defines a new type of entity with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of MODS, this method forces the
	 * internal
	 * list to be updated with the given entity regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param entity
	 * 		the entity to define
	 */
	public void define(Entity entity) {
		if (enabled) {
			EntityAdapter a = EntityAdapter.get(entity);
			Vanilla.Entity.ENTITY_NAME_MAP.put(a.getName(), a.getClass());
			Vanilla.Entity.ENTITY_CLASS_MAP.put(a.getClass(), a.getName());
			Vanilla.Entity.ENTITY_ID_MAP.put(Vanilla.entityIDCounter++, a.getClass());
			Vanilla.Entity.ENTITY_CLASS_ID_MAP.put(a.getClass(), Vanilla.entityIDCounter);
		}
	}

	public void redefine(Entity entity) {
		if (enabled) {
			// TODO
		}
	}

	/**
	 * Defines a new type of item with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of MODS, this method forces the
	 * internal
	 * list to be updated with the given entity regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param item
	 * 		the item to define
	 */
	public void define(Item item) {
		if (enabled) {
			// TODO
		}
	}

	public void redefine(Item item) {
		if (enabled) {
			// TODO
		}
	}

	/**
	 * Defines a new type of dimension with the given implementation.
	 * <p>
	 * This method adds this to the Quantum API internal list.
	 * </p>
	 * <p>
	 * Because of the concurrent nature of MODS, this method forces the
	 * internal
	 * list to be updated with the given entity regardless of whether it is new
	 * or not.
	 * </p>
	 *
	 * @param dimension
	 * 		the dimension to define
	 */
	public void define(Dimension dimension) {
		if (enabled) {
			// TODO
		}
	}

	public void redefine(Dimension dimension) {
		if (enabled) {
			// TODO
		}
	}


	public void remove(Block block) {
		// TODO
	}

	public void addTickHook(Mod mod, Tickable hook) {
		// TODO
	}

	public void removeTickHook(Mod mod, Tickable hook) {
		// TODO
	}

	/**
	 * Regardless of the state of a mod, the mod is immediately aborted as if a
	 * fatal exception were thrown. Often a fatal exception is the cause for
	 * abortion of mods.
	 *
	 * @param mod
	 * 		the mod to abort
	 * @param status
	 * 		the status of the mod, or the reason for abortion
	 */
	public static void abort(Mod mod, String status) {
		// TODO send to logger
		try {
			MODS.unload(mod, new Quantum());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public static String getName() {
		return Version.getName() + " " + getAPIVersion();
	}

	/**
	 * Gets the "formal", or full, name of the Quantum environment, including
	 * the versions of API, wrapper, and minecraft, respectively.
	 *
	 * @return the "formal" name of the Quantum environment
	 */
	public static String getFormalName() {
		return "[" + Version.getName().toLowerCase() + "_(API:" + getAPIVersion() + ")_(W:" + getWrapperVersion() + ")_(MC:" + getMinecraftVersion() + ")]";
	}

	/**
	 * Gets the version of the current API.
	 *
	 * @return the version of the current API
	 */
	public static String getAPIVersion() {
		return Version.getAPI();
	}

	/**
	 * Gets the version of the current wrapper.
	 *
	 * @return the version of the current wrapper
	 */
	public static String getWrapperVersion() {
		return Version.getWrapper();
	}

	/**
	 * Gets the current version of Minecraft
	 *
	 * @return the current version of Minecraft
	 */
	public static String getMinecraftVersion() {
		return Version.getMinecraft();
	}

	/**
	 * Gets the OS-dependent ".minecraft" directory for this system.
	 *
	 * @return an absolute ".minecraft" directory
	 */
	public static Path getMinecraftDir() {
		return MC_DIRECTORY;
	}

	/**
	 * Gets the current minecraft assets directory used for ResourcePacks.
	 *
	 * @return the current minecraft assets directory
	 */
	public static String getMinecraftAssetsDir() {
		return MC_ASSETS;
	}

	/**
	 * Gets the current resource pack in use by minecraft.
	 *
	 * @return the current resource pack
	 */
	public static ResourcePack getResourcePack() {
		return resourcePack;
	}

	/**
	 * Gets the current system's Operating System.
	 *
	 * @return the current system's Operating System
	 */
	private static String getOS() {
		String os = System.getProperty("os.name");
		if (os.contains("windows")) return "windows";
		else if (os.contains("nix")) return "unix";
		else return os;
	}


	/**
	 * Gets the Block singleton with the given name.
	 *
	 * @param name
	 * 		the name of the Block
	 *
	 * @return the Block with the given name
	 */
	public static Block getBlock(String name) {
		return BLOCKS.get(name);
	}

	/**
	 * Gets the class of the Entity that matches the given name.
	 *
	 * @param name
	 * 		the name of the Entity
	 *
	 * @return the class of the Entity that matches the given name
	 */
	public static Class<? extends Entity> getEntity(String name) {
		return ENTITIES.get(name);
	}

	private enum Vanilla {
		;

		enum Block {
			;

			static final ModelManager MODEL_MANAGER;

			static {
				try {
					Field modelManager = Minecraft.class.getDeclaredField("modelManager");
					modelManager.setAccessible(true);

					MODEL_MANAGER = (ModelManager) modelManager.get(getMinecraft());

					modelManager.setAccessible(false);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new RuntimeException("This version of minecraft is not compatible with Quantum", e);
				}
			}

		}

		@SuppressWarnings("unchecked")
		enum Entity {
			;
			static final Map<String, Class<? extends net.minecraft.entity.Entity>> ENTITY_NAME_MAP;

			static final Map<Class<? extends net.minecraft.entity.Entity>, String> ENTITY_CLASS_MAP;

			static final Map<Integer, Class<? extends net.minecraft.entity.Entity>> ENTITY_ID_MAP;

			static final Map<Class<? extends net.minecraft.entity.Entity>, Integer> ENTITY_CLASS_ID_MAP;

			static final Map<String, Integer> ENTITY_NAME_ID_MAP;

			static {
				try {
					// use reflection to retriev the mappings (which are private)
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
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new RuntimeException("This version of minecraft is not compatible with Quantum", e);
				}
			}
		}


		enum Item {
			;
			static final    int ITEM_COUNT    = itemRegistry.getKeys().size(); // list of items
			static volatile int itemIDCounter = ITEM_COUNT;
		}

		static final int BLOCK_COUNT     = blockRegistry.getKeys().size(); // list of blocks
		static final int ENTITY_COUNT    = 77; // list of entities
		static final int DIMENSION_COUNT = 3; // Overworld, End, Nether

		static volatile int blockIDCounter = BLOCK_COUNT;

		static volatile int entityIDCounter    = ENTITY_COUNT;
		static volatile int dimensionIDCounter = DIMENSION_COUNT;
	}

	private enum Version {
		;

		public static String getAPI() {
			return "1.0b";
		}

		public static String getWrapper() {
			return "1.0b";
		}

		public static String getMinecraft() {
			return "1.8.0";
		}

		public static String getName() {
			return "Quantum";
		}

	}

}
