package kris91268.drugcraft.drugs.Gui;

import org.lwjgl.opengl.GL11;

import kris91268.drugcraft.drugs.Tileentity.TileEntityDryingTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
@SideOnly(Side.CLIENT)
public class GuiDryingTable extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("drugcraft:textures/gui/dryer.png");
	private TileEntityDryingTable tile;
	
	public GuiDryingTable(InventoryPlayer par1, TileEntityDryingTable par2)
	{
		super(new ContainerDryingTable(par1, par2));
		this.tile = par2;
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	}
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1 = this.tile.getDryProgress(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
}