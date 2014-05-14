package kris91268.lbd.Blocks;

import java.util.Random;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityGravityLift;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockGravityLift extends BlockContainer
{
	public BlockGravityLift(Material material)
	{
		super(material);
		setResistance(20.0F);
		setHardness(10.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("gravityLift");
		setBlockTextureName("lbd:gravityLift");
		setCreativeTab(CreativeTabs.tabRedstone);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3F, 1.0F);
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		TileEntityGravityLift tileEntity = (TileEntityGravityLift)world.getTileEntity(x, y, z);
		if (entity instanceof EntityPlayer)
		{
			entity.setVelocity(0, tileEntity.height / 2, 0);
		}
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hx, float hy, float hz)
	{
		if (!world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityGravityLift tileEntity = (TileEntityGravityLift)world.getTileEntity(x, y, z);
			if (tileEntity != null)
			{
				player.openGui(ModLBD.instance, 0, world, x, y, z);
			}
			return true;
		}
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public int getRenderType()
	{
		return -1;
	}
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		world.spawnParticle("portal", x + 0.5d, y, z + 0.5d, 0.0d, 0.025d, 0.0d);
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityGravityLift();
	}
}