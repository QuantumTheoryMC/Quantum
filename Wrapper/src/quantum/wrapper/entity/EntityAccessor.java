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
 * Created file on 11/25/16 at 6:12 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.entity;

import quantum.api.entity.Entity;
import quantum.util.math.Vector3d;

/**
 * @author link
 */
@SuppressWarnings("MagicNumber") // IntelliJ IDEA
public class EntityAccessor implements Entity {

	private net.minecraft.entity.Entity entity;

	public EntityAccessor(net.minecraft.entity.Entity entity) {
		this.entity = entity;
	}

	@Override
	public double getX() {
		return entity.posX;
	}

	@Override
	public void setX(double x) {
		entity.setPosition(x, entity.posY, entity.posZ);
	}

	@Override
	public double getY() {
		return entity.posY;
	}

	@Override
	public void setY(double y) {
		entity.setPosition(entity.posX, y, entity.posZ);
	}

	@Override
	public double getZ() {
		return entity.lastTickPosZ;
	}

	@Override
	public void setZ(double z) {
		entity.setPosition(entity.posX, entity.posY, z);
	}

	@Override
	public Vector3d getLocation() {
		return new Vector3d(entity.posX, entity.posY, entity.posZ);
	}

	@Override
	public void setLocation(double x, double y, double z) {
		entity.setPosition(x, y, z);
	}

	@Override
	public double getYaw() {
		return entity.rotationYaw;
	}

	@Override
	public strictfp void setYaw(double yaw) {
		entity.rotationYaw = (float) (yaw % 360f);
	}

	@Override
	public double getPitch() {
		return entity.rotationPitch;
	}

	@Override
	public strictfp void setPitch(double pitch) {
		entity.rotationPitch = (float) (pitch % 360f);
	}

	@Override
	public double getRoll() {
		// not supported by vanilla minecraft
		return 0d;
	}

	@Override
	public void setRoll(double roll) {
		// not supported by vanilla minecraft
	}

	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public String getID() {
		return entity.getDisplayName().getUnformattedText();
	}
}
