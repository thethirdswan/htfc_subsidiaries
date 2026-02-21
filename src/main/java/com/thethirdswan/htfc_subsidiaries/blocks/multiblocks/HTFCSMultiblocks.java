package com.thethirdswan.htfc_subsidiaries.blocks.multiblocks;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorMultiblock;

public class HTFCSMultiblocks {
//    public static HashMap<String, Multiblock>
    public static IETemplateMultiblock CURD_SEPARATOR;
    public static void init() {
        CURD_SEPARATOR = registerMultiblock(CurdSeparatorMultiblock.INSTANCE);
    }

    private static <T extends MultiblockHandler.IMultiblock> T registerMultiblock(T multiblock) {
        MultiblockHandler.registerMultiblock(multiblock);
        return multiblock;
    }
}
