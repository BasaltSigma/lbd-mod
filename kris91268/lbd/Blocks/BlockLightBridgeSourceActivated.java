package kris91268.lbd.Blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSourceActivated;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightBridgeSourceActivated extends BlockContainer implements ILightSource
{
	public boolean activated;
	
	public BlockLightBridgeSourceActivated(Material material)
	{
		super(material);
		setTickRandomly(true);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightBridgeSourceActive");
		setBlockTextureName("lbd:lightSection");
		setBlockBounds(0.0F, 1.0F, 0.0F, 1.0F, 0.8F, 1.0F);
		setLightLevel(0.4F);
	}
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 5)
    	{
    		par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSource, 5, 2);
    		outer : for (int par7 = par2 + 1; par7 <= par2 + ModLBD.bridgeLength; ++par7)
    		{
    			if (par1World.getBlock(par7, par3, par4) == ModLBD.lightBridge)
    			{
    				par1World.setBlockToAir(par7, par3, par4);
    			}
    			else
    			{
    				for (Block b : allowedBlocks)
    				{
    					if (par1World.getBlock(par7, par3, par4) == b)
    					{
    						continue outer;
    					}
    				}
    				break;
    			}
    		}
    	}
		else if (par5 == 1)
    	{
    		par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSource, 1, 2);
    		outer : for (int par7 = par4 - 1; par7 >= par4 - ModLBD.bridgeLength; --par7)
    		{
    			if (par1World.getBlock(par2, par3, par7) == ModLBD.lightBridge)
    			{
    				par1World.setBlockToAir(par2, par3, par7);
    			}
    			else
    			{
    				for (Block b : allowedBlocks)
    				{
    					if (par1World.getBlock(par2, par3, par7) == b)
    					{
    						continue outer;
    					}
    				}
    				break;
    			}
    		}
    	}
		else if (par5 == 2)
    	{
    		par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSource, 2, 2);
    		outer : for (int par7 = par2 - 1; par7 >= par2 - ModLBD.bridgeLength; --par7)
    		{
    			if (par1World.getBlock(par7, par3, par4) == ModLBD.lightBridge)
    			{
    				par1World.setBlockToAir(par7, par3, par4);
    			}
    			else
    			{
    				for (Block b : allowedBlocks)
    				{
    					if (par1World.getBlock(par7, par3, par4) == b)
    					{
    						continue outer;
    					}
    				}
    				break;
    			}
    		}
    	}
		else if (par5 == 3)
    	{
    		par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSource, 3, 2);
    		outer : for (int par7 = par4 + 1; par7 <= par4 + ModLBD.bridgeLength; ++par7)
    		{
    			if (par1World.getBlock(par2, par3, par7) == ModLBD.lightBridge)
    			{
    				par1World.setBlockToAir(par2, par3, par7);
    			}
    			else
    			{
    				for (Block b : allowedBlocks)
    				{
    					if (par1World.getBlock(par2, par3, par7) == b)
    					{
    						continue outer;
    					}
    				}
    				break;
    			}
    		}
    	}
		TileEntity passover = par1World.getTileEntity(par2, par3, par4);
		if (passover instanceof TileEntityLightBridgeSource)
		{
			((TileEntityLightBridgeSource)passover).onFinished();
		}
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		if (!par1World.isRemote)
		{
			if ((!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) && 
					(!LinkMaker.isAdjacentBridgeActive(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4))))
            {
            	this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
            }
		}
	}
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		int par6 = par1World.getBlockMetadata(par2, par3, par4);
		boolean flag = false;
		if (!par1World.isSideSolid(par2 - 1, par3, par4, EAST, true) && par6 == 5)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2 + 1, par3, par4, WEST, true) && par6 == 2)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2, par3, par4 - 1, SOUTH, true) && par6 == 3)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2, par3, par4 + 1, NORTH, true) && par6 == 1)
		{
			flag = true;
		}
		if (flag)
		{
			this.deactivate(par1World, par2, par3, par4, par6, false);
			par1World.setBlockToAir(par2, par3, par4);
		}
        if (!par1World.isRemote)
        {
            if ((!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)))
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
			((TileEntityLightBridgeSourceActivated)par1World.getTileEntity(par2, par3, par4)).func_91429_a(par6ItemStack.getDisplayName());
		}
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public int getRenderType()
	{
		return -1;
	}
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityLightBridgeSourceActivated();
	}
}
