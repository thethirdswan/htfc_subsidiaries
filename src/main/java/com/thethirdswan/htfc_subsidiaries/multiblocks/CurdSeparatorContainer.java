package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.common.gui.IEBaseContainer;
import blusunrize.immersiveengineering.common.gui.IESlot;
import blusunrize.immersiveengineering.common.gui.sync.GenericContainerData;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

public class CurdSeparatorContainer extends IEBaseContainer<CurdSeparatorBlockEntity> {
    public CurdSeparatorContainer(MenuType<?> type, int id, Inventory playerInventory, final CurdSeparatorBlockEntity tile) {
        super(type, tile, id);

        this.addSlot(new IESlot.FluidContainer(this, this.inv, 0, 133, 15, 0));
        // todo multiblock slots still messy, attempting to fix later after fixing recipes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.addSlot(new IESlot.Output(this, this.inv, j + i, 80 + i * 18, 40 + j * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 85 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 143));
        }

        addGenericData(GenericContainerData.energy(tile.energyStorage));
        addGenericData(GenericContainerData.fluid(tile.tanks[0]));
    }
}
