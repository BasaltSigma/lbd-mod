package kris91268.lbd;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import kris91268.lbd.Tileentity.*;

/**
 * 
 * @author Arbiter
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBridgeSection.class, new TileEntityLightBridgeSectionRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBridgeSource.class, new TileEntityLightBridgeSourceRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBridgeSourceActivated.class, new TileEntityLightBridgeSourceActivatedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightDoorSection.class, new TileEntityLightDoorSectionRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightDoorSource.class, new TileEntityLightDoorSourceRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightDoorSourceActivated.class, new TileEntityLightDoorSourceActivatedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBarrierSection.class, new TileEntityLightBarrierSectionRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBarrierSource.class, new TileEntityLightBarrierSourceRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBarrierSourceActivated.class, new TileEntityLightBarrierSourceActivatedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGravityLift.class,  new TileEntityGravityLiftRenderer());
	}
}