package com.thethirdswan.htfc_subsidiaries.tfcrecipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class SpindleHeadAnvil implements FinishedRecipe {
    private final ResourceLocation id;

    public SpindleHeadAnvil(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.addProperty("type", "tfc:anvil");

        JsonObject inputObject = new JsonObject();
        inputObject.addProperty("tag", "forge:ingots/wrought_iron");
        jsonObject.add("input", inputObject);

        JsonObject resultObject = new JsonObject();
        resultObject.addProperty("item", "htfc_subsidiaries:wrought_iron_spindle_head");
        jsonObject.add("result", resultObject);

        jsonObject.addProperty("tier", 3);

        JsonArray rules = new JsonArray();
        rules.add("shrink_last");
        rules.add("hit_second_last");
        rules.add("shrink_third_last");
        jsonObject.add("rules", rules);

        jsonObject.addProperty("apply_forging_bonus", true);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.ANVIL.get();
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
