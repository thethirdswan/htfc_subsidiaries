package com.thethirdswan.htfc_subsidiaries.api;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.builders.IEFinishedRecipe;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CurdSeparatorRecipeBuilder extends IEFinishedRecipe<CurdSeparatorRecipeBuilder> {
    protected CurdSeparatorRecipeBuilder() {
        super(CurdSeparatorRecipe.SERIALIZER.get());
    }

    public static CurdSeparatorRecipeBuilder builder(ItemStack itemStack) {
        return new CurdSeparatorRecipeBuilder().addResult(itemStack);
    }

    public CurdSeparatorRecipeBuilder addInput(FluidTagInput input) {
        return addFluidTag(generateSafeInputKey(), input);
    }

    public CurdSeparatorRecipeBuilder addInput(TagKey<Fluid> input, int amount) {
        return addFluidTag(generateSafeInputKey(), input, amount);
    }


}
