package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.Registrate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.regex.Pattern;

public class LanguageProviders extends LanguageProvider {
	public LanguageProviders(DataGenerator gen, String locale) {
		super(gen, "thethirdswan_subsidiaries", locale);
	}
	// pattern to obtain metal type
	final Pattern pattern = Pattern.compile("^[A-Za-z]+");

	@Override
	protected void addTranslations() {
		add("itemGroup.thethirdswan_subsidiaries", "The Third Swan's Subsidiaries");
		add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get(), "Temperature Regulator Upgrade");
		// pneumatic GUI stuff
		add("pneumaticcraft.armor.upgrade.thethirdswan_subsidiaries.temperature_regulator", "Temperature Regulator");
		add("pneumaticcraft.gui.tab.info.item.armor.chest.thethirdswan_subsidiaries.temperature_regulatorUpgrade", "This upgrade allows you to regulate your body temperature to the safest temperature, no matter how hot or cold you are.");
		// mekanism stuff
		//todo plz add translation key for each metal ore processing item, you know what to do
//		add(Registrate.NICKEL_DUST.getTranslationKey(), "Nickel Dust");
//		add(Registrate.NICKEL_DUST_DIRTY.getTranslationKey(), "Dirty Nickel Dust");
//		add(Registrate.NICKEL_CLUMP.getTranslationKey(), "Nickel Clump");
//		add(Registrate.NICKEL_SHARD.getTranslationKey(), "Nickel Shard");
//		add(Registrate.NICKEL_CRYSTAL.getTranslationKey(), "Nickel Crystal");
//		add(Registrate.NICKEL_SLURRY.getCleanSlurry().getTranslationKey(), "Nickel Slurry");
//		add(Registrate.NICKEL_SLURRY.getDirtySlurry().getTranslationKey(), "Dirty Nickel Slurry");
	}
}