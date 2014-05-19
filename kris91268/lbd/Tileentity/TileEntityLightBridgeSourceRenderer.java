package kris91268.lbd.Tileentity;

import kris91268.lbd.Models.ModelLBSource;
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
public class TileEntityLightBridgeSourceRenderer extends TileEntitySpecialRenderer
{
	private ModelLBSource model = new ModelLBSource();
	
	public void renderAModelAt(TileEntityLightBridgeSource par1TileEntityLightBridgeSource, double par2, double par3, double par4, float par5)
	{
		int par6 = 0;
		if (par1TileEntityLightBridgeSource.getWorldObj() != null)
		{
			par6 = par1TileEntityLightBridgeSource.getBlockMetadata();
		}
		this.bindTexture(LightTextures.bridgeTextures[0]);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glRotatef((float)(par6 == 5 ? 0 : par6 * 90), 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderAModelAt((TileEntityLightBridgeSource)par1TileEntity, par2, par3, par4, par5);
	}
}
