/**
 * 
 */
package test;

import java.util.Arrays;

import mc.util.API;

/**
 * @author Link
 *
 */
public class Main {

	public static void main(String[] args) {
		net.minecraft.client.main.Main.main(concat(new String[] { "--version", "1.8 API", "--accessToken", "0",
				"--assetsDir", Main.class.getClassLoader().getResource("assets").toString().substring(5), "--assetIndex", "1.8", "--userProperties", "{}" }, args));
		API.getLogger();
	}

	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
}
