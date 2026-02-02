package com.thethirdswan.thethirdswan_subsidiaries.pnc.handlers;

import com.mojang.logging.LogUtils;
import com.thethirdswan.thethirdswan_subsidiaries.Registrate;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import me.desht.pneumaticcraft.api.pneumatic_armor.BaseArmorUpgradeHandler;
import me.desht.pneumaticcraft.api.pneumatic_armor.IArmorExtensionData;
import me.desht.pneumaticcraft.api.pneumatic_armor.ICommonArmorHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.slf4j.Logger;

public class TemperatureHandler extends BaseArmorUpgradeHandler<IArmorExtensionData> {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static boolean upgradeToggle;

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation("thethirdswan_subsidiaries", "temperature_regulator");
    }

    @Override
    public PNCUpgrade[] getRequiredUpgrades() {
        return new PNCUpgrade[]{Registrate.TEMPERATURE_REGULATOR.get()};
    }

    @Override
    public float getIdleAirUsage(ICommonArmorHandler iCommonArmorHandler) {
        return 0;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public void onToggle(ICommonArmorHandler commonArmorHandler, boolean newState) {
    }

    // testing purposes, adding air usage later
    @Override
    public void tick(ICommonArmorHandler commonArmorHandler, boolean enabled) {
        upgradeToggle = enabled;
    }

    @Override
    public void onShutdown(ICommonArmorHandler commonArmorHandler) {
        upgradeToggle = false;
    }
}