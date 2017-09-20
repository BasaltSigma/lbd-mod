package net.kris91268.lbd.block;

import net.kris91268.lbd.MainLBD;
import net.kris91268.lbd.tile.TileEntityLightBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockLightBridge extends Block {

    public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing");
    public static final IProperty<Boolean> ACTIVE = PropertyBool.create("active");

    public BlockLightBridge() {
        super(Material.IRON, MapColor.STONE);
        setHardness(2.0f);
        setCreativeTab(CreativeTabs.REDSTONE);
        setUnlocalizedName(MainLBD.MODID + ":light_bridge");
        setRegistryName(MainLBD.MODID + ":light_bridge");
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false));
    }

    @Override
    @Nullable
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ACTIVE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal() + (state.getValue(ACTIVE) ? 6 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        boolean active = meta >= 6;
        return getBlockState().getBaseState().withProperty(FACING, EnumFacing.values()[
                active ? meta - 6 : meta]).withProperty(ACTIVE, active);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Nullable
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
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

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer in, EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(FACING, getFacing(world, pos)).withProperty(ACTIVE, false);
    }
}