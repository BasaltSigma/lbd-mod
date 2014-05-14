package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Blocks.LinkMaker;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityLightDoorSource extends TileEntity implements LightTile
{
	private String aString;
	
	public void func_91249_a(String par1Str)
	{
		this.aString = par1Str;
	}
	public void onFinished()
	{
		if (ModLBD.enableLinkedBridges)
		{
			LinkMaker.deactivateDoors(getWorldObj(), xCoord, yCoord, zCoord, getBlockMetadata());
		}
	}
}