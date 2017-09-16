package net.kris91268.lbd.block;

import net.kris91268.lbd.MainLBD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockLightBridge extends Block {

    public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing");

    public BlockLightBridge() {
        super(Material.IRON, MapColor.STONE);
        setHardness(2.0f);
        setCreativeTab(CreativeTabs.REDSTONE);
        setUnlocalizedName(MainLBD.MODID + ":light_bridge_source");
        setRegistryName(MainLBD.MODID + ":light_bridge_source");
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);
    }
}