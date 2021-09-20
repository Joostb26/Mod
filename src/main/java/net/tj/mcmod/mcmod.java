package net.tj.mcmod;

import net.minecraft.block.Block;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tj.mcmod.biomes.CustomBiome;
import net.tj.mcmod.biomes.SurfaceBuilder;
import net.tj.mcmod.blocks.ModBlocks;
import net.tj.mcmod.container.ModContainers;
import net.tj.mcmod.items.ModItems;
import net.tj.mcmod.potions.ModPotions;
import net.tj.mcmod.screen.LightningChannelerScreen;
import net.tj.mcmod.tileentity.ModTileEntities;
import net.tj.mcmod.world.gen.ModConfiguredFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(mcmod.MOD_ID)

public class mcmod
{

    public static final String MOD_ID = "mcmod";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public mcmod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // De classes worden hier geroepen, zo werkt alles.

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModPotions.EFFECTS.register(eventBus);
        ModPotions.POTIONS.register(eventBus);
        CustomBiome.BIOMES.register(eventBus);
        CustomBiome.registerBiomes();
        SurfaceBuilder.SURFACE_BUILDERS.register(eventBus);
        ModConfiguredFeatures.FEATURES.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);


        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModConfiguredFeatures::addOres);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

        //Hier word de ModPotions geroept.
        ModPotions.AddPotionRecipes();

        RenderTypeLookup.setRenderLayer(ModBlocks.COCONUT_LEAVES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.COCONUT_SAPLING.get(), RenderType.cutout());

        // Hier laten we de blokken veranderen in naar die we willen. Als je het block stripped.
        event.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(ModBlocks.COCONUT_LOG.get(), ModBlocks.STRIPPED_COCONUT_LOG.get())
                    .put(ModBlocks.COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_WOOD.get()).build();
        });

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        ScreenManager.register(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(),
                LightningChannelerScreen::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.BANANA.get(), RenderType.cutout());
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
