package kris91268.drugcraft.drugs.Gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kris91268.drugcraft.drugs.Tileentity.TileEntityDryingTable;
import kris91268.drugcraft.utils.DryerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * 
 * @author Arbiter
 *
 */
public class ContainerDryingTable extends Container
{
	private TileEntityDryingTable tile;
	private int lastDryTime;
	
	public ContainerDryingTable(InventoryPlayer par1, TileEntityDryingTable par2)
	{
		this.tile = par2;
		this.addSlotToContainer(new Slot(par2, 0, 56, 35));
		this.addSlotToContainer(new SlotFurnace(par1.player, par2, 1, 116, 35));
		int i;
		for (i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(par1, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(par1, i, 8 + i * 18, 142));
		}
	}
	@Override
	public void addCraftingToCrafters(ICrafting par1)
	{
		super.addCraftingToCrafters(par1);
		par1.sendProgressBarUpdate(this, 0, this.tile.tableDryingTime);
	}
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting crafting = (ICrafting)this.crafters.get(i);
			if (this.lastDryTime != this.tile.tableDryingTime)
			{
				crafting.sendProgressBarUpdate(this, 0, this.tile.tableDryingTime);
			}
		}
		this.lastDryTime = this.tile.tableDryingTime;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.tile.tableDryingTime = par2;
		}
	}
	public boolean canInteractWith(EntityPlayer par1)
	{
		return this.tile.isUseableByPlayer(par1);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (DryerRecipes.INSTANCE.getDryingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}