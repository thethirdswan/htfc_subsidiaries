package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.ApiUtils;
import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import com.google.gson.JsonObject;
import com.thethirdswan.htfc_subsidiaries.Main;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class CurdSeparatorRecipeSerializer extends IERecipeSerializer<CurdSeparatorRecipe> {


    @Override
    public ItemStack getIcon() {
        return new ItemStack(HTFCSMultiblocks.CURD_SEPARATOR.get());
    }

    @Override
    public CurdSeparatorRecipe readFromJson(ResourceLocation resourceLocation, JsonObject jsonObject, ICondition.IContext iContext) {
//        FluidStack input = ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(jsonObject, "input0"));
        FluidStack input = ApiUtils.jsonDeserializeFluidStack(GsonHelper.getAsJsonObject(jsonObject, "input"));
        ItemStack[] output = Ingredient.fromJson(jsonObject.get("result")).getItems();
//        ItemStack output = jsonObject.get("result");
        Main.LOGGER.info("res id: {}", resourceLocation);
        Main.LOGGER.info("input: {}", input);
        Main.LOGGER.info("output: {}", (Object) output);
        Main.LOGGER.info("recipes: {}", CurdSeparatorRecipe.RECIPES);
        int energy = GsonHelper.getAsInt(jsonObject, "energy");
        CurdSeparatorRecipe recipe = new CurdSeparatorRecipe(resourceLocation, input, output);
        recipe.modifyTimeAndEnergy(() -> 1, () -> energy);
        return recipe;
    }

    @Override
    public @Nullable CurdSeparatorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        FluidStack input = buffer.readFluidStack();
        ItemStack[] output = Ingredient.fromNetwork(buffer).getItems();
        int energy = buffer.readInt();
        return new CurdSeparatorRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, CurdSeparatorRecipe recipe) {
        buffer.writeFluidStack(recipe.input);
        buffer.writeItemStack(recipe.getResultItem(), false);
        buffer.writeInt(recipe.getTotalProcessEnergy());
    }
}
