package com.thethirdswan.thethirdswan_subsidiaries.items.pnc.handlers;

import com.lumintorious.tfcambiental.capability.TemperatureCapability;
import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import me.desht.pneumaticcraft.api.pneumatic_armor.BaseArmorUpgradeHandler;
import me.desht.pneumaticcraft.api.pneumatic_armor.IArmorExtensionData;
import me.desht.pneumaticcraft.api.pneumatic_armor.ICommonArmorHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class TemperatureHandler extends BaseArmorUpgradeHandler<IArmorExtensionData> {

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
    public void onInit(ICommonArmorHandler commonArmorHandler) {
    }

    // TODO: implement the toggle button for the upgrade so this can be called
    @Override
    public void tick(ICommonArmorHandler commonArmorHandler, boolean enabled) {
        if (!enabled) {
            Player player = commonArmorHandler.getPlayer();
            player.getCapability(TemperatureCapability.CAPABILITY).ifPresent(capability -> {
// TODO instead of instant changes, use gradual changes
                capability.setTemperature(15f);
            });


        }

    }
}