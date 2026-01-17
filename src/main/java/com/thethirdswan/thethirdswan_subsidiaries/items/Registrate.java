package com.thethirdswan.thethirdswan_subsidiaries.items;

import com.thethirdswan.thethirdswan_subsidiaries.Main;

import com.thethirdswan.thethirdswan_subsidiaries.items.pnc.TemperatureRegulatorUpgrade;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import static me.desht.pneumaticcraft.api.PneumaticRegistry.RL;


public class Registrate {

	public static final DeferredRegister<PNCUpgrade> PNCUPGRADES = DeferredRegister.create(RL("upgrades"), "thethirdswan_subsidiaries");
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			"thethirdswan_subsidiaries");

	public static void init() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(eventBus);
		PNCUPGRADES.register(eventBus);
	}

public static final RegistryObject<PNCUpgrade> TEMPERATURE_REGULATOR = PNCUPGRADES
		.register("temperature_regulator", () ->
				new PNCUpgrade(1));
	public static final RegistryObject<Item> TEMPERATURE_REGULATOR_UPGRADE = ITEMS
			.register("temperature_regulator_upgrade", () -> new TemperatureRegulatorUpgrade(new Item.Properties().tab(Main.ITEM_GROUP), TEMPERATURE_REGULATOR, 1));


	public static final TagKey<Item> PNC_UPGRADES = TagKey.create(Registry.ITEM_REGISTRY,
			new ResourceLocation("thethirdswan_subsidiaries", "pnc_upgrades"));
}