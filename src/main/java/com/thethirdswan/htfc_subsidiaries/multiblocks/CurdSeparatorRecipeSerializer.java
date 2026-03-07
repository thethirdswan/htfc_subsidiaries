package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import com.google.gson.JsonObject;
import com.thethirdswan.htfc_subsidiaries.Main;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

public class CurdSeparatorRecipeSerializer extends IERecipeSerializer<CurdSeparatorRecipe> {

    @Override
    public ItemStack getIcon() {
        return new ItemStack(HTFCSMultiblocks.CURD_SEPARATOR.get());
    }

    @Override
    public CurdSeparatorRecipe readFromJson(ResourceLocation resourceLocation, JsonObject jsonObject, ICondition.IContext iContext) {
            FluidTagInput input = FluidTagInput.deserialize(GsonHelper.getAsJsonObject(jsonObject, "input"));
            Lazy<ItemStack> output = readOutput(jsonObject.get("result"));
            // todo result kept returning barrier/air?
            IngredientWithSize outgredient = IngredientWithSize.deserialize(GsonHelper.getAsJsonObject(jsonObject, "result"));
            Main.LOGGER.info("output: {}", output.get().getItem().getRegistryName().toString());
            Main.LOGGER.info("what getting the jsonobject results: {}", jsonObject.get("result"));
            Main.LOGGER.info("outgredient: {}", (Object) outgredient.getMatchingStacks());
            int energy = GsonHelper.getAsInt(jsonObject, "energy");

            CurdSeparatorRecipe recipe = new CurdSeparatorRecipe(resourceLocation, input, output, energy);
            return recipe;
    }

    @Override
    public @Nullable CurdSeparatorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            FluidTagInput input = FluidTagInput.read(buffer);
            Lazy<ItemStack> output = readLazyStack(buffer);
            int energy = buffer.readInt();
            return new CurdSeparatorRecipe(recipeId, input, output, energy);

    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, CurdSeparatorRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeInt(recipe.getTotalProcessEnergy());
    }
}
