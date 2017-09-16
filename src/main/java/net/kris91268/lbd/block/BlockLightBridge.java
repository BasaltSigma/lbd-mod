package net.kris91268.lbd.block;

import net.kris91268.lbd.MainLBD;
import net.kris91268.lbd.tile.TileEntityLightBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLightBridge();
    }

    public EnumFacing getFacing(IBlockAccess world, BlockPos pos) {
        TileEntityLightBridge entity = (TileEntityLightBridge)world.getTileEntity(pos);
        return entity != null ? entity.getFacing() : EnumFacing.NORTH;
    }

    public void setFacing(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        TileEntityLightBridge entity = (TileEntityLightBridge)world.getTileEntity(pos);
        if (entity != null) {
            entity.setFacing(facing);
        }
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = getFacing(world, pos);
        setFacing(world, pos, facing.rotateAround(axis.getAxis()));
        world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
        final int playerRotation = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (playerRotation == 0 && world.isSideSolid(pos.south(), EnumFacing.NORTH)) {
            setFacing(world, pos, EnumFacing.NORTH);
        } else if (playerRotation == 1 && world.isSideSolid(pos.west(), EnumFacing.EAST)) {
            setFacing(world, pos, EnumFacing.EAST);
        } else if (playerRotation == 2 && world.isSideSolid(pos.north(), EnumFacing.SOUTH)) {
            setFacing(world, pos, EnumFacing.SOUTH);
        } else if (playerRotation == 3 && world.isSideSolid(pos.east(), EnumFacing.WEST)) {
            setFacing(world, pos, EnumFacing.WEST);
        }
    }
}