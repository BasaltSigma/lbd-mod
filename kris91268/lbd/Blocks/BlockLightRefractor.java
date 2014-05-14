package kris91268.lbd.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockLightRefractor extends Block
{
	public BlockLightRefractor()
	{
		super(Material.iron);
		setHardness(4.0F);
		setResistance(8.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("lightRefractor");
		setBlockTextureName("lightRefractor");
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	/** 
	 * Returns true if it is the input side, false if it is the output side, or neither.
	 *
	public boolean isInput(World par1World, int par2, int par3, int par4, int side)
	{
		if (this.getIcon(side, par1World.getBlockMetadata(par2, par3, par4)) == this.iconList[1])
		{
			return true;
		}
		else if (this.getIcon(side, par1World.getBlockMetadata(par2, par3, par4)) == this.iconList[0])
		{
			return false;
		}
		return false;
	}
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("iron_block");
		this.iconList[0] = par1IconRegister.registerIcon("lbd:" + this.getTextureName() + "_exit");
		this.iconList[1] = par1IconRegister.registerIcon("lbd:" + this.getTextureName() + "_input");
	}*/
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		return true;
    }
}