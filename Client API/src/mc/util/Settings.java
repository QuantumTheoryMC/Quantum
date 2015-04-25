/**
 * 
 */
package mc.util;

import net.minecraft.client.settings.GameSettings;

/**
 * Wrapper class for the minecraft in-game settings.
 * 
 * @author Link
 *
 */
public final class Settings {

	/**
	 * Returns the smooth lighting level.
	 * 
	 * @obfuscated true
	 * @return the smooth lighting level
	 */
	public static synchronized int getLightLevel() {
		return MCSettings.getSettings().ambientOcclusion;
	}

	/**
	 * Returns the in-game GUI scale. Scale types are small (0), normal (1), and
	 * large (2).
	 * 
	 * @obfuscated true
	 * @return the in-game GUI scale
	 * @see mc.util.Scale
	 */
	public static synchronized int getGUIScale() {
		return MCSettings.getSettings().guiScale;
	}

	/**
	 * Returns the maximum available framerate
	 * 
	 * @obfuscated true
	 * @return the max allowed FPS
	 */
	public static synchronized int getMaxFPS() {
		return MCSettings.getSettings().limitFramerate;
	}

	/**
	 * Returns the mip map levels.
	 * 
	 * @obfuscated true
	 * @return the mip map levels.
	 */
	public static synchronized int getMipMapLevels() {
		return MCSettings.getSettings().mipmapLevels;
	}

	/**
	 * Returns the width of the game window
	 * 
	 * @obfuscated true
	 * @return the width of the game window
	 */
	public static synchronized int getWidth() {
		return MCSettings.getSettings().overrideWidth;
	}

	/**
	 * Returns the height of the game window
	 * 
	 * @obfuscated true
	 * @return the height of the game window
	 */
	public static synchronized int getHeight() {
		return MCSettings.getSettings().overrideHeight;
	}

	/**
	 * Returns the allowed number of rendered particles. Flags are all(0),
	 * decreased(1), and minimal(2).
	 * 
	 * @obfuscated true
	 * @return the particle setting
	 */
	public static synchronized int getRenderedParticles() {
		return MCSettings.getSettings().particleSetting;
	}

	/**
	 * Returns the allowed radius of loaded chunks from the player.
	 * 
	 * @obfuscated true
	 * @return the radius of loaded chunks
	 */
	public static synchronized int getRenderDistance() {
		return MCSettings.getSettings().renderDistanceChunks;
	}

	/**
	 * Returns the FPS shown in Debug mode.
	 * 
	 * @obfuscated false
	 * @return the FPS
	 */
	public static int getDebugFPS() {
		return API.getDebugFPS();
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static int twitchChatEnabled() {
		return MCSettings.getSettings().streamChatEnabled;
	}

	/**
	 * Unknown
	 * 
	 * @return unknown
	 */
	public static int getTwitchChatUserFilter() {
		return MCSettings.getSettings().streamChatUserFilter;
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static int getTwitchCompressionRate() {
		return MCSettings.getSettings().streamCompression;
	}

	/**
	 * Unknown
	 * 
	 * @return unknown
	 */
	public static int getMicToggleBehavior() {
		return MCSettings.getSettings().streamMicToggleBehavior;
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static float getBytesPerPixelRate() {
		return MCSettings.getSettings().streamBytesPerPixel;
	}

	/**
	 * Returns the FPS for frames sent to the current twitch streaming session.
	 * 
	 * @obfuscated true
	 * @return the FPS for frames sent over the current stream.
	 */
	public static float getTwitchFPS() {
		return MCSettings.getSettings().streamFps;
	}

	/**
	 * Returns the volume sent over the current twitch streaming session. Does
	 * not affect game volume.
	 * 
	 * @obfuscated true
	 * @return the volume of the current twitch streaming session.
	 */
	public static float getTwitchGameVolume() {
		return MCSettings.getSettings().streamGameVolume;
	}

	/**
	 * Returns the stream rate in Kilobits per second.
	 * 
	 * @obfuscated true
	 * @return the stream rate in Kbps
	 */
	public static float getTwitchKbps() {
		return MCSettings.getSettings().streamKbps;
	}

	/**
	 * Returns the player's current point of view.
	 * 
	 * @obfuscated true
	 * @return the player's POV
	 */
	public static int getPlayerPOV() {
		return MCSettings.getSettings().thirdPersonView;
	}

	/**
	 * Returns the setting for item tooltips
	 * 
	 * @obfuscated true
	 * @return the setting for item tooltips
	 */
	public static boolean itemTooltipsEnabled() {
		return MCSettings.getSettings().advancedItemTooltips;
	}

	/**
	 * Returns the setting for 3D anaglyph.
	 * 
	 * @obfuscated true
	 * @return the setting for 3D anaglyph
	 */
	public static boolean anaglyphEnabled() {
		return MCSettings.getSettings().anaglyph;
	}

	/**
	 * Returns the setting for chat colors
	 * 
	 * @obfuscated true
	 * @return the setting for chat colors
	 */
	public static boolean chatColorsEnabled() {
		return MCSettings.getSettings().chatColours;
	}

	/**
	 * Returns the setting for chat links
	 * 
	 * @obfuscated true
	 * @return the setting for chat links
	 */
	public static boolean chatLinksEnabled() {
		return MCSettings.getSettings().chatLinks;
	}

	/**
	 * Returns the setting for chat link prompts
	 * 
	 * @obfuscated true
	 * @return the setting for chat link prompts
	 */
	public static boolean chatLinkPromptsEnabled() {
		return chatLinksEnabled() && MCSettings.getSettings().chatLinksPrompt;
	}

	/**
	 * Returns the setting for clouds
	 * 
	 * @obfuscated true
	 * @return the setting for clouds
	 */
	public static boolean cloudsEnabled() {
		return MCSettings.getSettings().clouds;
	}

	/**
	 * Returns the setting for the debug camera
	 * 
	 * @obfuscated true
	 * @return the setting for the debug camera
	 */
	public static boolean debugCameraEnabled() {
		return MCSettings.getSettings().debugCamEnable;
	}

	/**
	 * Enables/Disables the debug camera.
	 * 
	 * @obfuscated true
	 * @param enable
	 *            the value to set
	 */
	public static void enableDebugCamera(boolean enable) {
		MCSettings.getSettings().debugCamEnable = enable;
	}

	/**
	 * Returns the status of the monitor's vertical synchronization flag.
	 * 
	 * @obfuscated true
	 * @return true if vsync is enabled, false otherwise
	 */
	public static boolean vsyncEnabled() {
		return MCSettings.getSettings().enableVsync;
	}

	/**
	 * Enables/Disables VSync
	 * 
	 * @obfuscated true
	 * @param enable
	 *            the value to set
	 */
	public static void enableVSync(boolean enable) {

	}

	/**
	 * Returns the fancy graphics setting.
	 * 
	 * @obfuscated true
	 * @return true if enabled, false otherwise
	 */
	public static boolean fancyGraphicsEnabled() {
		return MCSettings.getSettings().fancyGraphics;
	}

	/**
	 * Returns the VBO setting
	 * 
	 * @obfuscated true
	 * @return the VBO setting
	 */
	public static boolean usingVBO() {
		return MCSettings.getSettings().fboEnable;
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static boolean a() {
		return MCSettings.getSettings().field_178879_v;
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static boolean b() {
		return MCSettings.getSettings().field_178880_u;
	}

	/**
	 * Unknown
	 * 
	 * @obfuscated true
	 * @return unknown
	 */
	public static boolean c() {
		return MCSettings.getSettings().field_178881_t;
	}

	/**
	 * Returns the forced unicode font setting.
	 * 
	 * @obfuscated true
	 * @return the forced unicode font setting
	 */
	public static boolean unicodeFontForced() {
		return MCSettings.getSettings().forceUnicodeFont;
	}

	/**
	 * Returns the full screen setting.
	 * 
	 * @obfuscated true
	 * @return the full screen setting
	 */
	public static boolean fullScreenEnabled() {
		return MCSettings.getSettings().fullScreen;
	}

	/**
	 * Returns the held item tooltip setting.
	 * 
	 * @obfuscated true
	 * @return the held item tooltip setting
	 */
	public static boolean heldItemTooltipsEnabled() {
		return MCSettings.getSettings().heldItemTooltips;
	}

	/**
	 * Returns the GUI hidden setting.
	 * 
	 * @obfuscated true
	 * @return the GUI hidden setting
	 */
	public static boolean hideGUI() {
		return MCSettings.getSettings().hideGUI;
	}

	/**
	 * Returns the server address hidden setting.
	 * 
	 * @obfuscated true
	 * @return the server address hidden setting
	 */
	public static boolean serverAddressHidden() {
		return MCSettings.getSettings().hideServerAddress;
	}

	/**
	 * Returns the inverted mouse setting.
	 * 
	 * @obfuscated true
	 * @return the inverted mouse setting
	 */
	public static boolean mouseInverted() {
		return MCSettings.getSettings().invertMouse;
	}

	/**
	 * Returns the pauseOnLostFocus setting.
	 * 
	 * @obfuscated true
	 * @return the pauseOnLostFocus setting
	 */
	public static boolean pausesIfFocusLost() {
		return MCSettings.getSettings().pauseOnLostFocus;
	}

	/**
	 * Returns the showDebugInfo setting.
	 * 
	 * @obfuscated true
	 * @return the showDebugInfo setting
	 */
	public static boolean showsDebugInfo() {
		return MCSettings.getSettings().showDebugInfo;
	}

	/**
	 * Returns the showDebugProfilerChart setting.
	 * 
	 * @obfuscated true
	 * @return the showDebugProfilerChart setting
	 */
	public static boolean showsDebugProfilerChart() {
		return MCSettings.getSettings().showDebugProfilerChart;
	}

	/**
	 * Returns the showInventoryAchievementHint setting.
	 * 
	 * @obfuscated true
	 * @return the showInventoryAchievementHint setting
	 */
	public static boolean inventoryAchievementHintsEnabled() {
		return MCSettings.getSettings().showInventoryAchievementHint;
	}

	/**
	 * Returns the smoothCamera setting.
	 * 
	 * @obfuscated true
	 * @return the smoothCamera setting
	 */
	public static boolean smoothCameraEnabled() {
		return MCSettings.getSettings().smoothCamera;
	}

	/**
	 * Returns the snooperEnabled setting.
	 * 
	 * @obfuscated true
	 * @return the snooperEnabled setting.
	 */
	public static boolean snooperEnabled() {
		return MCSettings.getSettings().snooperEnabled;
	}

	/**
	 * Returns the touchscreen setting.
	 * 
	 * @obfuscated true
	 * @return the touchscreen setting
	 */
	public static boolean isTouchScreen() {
		return MCSettings.getSettings().touchscreen;
	}

	/**
	 * Returns the viewBobbing setting.
	 * 
	 * @obfuscated true
	 * @return the viewBobbing setting
	 */
	public static boolean bobbingEnabled() {
		return MCSettings.getSettings().viewBobbing;
	}

	/**
	 * Returns the shouldRenderClouds() setting.
	 * 
	 * @obfuscated true
	 * @return the shouldRenderClouds() setting
	 */
	public static boolean cloudRenderingEnabled() {
		return MCSettings.getSettings().shouldRenderClouds();
	}

	private static class MCSettings extends GameSettings {
		private final static GameSettings gsettings = API.getMinecraft().gameSettings;

		public static GameSettings getSettings() {
			return gsettings;
		}
	}
}
