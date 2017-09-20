package net.kris91268.lbd;

import net.kris91268.lbd.block.BlockLightBridge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.http.config.RegistryBuilder;

/**
 * @author kris91268
 */

@Mod(modid = MainLBD.MODID, version = "3.0", useMetadata = true)
@Mod.EventBusSubscriber
public final class MainLBD {

    public static final String MODID = "lbd";

    @Mod.Instance
    public static MainLBD instance;

    private static BlockLightBridge lightBridge = new BlockLightBridge();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        config.save();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(lightBridge);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {

    }
}
