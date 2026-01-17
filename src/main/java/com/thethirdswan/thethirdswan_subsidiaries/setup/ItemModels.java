package com.thethirdswan.thethirdswan_subsidiaries.setup;

import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "thethirdswan_subsidiaries", existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
        pncUpgradesModel(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get());
	}

	private ItemModelBuilder pncUpgradesModel(Item item) {
		return withExistingParent(item.getRegistryName().getPath(),
				new ResourceLocation("item/generated")).texture("layer1",
				new ResourceLocation("thethirdswan_subsidiaries", "item/upgrades/" + item.getRegistryName().getPath()))
				.texture("layer0", new ResourceLocation("pneumaticcraft", "item/upgrade_matrix"));
    }
}