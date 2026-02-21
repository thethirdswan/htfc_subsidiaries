package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CurdSeparatorManualData implements ClientMultiblocks.MultiblockManualData {
    private NonNullList<ItemStack> materials;

    @Override
    public NonNullList<ItemStack> getTotalMaterials() {
        materials.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering:steel_scaffolding_standard")), 4));
        materials.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering:light_engineering")), 6));
        materials.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering:sheetmetal_iron")), 8));
        materials.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering:rs_engineering"))));
        return materials;
    }

    @Override
    public boolean canRenderFormedStructure() {
        return true;
    }

    @Override
    public void renderFormedStructure(PoseStack poseStack, MultiBufferSource multiBufferSource) {

    }
}
