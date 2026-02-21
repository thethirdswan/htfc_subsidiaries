package com.thethirdswan.htfc_subsidiaries.blocks.multiblocks;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorMultiblock;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSBlocks;
import net.minecraftforge.registries.RegistryObject;

public class HTFCSMultiblocks {
//    public static HashMap<String, Multiblock>
    public static RegistryObject<CurdSeparatorBlock> CURD_SEPARATOR = HTFCSBlocks.registerMultiblockBlock("curd_separator", CurdSeparatorBlock::new);

    public static void forceLoad(){};

    public static void init() {
        MultiblockHandler.registerMultiblock(CurdSeparatorMultiblock.INSTANCE);
    }
}
