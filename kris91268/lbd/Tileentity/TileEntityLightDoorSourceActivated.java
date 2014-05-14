package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Blocks.LinkMaker;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityLightDoorSourceActivated extends TileEntity implements LightTile
{
	private String aString;
	
	public void something(String par1Str)
	{
		this.aString = par1Str;
	}
	public void onFinished()
	{
		if (ModLBD.enableLinkedBridges)
		{
			LinkMaker.activateDoors(getWorldObj(), xCoord, yCoord, zCoord, getBlockMetadata());
		}
	}
}
