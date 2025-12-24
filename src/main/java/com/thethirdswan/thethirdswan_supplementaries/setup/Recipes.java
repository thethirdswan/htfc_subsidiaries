package com.thethirdswan.thethirdswan_supplementaries.setup;

import java.util.function.Consumer;
import com.thethirdswan.thethirdswan_supplementaries.items.Registrate;
import me.desht.pneumaticcraft.common.PneumaticCraftTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
//import com.lgmrszd


public class Recipes extends RecipeProvider {
	public Recipes(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get())
		.pattern("cpc")
		.pattern("iam")
		.pattern("cpc")
		.define('p', PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON)
		.define('c', PneumaticCraftTags.Items.UPGRADE_COMPONENTS)
		.define('i', Items.ICE)
		.define('m', Items.MAGMA_BLOCK)
		// TODO: why is compressed creativity not downloaded as dependency
//		.define('a', )
		.group("thethirdswan_supplementaries")
		.unlockedBy("upgrades", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
		.save(consumer);
	}
}