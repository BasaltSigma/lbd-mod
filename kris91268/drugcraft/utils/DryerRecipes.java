package kris91268.drugcraft.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import kris91268.drugcraft.DrugCraft;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Arbiter
 *
 */
public class DryerRecipes
{
	public static final DryerRecipes INSTANCE = new DryerRecipes();
	private Map<ItemStack, ItemStack> dryingList = new HashMap<ItemStack, ItemStack>();
	
	private DryerRecipes()
	{
		dryingList.put(new ItemStack(DrugCraft.tobaccoLeaves), new ItemStack(DrugCraft.tobaccoLeavesDried, 1, 0));
	}
	public ItemStack getDryingResult(ItemStack input)
	{
		Iterator<Entry<ItemStack, ItemStack>> iterator = dryingList.entrySet().iterator();
		Entry<ItemStack, ItemStack> entry;
		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}
			entry = iterator.next();
		}
		while (!isEqualAndValid(input, entry.getKey()));
		return entry.getValue();
	}
	private boolean isEqualAndValid(ItemStack input, ItemStack entry)
	{
		return input.getItem() == entry.getItem() && (entry.getItemDamage() == 32767 || entry.getItemDamage() == input.getItemDamage());
	}
	public Map getRecipeList()
	{
		return this.dryingList;
	}
}