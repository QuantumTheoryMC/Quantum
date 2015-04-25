/**
 * 
 */
package mc;

import java.util.Arrays;

/**
 * @author Link
 *
 */
public class Main {

	public static void main(String... args) {
		net.minecraft.client.main.Main.main(new String[] {"--version", "mcp", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}", Arrays.toString(args)});
	}
}
