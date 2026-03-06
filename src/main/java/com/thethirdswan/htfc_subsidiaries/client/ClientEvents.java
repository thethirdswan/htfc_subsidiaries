package com.thethirdswan.htfc_subsidiaries.client;

import com.thethirdswan.htfc_subsidiaries.client.screen.CurdSeparatorScreen;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientEvents {
    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEvents::clientSetup);
    }

    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(HTFCSContainers.CURD_SEPARATOR.getType(), CurdSeparatorScreen::new);
        });
    }
}
