package kris91268.lbd.Tileentity;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kris91268.lbd.Models.ModelLightDoorSource;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Arbiter
 *
 */
@SideOnly(Side.CLIENT)
public class TileEntityLightDoorSourceRenderer extends TileEntitySpecialRenderer
{
	private ModelLightDoorSource model = new ModelLightDoorSource();
	
	public void renderAModelAt(TileEntityLightDoorSource par1, double par2, double par3, double par4, float par5)
	{
		int par6 = 0;
		if (par1.getWorldObj() != null)
		{
			par6 = par1.getBlockMetadata();
		}
		short par7 = 0;
		if (par6 == 0)
		{
			par7 = 0;
		}
		if (par6 == 1)
		{
			par7 = 90;
		}
		if (par6 == 2)
		{
			par7 = 180;
		}
		if (par6 == 3)
		{
			par7 = 270;
		}
		this.bindTexture(LightTextures.doorTextures[0]);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glRotatef((float)par7, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderAModelAt((TileEntityLightDoorSource)par1TileEntity, par2, par3, par4, par5);
	}
}
