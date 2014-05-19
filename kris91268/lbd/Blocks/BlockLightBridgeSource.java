package kris91268.lbd.Blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import java.util.Random;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSource;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSourceActivated;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightBridgeSource extends BlockContainer implements ILightSource
{
	public transient final boolean powered = false;
	
	public BlockLightBridgeSource(Material material)
	{
		super(material); // this is where the error originates.
		setHardness(1.5F);
		setResistance(6.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("lightBridgeSource");
		setBlockTextureName("lbd:lightBridgeSource");
		setCreativeTab(CreativeTabs.tabRedstone);
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
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		 // 2 is west facing, 3 is south facing, 1 is north facing, 5 is east facing
		switch (par5)
		{
		case 5:
			par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSourceActive, par5, 2);
			outer : for (int par6 = par2 + 1; par6 <= par2 + ModLBD.bridgeLength; ++par6)
			{
				if (par1World.isAirBlock(par6, par3, par4))
				{
					par1World.setBlock(par6, par3, par4, ModLBD.lightBridge, par5, 2);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							continue outer;
						}
					}
					for (Block b : burnsOnCollision)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							par1World.setBlock(par6, par3, par4, Blocks.fire);
							break outer;
						}
					}
					break;
				}
			}
			break;
		case 1:
			par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSourceActive, par5, 2);
			outer : for (int par6 = par4 - 1; par6 >= par4 - ModLBD.bridgeLength; --par6)
			{
				if (par1World.isAirBlock(par2, par3, par6))
				{
					par1World.setBlock(par2, par3, par6, ModLBD.lightBridge, par5, 2);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							continue outer;
						}
					}
					for (Block b : burnsOnCollision)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							par1World.setBlock(par2, par3, par6, Blocks.fire);
							break outer;
						}
					}
					break;
				}
			}
			break;
		case 2:
			par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSourceActive, par5, 2);
			outer : for (int par6 = par2 - 1; par6 >= par2 - ModLBD.bridgeLength; --par6)
			{
				if (par1World.isAirBlock(par6, par3, par4))
				{
					par1World.setBlock(par6, par3, par4, ModLBD.lightBridge, par5, 2);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							continue outer;
						}
					}
					for (Block b : burnsOnCollision)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							par1World.setBlock(par6, par3, par4, Blocks.fire);
							break outer;
						}
					}
					break;
				}
			}
			break;
		case 3:
			par1World.setBlock(par2, par3, par4, ModLBD.lightBridgeSourceActive, par5, 2);
			outer : for (int par6 = par4 + 1; par6 <= par4 + ModLBD.bridgeLength; ++par6)
			{
				if (par1World.isAirBlock(par2, par3, par6))
				{
					par1World.setBlock(par2, par3, par6, ModLBD.lightBridge, par5, 2);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							continue outer;
						}
					}
					for (Block b : burnsOnCollision)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							par1World.setBlock(par2, par3, par6, Blocks.fire);
							break outer;
						}
					}
					break;
				}
			}
			break;
		}
		TileEntity passover = par1World.getTileEntity(par2, par3, par4);
		if (passover instanceof TileEntityLightBridgeSourceActivated)
		{
			((TileEntityLightBridgeSourceActivated)passover).onFinished();
		}
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{ 
		super.onBlockAdded(par1World, par2, par3, par4);
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
		{
			if (par1World.isSideSolid(par2 - 1, par3, par4, EAST, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			}
			else if (par1World.isSideSolid(par2 + 1, par3, par4, WEST, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			}
			else if (par1World.isSideSolid(par2, par3, par4 - 1, SOUTH, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			}
			else if (par1World.isSideSolid(par2, par3, par4 + 1, NORTH, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
			}
			else
			{
				this.dropLightBridgeIfCantStay(par1World, par2, par3, par4);
			}
		}
		if (par1World.isRemote)
		{			
			if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
            	this.activate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
            }
		}
	}
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return par1World.isSideSolid(par2 - 1, par3, par4, EAST, true) ||
				par1World.isSideSolid(par2 + 1, par3, par4, WEST, true) ||
				par1World.isSideSolid(par2, par3, par4 - 1, SOUTH, true) ||
				par1World.isSideSolid(par2, par3, par4 + 1, NORTH, true);
	}
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int par10 = par9;
		if (par9 == 1 && par1World.isSideSolid(par2, par3, par4 + 1, NORTH, true))
		{
			par10 = 1;
		}
		if (par9 == 3 && par1World.isSideSolid(par2, par3, par4 - 1, SOUTH, true))
		{
			par10 = 3;
		}
		if (par9 == 5 && par1World.isSideSolid(par2 + 1, par3, par4, WEST, true))
		{
			par10 = 5;
		}
		if (par9 == 2 && par1World.isSideSolid(par2 - 1, par3, par4, EAST, true))
		{
			par10 = 2;
		}
		return par10;
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int par5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float par6 = 0.2F;
		if (par5 == 5)
		{
			setBlockBounds(0.0F, 1.0F, 0.0F, 0.2F, 0.8F, 1.0F);
		}
		if (par5 == 1)
		{
			setBlockBounds(0.0F, 1.0F, 1.0F - par6, 1.0F, 0.8F, 1.0F);
		}
		if (par5 == 2)
		{
			setBlockBounds(1.0F - par6, 1.0F, 0.0F, 1.0F, 0.8F, 1.0F);
		}
		if (par5 == 3)
		{
			setBlockBounds(0.0F, 1.0F, 0.0F, 1.0F, 0.8F, 0.2F);
		}
	}
	protected boolean dropLightBridgeIfCantStay(World par1World, int par2, int par3, int par4)
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
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		if (this.dropLightBridgeIfCantStay(par1World, par2, par3, par4))
		{
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
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
        if (!par1World.isRemote)
        {        	
            if ((!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)))
            {            	
            	this.activate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
            }
        }
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int par7 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (par7 == 0 && par1World.isSideSolid(par2, par3, par4 + 1, NORTH))
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
		if (par7 == 1 && par1World.isSideSolid(par2 - 1, par3, par4, EAST))
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}
		if (par7 == 2 && par1World.isSideSolid(par2, par3, par4 - 1, SOUTH))
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}
		if (par7 == 3 && par1World.isSideSolid(par2 + 1, par3, par4, WEST))
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}
		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityLightBridgeSource)par1World.getTileEntity(par2, par3, par4)).func_91429_a(par6ItemStack.getDisplayName());
		}
	}
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
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
		return new TileEntityLightBridgeSource();
	}
}
