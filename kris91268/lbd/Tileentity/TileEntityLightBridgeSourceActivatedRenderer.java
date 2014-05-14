package kris91268.lbd.Tileentity;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Models.ModelLBSourceActivated;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
@SideOnly(Side.CLIENT)
public class TileEntityLightBridgeSourceActivatedRenderer extends TileEntitySpecialRenderer
{
    private ModelLBSourceActivated model = new ModelLBSourceActivated();
    private byte index;
	
	public boolean shouldRender3DInInventory()
	{
		return true;
	}
	public void renderAModelAt(TileEntityLightBridgeSourceActivated par1, double par2, double par3, double par4, float par5)
	{
		int par6 = 0;
		if (par1.getWorldObj() != null)
		{
			par6 = par1.getBlockMetadata();
		}
		short par7 = (short)(par6 * 90);
		this.bindTexture(LightTextures.bridgeTextures[index / 5]);
		if (ModLBD.shouldAnimate)
		{
			index++;
			if (index / 5 == LightTextures.bridgeTextures.length)
			{
				index = 0;
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glRotatef((float)par7, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);	
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderAModelAt((TileEntityLightBridgeSourceActivated)par1TileEntity, par2, par3, par4, par5);
	}
}
