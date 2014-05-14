package kris91268.lbd;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import kris91268.lbd.Packet.PacketUpdateHeight;
import kris91268.lbd.Tileentity.TileEntityGravityLift;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author Arbiter
 *
 */
public class GuiGravityLift extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("lbd:textures/gui/gravLift.png");
	public TileEntityGravityLift tileEntity;
	private GuiButton doneButton, decByPointZeroOne, decByPointOne, incByPointOne, incByOnePointZero;
	private GuiTextField number;
	private int liftHeight;
	
	public GuiGravityLift(TileEntityGravityLift tileEntity)
	{
		super(new ContainerGravityLift());
		this.tileEntity = tileEntity;
	}
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.drawString(fontRendererObj, "Gravity Lift", this.width / 2 - fontRendererObj.getStringWidth("Gravity Lift") / 2, 
				this.height / 4 - 7, 4210752);
	}
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		this.number.drawTextBox();
	}
	@Override
	public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);    
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	@Override
	public void initGui()
	{
		this.buttonList.clear();
		this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 25, this.height / 4 + 38, 50, 20, "Done"));
		this.buttonList.add(this.decByPointZeroOne = new GuiButton(1, this.width / 2 - 75, this.height / 4 + 3, 40, 20, "-0.1"));
		this.buttonList.add(this.decByPointOne = new GuiButton(2, this.width / 2 - 75, this.height / 4 + 28, 40, 20, "-1.0"));
		this.buttonList.add(this.incByPointOne = new GuiButton(3, this.width / 2 + 35, this.height / 4 + 3, 40, 20, "+0.1"));
		this.buttonList.add(this.incByOnePointZero = new GuiButton(4, this.width / 2 + 35, this.height / 4 + 28, 40, 20, "+1.0"));
		this.number = new GuiTextField(this.fontRendererObj, this.width / 2 - 20, this.height / 4 + 8, 40, 20);
		this.number.setFocused(true);
		this.number.setVisible(true);
		this.number.setText(Float.toString(tileEntity.height));
		this.liftHeight = (int)(tileEntity.height * 10);
	}
	@Override
	public void updateScreen()
	{
		this.number.updateCursorCounter();
	}
	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
		ModLBD.packets.sendToServer(new PacketUpdateHeight(tileEntity.getWorldObj().provider.dimensionId, tileEntity.xCoord, 
				tileEntity.yCoord, tileEntity.zCoord, intToFloat(liftHeight)));
	}
	@Override
	protected void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 0:
			this.mc.displayGuiScreen((GuiScreen)null);
			break;
		case 1:
			liftHeight -= 1;
			if (liftHeight < 0)
			{
				liftHeight = 0;
			}
			number.setText(Float.toString(intToFloat(liftHeight)));
			break;
		case 2:
			liftHeight -= 10;
			liftHeight -= 1;
			if (liftHeight < 0)
			{
				liftHeight = 0;
			}
			number.setText(Float.toString(intToFloat(liftHeight)));
			break;
		case 3:
			liftHeight += 1;
			if (liftHeight > 50)
			{
				liftHeight = 50;
			}
			number.setText(Float.toString(intToFloat(liftHeight)));
			break;
		case 4:
			liftHeight += 10;
			if (liftHeight > 50)
			{
				liftHeight = 50;
			}
			number.setText(Float.toString(intToFloat(liftHeight)));
			break;
		}
	}
	private static float intToFloat(int num)
	{
		String integer = Integer.toString(num);
		if (integer.length() > 2)
		{
			throw new IllegalArgumentException("Shouldn't happen");
		}
		else if (integer.length() == 1)
		{
			integer = "0." + integer;
		}
		else
		{
			integer = integer.charAt(0) + "." + integer.charAt(1);
		}
		return Float.parseFloat(integer);
	}
}
class ContainerGravityLift extends Container
{
	@Override
	public boolean canInteractWith(EntityPlayer var1) 
	{
		return true;
	}	
}