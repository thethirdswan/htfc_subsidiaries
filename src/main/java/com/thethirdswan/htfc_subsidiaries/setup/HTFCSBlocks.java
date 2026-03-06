package com.thethirdswan.htfc_subsidiaries.setup;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class HTFCSBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "htfc_subsidiaries");


    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockConstructor, @Nullable Function<T, ? extends BlockItem> blockItem){
        RegistryObject<T> block = BLOCKS.register(name, blockConstructor);
        if(blockItem != null){
            registerItem(name, () -> blockItem.apply(block.get()));
        }
        return block;
    }

    public static <T extends Block> RegistryObject<T> registerMultiblockBlock(String name, Supplier<T> blockConstructor){
        return registerBlock(name, blockConstructor, block -> new BlockItem(block, new Item.Properties()));
    }

    public static void registerItem(String registry_name,  Supplier<Item> itemSupplier){
        HTFCSItems.ITEMS.register(registry_name, itemSupplier);
    }


    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
    }
}
