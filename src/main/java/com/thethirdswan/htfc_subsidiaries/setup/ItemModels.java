package com.thethirdswan.htfc_subsidiaries.setup;

import com.thethirdswan.htfc_subsidiaries.Registrate;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thethirdswan.htfc_subsidiaries.Registrate.*;

public class ItemModels extends ItemModelProvider {
	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "htfc_subsidiaries", existingFileHelper);
	}

	// pattern for getting item type
	final Pattern pattern = Pattern.compile("(?<=_)[A-Z]\\w+");

	@Override
	protected void registerModels() {
		withExistingParent(WROUGHT_IRON_SPINDLE.get().getRegistryName().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation("htfc_subsidiaries", "item/" + WROUGHT_IRON_SPINDLE.get().getRegistryName().getPath()));
		withExistingParent(WROUGHT_IRON_SPINDLE_HEAD.get().getRegistryName().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation("htfc_subsidiaries", "item/" + WROUGHT_IRON_SPINDLE_HEAD.get().getRegistryName().getPath()));
        pncUpgradesModel(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get());
		for (ItemRegistryObject<Item> item : dusts.values()) {
			Set<String> keySet = dusts.keySet();
			for (String key : keySet) {
				if (dusts.get(key) == item) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
			}
		}
		for (ItemRegistryObject<Item> item : dirty_dusts.values()) {
			Set<String> keySet = dirty_dusts.keySet();
			for (String key : keySet) {
				if (dirty_dusts.get(key) == item) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String item_type = matcher.group();
					mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
				}
			}
		}
		for (ItemRegistryObject<Item> item : clumps.values()) {
			Set<String> keySet = clumps.keySet();
			for (String key : keySet) {
				if (clumps.get(key) == item) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String item_type = matcher.group();
					mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
				}
			}
		}
		for (ItemRegistryObject<Item> item : shards.values()) {
			Set<String> keySet = shards.keySet();
			for (String key : keySet) {
				if (shards.get(key) == item) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String item_type = matcher.group();
					mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
				}
			}
		}
		for (ItemRegistryObject<Item> item : crystals.values()) {
			Set<String> keySet = crystals.keySet();
			for (String key : keySet) {
				if (crystals.get(key) == item) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String item_type = matcher.group();
					mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
				}
			}
		}
	}

	private ItemModelBuilder mekOreProcessingItemModels(Item item, String itemType) {
		if (Objects.equals(itemType, "dust_dirty")) {
		return withExistingParent("dusts/dirty/" + item.getRegistryName().getPath(),
				new ResourceLocation("item/generated")).texture("layer0",
				new ResourceLocation("htfc_subsidiaries", "item/dusts/dirty/" + item.getRegistryName().getPath()));
		} else if (Objects.equals(itemType, "dust")) {
			return withExistingParent("dusts/clean/" + item.getRegistryName().getPath(),
					new ResourceLocation("item/generated")).texture("layer0",
					new ResourceLocation("htfc_subsidiaries", "item/dusts/clean/" + item.getRegistryName().getPath()));
		} else {
			return withExistingParent(itemType + "/" + item.getRegistryName().getPath(),
					new ResourceLocation("item/generated")).texture("layer0",
					new ResourceLocation("htfc_subsidiaries", "item/" + itemType + "/" + item.getRegistryName().getPath()));
		}
	}

	private ItemModelBuilder pncUpgradesModel(Item item) {
		return withExistingParent("pnc_upgrades/" + item.getRegistryName().getPath(),
				new ResourceLocation("item/generated")).texture("layer1",
				new ResourceLocation("htfc_subsidiaries", "item/upgrades/" + item.getRegistryName().getPath()))
				.texture("layer0", new ResourceLocation("pneumaticcraft", "item/upgrade_matrix"));
    }
}