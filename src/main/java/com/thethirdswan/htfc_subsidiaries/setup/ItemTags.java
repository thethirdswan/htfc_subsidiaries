package com.thethirdswan.htfc_subsidiaries.setup;

import com.thethirdswan.htfc_subsidiaries.Registrate;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
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
		super(generator, blockTags, "htfc_subsidiaries", helper);
	}

	public static final TagKey<Item> PNC_UPGRADES = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("htfc_subsidiaries", "pnc_upgrades")); // why? just add this to pnc's upgrade tags

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
	public static final TagKey<Item> SPINDLES = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("forge", "spindles"));

	public static final TagKey<Item> USABLE_ON_TOOL_RACK = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("tfc", "usable_on_tool_rack"));
	public static final TagKey<Item> WROUGHT_IRON_METAL_ITEM = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("tfc", "metal_item/wrought_iron"));
	public static final TagKey<Item> WROUGHT_IRON_TOOLS = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("tfc", "metal_item/wrought_iron_tools"));

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

		tag(USABLE_ON_TOOL_RACK).add(Registrate.WROUGHT_IRON_SPINDLE.get());
		tag(WROUGHT_IRON_METAL_ITEM).add(Registrate.WROUGHT_IRON_SPINDLE.get());
		tag(WROUGHT_IRON_METAL_ITEM).add(Registrate.WROUGHT_IRON_SPINDLE_HEAD.get());
		tag(WROUGHT_IRON_TOOLS).add(Registrate.WROUGHT_IRON_SPINDLE.get());

		tag(SPINDLES).add(Registrate.WROUGHT_IRON_SPINDLE.get());
		tag(SPINDLES).add(TFCItems.SPINDLE.get());
	}
	
	@Override
	public String getName() {
		return "HTFC Subsidiaries Item Tags";
	}
}