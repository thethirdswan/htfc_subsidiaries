package com.thethirdswan.htfc_subsidiaries.blocks;

import blusunrize.immersiveengineering.common.blocks.IEMultiblockBlock;
import blusunrize.immersiveengineering.common.blocks.MultiblockBEType;
import blusunrize.immersiveengineering.common.blocks.generic.MultiblockPartBlockEntity;
import blusunrize.immersiveengineering.common.blocks.metal.MetalMultiblockBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class MultiblockBlock<T extends MultiblockPartBlockEntity<T>> extends MetalMultiblockBlock {
    private final MultiblockBEType<T> entityType;
    public MultiblockBlock(MultiblockBEType<T> entityType) {
        super(entityType, BlockBehaviour.Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(3, 15)
                .requiresCorrectToolForDrops()
                .isViewBlocking((state, blockReader, pos) -> false)
                .noOcclusion()
                .dynamicShape());
        this.entityType = entityType;
    }
}
