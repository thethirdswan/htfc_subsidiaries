package com.thethirdswan.htfc_subsidiaries.tfcrecipes;

import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class Heating implements FinishedRecipe {
    private final ResourceLocation id;
    private final String itemName;
    private final int temperature;
    private final String fluidName;

    public Heating(ResourceLocation id, String itemName, int temperature, String fluidName) {
        this.id = id;
        this.itemName = itemName;
        this.temperature = temperature;
        this.fluidName = fluidName;
    }

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.addProperty("type", "tfc:heating");

        JsonObject ingredientObject = new JsonObject();
        ingredientObject.addProperty("item", itemName);
        jsonObject.add("ingredient", ingredientObject);

        JsonObject resultFluidObject = new JsonObject();
        resultFluidObject.addProperty("fluid", fluidName);
        resultFluidObject.addProperty("amount", 100);
        jsonObject.add("result_fluid", resultFluidObject);

        jsonObject.addProperty("temperature", temperature);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.HEATING.get();
    }

    @Override
    public @Nullable JsonObject serializeAdvancement() {
        return null;
    }

    @Override
    public @Nullable ResourceLocation getAdvancementId() {
        return null;
    }
}
