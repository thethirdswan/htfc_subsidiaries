package com.thethirdswan.thethirdswan_subsidiaries.setup;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags extends BlockTagsProvider {
	public BlockTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, "thethirdswan_subsidiaries", helper);
	}
	
	@Override
	protected void addTags() {
		
	}
	
	@Override
	public String getName() {
		return "Block Tags";
	}
}