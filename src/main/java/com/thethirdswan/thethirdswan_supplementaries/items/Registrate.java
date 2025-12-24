package com.thethirdswan.thethirdswan_supplementaries.items;

import com.thethirdswan.thethirdswan_supplementaries.Main;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

public class Registrate {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			"thethirdswan_supplementaries");

	public static void init() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(eventBus);
	}

	public static final RegistryObject<Item> TEMPERATURE_REGULATOR_UPGRADE = ITEMS
			.register("TEMPERATURE_REGULATOR_UPGRADE", () -> new Item(new Item.Properties().tab(Main.ITEM_GROUP)));

	public static final TagKey<Item> PNC_UPGRADES = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("thethirdswan_supplementaries", "pnc_upgrades"));
}