package kris91268.lbd.Blocks;

import java.util.Random;

import kris91268.lbd.ModLBD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockLightRailSourceActivated extends BlockRailBase implements ILightSource
{
	public BlockLightRailSourceActivated()
	{
		super(false);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightRailSourceActive");
		setBlockTextureName("lbd:lightRailSourceActive");
		setCreativeTab((CreativeTabs)null);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setLightLevel(0.4F);
	}
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 0)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSourceActivated, 0, 2);
		}
		if (par5 == 1)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSourceActivated, 1, 2);
		}
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 0)
		{
			if (par1World.isSideSolid(par2, par3 - 1, par4, DOWN))
			{
				par1World.setBlock(par2, par3, par4, ModLBD.lightRailSource, 0, 2);
			}
			for (int par6 = par4 - 1; par6 >= par4 - ModLBD.railLength; --par6)
			{
				if (par1World.getBlock(par2, par3, par6) == ModLBD.lightRailSection)
				{
					par1World.setBlockToAir(par2, par3, par6);
				}
				else
				{
					break;
				}
			}
			for (int par6 = par4 + 1; par6 <= par4 + ModLBD.railLength; ++par6)
			{
				if (par1World.getBlock(par2, par3, par6) == ModLBD.lightRailSection)
				{
					par1World.setBlockToAir(par2, par3, par6);
				}
				else
				{
					break;
				}
			}
		}
		if (par5 == 1)
		{
			if (par1World.isSideSolid(par2, par3 - 1, par4, DOWN))
			{
				par1World.setBlock(par2, par3, par4, ModLBD.lightRailSource, 1, 2);
			}
			for (int par6 = par2 - 1; par6 >= par2 - ModLBD.railLength; --par6)
			{
				if (par1World.getBlock(par6, par3, par4) == ModLBD.lightRailSection)
				{
					par1World.setBlockToAir(par6, par3, par4);
				}
				else
				{
					break;
				}
			}
			for (int par6 = par2 + 1; par6 <= par2 + ModLBD.railLength; ++par6)
			{
				if (par1World.getBlock(par6, par3, par4) == ModLBD.lightRailSection)
				{
					par1World.setBlockToAir(par6, par3, par4);
				}
				else
				{
					break;
				}
			}
		}
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (par1World.isRemote)
		{
			if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
            	this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
            }
		}
	}
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		if (!par1World.isSideSolid(par2, par3 - 1, par4, DOWN))
		{
			this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
			par1World.setBlockToAir(par2, par3, par4);
		}
        if (!par1World.isRemote)
        {
            if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
            	this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
            }
        }	
    } 
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5, ItemStack par6ItemStack)
	{
		int par7 = MathHelper.floor_double((double)(par5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (par7 == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		}
		if (par7 == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
		if (par7 == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		}
		if (par7 == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
	}
	@Override
	public boolean isFlexibleRail(IBlockAccess par1World, int par2, int par3, int par4)
	{
		return false;
	}
	@Override
	public boolean canMakeSlopes(IBlockAccess par1World, int par2, int par3, int par4)
	{
		return false;
	}
}