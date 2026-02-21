package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.MultiblockRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.Lazy;

public class CurdSeparatorRecipe extends MultiblockRecipe {
    protected CurdSeparatorRecipe(Lazy<ItemStack> outputDummy, RecipeType<?> type, ResourceLocation id) {
        super(outputDummy, type, id);
    }

    @Override
    protected IERecipeSerializer<?> getIESerializer() {
        return null;
    }

    @Override
    public int getMultipleProcessTicks() {
        return 0;
    }
}
