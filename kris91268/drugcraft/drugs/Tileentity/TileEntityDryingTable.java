package kris91268.drugcraft.drugs.Tileentity;

import java.util.Scanner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kris91268.drugcraft.utils.DryerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityDryingTable extends TileEntity implements ISidedInventory
{
	private static final int[] slotsTop = new int[] {1}, slotsBottom = new int[] {}, slotsSides = new int[] {0};
	private ItemStack[] tableItemStacks = new ItemStack[2];
	public int tableDryingTime;
	private String inventoryName;
	
	public int getSizeInventory()
	{
		return this.tableItemStacks.length;
	}
	public ItemStack getStackInSlot(int par1)
	{
		return this.tableItemStacks[par1];
	}
	public int getInventoryStackLimit()
	{
		return 4;
	}
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (tableItemStacks[par1] != null)
		{
			ItemStack stack;
			if (tableItemStacks[par1].stackSize <= par2)
			{
				stack = this.tableItemStacks[par1];
				this.tableItemStacks[par1] = null;
				return stack;
			}
			else
			{
				stack = this.tableItemStacks[par1].splitStack(par2);
				if (this.tableItemStacks[par1].stackSize == 0)
				{
					this.tableItemStacks[par1] = null;
				}
				return stack;
			}
		}
		else
		{
			return null;
		}
	}
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.tableItemStacks[par1] != null)
		{
			ItemStack stack = this.tableItemStacks[par1];
			this.tableItemStacks[par1] = null;
			return stack;
		}
		else
		{
			return null;
		}
	}
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.tableItemStacks[par1] = par2ItemStack;
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? inventoryName : "Drying Table";
	}
	public boolean hasCustomInventoryName()
	{
		return this.inventoryName != null && this.inventoryName.length() > 0;
	}
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false :
			par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	public void openInventory() {}
	public void closeInventory() {}
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 != 1;
	}
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slotsSides : slotsTop;
	}
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return true;
	}
	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);
		NBTTagList taglist = par1.getTagList("Items", 10);
		this.tableItemStacks = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < taglist.tagCount(); i++)
		{
			NBTTagCompound tag = taglist.getCompoundTagAt(i);
			byte b = tag.getByte("Slot");
			if (b >= 0 && b < this.tableItemStacks.length)
			{
				this.tableItemStacks[b] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.tableDryingTime = par1.getShort("DryingTime");
		if (par1.hasKey("CustomName", 8))
		{
			this.inventoryName = par1.getString("CustomName");
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
		par1.setShort("DryingTime", (short)this.tableDryingTime);
		NBTTagList taglist = new NBTTagList();
		for (int i = 0; i < this.tableItemStacks.length; i++)
		{
			if (this.tableItemStacks[i] != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				this.tableItemStacks[i].writeToNBT(tag);
				taglist.appendTag(tag);
			}
		}
		par1.setTag("Items", taglist);
		if (this.hasCustomInventoryName())
		{
			par1.setString("CustomName", this.inventoryName);
		}
	}
	@Override
	public void updateEntity()
	{
		boolean flag = false;
		if (!this.worldObj.isRemote)
		{
			if (this.tableItemStacks[0] != null && canDry())
			{
				++tableDryingTime;
				if (this.tableDryingTime == 4000)
				{
					this.tableDryingTime = 0;
					dryItem();
					flag = true;
				}
			}
		}
		if (flag)
		{
			markDirty();
		}
	}
	@SideOnly(Side.CLIENT)
	public int getDryProgress(int par1)
	{
		return this.tableDryingTime * par1 / 4000;
	}
	public void dryItem()
	{
		if (canDry())
		{
			ItemStack itemstack = DryerRecipes.INSTANCE.getDryingResult(this.tableItemStacks[0]);
			if (this.tableItemStacks[1] == null)
			{
				this.tableItemStacks[1] = itemstack.copy();
				this.tableItemStacks[1].stackSize = tableItemStacks[0].stackSize;
			}
			else if (this.tableItemStacks[1].getItem() == itemstack.getItem())
			{
				this.tableItemStacks[1].stackSize += tableItemStacks[0].stackSize;
			}
			this.tableItemStacks[0] = null;
		}
	}
	private boolean canDry()
	{
		if (tableItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			if (!this.worldObj.provider.hasNoSky)
			{
				if (this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord))
				{
					ItemStack itemstack = DryerRecipes.INSTANCE.getDryingResult(tableItemStacks[0]);
					if (itemstack == null) return false;
					if (this.tableItemStacks[1] == null) return true;
					if (!this.tableItemStacks[1].isItemEqual(itemstack)) return false;
					int result = this.tableItemStacks[1].stackSize + itemstack.stackSize;
					return result <= getInventoryStackLimit() && result <= this.tableItemStacks[1].getMaxStackSize();
				}
			}
			return false;
		}
	}
}