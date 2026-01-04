package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
	public ItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, "thethirdswan_subsidiaries", helper);
	}
	
	@Override
	protected void addTags() {
		tag(Registrate.PNC_UPGRADES).add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get());
	}
	
	@Override
	public String getName() {
		return "The Third Swan Subsidiaries Item Tags";
	}
}