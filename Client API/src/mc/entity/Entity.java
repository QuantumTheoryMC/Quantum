/**
 * 
 */
package mc.entity;

import mc.APIObject;
import mc.world.World;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Link
 *
 */
public abstract class Entity extends APIObject {

	protected int x, y, z;
	
	
	protected Entity() {
		super("");
	}
	
	protected abstract void init();
	
	/**
	 * Set the x location.
	 * 
	 * @param x the new x coordinate
	 */
	public final void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set the y location.
	 * 
	 * @param y the new y coordinate
	 */
	public final void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Set the z location.
	 * 
	 * @param z the new z coordinate
	 */
	public final void setZ(int z) {
		this.z = z;
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final int getZ() {
		return z;
	}
	
	private static class MEntity extends net.minecraft.entity.Entity {
		
		protected transient final Entity entity;
		protected MEntity(Entity entity) {
			super(World.getCurrentWorld().format());
			this.entity = entity;
			
		}

		@Override
		protected void entityInit() {
			entity.init();
		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		}
	}
	
	@Override
	public net.minecraft.entity.Entity format() {
		
		return new MEntity(this);
	}

}
