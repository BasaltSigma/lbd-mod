package net.kris91268.lbd;

import net.kris91268.lbd.block.BlockLightBridge;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.http.config.RegistryBuilder;

/**
 * @author kris91268
 */

@Mod(modid = "lbd", version = "3.0", useMetadata = true)
public class MainLBD {

    private BlockLightBridge lightBridge = new BlockLightBridge();

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(lightBridge);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }
}