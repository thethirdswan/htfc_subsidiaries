package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.MultiblockRecipe;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class CurdSeparatorRecipe extends MultiblockRecipe {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static RegistryObject<IERecipeSerializer<CurdSeparatorRecipe>> SERIALIZER;
    public static RecipeType<CurdSeparatorRecipe> RECIPE_TYPE;
    public static final CachedRecipeList<CurdSeparatorRecipe> RECIPES = new CachedRecipeList<>(() -> RECIPE_TYPE, CurdSeparatorRecipe.class);

    public final FluidStack input;
    public final ItemStack[] output;
    protected CurdSeparatorRecipe(ResourceLocation id, FluidStack input, ItemStack[] output) {
        super(LAZY_EMPTY, RECIPE_TYPE, id);
        this.input = input;
        this.output = output;
    }

    @Override
    protected IERecipeSerializer<CurdSeparatorRecipe> getIESerializer() {
        return SERIALIZER.get();
    }

    public static CurdSeparatorRecipe findRecipe(Level level, FluidStack input) {
        LOGGER.info("is this even being called?");
        for (CurdSeparatorRecipe recipe : RECIPES.getRecipes(level)) {
            LOGGER.info("checking recipe {}", recipe);
            if (!input.isEmpty()) {
                LOGGER.info("input is not empty");
                if (recipe.input != null && recipe.input.containsFluid(input)) {
                    LOGGER.info("recipe is correct");
                    return recipe;
                }
            }
        }
        return null;
    }


    @Override
    public int getMultipleProcessTicks() {
        return 0;
    }
}
