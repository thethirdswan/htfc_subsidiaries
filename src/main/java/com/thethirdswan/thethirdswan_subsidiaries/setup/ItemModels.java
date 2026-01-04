package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "thethirdswan_subsidiaries", existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent(Registrate.TEMPERATURE_REGULATOR_UPGRADE.getId().getPath(), modLoc("items/temperature_regulator_upgrade"));
		singleTexture(Registrate.TEMPERATURE_REGULATOR_UPGRADE.getId().getPath(), mcLoc("item/generated"), modLoc("item/temperature_regulator_upgrade"));
	}
	
}