package kris91268.drugcraft.drugs.Items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import kris91268.drugcraft.DrugCraft;
import kris91268.drugcraft.utils.SmokingEffects;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemCigarette extends Item implements Smokable
{
	private PotionEffect[] effects;
	private boolean isLit = false;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public ItemCigarette(SmokingEffects effects, String textureName)
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
		setMaxDamage(CIGARETTE);
	}
	public void onSmoked(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, float buffer)
	{
		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			par1ItemStack.damageItem((int)(buffer / 2), par2EntityPlayer);
		}
		par2EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (int)(5 * 20 * buffer), 0));
		for (PotionEffect e : effects)
		{
			par2EntityPlayer.addPotionEffect(new PotionEffect(e.getPotionID(), (int)(e.getDuration() * buffer), e.getAmplifier()));
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
		if (!isLit && par1ItemStack.getItemDamage() > 0 && inventory.hasItem(Items.flint_and_steel))
		{
			isLit = true;
			List<String> list = new ArrayList<String>();
			list.add("Lit");
			ItemStack item = inventory.getItemStack();
			this.addInformation(par1ItemStack, par3EntityPlayer, list, true);
			return par1ItemStack;
		}
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		System.out.println(par3EntityPlayer.rotationPitch);
		return par1ItemStack.getItemDamage() >= par1ItemStack.getMaxDamage() ? new ItemStack(DrugCraft.cigaretteButt) : par1ItemStack;
	}
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		float duration = (this.getMaxItemUseDuration(par1ItemStack) - par4) / 10;
		if (!par3EntityPlayer.capabilities.isCreativeMode)
		{
			if (duration > 5.0f)
			{
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, (int)((duration - 5.0f) * 2) * 20, 0));
			}
		}
		onSmoked(par1ItemStack, par3EntityPlayer, duration);
		double py = par3EntityPlayer.posY - Math.sin(Math.toRadians(par3EntityPlayer.rotationPitch)) * 0.3;
		double px = par3EntityPlayer.posX - Math.sin(Math.toRadians(par3EntityPlayer.rotationYaw)) * 0.3;
		double pz = par3EntityPlayer.posZ + Math.cos(Math.toRadians(par3EntityPlayer.rotationYaw)) * 0.3;
		for (int i = 0; i < 8; i++)
		{
			par2World.spawnParticle("smoke", px, py, pz, 0.0, 0.05, 0.0);
		}
	}
}