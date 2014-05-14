package kris91268.lbd.Blocks;

import kris91268.lbd.ModLBD;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public final class LinkMaker
{
	private LinkMaker() {}
	
	private static Block[] getAdjacentBlocks(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = new Block[2];
		switch (meta)
		{
		case 2: case 5:
			adjacentBlocks[0] = world.getBlock(x, y, z - 1);
			adjacentBlocks[1] = world.getBlock(x, y, z + 1);
			break;
		case 1: case 3:
			adjacentBlocks[0] = world.getBlock(x - 1, y, z);
			adjacentBlocks[1] = world.getBlock(x + 1, y, z);
			break;
		}
		return adjacentBlocks;
	}
	private static int[] getAdjacentMetadata(World world, int x, int y, int z, int meta)
	{
		int[] metas = new int[2];
		switch (meta)
		{
		case 2: case 5:
			metas[0] = world.getBlockMetadata(x, y, z - 1);
			metas[1] = world.getBlockMetadata(x, y, z + 1);
			break;
		case 1: case 3:
			metas[0] = world.getBlockMetadata(x - 1, y, z);
			metas[1] = world.getBlockMetadata(x + 1, y, z);
			break;
		}
		return metas;
	}
	public static void activateBridges(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightBridgeSource && metas[0] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightBridgeSource)adjacentBlocks[0]).activate(world, x, y, z - 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightBridgeSource)adjacentBlocks[0]).activate(world, x - 1, y, z, meta, true);
				break;
			}
		}
		if (adjacentBlocks[1] instanceof BlockLightBridgeSource && metas[1] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightBridgeSource)adjacentBlocks[1]).activate(world, x, y, z + 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightBridgeSource)adjacentBlocks[1]).activate(world, x + 1, y, z, meta, true);
				break;
			}
		}
	}
	public static void deactivateBridges(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightBridgeSourceActivated && metas[0] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightBridgeSourceActivated)adjacentBlocks[0]).deactivate(world, x, y, z - 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightBridgeSourceActivated)adjacentBlocks[0]).deactivate(world, x - 1, y, z, meta, true);
				break;
			}
		}
		if (adjacentBlocks[1] instanceof BlockLightBridgeSourceActivated && metas[1] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightBridgeSourceActivated)adjacentBlocks[1]).deactivate(world, x, y, z + 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightBridgeSourceActivated)adjacentBlocks[1]).deactivate(world, x + 1, y, z, meta, true);
				break;
			}
		}
	}
	public static void activateDoors(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightDoorSource && metas[0] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightDoorSource)adjacentBlocks[0]).activate(world, x, y, z - 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightDoorSource)adjacentBlocks[0]).activate(world, x - 1, y, z, meta, true);
				break;
			}
		}
		if (adjacentBlocks[1] instanceof BlockLightDoorSource && metas[1] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightDoorSource)adjacentBlocks[1]).activate(world, x, y, z + 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightDoorSource)adjacentBlocks[1]).activate(world, x + 1, y, z, meta, true);
				break;
			}
		}
	}
	public static void deactivateDoors(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightDoorSourceActivated && metas[0] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightDoorSourceActivated)adjacentBlocks[0]).deactivate(world, x, y, z - 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightDoorSourceActivated)adjacentBlocks[0]).deactivate(world, x - 1, y, z, meta, true);
				break;
			}
		}
		if (adjacentBlocks[1] instanceof BlockLightDoorSourceActivated && metas[1] == meta)
		{
			switch (meta)
			{
			case 2: case 5:
				((BlockLightDoorSourceActivated)adjacentBlocks[1]).deactivate(world, x, y, z + 1, meta, true);
				break;
			case 1: case 3:
				((BlockLightDoorSourceActivated)adjacentBlocks[1]).deactivate(world, x + 1, y, z, meta, true);
				break;
			}
		}
	}
	public static void activateBarriers(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = new Block[] {world.getBlock(x, y + 1, z), world.getBlock(x, y - 1, z)};
		int[] metas = new int[] {world.getBlockMetadata(x, y + 1, z), world.getBlockMetadata(x, y - 1, z)};
		if (adjacentBlocks[0] instanceof BlockLightBarrierSource && metas[0] == meta)
		{
			((BlockLightBarrierSource)adjacentBlocks[0]).activate(world, x, y + 1, z, meta, true);
		}
		if (adjacentBlocks[1] instanceof BlockLightBarrierSource && metas[1] == meta)
		{
			((BlockLightBarrierSource)adjacentBlocks[1]).activate(world, x, y - 1, z, meta, true);
		}
	}
	public static void deactivateBarriers(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = new Block[] {world.getBlock(x, y + 1, z), world.getBlock(x, y - 1, z)};
		int[] metas = new int[] {world.getBlockMetadata(x, y + 1, z), world.getBlockMetadata(x, y - 1, z)};
		if (adjacentBlocks[0] instanceof BlockLightBarrierSourceActivated && metas[0] == meta)
		{
			((BlockLightBarrierSourceActivated)adjacentBlocks[0]).deactivate(world, x, y + 1, z, meta, true);
		}
		if (adjacentBlocks[1] instanceof BlockLightBarrierSourceActivated && metas[1] == meta)
		{
			((BlockLightBarrierSourceActivated)adjacentBlocks[1]).deactivate(world, x, y - 1, z, meta, true);
		}
	}
	public static boolean isAdjacentBridgeActive(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightBridgeSourceActivated && metas[0] == meta)
		{
			return true;
		}
		else if (adjacentBlocks[1] instanceof BlockLightBridgeSourceActivated && metas[1] == meta)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isAdjacentDoorActive(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = getAdjacentBlocks(world, x, y, z, meta);
		int[] metas = getAdjacentMetadata(world, x, y, z, meta);
		if (adjacentBlocks[0] instanceof BlockLightDoorSourceActivated && metas[0] == meta)
		{
			return true;
		}
		else if (adjacentBlocks[1] instanceof BlockLightDoorSourceActivated && metas[1] == meta)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isAdjacentBarrierActive(World world, int x, int y, int z, int meta)
	{
		Block[] adjacentBlocks = new Block[] {world.getBlock(x, y + 1, z), world.getBlock(x, y - 1, z)};
		int[] metas = new int[] {world.getBlockMetadata(x, y + 1, z), world.getBlockMetadata(x, y - 1, z)};
		if (adjacentBlocks[0] instanceof BlockLightBarrierSourceActivated && metas[0] == meta)
		{
			return true;
		}
		else if (adjacentBlocks[1] instanceof BlockLightBarrierSourceActivated && metas[1] == meta)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}