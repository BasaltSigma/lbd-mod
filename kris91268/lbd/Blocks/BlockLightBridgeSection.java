package kris91268.lbd.Blocks;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightBridgeSection extends BlockContainer
{
	public BlockLightBridgeSection(Material material)
	{
		super(material);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);	
		setBlockName("lightBridgeSection");
		setBlockTextureName("lbd:lightSection");
		setBlockBounds(0.0F, 1.0F, 0.0F, 1.0F, 0.8F, 1.0F);
		setLightLevel(0.4F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return true;
	}
	public boolean isOpaqueCube()
	{
		return false;
	}
	public int getRenderBlockPass()
	{
		return 0;
	}
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	public int getRenderType()
	{
		return -1;
	}
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityLightBridgeSection();
	}
}