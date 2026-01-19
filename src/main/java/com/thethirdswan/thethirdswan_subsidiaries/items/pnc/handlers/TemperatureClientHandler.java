package com.thethirdswan.thethirdswan_subsidiaries.items.pnc.handlers;

import com.thethirdswan.thethirdswan_subsidiaries.items.pnc.UpgradeHandlers;
import me.desht.pneumaticcraft.api.client.pneumatic_helmet.IArmorUpgradeClientHandler;

public class TemperatureClientHandler extends IArmorUpgradeClientHandler.SimpleToggleableHandler<TemperatureHandler> {
    public TemperatureClientHandler() {
        super(UpgradeHandlers.temperatureHandler);
    }
}
