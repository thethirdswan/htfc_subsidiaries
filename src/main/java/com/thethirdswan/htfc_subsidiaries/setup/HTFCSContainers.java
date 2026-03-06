package com.thethirdswan.htfc_subsidiaries.setup;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.common.gui.IEBaseContainer;
import blusunrize.immersiveengineering.common.register.IEContainerTypes;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorBlockEntity;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorContainer;
import com.thethirdswan.htfc_subsidiaries.multiblocks.HTFCSContainerProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.function.Supplier;

public class HTFCSContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, "htfc_subsidiaries");

    public static final HTFCSContainerProvider.BEContainerHTFCS<CurdSeparatorBlockEntity, CurdSeparatorContainer> CURD_SEPARATOR = register("curd_separator", CurdSeparatorContainer::new);

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> factory) {
        return CONTAINER.register(name, factory);
    }

    public static <T extends BlockEntity, C extends IEBaseContainer<? super T>> HTFCSContainerProvider.BEContainerHTFCS<T, C> register(String name, IEContainerTypes.BEContainerConstructor<T, C> container) {
        RegistryObject<MenuType<C>> typeRef = registerMenu(name, () -> {
            Mutable<MenuType<C>> typeBox = new MutableObject<>();
            MenuType<C> type = new MenuType<>((IContainerFactory<C>) (windowId, inv, data) -> {
                Level world = ImmersiveEngineering.proxy.getClientWorld();
                BlockPos pos = data.readBlockPos();
                BlockEntity blockEntity = world.getBlockEntity(pos);
                return container.construct(typeBox.getValue(), windowId, inv, (T) blockEntity);
            });
            typeBox.setValue(type);
            return type;
        });
        return new HTFCSContainerProvider.BEContainerHTFCS<>(typeRef, container);
    }

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CONTAINER.register(bus);
    }
}
