package kris91268.lbd.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityGravityLift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * The packet to update the height of a gravity lift
 * @author Arbiter
 *
 */
public class PacketUpdateHeight extends AbstractPacket
{
	private int dimension, x, y, z;
	private float height;
	
	public PacketUpdateHeight() {}
	public PacketUpdateHeight(int dimension, int x, int y, int z, float height)
	{
		this.dimension = dimension;
		this.x = x;
		this.y = y;
		this.z = z;
		this.height = height;
	}
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(dimension);
		buffer.writeInt(x);
		buffer.writeShort(y);
		buffer.writeInt(z);
		buffer.writeFloat(height);
	}
	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		dimension = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readShort();
		z = buffer.readInt();
		height = buffer.readFloat();
	}
	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		
	}
	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		World world = player.worldObj;
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityGravityLift)
		{
			TileEntityGravityLift proper = (TileEntityGravityLift)tileEntity;
			proper.setHeight(height);
			NBTTagCompound data = new NBTTagCompound();
			tileEntity.writeToNBT(data);
			ModLBD.packets.sendToDimension(new PacketUpdateTE(x, y, z, data), dimension);
		}
	}	
}