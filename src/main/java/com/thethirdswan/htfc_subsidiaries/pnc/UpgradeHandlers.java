package com.thethirdswan.htfc_subsidiaries.pnc;

import com.thethirdswan.htfc_subsidiaries.pnc.handlers.TemperatureClientHandler;
import com.thethirdswan.htfc_subsidiaries.pnc.handlers.TemperatureHandler;
import me.desht.pneumaticcraft.client.pneumatic_armor.ArmorUpgradeClientRegistry;
import me.desht.pneumaticcraft.common.pneumatic_armor.ArmorUpgradeRegistry;
import com.thethirdswan.htfc_subsidiaries.pnc.handlers.*;

public class UpgradeHandlers {
    public static TemperatureHandler temperatureHandler;

    public static void init() {
        ArmorUpgradeRegistry registry = ArmorUpgradeRegistry.getInstance();
        ArmorUpgradeClientRegistry clientregistry = ArmorUpgradeClientRegistry.getInstance();

        temperatureHandler = registry.registerUpgradeHandler(new TemperatureHandler());
        clientregistry.registerHandler(temperatureHandler, new TemperatureClientHandler());
    }
}
