package com.thethirdswan.thethirdswan_subsidiaries.setup;

import java.util.function.Consumer;
import com.thethirdswan.thethirdswan_subsidiaries.items.Registrate;
import me.desht.pneumaticcraft.common.PneumaticCraftTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
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
		        .define('a', new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("compressedcreativity", "industrial_air_blower"))).getItem())
                .group("thethirdswan_subsidiaries")
                .unlockedBy("upgrades", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .save(consumer);
    }
}