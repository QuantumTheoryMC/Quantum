/**
 * 
 */
package mc.entity;

import mc.APIObject;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

/**
 * @author Link
 *
 */
public class NBTElement extends APIObject {

	private final NBTBase nbt;
	private Value type;

	public static enum Value {
		/** Indicates this NBTTag will contain a value of type Boolean */
		BOOLEAN,
		/** Indicates this NBTTag will contain a value of type Byte */
		BYTE,
		/** Indicates this NBTTag will contain a value of type Short */
		SHORT,
		/** Indicates this NBTTag will contain a value of type Integer */
		INTEGER,
		/** Indicates this NBTTag will contain a value of type Long */
		LONG,
		/** Indicates this NBTTag will contain a value of type Float */
		FLOAT,
		/** Indicates this NBTTag will contain a value of type Double */
		DOUBLE,
		/** Indicates this NBTTag will contain a value of type Byte[] */
		BYTE_ARRAY,
		/** Indicates this NBTTag will contain a value of type Integer[] */
		INTEGER_ARRAY
	}

	/**
	 * 
	 */
	public <V> NBTElement(String name, V value, Value val) throws UnsupportedOperationException {
		super(name);
		// debug
		assert value != null : "[Error] NBTElement.<init>:[value:[" + value + "], value cannot be null]";

		switch (val) {
		case BOOLEAN:
			nbt = new NBTTagByte((byte) ((Boolean) value == true ? 1 : 0));
			break;
		case BYTE:
			nbt = new NBTTagByte((byte) value);
			break;
		case SHORT:
			nbt = new NBTTagShort((short) value);
			break;
		case INTEGER:
			nbt = new NBTTagInt((int) value);
			break;
		case LONG:
			nbt = new NBTTagLong((long) value);
			break;
		case FLOAT:
			nbt = new NBTTagFloat((float) value);
			break;
		case DOUBLE:
			nbt = new NBTTagDouble((double) value);
			break;
		case BYTE_ARRAY:
			nbt = new NBTTagByteArray((byte[]) value);
			break;
		case INTEGER_ARRAY:
			nbt = new NBTTagIntArray((int[]) value);
			break;
		default:
			throw new UnsupportedOperationException("Unsupported Primitive NBTTag type: " + val.name());
		}
		this.type = val;
	}

	@Override
	public NBTBase format() {
		return nbt;
	}

	Value getType() {
		return type;
	}

	public static NBTElement format(NBTTagByte nbt) {
		return new NBTElement(nbt.toString(), nbt.getByte(), Value.BYTE);
	}

}
