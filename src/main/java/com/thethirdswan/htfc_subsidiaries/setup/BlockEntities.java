package com.thethirdswan.htfc_subsidiaries.setup;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.blocks.MultiblockBEType;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES, "htfc_subsidiaries"
    );

    public static final MultiblockBEType<CurdSeparatorBlockEntity> CURD_SEPARATOR = makeMultiblock("curd_separator", CurdSeparatorBlockEntity::new, HTFCSBlocks.CURD_SEPARATOR);

    private static <T extends BlockEntity & IEBlockInterfaces.IGeneralMultiblock>
    MultiblockBEType<T> makeMultiblock(String name, MultiblockBEType.BEWithTypeConstructor<T> make, Supplier<? extends Block> block) {
        return new MultiblockBEType<>(name, BLOCK_ENTITIES, make, block, state -> state.hasProperty(IEProperties.MULTIBLOCKSLAVE)&&!state.getValue(IEProperties.MULTIBLOCKSLAVE));
    }

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCK_ENTITIES.register(eventBus);
    }
}
