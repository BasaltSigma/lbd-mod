package kris91268.lbd.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockLightRailSection extends BlockRailBase
{
	public BlockLightRailSection()
	{
		super(false);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightRailSection");
		setBlockTextureName("lbd:lightRailSection");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setCreativeTab((CreativeTabs)null);
		setLightLevel(0.4F);
	}
	public boolean isFlexibleRail(World par1World, int par2, int par3, int par4)
	{
		return false;
	}
	public boolean canMakeSlopes(World par1World, int par2, int par3, int par4)
	{
		return false;
	}
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return false;
	}
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		return;
	}
	protected void func_150052_a(World par1World, int par2, int par3, int par4, boolean par5)
	{
		return;
	}
	public boolean isOpaqueCube()
	{
		return false;
	}
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5, ItemStack par6ItemStack)
	{
		// 2 is west facing, 3 is south facing, 1 is north facing, 5 is east facing
		int par7 = MathHelper.floor_double((double)(par5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		// South facing
		if (par7 == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		}
		// West facing
		if (par7 == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
		// North facing
		if (par7 == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		}
		// East facing
		if (par7 == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
	}
}