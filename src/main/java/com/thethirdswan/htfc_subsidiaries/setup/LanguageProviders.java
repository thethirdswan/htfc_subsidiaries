package com.thethirdswan.htfc_subsidiaries.setup;

import com.thethirdswan.htfc_subsidiaries.Registrate;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thethirdswan.htfc_subsidiaries.Registrate.*;

public class LanguageProviders extends LanguageProvider {
	public LanguageProviders(DataGenerator gen, String locale) {
		super(gen, "htfc_subsidiaries", locale);
	}
	// pattern to obtain metal type
	final Pattern pattern = Pattern.compile("^[A-Za-z]+");
	public static String textNormalizer(String text) {
		String lowercase = text.toLowerCase();
		return Character.toUpperCase(lowercase.charAt(0)) + lowercase.substring(1);
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.htfc_subsidiaries", "HTFC Subsidiaries");
		add("htfc_subsidiaries.curd_separator", "Curd Separator");
		add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get(), "Temperature Regulator Upgrade");
		// pneumatic GUI stuff
		add("pneumaticcraft.armor.upgrade.htfc_subsidiaries.temperature_regulator", "Temperature Regulator");
		add("pneumaticcraft.gui.tab.info.item.armor.chest.htfc_subsidiaries.temperature_regulatorUpgrade", "This upgrade allows you to regulate your body temperature to the safest temperature, no matter how hot or cold you are.");
		// mekanism stuff
		for (ItemRegistryObject<Item> dust : dusts.values()) {
			Set<String> keySet = dusts.keySet();
			for (String key : keySet) {
				if (dusts.get(key) == dust) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(dust.getTranslationKey(), textNormalizer(metal_type) + " Dust");
				}
			}
		}
		for (ItemRegistryObject<Item> dust : dirty_dusts.values()) {
			Set<String> keySet = dirty_dusts.keySet();
			for (String key : keySet) {
				if (dirty_dusts.get(key) == dust) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(dust.getTranslationKey(), "Dirty " + textNormalizer(metal_type) + " Dust");
				}
			}
		}
		for (ItemRegistryObject<Item> clump : clumps.values()) {
			Set<String> keySet = clumps.keySet();
			for (String key : keySet) {
				if (clumps.get(key) == clump) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(clump.getTranslationKey(), textNormalizer(metal_type) + " Clump");
				}
			}
		}
		for (ItemRegistryObject<Item> shard : shards.values()) {
			Set<String> keySet = shards.keySet();
			for (String key : keySet) {
				if (shards.get(key) == shard) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(shard.getTranslationKey(), textNormalizer(metal_type) + " Shard");
				}
			}
		}
		for (ItemRegistryObject<Item> crystal : crystals.values()) {
			Set<String> keySet = crystals.keySet();
			for (String key : keySet) {
				if (crystals.get(key) == crystal) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(crystal.getTranslationKey(), textNormalizer(metal_type) + " Crystal");
				}
			}
		}
		for (SlurryRegistryObject<Slurry, Slurry> slurry : slurries.values()) {
			Set<String> keySet = slurries.keySet();
			for (String key : keySet) {
				if (slurries.get(key) == slurry) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(slurry.getCleanSlurry().getTranslationKey(), textNormalizer(metal_type) + " Slurry");
					add(slurry.getDirtySlurry().getTranslationKey(), "Dirty " + textNormalizer(metal_type) + " Slurry");
				}
			}
		}
    }
}