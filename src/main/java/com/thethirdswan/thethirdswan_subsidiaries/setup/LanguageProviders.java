package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageProviders extends LanguageProvider {
	public LanguageProviders(DataGenerator gen, String locale) {
		super(gen, "thethirdswan_subsidiaries", locale);
	}
	
	@Override
	protected void addTranslations() {
		add("itemGroup.thethirdswan_subsidiaries", "The Third Swan's Subsidiaries");
		add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get(), "Temperature Regulator Upgrade");		
	}
}