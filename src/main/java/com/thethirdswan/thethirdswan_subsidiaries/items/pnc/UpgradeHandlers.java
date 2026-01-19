package com.thethirdswan.thethirdswan_subsidiaries.items.pnc;

import me.desht.pneumaticcraft.client.pneumatic_armor.ArmorUpgradeClientRegistry;
import me.desht.pneumaticcraft.common.pneumatic_armor.ArmorUpgradeRegistry;
import com.thethirdswan.thethirdswan_subsidiaries.items.pnc.handlers.*;

public class UpgradeHandlers {
    public static TemperatureHandler temperatureHandler;

    public static void init() {
        ArmorUpgradeRegistry registry = ArmorUpgradeRegistry.getInstance();
        ArmorUpgradeClientRegistry clientregistry = ArmorUpgradeClientRegistry.getInstance();

        registry.registerUpgradeHandler(new TemperatureHandler());
        clientregistry.registerHandler(new TemperatureHandler(), new TemperatureClientHandler());
    }
}
