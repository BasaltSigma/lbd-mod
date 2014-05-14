package kris91268.lbd.Blocks;

import java.util.Random;
import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSourceActivated;
import kris91268.lbd.Tileentity.TileEntityLightDoorSource;
import kris91268.lbd.Tileentity.TileEntityLightDoorSourceActivated;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import static net.minecraftforge.common.util.ForgeDirection.*;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightDoorSourceActivated extends BlockContainer implements ILightSource
{
	public BlockLightDoorSourceActivated(Material material)
	{
		super(material);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightDoorSourceActive");
		setBlockTextureName("lbd:lightSection");
		setLightLevel(0.4F);
	}
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }
	public int getRenderBlockPass()
	{
		return 1;
	}
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (ModLBD.doesDoorHurt)
		{
			par5Entity.attackEntityFrom(DamageSource.cactus, 4);
		}
	}
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		par1World.setBlock(par2, par3, par4, ModLBD.lightDoorSource, par5, 2);
		outer : for (int par7 = par3 - 1; par7 >= par3 - ModLBD.bridgeLength; --par7)
		{
			if (par1World.getBlock(par2, par7, par4) == ModLBD.lightDoorSection)
			{
				par1World.setBlockToAir(par2, par7, par4);
			}
			else
			{
				for (Block b : allowedBlocks)
				{
					if (par1World.getBlock(par2, par7, par4) == b)
					{
						continue outer;
					}
				}
				break;
			}
		}
		TileEntity passover = par1World.getTileEntity(par2, par3, par4);
		if (passover instanceof TileEntityLightDoorSource)
		{
			((TileEntityLightDoorSource)passover).onFinished();
		}
	}
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (par1World.isRemote)
		{
			if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) &&
					!LinkMaker.isAdjacentDoorActive(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4)))
			{
				this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
			}
		}
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int par5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float par6 = 0.625F;
		float par7 = 0.575F;
		if (par5 == 5)
		{
			setBlockBounds(1.0F - par6, 0.0F, 0.0F, 0.55F, 1.0F, 1.0F);
		}
		if (par5 == 1)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - par7, 1.0F, 1.0F, 0.625F);
		}
		if (par5 == 2)
		{
			setBlockBounds(1.0F - par7, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
		}
		if (par5 == 3)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - par6, 1.0F, 1.0F, 0.55F);
		}
	}
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		if (!par1World.isSideSolid(par2, par3 + 1, par4, UP))
		{
			this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), true);
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
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int par7 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (par7 == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}
		if (par7 == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}
		if (par7 == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}
		if (par7 == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityLightDoorSourceActivated)par1World.getTileEntity(par2, par3, par4)).something(par6ItemStack.getDisplayName());
		}
	}
	public boolean isOpaqueCube()
	{
		return false;
	}
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	public int getRenderType()
	{
		return -1;
	}
	public TileEntity getBlockEntity()
	{
		return new TileEntityLightDoorSourceActivated();
	}
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityLightDoorSourceActivated();
	}
}