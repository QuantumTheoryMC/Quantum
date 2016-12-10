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
 * Created file on 7/20/16 at 8:44 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import quantum.api.entity.Entity;
import quantum.api.world.World;
import quantum.wrapper.minecraft.world.WorldAdapter;

/**
 * TODO
 *
 * @author link
 */
public class EntityAdapter extends net.minecraft.entity.Entity {

	public EntityAdapter(Entity entity) {
		super(new WorldAdapter(World.getCurrentWorld()));
	}

	public static EntityAdapter get(Entity entity) {
		// TODO
		return new EntityAdapter(entity);
	}

	@Override
	public void onChunkLoad() {

	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {

	}

	@Override
	public void onEntityUpdate() {

	}

	@Override
	public void onKillEntity(EntityLivingBase entity) {

	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightning) {

	}

	@Override
	public void onUpdate() {

	}

	@Override
	protected void preparePlayerToSpawn() {

	}


	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {

	}

}
