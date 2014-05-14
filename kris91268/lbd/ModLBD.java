package kris91268.lbd;

import kris91268.lbd.Blocks.*;
import kris91268.lbd.Tileentity.*;
import kris91268.lbd.Packet.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * 
 * @author Arbiter
 *
 */
@Mod(modid=ModLBD.name, name="kris91268's Light Bridges and Doors", version="0.2.0", useMetadata=true)
public class ModLBD
{
	public static final String name = "LightBridgesAndDoors";
	public static final PacketPipeline packets = new PacketPipeline();
	public static int bridgeLength;
	public static int doorHeight;
	public static int railLength;
	public static int barrierLength;
	public static boolean doesDoorHurt;
	public static boolean enableLinkedBridges;
	public static boolean shouldAnimate;
	public static final Block lightBridgeSource = new BlockLightBridgeSource(Material.iron);
	public static final Block lightBridgeSourceActive = new BlockLightBridgeSourceActivated(Material.glass);
	public static final Block lightBridge = new BlockLightBridgeSection(Material.glass);
	public static final Block lightDoorSource = new BlockLightDoorSource(Material.iron);
	public static final Block lightDoorSourceActive = new BlockLightDoorSourceActivated(Material.glass);
	public static final Block lightDoorSection = new BlockLightDoorSection(Material.glass);
	public static final Block lightRailSection = new BlockLightRailSection();
	public static final Block lightRailSource = new BlockLightRailSource();
	public static final Block lightRailSourceActivated = new BlockLightRailSourceActivated();
	public static final Block lightBarrierSource = new BlockLightBarrierSource(Material.iron);
	public static final Block lightBarrierSourceActivated = new BlockLightBarrierSourceActivated(Material.glass);
	public static final Block lightBarrierSection = new BlockLightBarrierSection(Material.glass);
	public static final Block gravLift = new BlockGravityLift(Material.iron);
	@SidedProxy(clientSide = "kris91268.lbd.ClientProxy", serverSide = "kris91268.lbd.CommonProxy")
	public static CommonProxy proxy;
	@Instance("LightBridgesAndDoors")
	public static ModLBD instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{ 
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		bridgeLength = config.get(Configuration.CATEGORY_GENERAL, "Bridge Length (max 100)", 50).getInt();
		doorHeight = config.get(Configuration.CATEGORY_GENERAL, "Door Height (max 100)", 50).getInt();
		railLength = config.get(Configuration.CATEGORY_GENERAL, "Rail Length (max 50)", 25).getInt();
		barrierLength = config.get(Configuration.CATEGORY_GENERAL, "Barrier Length (max 100)", 50).getInt();
		doesDoorHurt = config.get(Configuration.CATEGORY_GENERAL, "Does the light doors hurt you on collision?", false).getBoolean(false);
		shouldAnimate = config.get("Advanced", "Should the light blocks be animated?", true).getBoolean(true);
		enableLinkedBridges = config.get("Advanced", "Should the light emitters have linked activation? Disable if it does not work.", true).getBoolean(true);
		config.save();
		if ((bridgeLength) > 100 || (doorHeight > 100) || (barrierLength > 100))
		{
			throw new IllegalArgumentException("You cannot have the bridge, barrier, or door length/height past 100");
		}
		if (railLength > 50)
		{
			throw new IllegalArgumentException("You cannot have the rail length greater than 50 (going in one way)");
		}
		GameRegistry.registerBlock(lightBridgeSource, "lightBridgeSource");
		GameRegistry.registerBlock(lightBridgeSourceActive, "lightBridgeSourceActive");
		GameRegistry.registerBlock(lightBridge, "lightBridgeSection");
		GameRegistry.registerBlock(lightDoorSource, "lightDoorSource");
		GameRegistry.registerBlock(lightDoorSourceActive, "lightDoorSourceActive");
		GameRegistry.registerBlock(lightDoorSection, "lightDoorSection");
		GameRegistry.registerBlock(lightRailSection, "lightRailSection");
		GameRegistry.registerBlock(lightRailSource, "lightRailSource");
		GameRegistry.registerBlock(lightRailSourceActivated, "lightRailSourceActive");
		GameRegistry.registerBlock(lightBarrierSection, "lightBarrierSection");
		GameRegistry.registerBlock(lightBarrierSource, "lightBarrierSource");
		GameRegistry.registerBlock(lightBarrierSourceActivated, "lightBarrierSourceActive");
		GameRegistry.registerBlock(gravLift, "gravityLift");
		GameRegistry.registerTileEntity(TileEntityLightBridgeSection.class, "TileEntityLightBridge");
		GameRegistry.registerTileEntity(TileEntityLightBridgeSource.class, "TileEntityLightBridgeSource");
		GameRegistry.registerTileEntity(TileEntityLightBridgeSourceActivated.class, "TileEntityLightBridgeSourceActivated");
		GameRegistry.registerTileEntity(TileEntityLightDoorSection.class, "TileEntityLightDoorSection");
		GameRegistry.registerTileEntity(TileEntityLightDoorSource.class, "TileEntityLightDoorSource");
		GameRegistry.registerTileEntity(TileEntityLightDoorSourceActivated.class, "TileEntityLightDoorSourceActivated");
		GameRegistry.registerTileEntity(TileEntityLightBarrierSection.class, "TileEntityLightBarrierSection");
		GameRegistry.registerTileEntity(TileEntityLightBarrierSource.class, "TileEntityLightBarrierSource");
		GameRegistry.registerTileEntity(TileEntityLightBarrierSourceActivated.class, "TileEntityLightBarrierSourceActivated");
		GameRegistry.registerTileEntity(TileEntityGravityLift.class, "TileEntityGravityLift");
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		packets.initialise();
		proxy.registerRenderers();
		registerRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiGravLiftHandler());
		packets.registerPacket(PacketUpdateHeight.class);
		packets.registerPacket(PacketUpdateTE.class);
	}
	public void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(lightBridgeSource, 2), new Object[] {"XWX", "YZY", "XWX", 'X', Items.redstone, 'Y', Items.iron_ingot, 'Z', Items.diamond, 'W', Items.glowstone_dust});
		GameRegistry.addRecipe(new ItemStack(lightDoorSource, 2), new Object[] {"YZY", "XWX", 'X', Items.redstone, 'Y', Items.iron_ingot, 'Z', Items.diamond, 'W', Items.glowstone_dust});
		GameRegistry.addRecipe(new ItemStack(gravLift, 1), new Object[] {"XYX", "YZY", "XYX", 'X', new ItemStack(Items.dye, 1, 5), 'Y', Items.iron_ingot, 'Z', Items.ender_pearl});
		GameRegistry.addRecipe(new ItemStack(lightRailSource, 1), new Object[] {"XYX", "XZX", "XAX", 'X', Items.iron_ingot, 'Y', Items.glowstone_dust, 'Z', Items.diamond, 'A', Blocks.detector_rail});
		GameRegistry.addRecipe(new ItemStack(lightBarrierSource, 2), new Object[] {"YX", "ZW", "YX", 'X', Items.redstone, 'Y', Items.iron_ingot, 'Z', Items.diamond, 'W', Items.glowstone_dust});
	}
	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		packets.postInitialise();
	}
}