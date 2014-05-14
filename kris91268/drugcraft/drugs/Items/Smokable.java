package kris91268.drugcraft.drugs.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Arbiter
 *
 */
public interface Smokable
{
	/** Variables for defining how each smokable object lasts for **/
	int CIGARETTE = 15;
	int CIGAR = 30;
	int PIPE = 25;
	int BONG = 20;
	int JOINT = 18;
	/**
	 * Called in onPlayerStoppedUsing, define your special smoking powers here.
	 * @param par1ItemStack
	 * @param par2EntityPlayer
	 */
	void onSmoked(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, float buffer);
	
	/**
	 * Should be called in the constructor of each item that implements this interface.
	 */
	void setSmokingAmount();
}