package com.thethirdswan.htfc_subsidiaries.tfcrecipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class YarnsAndSpindles implements FinishedRecipe {
    private final ResourceLocation id;
    private final Recipe<CraftingContainer> recipe;
    private static final Logger LOGGER = LogUtils.getLogger();

    public YarnsAndSpindles(ResourceLocation id, Recipe<CraftingContainer> recipe) {
        this.id = id;
        this.recipe = recipe;
    }

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.addProperty("type", "tfc:damage_inputs_shapeless_crafting");
        JsonObject recipeObject = new JsonObject();
        recipeObject.addProperty("type", "minecraft:crafting_shapeless");
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        JsonArray ingredientsArray = new JsonArray();

        for (int i = 0; i < ingredients.size(); i++) {
            JsonObject ingredientObject = new JsonObject();
            ingredientObject.addProperty("item", ingredients.get(i).getItems()[0].getItem().getRegistryName().toString());
            ingredientsArray.add(ingredientObject);
        }

        JsonObject spindleTagObject = new JsonObject();
        spindleTagObject.addProperty("tag", "forge:spindles");
        ingredientsArray.add(spindleTagObject);

        JsonObject resultObject = new JsonObject();
        resultObject.addProperty("item", recipe.getResultItem().getItem().getRegistryName().toString());
        resultObject.addProperty("count", recipe.getResultItem().getCount());
        recipeObject.add("ingredients", ingredientsArray);
        recipeObject.add("result", resultObject);
        jsonObject.add("recipe", recipeObject);
    }


    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.DAMAGE_INPUTS_SHAPELESS_CRAFTING.get();
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
