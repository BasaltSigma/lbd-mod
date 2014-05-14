package kris91268.drugcraft.utils;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * 
 * @author Arbiter
 *
 */
public enum SmokingEffects
{
	PLAIN(),
	SUGAR(new PotionEffect(Potion.moveSpeed.id, convertToTicks(6), 0)),
	PUMPKIN(new PotionEffect(Potion.damageBoost.id, convertToTicks(5), 0)),
	MELON(new PotionEffect(Potion.jump.id, convertToTicks(6), 0)),
	CHOCOLATE(new PotionEffect(Potion.moveSpeed.id, convertToTicks(2), 0), new PotionEffect(Potion.regeneration.id, convertToTicks(2), 0)),
	APPLE(new PotionEffect(Potion.regeneration.id, convertToTicks(4), 0)),
	CARROT(new PotionEffect(Potion.nightVision.id, convertToTicks(4), 0)),
	NETHER_WART(new PotionEffect(Potion.fireResistance.id, convertToTicks(6), 0)),
	GOLD_APPLE(new PotionEffect(Potion.regeneration.id, convertToTicks(5), 0), new PotionEffect(Potion.heal.id, convertToTicks(2), 0));
	
	private PotionEffect[] effects;
	
	private SmokingEffects(PotionEffect... effects)
	{
		this.effects = effects;
	}
	public PotionEffect[] getEffects()
	{
		return this.effects;
	}
	public static int convertToTicks(int amount)
	{
		return amount * 20;
	}
}