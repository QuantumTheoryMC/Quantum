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
package api.quantum.block;

import api.quantum.block.AbstractBlock.BlockInfo;
import api.quantum.block.AbstractBlock.BlockInfo.BlockConstraints;
import api.quantum.block.AbstractBlock.BlockInfo.BlockLighting;
import api.quantum.block.AbstractBlock.BlockInfo.ToolInfo;
import api.quantum.meta.Untested;
import api.quantum.meta.WIP;

import java.util.Random;

/**
 * @author link
 */
@WIP
class BlockWrapper {

	private final BlockImpl wrappedBlock;

	BlockWrapper(net.minecraft.block.Block block) {
		wrappedBlock = new BlockImpl(block);
	}

	BlockWrapper(BlockInfo info) {
		wrappedBlock = new BlockImpl(info);
	}

	net.minecraft.block.Block getWrappedBlock() {
		return wrappedBlock.getWrappedBlock();
	}

	boolean isSilkTouchEnabled() {
		return wrappedBlock.canSilkHarvest();
	}

	final boolean isFullBlock() {
		return wrappedBlock.getWrappedBlock().isFullBlock();
	}

	final int getOpacity() {
		return wrappedBlock.getWrappedBlock().getLightOpacity();
	}

	final int getLightEmission() {
		return wrappedBlock.getWrappedBlock().getLightValue();
	}

	final boolean isTranslucent() {
		return wrappedBlock.getWrappedBlock().isTranslucent();
	}

	final boolean useNeighborBrightness() {
		return wrappedBlock.getWrappedBlock().getUseNeighborBrightness();
	}

	// final BlockState convertToState(int metadata)
	// final int contertToMetadata(BlockState blockState)
	// final BlockState getCurrentState(int x, int y, int z)
	final boolean isFullSolid() {
		return wrappedBlock.getWrappedBlock().isSolidFullCube();
	}

	final boolean isDefault() {
		return wrappedBlock.getWrappedBlock().isNormalCube();
	}

	final boolean isOpaque() {
		return wrappedBlock.getWrappedBlock().isVisuallyOpaque();
	}

	@WIP
	final boolean isClippable() {
		return wrappedBlock.getWrappedBlock().isPassable(null, null);
	}

	final int getRenderMethod() {
		return wrappedBlock.getWrappedBlock().getRenderType();
	}

	@WIP
	final boolean isOverwritable() {
		return wrappedBlock.getWrappedBlock().isReplaceable(null, null);
	}

	// final boolean isOverwritable(WorldLocation location, (Block).Side side, ItemHeap item)
	// final boolean isOverwritable(WorldLocation location, (Block).Side side)
	//@Override
	// void RedstoneListener#sendPower(WorldLocation location, BlockState blockState, Player player, Side side, Vector3D hitLocation)
	//@Override
	// int BlockListener#getPowerLevel(WorldLocation location, BlockState blockState, Side side)
	//@Override
	// int BlockListener#getWeakPowerLevel(WorldLocation location, BlockState blockState, Side side)
	//@Override
	// boolean BlockListener#isPowerSource()
	//@Override
	// void BlockListener#harvest(Player player, WorldLocation location, BlockState blockState, BlockEntity blockEntity)
	@WIP
	final float getHardness() {
		return wrappedBlock.getWrappedBlock().getBlockHardness(null, null);
	}

	final boolean usesRandomTick() {
		return wrappedBlock.getWrappedBlock().getTickRandomly();
	}

	/**
	 * Defines whether this Block uses a BlockEntity (used for extra info for a
	 * Block in the World).
	 * <p>
	 * Blocks use BlockEntities to store extra information when they are
	 * rendered in the world. One example is signs; they use a BlockEntity to
	 * store the text on the sign.
	 *
	 * @return true if this Block uses a BlockEntity
	 */
	final boolean usesBlockEntity() {
		return wrappedBlock.getWrappedBlock().hasTileEntity();
	}

	@WIP
	final int getBrightness(int x, int y, int z) {
		return 15;
	}

	// final boolean canRenderSide(Direction direction)
	// AABB getAABB(WorldLocation location)
	// AABB getCollisionAABB(WorldLocation location)
	// void updateRandom(BlockState blockState, Random random, WorldLocation location)
	// abstract void update(BlockState blockState, Random random, WorldLocation location)
	@WIP
	final int getRemainingTicks() {
		return wrappedBlock.getWrappedBlock().tickRate(null);
	}

	//@Override
	// void blockAdded(BlockState blockState, WorldLocation location)
	//@Override
	// void onBlockBreak(BlockState blockState, WorldLocation location)
	@Untested
	final int getDropCount(Random random) {
		return wrappedBlock.getWrappedBlock().quantityDropped(random);
	}

	//@Untested
	// Item getDrop(BlockState blockState, Random random, int fortune)
	// final float getRelativeHardness(Player player, WorldLocation location)
	// final void dropAsItem(BlockState blockState, int fortune, WorldLocation location)
	// final void dropAsItem(BlockState blockState, int fortune, float chance, WorldLocation locatsion)
	// final Metadata getDropItemMetadata(BlockState blockState) //damageDropped()
	// final float getExplosiveResistance(Entity entity)
	// final boolean isWithinConstraints(Vector3D vector)
	// final RenderGroup getRenderGroup()
	// final int getRenderMode()
	final double getMinWidth() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMinX();
	}

	final double getMinHeight() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMinY();
	}

	final double getMinDepth() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMinZ();
	}

	final double getMaxWidth() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMaxX();
	}

	final double getMaxHeight() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMaxY();
	}

	final double getMaxDepth() {
		return wrappedBlock.getWrappedBlock().getBlockBoundsMaxZ();
	}

	// final ItemHeap createItemHeap(BlockState blockState)
	// int getDropQuantity(int fortune, Random random)
	final String getName() {
		return wrappedBlock.getWrappedBlock().getUnlocalizedName();
	}

	final String getFullName() {
		return getName() + ".name";
	}

	final boolean areStatsEnabled() {
		return wrappedBlock.getWrappedBlock().getEnableStats();
	}

	final int getPistonMobility() {
		return wrappedBlock.getWrappedBlock().getMobilityFlag();
	}

	final float getAmbientOcclusion() {
		return wrappedBlock.getWrappedBlock().getAmbientOcclusionLightValue();
	}

	/**
	 * Returns whether this block is a flower pot or not.
	 *
	 * @return true if this block is a flower pot
	 * @deprecated use {@code myBlock instanceof FlowerPot}
	 */
	@Deprecated
	final boolean isFlowerPot() {
		return wrappedBlock.getWrappedBlock().isFlowerPot();
	}

	// final Item getDropItem()
	// final int getDamage(Location location)
	// final ArrayList<Block> getVariants()
	// final ItemTab getItemTab()

	final boolean canUpdate() {
		return wrappedBlock.getWrappedBlock().requiresUpdates();
	}

	private static class BlockImpl {

		final net.minecraft.block.Block wrappedBlock;
		final boolean silkTouch;

		BlockImpl(net.minecraft.block.Block block) {
			wrappedBlock = block;
			silkTouch = true;
		}

		BlockImpl(BlockInfo info) {
			wrappedBlock = createBlock(info);
			silkTouch = ((BlockAccessor) wrappedBlock).silkTouch;
		}

		net.minecraft.block.Block getWrappedBlock() {
			return wrappedBlock;
		}

        boolean canSilkHarvest(1) {
            return silkTouch;
		}

		private BlockAccessor createBlock(BlockInfo info) {
			return new BlockAccessor(info);
		}

		private class BlockAccessor extends net.minecraft.block.Block {

			private final boolean silkTouch;

			BlockAccessor(BlockInfo info) {
				super(info.getType().getMaterialWrapper().getWrappedMaterial());

				BlockConstraints constraints = info.getConstraints();
				BlockLighting lighting = info.getLighting();
				ToolInfo toolInfo = info.getToolInfo();

				this.translucent = !lighting.isOpaque();
				this.lightOpacity = lighting.getOpacity();
				this.lightValue = lighting.getLightEmission();
				this.blockHardness = toolInfo.getHardness();
				this.blockResistance = toolInfo.getResistance();
				this.blockParticleGravity = toolInfo.getParticleGravity();
				this.minX = constraints.getMinWidth();
				this.minY = constraints.getMinHeight();
				this.minZ = constraints.getMinDepth();
				this.maxX = constraints.getMaxWidth();
				this.maxY = constraints.getMaxHeight();
				this.maxZ = constraints.getMaxDepth();
				this.silkTouch = toolInfo.isSilkTouchEnabled();

			}

			boolean isSilkTouchEnabled() {
				return silkTouch;
			}
		}

	}

	// final BlockState getCurrentState()
	// final BlockState getDefaultState()
}
