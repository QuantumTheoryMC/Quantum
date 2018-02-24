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
 * Created file on 7/18/16 at 10:39 AM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.block;

import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import quantum.api.block.Block;
import quantum.api.block.state.State;
import quantum.api.block.state.States;
import quantum.wrapper.minecraft.client.resources.QResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO BlockHybrid to replace BlockAdapter
 *
 * @author link
 */
public class BlockAdapter extends net.minecraft.block.Block {

	private static final Map<net.minecraft.block.Block, Block> BLOCK_TO_BLOCK   = new HashMap<>(blockRegistry.getKeys().size(), 0.88f);
	private static final Map<Block, net.minecraft.block.Block> MCBLOCK_TO_BLOCK = new HashMap<>(blockRegistry.getKeys().size(), 0.88f);

	private final boolean                        multiState;
	private final TIntObjectHashMap<IBlockState> states;
	private final Function<Void, Integer>        dropCount;
	private final Runnable                       tick;

	private BlockAdapter(Block block, int blockID) {
		super(new TypeAdapter(block.getType()));
		// register this block with vanilla minecraft
		blockRegistry.register(blockID, new QResourceLocation(block.getMod(), block.getID(), false), this);
		// add this block to the block mappings
		BlockAdapter.set(this, block);
		BlockAdapter.set(block, this);
		// set block states
		// map this block to its default state
		if (States.get(block) == null)
			States.map(block, State.getDefault(block));
		State blockState = States.get(block);
		net.minecraft.block.Block blockMC = BlockAdapter.get(block);
		// register the block state so that the World always uses the same
		// IBlockState object for compatibility with the vanilla ExtendedBlockStorage class.
		BlockStates.add(blockState, blockMC);
		// set the default state
		setDefaultState(BlockStates.get(blockState));
		BLOCK_STATE_IDS.put(getDefaultState(), blockID << 4 | getMetaFromState(getDefaultState()));
		multiState = blockState.getStates().size() > 1;
		// set the friendly name
		setUnlocalizedName(block.getName());

		List<State> states = blockState.getStates();
		this.states = multiState ? new TIntObjectHashMap<>(states.size()) : null;

		if (this.states != null) for (State state : states) {
			BlockStates.add(state, blockMC);
			IBlockState istate = BlockStates.get(state);
			this.states.put(state.hashCode(), istate);
			BLOCK_STATE_IDS.put(istate, blockID << 4 | getMetaFromState(istate));
		}

		dropCount = (_void_) -> block.getDropCount();
		tick = block::tick;
	}

	public static BlockAdapter create(Block block, int blockID) {
		// TODO implement per-mod BlockAdapters
		return new BlockAdapter(block, blockID);
	}

	public static Block get(net.minecraft.block.Block block) {
		return BLOCK_TO_BLOCK.get(block);
	}

	public static net.minecraft.block.Block get(Block block) {
		return MCBLOCK_TO_BLOCK.get(block);
	}

	public static void set(net.minecraft.block.Block key, Block value) {
		BLOCK_TO_BLOCK.put(key, value);
	}

	public static void set(Block key, net.minecraft.block.Block value) {
		MCBLOCK_TO_BLOCK.put(key, value);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO multi-state support
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return blockMaterial.isOpaque();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return !multiState ? getDefaultState() : getState(meta);
	}

	private IBlockState getState(int meta) {
		return states.get(meta);
	}

	@Override
	public int getRenderType() {
		return 3;
	}


	//----------------------------- Ticking -------------------------------


//	@Override
//	public void randomTick(World world, BlockPos location, IBlockState state, Random random) {
//
//	}
//
//	@Override
//	public void updateTick(World world, BlockPos location, IBlockState state, Random random) {
//		tick.run();
//	}
//
//	@Override
//	public void randomDisplayTick(World world, BlockPos location, IBlockState state, Random random) {
//
//	}
//
//	@Override
//	public int tickRate(World world) {
//		return 10;
//	}


	//------------------------------- Events -----------------------------------


	//	@Override
//	public void onBlockDestroyedByPlayer(World world, BlockPos location, IBlockState state) {
//
//	}
//
//	@Override
//	public void onNeighborBlockChange(World world, BlockPos location, IBlockState state, net.minecraft.block.Block neighbor) {
//
//	}
//
//
//
	@Override
	public void onBlockAdded(World world, BlockPos location, IBlockState state) {
		System.out.println("Quantum test block added and blockstate is: " + state);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

//
//	@Override
//	public void breakBlock(World world, BlockPos pos, IBlockState state) {
//
//	}
//
//	@Override
//	protected void dropXpOnBlockBreak(World world, BlockPos location, int quantity) {
//		super.dropXpOnBlockBreak(world, location, quantity);
//	}
//
//	@Override
//	public int quantityDropped(Random random) {
//		return dropCount.apply(null);
//	}

}
