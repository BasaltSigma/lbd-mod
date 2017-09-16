package net.kris91268.lbd.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * Extension of TileEntity but with rotation and overriden methods to apply rotation of this block/entity seamlessley.
 */
public class RotatableTileEntity extends TileEntity {

    private EnumFacing facing = EnumFacing.NORTH;

    public EnumFacing getFacing() {
        return facing;
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        facing = EnumFacing.getFront(tag.getInteger("facing"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("facing", facing.getIndex());
        return tag;
    }

    @Override
    public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
        IBlockState state = getWorld().getBlockState(getPos());
        this.getWorld().notifyBlockUpdate(this.getPos(), state, state, 3);
    }
}
