package com.thethirdswan.htfc_subsidiaries.setup;

import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorRecipe;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorRecipeSerializer;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

public class RecipeSerializers {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "htfc_subsidiaries");

    static {
        CurdSeparatorRecipe.SERIALIZER = RECIPE_SERIALIZER.register(
                "curd_separator", CurdSeparatorRecipeSerializer::new
        );
    }

    public static void init() {
        LOGGER.info("Registering recipe serializers");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RECIPE_SERIALIZER.register(eventBus);
    }

}
