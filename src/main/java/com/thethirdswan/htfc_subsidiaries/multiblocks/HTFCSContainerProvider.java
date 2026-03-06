package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.gui.IEBaseContainer;
import blusunrize.immersiveengineering.common.register.IEContainerTypes;
import com.google.common.base.Preconditions;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public interface HTFCSContainerProvider<T extends BlockEntity & HTFCSContainerProvider<T>> extends IEBlockInterfaces.IInteractionObjectIE<T> {
    default IEContainerTypes.BEContainer<? super T, ?> getContainerType() {
        return null;
    }

    @Nonnull
    BEContainerHTFCS<? super T, ?> getContainerTypeHTFCS();

    @Nonnull
    @Override
    default AbstractContainerMenu createMenu(int id, @Nonnull Inventory playerInventory, @Nonnull Player player) {
        T master = getGuiMaster();
        Preconditions.checkNotNull(master);
        BEContainerHTFCS<? super T, ?> container = getContainerTypeHTFCS();
        return container.create(id, playerInventory, master);
    }

    record BEContainerHTFCS<T extends BlockEntity, C extends IEBaseContainer<? super T>>(RegistryObject<MenuType<C>> type, IEContainerTypes.BEContainerConstructor<T, C> factory) {
        public C create(int windowId, Inventory playerInventory, T tile) {
            return factory.construct(getType(), windowId, playerInventory, tile);
        }

        public MenuType<C> getType() {
            return type.get();
        }
    }
}
