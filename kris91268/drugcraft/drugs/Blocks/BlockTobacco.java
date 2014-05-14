package kris91268.drugcraft.drugs.Blocks;

import java.util.Random;

import kris91268.drugcraft.DrugCraft;
import net.minecraft.block.BlockPotato;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockTobacco extends BlockPotato
{
	private IIcon[] cropIcons;
	
	public BlockTobacco()
	{
		super();
		setBlockName("tobacco");
		setBlockTextureName("drugcraft:tobacco");
	}
	@Override
	public Item getItemDropped(int meta, Random par2Random, int par3)
	{
		return meta == 3 ? DrugCraft.tobaccoLeaves : DrugCraft.tobaccoSeeds;
	}
	@Override
	public int quantityDropped(int par1, int par2, Random par3)
	{
		return par1 == 3 ? 1 + par3.nextInt(2) : 1 + par3.nextInt(4);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1)
	{
		this.cropIcons = new IIcon[4];
		for (int i = 0; i < cropIcons.length; ++i)
		{
			this.cropIcons[i] = par1.registerIcon(this.getTextureName() + "_stage_" + i);
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            if (par2 == 6)
            {
                par2 = 5;
            }
            return this.cropIcons[par2 >> 1];
        }
        else
        {
            return this.cropIcons[3];
        }
    }
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		return;
	}
}