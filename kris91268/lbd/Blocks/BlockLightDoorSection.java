package kris91268.lbd.Blocks;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightDoorSection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightDoorSection extends BlockContainer
{
	public BlockLightDoorSection(Material material)
	{
		super(material);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightDoorSection");
		setBlockTextureName("lbd:lightSection");
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
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (ModLBD.doesDoorHurt)
		{
			par5Entity.attackEntityFrom(DamageSource.cactus, 4);
		}
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int par5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float par6 = 0.625F;
		float par7 = 0.575F;
		if (par5 == 0)
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
	@Override
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityLightDoorSection();
	}
}
