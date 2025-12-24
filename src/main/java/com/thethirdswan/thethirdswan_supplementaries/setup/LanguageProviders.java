package com.thethirdswan.thethirdswan_supplementaries.setup;

import com.thethirdswan.thethirdswan_supplementaries.items.Registrate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageProviders extends LanguageProvider {
	public LanguageProviders(DataGenerator gen, String locale) {
		super(gen, "thethirdswan_supplementaries", locale);
	}
	
	@Override
	protected void addTranslations() {
		add("itemGroup.The Third Swan's Supplementaries", "Tutorial");
		add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get(), "Temperature Regulator Upgrade");		
	}
}