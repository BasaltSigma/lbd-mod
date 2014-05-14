package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Blocks.LinkMaker;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityLightBarrierSourceActivated extends TileEntity implements LightTile
{
	private String name;
	
	public void func_91429_a(String par1Str)
	{
		this.name = par1Str;
	}
	public void onFinished()
	{
		if (ModLBD.enableLinkedBridges)
		{
			LinkMaker.activateBarriers(getWorldObj(), xCoord, yCoord, zCoord, getBlockMetadata());
		}
	}
}
