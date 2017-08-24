package net.kris91268.lbd.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockLightBridge extends Block {

    public BlockLightBridge() {
        super(Material.IRON);
        setHardness(2.0f);
        setCreativeTab(CreativeTabs.REDSTONE);
        setUnlocalizedName("light_bridge");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return super.createTileEntity(world, state);
    }
}