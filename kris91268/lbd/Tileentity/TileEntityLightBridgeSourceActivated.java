package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Blocks.LinkMaker;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityLightBridgeSourceActivated extends TileEntity implements LightTile
{
	private String aString;
	
	public void func_91429_a(String par1Str)
	{
		this.aString = par1Str;
	}
	public void onFinished()
	{
		if (ModLBD.enableLinkedBridges)
		{
			LinkMaker.activateBridges(getWorldObj(), xCoord, yCoord, zCoord, getBlockMetadata());
		}
	}
}
