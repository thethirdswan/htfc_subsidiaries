package com.thethirdswan.thethirdswan_subsidiaries.pnc.items;

import me.desht.pneumaticcraft.api.PneumaticRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.ChatFormatting;
import java.util.List;
import java.util.function.Supplier;
import me.desht.pneumaticcraft.api.item.IUpgradeItem;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

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

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> infoList, @NotNull TooltipFlag par4) {
		if (Screen.hasShiftDown()) {
			infoList.add(new TranslatableComponent("pneumaticcraft.gui.tooltip.item.upgrade.usedIn").withStyle(ChatFormatting.GOLD));
			PneumaticRegistry.getInstance().getUpgradeRegistry().addUpgradeTooltip(upgrade.get(), infoList);
		} else {
			infoList.add(new TranslatableComponent("pneumaticcraft.gui.tooltip.item.upgrade.shiftMessage").withStyle(ChatFormatting.GOLD));
		}
	}
}