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
package api.quantum.sound;

import api.quantum.error.UnexpectedError;
import net.minecraft.client.audio.SoundCategory;

/**
 * @author link
 */
public enum SoundGroup {
	MASTER(SoundCategory.MASTER),
	MUSIC(SoundCategory.MUSIC),
	RECORD(SoundCategory.RECORDS),
	WEATHER(SoundCategory.WEATHER),
	BLOCK(SoundCategory.BLOCKS),
	HOSTILE(SoundCategory.MOBS),
	NEUTRAL(SoundCategory.ANIMALS),
	PLAYER(SoundCategory.PLAYERS),
	AMBIENT(SoundCategory.AMBIENT);

	private final SoundCategory category;

	SoundGroup(SoundCategory category) {
		this.category = category;
	}

	public static SoundGroup get(String groupName) throws UnexpectedError {
		return wrap(SoundCategory.func_147154_a(groupName));
	}

	private static SoundGroup wrap(SoundCategory cat) throws UnexpectedError {
		switch (cat) {
			case MASTER:
				return MASTER;
			case MUSIC:
				return MUSIC;
			case WEATHER:
				return WEATHER;
			case BLOCKS:
				return BLOCK;
			case MOBS:
				return HOSTILE;
			case ANIMALS:
				return NEUTRAL;
			case PLAYERS:
				return PLAYER;
			case AMBIENT:
				return AMBIENT;
			default:
				// John Cena was here
				throw new UnexpectedError(SoundGroup.class,
						                         "An unsupported SoundGroup was found. This is a serious error. Either you are using a version of the API not made for this version of minecraft or link forgot to abstract something.");
		}
	}

	public String getName() {
		return category.getCategoryName();
	}

	public int getID() {
		return category.getCategoryId();
	}
}
