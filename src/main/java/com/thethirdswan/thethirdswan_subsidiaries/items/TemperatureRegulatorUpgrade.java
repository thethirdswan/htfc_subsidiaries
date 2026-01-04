package com.thethirdswan.thethirdswan_subsidiaries.items;

import net.minecraft.world.item.Item;
import java.util.function.Supplier;
import me.desht.pneumaticcraft.api.item.IUpgradeItem;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;

public class TemperatureRegulatorUpgrade extends Item implements IUpgradeItem {
	private final Supplier<PNCUpgrade> upgrade;
	private final int tier;

	public TemperatureRegulatorUpgrade(Properties props, Supplier<PNCUpgrade> upgrade, int tier) {
		super(props);
		this.upgrade = upgrade;
		this.tier = tier;
	}
	
	@Override
	public int getUpgradeTier() {
		return tier;
	}
	
	@Override
	public PNCUpgrade getUpgradeType() {
		return upgrade.get();
	}
}