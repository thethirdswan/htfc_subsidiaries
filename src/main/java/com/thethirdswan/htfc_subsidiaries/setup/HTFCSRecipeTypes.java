package com.thethirdswan.htfc_subsidiaries.setup;

import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "htfc_subsidiaries",  bus = Mod.EventBusSubscriber.Bus.MOD)
public class HTFCSRecipeTypes {
    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> b) {
        CurdSeparatorRecipe.RECIPE_TYPE = register("curd_separator");
    }

    private static <T extends Recipe<?>> RecipeType<T> register(String name) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation("htfc_subsidiaries", name), new RecipeType<T>() {
           @Override public String toString() {
               return name;
           }
        });
    }

}
