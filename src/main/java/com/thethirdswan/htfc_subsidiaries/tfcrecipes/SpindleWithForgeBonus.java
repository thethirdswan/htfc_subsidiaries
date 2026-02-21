package com.thethirdswan.htfc_subsidiaries.tfcrecipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class SpindleWithForgeBonus implements FinishedRecipe {
    private final ResourceLocation id;

    public SpindleWithForgeBonus(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.addProperty("type", "tfc:advanced_shaped_crafting");

        JsonArray patternArray = new JsonArray();
        patternArray.add("H");
        patternArray.add("S");
        jsonObject.add("pattern", patternArray);

        JsonObject keyObject = new JsonObject();
        JsonObject HObject = new JsonObject();
        HObject.addProperty("item", "htfc_subsidiaries:wrought_iron_spindle_head");
        keyObject.add("H", HObject);
        JsonObject SObject = new JsonObject();
        SObject.addProperty("tag", "forge:rods/wooden");
        keyObject.add("S", SObject);
        jsonObject.add("key", keyObject);

        JsonObject resultObject = new JsonObject();
        JsonObject stackObject = new JsonObject();
        stackObject.addProperty("item", "htfc_subsidiaries:wrought_iron_spindle");
        resultObject.add("stack", stackObject);
        JsonArray modifierArray = new JsonArray();
        modifierArray.add("tfc:copy_forging_bonus");
        resultObject.add("modifiers", modifierArray);
        jsonObject.add("result", resultObject);

        jsonObject.addProperty("input_row", 0);
        jsonObject.addProperty("input_column", 0);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.ADVANCED_SHAPED_CRAFTING.get();
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
