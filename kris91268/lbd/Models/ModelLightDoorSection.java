package kris91268.lbd.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Arbiter
 *
 */
public class ModelLightDoorSection extends ModelBase
{
	ModelRenderer Shape4;
	
	public ModelLightDoorSection()
	{
		textureWidth = 64;
		textureHeight = 36;
	    Shape4 = new ModelRenderer(this, 23, 4);
	    Shape4.addBox(0F, 0F, 0F, 1, 16, 16);
	    Shape4.setRotationPoint(-1F, 8F, -8F);
	    Shape4.setTextureSize(64, 36);
	    Shape4.mirror = true;
	    setRotation(Shape4, 0F, 0F, 0F);
	}
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7);
		Shape4.render(par7);
	}
	private void setRotation(ModelRenderer par1, float par2, float par3, float par4)
	{
		par1.rotateAngleX = par2;
		par1.rotateAngleY = par3;
		par1.rotateAngleZ = par4;
	}
	public void renderModel(float f5)
	{
		Shape4.render(f5);
	}
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, (Entity)null);
	}
}
