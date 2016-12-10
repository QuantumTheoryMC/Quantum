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
 * Created file on 12/24/15 at 11:38 AM.
 *
 * This file is part of Quantum API
 */
package quantum.api.entity;

import quantum.api.MinecraftObject;
import quantum.math.Vector3d;

/**
 * @author link
 */
public interface Entity extends MinecraftObject {

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getZ();

	void setZ(double z);

	Vector3d getLocation();

	void setLocation(double x, double y, double z);

	/**
	 * Gets the yaw rotation in degrees of this Entity.
	 * <p>
	 * The Yaw rotation is bound to the Y and Z axis, and rotates about the
	 * bottom-center of the Entity (as seen when a mob is killed).
	 * </p>
	 *
	 * @return the yaw rotation in degrees
	 */
	double getYaw();

	/**
	 * Sets the yaw rotation in degrees of this Entity.
	 *
	 * @param yaw
	 * 		the yaw rotation in degrees
	 */
	void setYaw(double yaw);

	/**
	 * Gets the pitch rotation in degrees of this Entity.
	 * <p>
	 * The Pitch rotation is bound to the Y and Z axis, and rotates about the
	 * origin.
	 * </p>
	 *
	 * @return the pitch rotation in degrees
	 */
	double getPitch();

	/**
	 * Sets the pitch rotation in degrees of this Entity.
	 *
	 * @param pitch
	 * 		the pitch rotation in degrees
	 */
	void setPitch(double pitch);

	/**
	 * <p>
	 * <em><strong>WARNING</strong>: Not supported by vanilla minecraft
	 * (yet)!</em>
	 * </p>
	 * Gets the roll rotation in degrees of this Entity.
	 * <p>
	 * The Roll rotation is bound to the Y and X axis, and rotates about the
	 * origin.
	 * </p>
	 *
	 * @return the roll rotation in degrees
	 */
	double getRoll();

	/**
	 * <p>
	 * <em><strong>WARNING</strong>: Not supported by vanilla minecraft
	 * (yet)!</em>
	 * </p>
	 * Sets the roll rotation in degrees of this Entity.
	 *
	 * @param roll
	 * 		the roll rotation in degrees
	 */
	void setRoll(double roll);

}
