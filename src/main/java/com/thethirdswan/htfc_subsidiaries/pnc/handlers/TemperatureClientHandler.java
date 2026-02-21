package com.thethirdswan.htfc_subsidiaries.pnc.handlers;

import com.thethirdswan.htfc_subsidiaries.pnc.UpgradeHandlers;
import me.desht.pneumaticcraft.api.client.pneumatic_helmet.IArmorUpgradeClientHandler;

public class TemperatureClientHandler extends IArmorUpgradeClientHandler.SimpleToggleableHandler<TemperatureHandler> {
    public TemperatureClientHandler() {
        super(UpgradeHandlers.temperatureHandler);
    }
}
