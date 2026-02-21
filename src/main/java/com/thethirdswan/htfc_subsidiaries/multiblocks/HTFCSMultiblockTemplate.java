package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class HTFCSMultiblockTemplate extends IETemplateMultiblock {
    private final Supplier<? extends Block> baseState;
    public HTFCSMultiblockTemplate(ResourceLocation loc, BlockPos masterFromOrigin, BlockPos triggerFromOrigin, BlockPos size, Supplier<? extends Block> baseState) {
        super(loc, masterFromOrigin, triggerFromOrigin, size, new IEBlocks.BlockEntry<Block>(baseState.get()));
        this.baseState = baseState;
    }

    @Override
    public float getManualScale() {
        return 0;
    }

    public Block getBaseBlock() {
        return baseState.get();
    }
}
