package kris91268.drugcraft.drugs.Items;

import kris91268.drugcraft.DrugCraft;
import kris91268.drugcraft.utils.SmokingEffects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class ItemCigar extends Item implements Smokable
{
	private PotionEffect[] effects;
	private boolean isLit = false;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public ItemCigar(SmokingEffects effects, String textureName)
	{
		super();
		this.effects = effects.getEffects();
		setUnlocalizedName(textureName);
		setTextureName("drugcraft:" + textureName);
		setSmokingAmount();
		setCreativeTab(DrugCraft.tabDrugCraft);
		maxStackSize = 1;
	}
	public void setSmokingAmount()
	{
		setMaxDamage(CIGAR);
	}
	public void onSmoked(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, float buffer)
	{
		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			par1ItemStack.damageItem((int)(buffer / 2), par2EntityPlayer);
		}
		par2EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (int)(5 * 20 * buffer * 2), 0));
		for (PotionEffect e : effects)
		{
			par2EntityPlayer.addPotionEffect(new PotionEffect(e.getPotionID(), (int)(e.getDuration() * buffer * 2), e.getAmplifier()));
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1)
	{
		this.icons = new IIcon[2];
		this.icons[0] = par1.registerIcon(this.getIconString());
		this.icons[1] = par1.registerIcon(this.getIconString() + "_lit");
		if (isLit)
		{
			this.itemIcon = icons[1];
		}
		else
		{
			this.itemIcon = icons[0];
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(ItemStack par1ItemStack, int par2)
	{
		return par1ItemStack.getItemDamage() == 0 ? this.icons[0] : this.icons[1];
	}
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		InventoryPlayer inventory = par3EntityPlayer.inventory;
		if (!isLit && par1ItemStack.getItemDamage() > 0)
		{
			isLit = true;
			return par1ItemStack;
		}
		return par1ItemStack.getItemDamage() >= par1ItemStack.getMaxDamage() ? new ItemStack(DrugCraft.cigaretteButt) : par1ItemStack;
	}
}