package com.thethirdswan.thethirdswan_supplementaries.setup;

import com.thethirdswan.thethirdswan_supplementaries.items.Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "thethirdswan_supplementaries", existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent(Registrate.TEMPERATURE_REGULATOR_UPGRADE.getId().getPath(), modLoc("items/temperature_regulator_upgrade"));
		singleTexture(Registrate.TEMPERATURE_REGULATOR_UPGRADE.getId().getPath(), mcLoc("item/generated"), modLoc("item/temperature_regulator_upgrade"));
	}
	
}