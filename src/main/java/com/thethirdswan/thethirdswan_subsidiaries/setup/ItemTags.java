package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.Registrate;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ItemTags extends ItemTagsProvider {
	public ItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, "thethirdswan_subsidiaries", helper);
	}

	public static final TagKey<Item> PNC_UPGRADES = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("thethirdswan_subsidiaries", "pnc_upgrades"));
	public static final TagKey<Item> NICKEL_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/nickel"));
	public static final TagKey<Item> SILVER_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/silver"));
	public static final TagKey<Item> BISMUTH_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/bismuth"));
	public static final TagKey<Item> ALUMINUM_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/aluminium"));
	public static final TagKey<Item> CHROMIUM_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/chromium"));
	public static final TagKey<Item> ZINC_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "ingot/zinc"));

	@Override
	protected void addTags() {
		tag(PNC_UPGRADES).add(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get());
		// come on, you can do a little better than to reference an ingot with a custom tag
		tag(NICKEL_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tfc", "metal/ingot/nickel"))));
		tag(ZINC_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tfc", "metal/ingot/zinc"))));
		tag(BISMUTH_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tfc", "metal/ingot/bismuth"))));
		tag(SILVER_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tfc", "metal/ingot/silver"))));
		tag(ALUMINUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering", "ingot_aluminum"))));
		tag(CHROMIUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("firmalife", "metal/ingot/chromium"))));

	}
	
	@Override
	public String getName() {
		return "The Third Swan Subsidiaries Item Tags";
	}
}