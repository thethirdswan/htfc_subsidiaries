package com.thethirdswan.htfc_subsidiaries.client.screen;

import blusunrize.immersiveengineering.client.gui.IEContainerScreen;
import blusunrize.immersiveengineering.client.gui.info.EnergyInfoArea;
import blusunrize.immersiveengineering.client.gui.info.FluidInfoArea;
import blusunrize.immersiveengineering.client.gui.info.InfoArea;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorBlockEntity;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorContainer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CurdSeparatorScreen extends IEContainerScreen<CurdSeparatorContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("htfc_subsidiaries:textures/gui/curd_separator.png");
    public CurdSeparatorBlockEntity tile;
    public CurdSeparatorScreen(CurdSeparatorContainer container, Inventory inv, Component title) {
        super(container, inv, title, TEXTURE);
        this.tile = container.tile;
    }

    @Override
    protected @NotNull List<InfoArea> makeInfoAreas() {
        return List.of(
                new FluidInfoArea(tile.tanks[0], new Rect2i(leftPos + 13, topPos + 20, 16, 47), 177, 31, 20, 51, TEXTURE),
                new EnergyInfoArea(leftPos + 157, topPos + 21, tile.energyStorage));
    }
// not yet
//    @Override
//    protected void drawContainerBackgroundPre(@NotNull PoseStack matrixStack, float partialTicks, int x, int y) {
//        super.drawContainerBackgroundPre(matrixStack, partialTicks, x, y);
//    }
}
