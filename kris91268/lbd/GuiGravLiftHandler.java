package kris91268.lbd;

import kris91268.lbd.Tileentity.TileEntityGravityLift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * 
 * @author Arbiter
 *
 */
public class GuiGravLiftHandler implements IGuiHandler
{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEntityGravityLift && id == 0)
		{
			return new GuiGravityLift((TileEntityGravityLift)tile);
		}
		return null;
	}
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEntityGravityLift && id == 0)
		{
			return new ContainerGravityLift();
		}
		return null;
	}
}