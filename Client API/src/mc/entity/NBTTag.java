/**
 * 
 */
package mc.entity;

import java.util.Arrays;

import mc.entity.NBTElement.Value;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Link
 *
 */
public class NBTTag {

	private final NBTTagCompound tags;

	public NBTTag(NBTElement... tags) {
		this.tags = new NBTTagCompound();
	}

	public NBTElement getElement(String get) {
		return NBTElement.format(tags.getTag(get));
	}
	
	public boolean getBoolean(String get) {
		return tags.getBoolean(get);
	}
	
	public byte getByte(String get) {
		return tags.getByte(get);
	}
	
	public short getShort(String get) {
		return tags.getShort(get);
	}
	
	public int getInteger(String get) {
		return tags.getInteger(get);
	}
	
	public long getLong(String get) {
		return tags.getLong(get);
	}
	
	public float getFloat(String get) {
		return tags.getFloat(get);
	}
	
	public double getDouble(String get) {
		return tags.getDouble(get);
	}
	
	public byte[] getByteArray(String get) {
		return tags.getByteArray(get);
	}
	
	public int[] getIntegerArray(String get) {
		return tags.getIntArray(get);
	}
	
	public <V> NBTElement(Value value, String set, V val) {
		
	}
}
