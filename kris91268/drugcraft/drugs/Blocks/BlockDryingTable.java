package kris91268.drugcraft.drugs.Blocks;

import java.util.Random;

import kris91268.drugcraft.DrugCraft;
import kris91268.drugcraft.drugs.Tileentity.TileEntityDryingTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockDryingTable extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons; // 0 is bottom, 1 is top, 2 is sides, 3 is top while drying
	private boolean drying;
	private final Random rand = new Random();
	
	public BlockDryingTable()
	{
		super(Material.wood);
		setBlockName("dryingTable");
		setBlockTextureName("drugcraft:dryingTable");
		setCreativeTab(DrugCraft.tabDrugCraft);
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 0 ? this.icons[0] : (par1 == 1 ? (drying ? icons[3] : icons[1]) : this.icons[2]);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1)
	{
		this.icons = new IIcon[4];
		this.icons[0] = par1.registerIcon(this.getTextureName() + "_bottom");
		this.icons[1] = par1.registerIcon(this.getTextureName() + "_top");
		this.icons[2] = par1.registerIcon(this.getTextureName() + "_side");
		this.icons[3] = par1.registerIcon(this.getTextureName() + "_top_active");
	}
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityDryingTable();
	}
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5, int par6, float par7, float par8, float par9)
	{
		if (!par1World.isRemote)
		{
			TileEntityDryingTable tileEntity = (TileEntityDryingTable)par1World.getTileEntity(par2, par3, par4);
			if (tileEntity != null)
			{
				par5.openGui(DrugCraft.instance, 0, par1World, par2, par3, par4);
			}
		}
		return true;
	}
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		TileEntityDryingTable tileEntity = (TileEntityDryingTable)par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null)
		{
			for (int i = 0; i < tileEntity.getSizeInventory(); i++)
			{
				ItemStack itemstack = tileEntity.getStackInSlot(i);
				if (itemstack != null)
				{
					float f = rand.nextFloat() * 0.8F + 0.1F;
					float f1 = rand.nextFloat() * 0.8F + 0.1F;
					float f2 = rand.nextFloat() * 0.8F + 0.1F;
					while (itemstack.stackSize > 0)
					{
						int i1 = rand.nextInt(21) + 10;
						if (i1 > itemstack.stackSize)
						{
							i1 = itemstack.stackSize;
						}
						itemstack.stackSize -= i1;
						EntityItem entityItem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), 
								new ItemStack(itemstack.getItem(), i1, itemstack.getItemDamage()));
						if (itemstack.hasTagCompound())
						{
							entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
						float f3 = 0.05F;
						entityItem.motionX = (double)((float)rand.nextGaussian() * f3);
						entityItem.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
						entityItem.motionZ = (double)((float)rand.nextGaussian() * f3);
						par1World.spawnEntityInWorld(entityItem);
					}
				}
			}
			par1World.func_147453_f(par2, par3, par4, par5);
		}
		super.breakBlock(par1World, par2, par3, par4,  par5, par6);
	}
}