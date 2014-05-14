package kris91268.lbd.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Arbiter
 *
 */
public class ModelLightBridgeSection extends ModelBase
{
	ModelRenderer Shape4;
	
	public ModelLightBridgeSection()
	{
		textureWidth = 64;
		textureHeight = 36;
	    Shape4 = new ModelRenderer(this, 0, 19);
	    Shape4.addBox(0F, 0F, 0F, 16, 1, 16);
	    Shape4.setRotationPoint(-8F, 9F, -8F);
	    Shape4.setTextureSize(64, 36);
	    Shape4.mirror = true;
	    setRotation(Shape4, 0F, 0F, 0F);
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5);
	    Shape4.render(f5);
	  }
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity)null);
	  }
	  public void renderModel(float f5)
	  {
		    Shape4.render(f5);
	  }
}