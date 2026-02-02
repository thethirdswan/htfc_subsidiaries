package com.thethirdswan.thethirdswan_subsidiaries.pnc;

import com.thethirdswan.thethirdswan_subsidiaries.Registrate;
import me.desht.pneumaticcraft.api.item.IUpgradeRegistry.Builder;
import me.desht.pneumaticcraft.common.core.ModItems;
import me.desht.pneumaticcraft.common.util.upgrade.ApplicableUpgradesDB;
import me.desht.pneumaticcraft.common.util.upgrade.UpgradesDBSetup;

public class PNCUpgradesSetup extends UpgradesDBSetup {
    private static void initItems() {
        ApplicableUpgradesDB db = ApplicableUpgradesDB.getInstance();

        db.addApplicableUpgrades(ModItems.PNEUMATIC_CHESTPLATE.get(), new Builder()
                .with(Registrate.TEMPERATURE_REGULATOR.get(), 1));
    }

    public static void init() {
        initItems();
    }
}