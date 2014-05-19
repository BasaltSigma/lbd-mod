package kris91268.lbd.Tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kris91268.lbd.Models.ModelLightBarrierSource;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author Arbiter
 *
 */
@SideOnly(Side.CLIENT)
public class TileEntityLightBarrierSourceRenderer extends TileEntitySpecialRenderer
{
	private ModelLightBarrierSource model = new ModelLightBarrierSource();
	private static final ResourceLocation texture = new ResourceLocation("lbd:textures/entity/lbd/lbasource.png");
	
	public void renderAModelAt(TileEntityLightBarrierSource par1, double par2, double par3, double par4, float par5)
	{
		int par6 = 0;
		if (par1.getWorldObj() != null)
		{
			par6 = par1.getBlockMetadata();
		}
		this.bindTexture(LightTextures.barrierTextures[0]);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glRotatef((float)(par6 == 5 ? 0 : par6 * 90), 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderAModelAt((TileEntityLightBarrierSource)par1TileEntity, par2, par3, par4, par5);
	}
}