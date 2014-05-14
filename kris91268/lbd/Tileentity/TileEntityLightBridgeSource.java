package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Blocks.LinkMaker;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityLightBridgeSource extends TileEntity implements LightTile
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
			LinkMaker.deactivateBridges(getWorldObj(), xCoord, yCoord, zCoord, getBlockMetadata());
		}
	}
}
