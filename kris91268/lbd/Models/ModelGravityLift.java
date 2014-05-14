package kris91268.lbd.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Arbiter
 *
 */
public class ModelGravityLift extends ModelBase
{
    ModelRenderer Hub;
    ModelRenderer fork1;
    ModelRenderer fork2;
    ModelRenderer fork3;
    ModelRenderer fork4;
  
  public ModelGravityLift()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Hub = new ModelRenderer(this, 0, 23);
      Hub.addBox(0F, 0F, 0F, 6, 3, 6);
      Hub.setRotationPoint(-3F, 21F, -3F);
      Hub.setTextureSize(64, 32);
      Hub.mirror = true;
      setRotation(Hub, 0F, 0F, 0F);
      fork1 = new ModelRenderer(this, 0, 0);
      fork1.addBox(0F, 0F, 0F, 4, 2, 5);
      fork1.setRotationPoint(-2F, 22F, -8F);
      fork1.setTextureSize(64, 32);
      fork1.mirror = true;
      setRotation(fork1, 0F, 0F, 0F);
      fork2 = new ModelRenderer(this, 18, 0);
      fork2.addBox(0F, 0F, 0F, 4, 2, 5);
      fork2.setRotationPoint(-8F, 22F, 2F);
      fork2.setTextureSize(64, 32);
      fork2.mirror = true;
      setRotation(fork2, 0F, 1.570796F, 0F);
      fork3 = new ModelRenderer(this, 18, 0);
      fork3.addBox(0F, 0F, 0F, 4, 2, 5);
      fork3.setRotationPoint(3F, 22F, 2F);
      fork3.setTextureSize(64, 32);
      fork3.mirror = true;
      setRotation(fork3, 0F, 1.570796F, 0F);
      fork4 = new ModelRenderer(this, 0, 0);
      fork4.addBox(0F, 0F, 0F, 4, 2, 5);
      fork4.setRotationPoint(-2F, 22F, 3F);
      fork4.setTextureSize(64, 32);
      fork4.mirror = true;
      setRotation(fork4, 0F, 0F, 0F);
  }  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Hub.render(f5);
    fork1.render(f5);
    fork2.render(f5);
    fork3.render(f5);
    fork4.render(f5);
  }  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  public void renderModel(float f5)
  {
	  Hub.render(f5);
	  fork1.render(f5);
	  fork2.render(f5);
	  fork3.render(f5);
	  fork4.render(f5);
  }
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity)null);
  }
}
