package kris91268.lbd.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLBSource extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
  
  public ModelLBSource()
  {
    textureWidth = 64;
    textureHeight = 36;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 1, 3, 16);
      Shape1.setRotationPoint(-8F, 8F, -8F);
      Shape1.setTextureSize(64, 36);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 1, 1, 16);
      Shape2.setRotationPoint(-7F, 8F, -8F);
      Shape2.setTextureSize(64, 36);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 1, 1, 16);
      Shape3.setRotationPoint(-7F, 10F, -8F);
      Shape3.setTextureSize(64, 36);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
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
	  Shape1.render(f5);
	    Shape2.render(f5);
	    Shape3.render(f5);
  }

}
