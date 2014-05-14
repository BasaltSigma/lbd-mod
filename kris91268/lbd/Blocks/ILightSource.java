package kris91268.lbd.Blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public interface ILightSource
{
	static final Block[] allowedBlocks = {Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane};
	static final Block[] burnsOnCollision = {Blocks.tallgrass, Blocks.leaves, Blocks.vine, Blocks.deadbush, Blocks.sapling};
	/**
	 * Activates the light device
	 * @param par1World World object
	 * @param par2 x
	 * @param par3 y
	 * @param par4 z
	 * @param par5 direction
	 * @param continued Is continued
	 */
	abstract void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued);
	
	/**
	 * Deactivates the light device
	 * @param par1World World object
	 * @param par2 x
	 * @param par3 y
	 * @param par4 z
	 * @param par5 direction
	 * @param continued Is continued
	 */
	abstract void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued);
}