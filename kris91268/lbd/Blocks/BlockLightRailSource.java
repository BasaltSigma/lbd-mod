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

public class BlockLightRailSource extends BlockRailBase implements ILightSource
{
	public BlockLightRailSource()
	{
		super(false);
		setHardness(1.5F);
		setResistance(0.5F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("lightRailSource");
		setBlockTextureName("lbd:lightRailSource");
		setCreativeTab(CreativeTabs.tabTransport);
	}
	public boolean isPowered()
	{
		return false;
	}
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 0)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSourceActivated, 0, 2);
			for (int par6 = par4 - 1; par6 >= par4 - ModLBD.railLength; --par6)
			{
				if (par1World.isAirBlock(par2, par3, par6))
				{
					par1World.setBlock(par2, par3, par6, ModLBD.lightRailSection, 0, 2);
				}
				else
				{
					break;
				}
			}
			for (int par6 = par4 + 1; par6 <= par4 + ModLBD.railLength; ++par6)
			{
		        if (par1World.isAirBlock(par2, par3, par6))
		        {
		        	par1World.setBlock(par2, par3, par6, ModLBD.lightRailSection, 0, 2);
		        }
		        else
		        {
		        	break;
		        }
			}
		}
		if (par5 == 1)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSourceActivated, 1, 2);
			for (int par6 = par2 - 1; par6 >= par2 - ModLBD.railLength; --par6)
			{
				if (par1World.isAirBlock(par6, par3, par4))
				{
					par1World.setBlock(par6, par3, par4, ModLBD.lightRailSection, 1, 2);
				}
				else
				{
					break;
				}
			}
			for (int par6 = par2 + 1; par6 <= par2 + ModLBD.railLength; ++par6)
			{
				if (par1World.isAirBlock(par6, par3, par4))
				{
					par1World.setBlock(par6, par3, par4, ModLBD.lightRailSection, 1, 2);
				}
				else
				{
					break;
				}
			}
		}
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 0)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSource, 0, 2);
		}
		if (par5 == 1)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightRailSource, 1, 2);
		}
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
		{
			if (par1World.isSideSolid(par2, par3 - 1, par4, DOWN, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
			}
			else if (par1World.isSideSolid(par2, par3 - 1, par4, DOWN, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
			}
		}
		if (par1World.isRemote)
		{
			if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				this.activate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
			}
		}
	}
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int par10 = par9;
		if (par9 == 1 && par1World.isSideSolid(par2, par3 - 1, par4, DOWN, true))
		{
			par10 = 1;
		}
		if (par9 == 0 && par1World.isSideSolid(par2, par3 - 1, par4, DOWN, true))
		{
			par10 = 0;
		}
		return par10;
	}
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return par1World.isSideSolid(par2, par3 - 1, par4, DOWN);
	}
	protected boolean dropRailIfCantStay(World par1World, int par2, int par3, int par4)
	{
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
		{
			if (par1World.getBlock(par2, par3, par4) == this)
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}
			return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		if (this.dropRailIfCantStay(par1World, par2, par3, par4))
		{			
			if (!par1World.isSideSolid(par2, par3 - 1, par4, DOWN))
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
		if (!par1World.isRemote)
		{
			if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				this.activate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
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