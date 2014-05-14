package kris91268.drugcraft.utils;

import kris91268.drugcraft.drugs.Gui.ContainerDryingTable;
import kris91268.drugcraft.drugs.Gui.GuiDryingTable;
import kris91268.drugcraft.drugs.Tileentity.TileEntityDryingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * 
 * @author Arbiter
 *
 */
public class ModGuiHandler implements IGuiHandler
{
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityDryingTable tileEntity = (TileEntityDryingTable)world.getTileEntity(x, y, z);
		return new ContainerDryingTable(player.inventory, tileEntity);
	}
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		return tileEntity instanceof TileEntityDryingTable ? new GuiDryingTable(player.inventory, (TileEntityDryingTable)tileEntity) : null;
	}
}