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
 *
 * Created file on 12/22/15 at 11:36 AM.
 *
 * This file is part of Quantum API
 */
package api.quantum.block;

import api.quantum.meta.Untested;
import api.quantum.meta.WIP;

import java.util.List;
import java.util.Random;

/**
 * @author link
 *         Created on 12/21/15 at 1:55 PM.
 */
public interface Block {
	boolean isFullBlock();

	int getOpacity();

	int getLightEmission();

	boolean isTranslucent();

	boolean useNeighborBrightness();

	BlockType getBlockType();

	BlockType.BlockColor getBlockColor();

	// BlockState convertToState(int metadata)
	// int convertToMetadata(BlockState blockState)
	// BlockState getCurrentState(int x, int y, int z)

	boolean isFullSolid();

	boolean isDefault();

	boolean isOpaque();

	boolean isClippable();

	int getRenderMethod();

	@WIP
	boolean isOverwritable();

	// boolean isOverwritable(WorldLocation location, (AbstractBlock).Side side, ItemHeap item)
	// boolean isOverwritable(WorldLocation location, (AbstractBlock).Side side)
	//@Override
	//public void RedstoneListener#sendPower(WorldLocation location, BlockState blockState, Player player, Side side, Vector3D hitLocation)
	//@Override
	//public int BlockListener#getPowerLevel(WorldLocation location, BlockState blockState, Side side)
	//@Override
	//public int BlockListener#getWeakPowerLevel(WorldLocation location, BlockState blockState, Side side)
	//@Override
	//public boolean BlockListener#isPowerSource()
	//@Override
	//public void BlockListener#harvest(Player player, WorldLocation location, BlockState blockState, BlockEntity blockEntity)

	double getHardness();

	boolean usesRandomTick();

	/**
	 * Defines whether this AbstractBlock uses a BlockEntity (used for extra
	 * info for a
	 * AbstractBlock in the World).
	 * <p>
	 * Blocks use BlockEntities to store extra information when they are
	 * rendered in the world. One example is signs; they use a BlockEntity to
	 * store the text on the sign.
	 *
	 * @return true if this AbstractBlock uses a BlockEntity
	 */
	boolean usesBlockEntity();

	int getBrightness(int x, int y, int z);

	// boolean canRenderSide(Direction direction)
	//public AABB getAABB(WorldLocation location)
	//public AABB getCollisionAABB(WorldLocation location)
	//public void updateRandom(BlockState blockState, Random random, WorldLocation location)
	//public abstract void update(BlockState blockState, Random random, WorldLocation location)
	@WIP
	int getRemainingTicks();

	//@Override
	//public void blockAdded(BlockState blockState, WorldLocation location)
	//@Override
	//public void onBlockBreak(BlockState blockState, WorldLocation location)
	@Untested int getDropCount(Random random);

	//@Untested
	//public Item getDrop(BlockState blockState, Random random, int fortune)
	// double getRelativeHardness(Player player, WorldLocation location)
	// void dropAsItem(BlockState blockState, int fortune, WorldLocation location)
	// void dropAsItem(BlockState blockState, int fortune, double chance, WorldLocation locatsion)
	// Metadata getDropItemMetadata(BlockState blockState) //damageDropped()
	// double getExplosiveResistance(Entity entity)
	// boolean isWithinConstraints(Vector3D vector)
	// RenderGroup getRenderGroup()
	// int getRenderMode()
	double getMinWidth();

	double getMinHeight();

	double getMinDepth();

	double getMaxWidth();

	double getMaxHeight();

	double getMaxDepth();

	// ItemHeap createItemHeap(BlockState blockState)
	//public int getDropQuantity(int fortune, Random random)
	String getName();

	String getFullName();

	boolean areStatsEnabled();

	int getPistonMobility();

	float getAmbientOcclusion();

	/**
	 * Returns whether this block is a flower pot or not.
	 *
	 * @return true if this block is a flower pot
	 * @deprecated use {@code myBlock instanceof FlowerPot}
	 */
	@Deprecated
	boolean isFlowerPot();

	boolean canUpdate();

	// Item getDropItem()
	// int getDamage(Location location)
	// ArrayList<AbstractBlock> getVariants()
	// ItemTab getItemTab()

	boolean hasVariants();

	//isAssociatedBlock(AbstractBlock other)
	boolean isVariant(Block block);

	List<Block> getVariants();
	// BlockState getCurrentState()
	// BlockState getDefaultState()

}
