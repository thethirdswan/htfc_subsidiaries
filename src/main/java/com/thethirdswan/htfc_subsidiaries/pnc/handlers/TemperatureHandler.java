package com.thethirdswan.htfc_subsidiaries.pnc.handlers;

import com.lumintorious.tfcambiental.capability.TemperatureCapability;
import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.Registrate;
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
    public static float targetTemp;
    public static float currentTargetTemp;
    public static float tempDiff;

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation("htfc_subsidiaries", "temperature_regulator");
    }

    @Override
    public PNCUpgrade[] getRequiredUpgrades() {
        return new PNCUpgrade[]{Registrate.TEMPERATURE_REGULATOR.get()};
    }

    @Override
    public float getIdleAirUsage(ICommonArmorHandler iCommonArmorHandler) {
        if (tempDiff > 1f) {
            return tempDiff * 10;
        }
        return 5;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public void onToggle(ICommonArmorHandler commonArmorHandler, boolean newState) {
    }

    @Override
    public void tick(ICommonArmorHandler commonArmorHandler, boolean enabled) {
        upgradeToggle = enabled;
        if (upgradeToggle) {
            commonArmorHandler.getPlayer().getCapability(TemperatureCapability.CAPABILITY).ifPresent(capability -> {
                currentTargetTemp = capability.getTarget();
            });
            if (currentTargetTemp > 15f) {
                float tempReduction = (currentTargetTemp - 15f) / 2;
                tempDiff = tempReduction;
                targetTemp = currentTargetTemp - tempReduction;
            } else if (currentTargetTemp < 15f) {
                float tempIncrease = ((currentTargetTemp - 15f) * -1) / 2;
                tempDiff = tempIncrease;
                targetTemp = currentTargetTemp + tempIncrease;
            }
        }
        // cant restore temperature back to environment incrementally :(
//        else {
//            float environmentTemp = EnvironmentalModifier.getEnvironmentTemperatureWithTimeOfDay(commonArmorHandler.getPlayer());
//            if (environmentTemp > 15f && targetTemp < environmentTemp) {
//                float tempIncrease = (environmentTemp - targetTemp) / 2;
//                targetTemp = targetTemp + tempIncrease;
//            } else if (environmentTemp < 15f && targetTemp > environmentTemp) {
//                float tempDecrease = (environmentTemp - targetTemp) / 2;
//                targetTemp = targetTemp + tempDecrease;
//            }
//        }
    }

    @Override
    public void onShutdown(ICommonArmorHandler commonArmorHandler) {
        upgradeToggle = false;
    }
}