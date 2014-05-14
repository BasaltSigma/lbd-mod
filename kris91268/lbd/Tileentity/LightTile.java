package kris91268.lbd.Tileentity;

import net.minecraft.world.World;

/**
 * Interface to define the guidelines of most tile entities in this mod. Must be used <br />
 * by every tile entity representing a light device for that matter. Not to be used on other tile 
 * entities that do not have that purpose.
 * @author Arbiter
 */
public interface LightTile
{
	/**
	 * Called when the bridge has finished activating/deactivating
	 */
	void onFinished();
}