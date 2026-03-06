package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class CurdSeparatorMultiblock extends HTFCSMultiblockTemplate {
    public static final CurdSeparatorMultiblock INSTANCE = new CurdSeparatorMultiblock();

    public CurdSeparatorMultiblock() {
        super(new ResourceLocation("htfc_subsidiaries", "multiblocks/curd_separator"),
                new BlockPos(1, 0, 2),
                new BlockPos(1, 1, 1),
                new BlockPos(3, 2, 3),
                HTFCSMultiblocks.CURD_SEPARATOR);
    }

    @Override
    public float getManualScale() {
        return 12;
    }

    @Override
    public void initializeClient(Consumer<ClientMultiblocks.MultiblockManualData> consumer) {
        consumer.accept(new HTFCSMultiblockManualData(INSTANCE));
    }
}
