package com.thethirdswan.htfc_subsidiaries;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import com.mojang.logging.LogUtils;

import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.CurdSeparatorBlock;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import com.thethirdswan.htfc_subsidiaries.pnc.PNCUpgradesSetup;
import com.thethirdswan.htfc_subsidiaries.pnc.UpgradeHandlers;
import com.thethirdswan.htfc_subsidiaries.setup.BlockEntities;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.NewRegistryEvent;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("htfc_subsidiaries")
public class Main {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main() {
        LOGGER.info("Starting HTFC Subsidiaries");
        HTFCSMultiblocks.forceLoad();
        LOGGER.info("Pre-Loaded Multiblocks");
        HTFCSBlocks.init();
        LOGGER.info("Initialized Blocks");
        Registrate.init();
        LOGGER.info("Registrate Initialization");
        BlockEntities.init();
        LOGGER.info("Block Entity Initialization");

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {
        UpgradeHandlers.init();
        HTFCSMultiblocks.init();
        LOGGER.info("Multiblock Initialization");
        event.enqueueWork(PNCUpgradesSetup::init);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("tfc", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
//        @SubscribeEvent
//        public static void onNewRegistry(NewRegistryEvent event) {
//
//        }
    }

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("htfc_subsidiaries") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get());
        }
    };
}
