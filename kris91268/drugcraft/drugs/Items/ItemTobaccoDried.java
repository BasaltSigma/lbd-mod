package kris91268.drugcraft.drugs.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kris91268.drugcraft.DrugCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

/**
 * 
 * @author Arbiter
 *
 */
public class ItemTobaccoDried extends Item
{
	private static final String[] NAMES = {"plain", "sugar", "pumpkin", "melon", "chocolate", "apple", "carrot", "wart", "goldApple"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public ItemTobaccoDried()
	{
		super();
		setUnlocalizedName("tobaccoLeavesDried");
		setTextureName("drugcraft:tobaccoLeavesDried");
		setHasSubtypes(true);
		setCreativeTab(DrugCraft.tabDrugCraft);
		setMaxDamage(0);
	}
	@SideOnly(Side.CLIENT) @Override
	public IIcon getIconFromDamage(int par1)
	{
		int i = MathHelper.clamp_int(par1, 0, 8);
		return this.icons[i];
	}
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 8);
		return super.getUnlocalizedName() + "." + NAMES[i];
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1Item, CreativeTabs par2, List par3List)
	{
		for (int i = 0; i < 9; i++)
		{
			par3List.add(new ItemStack(par1Item, 1, i));
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1)
	{
		this.icons = new IIcon[NAMES.length];
		for (int i = 0; i < this.icons.length; i++)
		{
			this.icons[i] = par1.registerIcon(getIconString() + "_" + NAMES[i]);
		}
	}
}