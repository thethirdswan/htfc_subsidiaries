package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.util.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;

public class HTFCSMultiblockManualData implements ClientMultiblocks.MultiblockManualData {
    private final IETemplateMultiblock multiblock;
    private NonNullList<ItemStack> materials;
    public HTFCSMultiblockManualData(IETemplateMultiblock multiblock) {
        this.multiblock = multiblock;
    }

    @Override
    public NonNullList<ItemStack> getTotalMaterials() {
        if (materials == null) {
            List<StructureTemplate.StructureBlockInfo> structure = multiblock.getStructure(null);
            materials = NonNullList.create();

            for (StructureTemplate.StructureBlockInfo info : structure) {
                if (info.state.hasProperty(IEProperties.MULTIBLOCKSLAVE) && info.state.getValue(IEProperties.MULTIBLOCKSLAVE)) continue;
                ItemStack picked = Utils.getPickBlock(info.state);
                boolean added = false;
                for (ItemStack exists : materials) {
                    if (ItemStack.isSame(exists, picked)) {
                        exists.grow(1);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    materials.add(picked.copy());
                }
            }
        }
        return materials;
    }

    @Override
    public boolean canRenderFormedStructure() {
        return true;
    }

    @Override
    public void renderFormedStructure(PoseStack poseStack, MultiBufferSource multiBufferSource) {
        poseStack.pushPose();
        BlockPos offset = multiblock.getMasterFromOriginOffset();
        poseStack.translate(offset.getX(), offset.getY(), offset.getZ());

        poseStack.popPose();
    }
}
