package com.thethirdswan.htfc_subsidiaries.setup;

import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.CurdSeparatorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class HTFCSBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "htfc_subsidiaries");

    public static final Supplier<BlockBehaviour.Properties> METAL_PROPERTIES = () -> Block.Properties.of(Material.METAL)
            .sound(SoundType.METAL)
            .strength(3, 15)
            .requiresCorrectToolForDrops()
            .isViewBlocking((state, blockReader, pos) -> false);

    public static final RegistryObject<CurdSeparatorBlock> CURD_SEPARATOR = BLOCKS.register("curd_separator", CurdSeparatorBlock::new);

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
    }
}
