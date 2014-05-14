package kris91268.lbd.Blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Defines the layout for a block that acts like a light bridge
 * @author Arbiter
 *
 */
public interface ILightSource
{
	/** A list of blocks that the light beams can pass through */
	static final Block[] allowedBlocks = {Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane};
	/** A list of blocks that catches fire when the light beams collide with it */
	static final Block[] burnsOnCollision = {Blocks.tallgrass, Blocks.leaves, Blocks.vine, Blocks.deadbush, Blocks.sapling};
	/**
	 * Activates the light device. Can be left empty
	 * @param par1World World object
	 * @param par2 x
	 * @param par3 y
	 * @param par4 z
	 * @param par5 direction
	 * @param continued Is continued (unused)
	 */
	abstract void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued);
	
	/**
	 * Deactivates the light device. Can be left empty
	 * @param par1World World object
	 * @param par2 x
	 * @param par3 y
	 * @param par4 z
	 * @param par5 direction
	 * @param continued Is continued (unused)
	 */
	abstract void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued);
}