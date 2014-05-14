package kris91268.lbd.Tileentity;

import net.minecraft.world.World;

/**
 * Interface to define the guidelines of most tile entities in this mod. Must be used <br />
 * by every tile entity for that matter. 
 * @author Arbiter
 * @version 0.2.0
 */
public interface LightTile
{
	/**
	 * Called when the bridge has finished activating/deactivating
	 */
	void onFinished();
}