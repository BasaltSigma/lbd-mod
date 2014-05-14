package kris91268.lbd.Tileentity;

import net.minecraft.util.ResourceLocation;

/**
 * A final class which holds all of my ResourceLocations for the rendering frames.
 * Ensures that only one copy of each ResourceLocation is ever created, thus saving memory.
 * @author Arbiter
 *
 */
public final class LightTextures
{
	/** The textures */
	public static final ResourceLocation[] bridgeTextures;
	public static final ResourceLocation[] barrierTextures;
	public static final ResourceLocation[] doorTextures;
	public static final ResourceLocation gravityTexture;
	
	/**
	 * Initializer for the resource locations. 
	 */
	static
	{
		gravityTexture = new ResourceLocation("lbd:textures/entity/lbd/gravLift.png");
		bridgeTextures = new ResourceLocation[19]; // there are only 18 frames
		barrierTextures = new ResourceLocation[16]; // "     "   "   16  "
		doorTextures = new ResourceLocation[16]; //    "    "    "   16  "
		for (int i = 0; i < bridgeTextures.length; i++)
		{
			bridgeTextures[i] = new ResourceLocation("lbd:textures/entity/lbd/lbsource/lbsource_" + i + ".png");
		}
		for (int i = 0; i < doorTextures.length && i < barrierTextures.length; i++)
		{
			doorTextures[i] = new ResourceLocation("lbd:textures/entity/lbd/ldsource/ldsource_" + i + ".png");
			barrierTextures[i] = new ResourceLocation("lbd:textures/entity/lbd/lbasource/lbasource_" + i + ".png");
		}
	}
	
	private LightTextures() {} // prevents instantation
}