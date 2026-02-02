package com.thethirdswan.thethirdswan_subsidiaries;

import com.mojang.logging.LogUtils;

import com.thethirdswan.thethirdswan_subsidiaries.mekanism.MiscOreResource;
import com.thethirdswan.thethirdswan_subsidiaries.pnc.items.TemperatureRegulatorUpgrade;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.desht.pneumaticcraft.api.PneumaticRegistry.RL;


public class Registrate {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "thethirdswan_subsidiaries");

    public static final DeferredRegister<PNCUpgrade> PNCUPGRADES = DeferredRegister.create(RL("upgrades"), "thethirdswan_subsidiaries");

    public static final ItemDeferredRegister MEKANISM_ITEMS = new ItemDeferredRegister("thethirdswan_subsidiaries");
    public static final SlurryDeferredRegister MEKANISM_SLURRIES = new SlurryDeferredRegister("thethirdswan_subsidiaries");

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        PNCUPGRADES.register(eventBus);
        MEKANISM_ITEMS.register(eventBus);
        MEKANISM_SLURRIES.register(eventBus);
        mekItemInit();
    }

    public static final RegistryObject<PNCUpgrade> TEMPERATURE_REGULATOR = PNCUPGRADES
            .register("temperature_regulator", () ->
                    new PNCUpgrade(1));
    public static final RegistryObject<Item> TEMPERATURE_REGULATOR_UPGRADE = ITEMS
            .register("temperature_regulator_upgrade", () -> new TemperatureRegulatorUpgrade(new Item.Properties().tab(Main.ITEM_GROUP), TEMPERATURE_REGULATOR, 1));

    public static Map<String, ItemRegistryObject<Item>> dusts = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> dirty_dusts = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> clumps = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> shards = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> crystals = new HashMap<>();
    public static Map<String, SlurryRegistryObject<Slurry, Slurry>> slurries = new HashMap<>();

    public static void mekItemInit() {
        for (MiscOreResource resource : MiscOreResource.values()) {
            dusts.put(resource.name() + "_DUST", MEKANISM_ITEMS.register("dust_" + resource.getName()));
            dirty_dusts.put(resource.name() + "_DUST_DIRTY", MEKANISM_ITEMS.register("dust_dirty_" + resource.getName()));
            clumps.put(resource.name() + "_CLUMP", MEKANISM_ITEMS.register("clump_" + resource.getName()));
            shards.put(resource.name() + "_SHARD", MEKANISM_ITEMS.register("shard_" + resource.getName()));
            crystals.put(resource.name() + "_CRYSTAL", MEKANISM_ITEMS.register("crystal_" + resource.getName()));
            slurries.put(resource.name() + "_SLURRY", MEKANISM_SLURRIES.register(resource.getName(), Slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/" + resource.getName() + "_base_color"))));
        }
    }


//    public static final ItemRegistryObject<Item> NICKEL_DUST = MEKANISM_ITEMS.register("dust_nickel");
//    public static final ItemRegistryObject<Item> SILVER_DUST = MEKANISM_ITEMS.register("dust_silver");
//    public static final ItemRegistryObject<Item> ZINC_DUST = MEKANISM_ITEMS.register("dust_zinc");
//    public static final ItemRegistryObject<Item> BISMUTH_DUST = MEKANISM_ITEMS.register("dust_bismuth");
//    public static final ItemRegistryObject<Item> ALUMINIUM_DUST = MEKANISM_ITEMS.register("dust_aluminium");
//    public static final ItemRegistryObject<Item> CHROMIUM_DUST = MEKANISM_ITEMS.register("dust_chromium");
//
//    public static final ItemRegistryObject<Item> NICKEL_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_nickel");
//    public static final ItemRegistryObject<Item> SILVER_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_silver");
//    public static final ItemRegistryObject<Item> ZINC_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_zinc");
//    public static final ItemRegistryObject<Item> BISMUTH_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_bismuth");
//    public static final ItemRegistryObject<Item> ALUMINIUM_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_aluminium");
//    public static final ItemRegistryObject<Item> CHROMIUM_DUST_DIRTY = MEKANISM_ITEMS.register("dust_dirty_chromium");
//
//    public static final ItemRegistryObject<Item> NICKEL_CLUMP = MEKANISM_ITEMS.register("clump_nickel");
//    public static final ItemRegistryObject<Item> SILVER_CLUMP = MEKANISM_ITEMS.register("clump_silver");
//    public static final ItemRegistryObject<Item> ZINC_CLUMP = MEKANISM_ITEMS.register("clump_zinc");
//    public static final ItemRegistryObject<Item> BISMUTH_CLUMP = MEKANISM_ITEMS.register("clump_bismuth");
//    public static final ItemRegistryObject<Item> ALUMINIUM_CLUMP = MEKANISM_ITEMS.register("clump_aluminium");
//    public static final ItemRegistryObject<Item> CHROMIUM_CLUMP = MEKANISM_ITEMS.register("clump_chromium");
//
//    public static final ItemRegistryObject<Item> NICKEL_SHARD = MEKANISM_ITEMS.register("shard_nickel");
//    public static final ItemRegistryObject<Item> SILVER_SHARD = MEKANISM_ITEMS.register("shard_silver");
//    public static final ItemRegistryObject<Item> ZINC_SHARD = MEKANISM_ITEMS.register("shard_zinc");
//    public static final ItemRegistryObject<Item> BISMUTH_SHARD = MEKANISM_ITEMS.register("shard_bismuth");
//    public static final ItemRegistryObject<Item> ALUMINIUM_SHARD = MEKANISM_ITEMS.register("shard_aluminium");
//    public static final ItemRegistryObject<Item> CHROMIUM_SHARD = MEKANISM_ITEMS.register("shard_chromium");
//
//    public static final ItemRegistryObject<Item> NICKEL_CRYSTAL = MEKANISM_ITEMS.register("crystal_nickel");
//    public static final ItemRegistryObject<Item> SILVER_CRYSTAL = MEKANISM_ITEMS.register("crystal_silver");
//    public static final ItemRegistryObject<Item> ZINC_CRYSTAL = MEKANISM_ITEMS.register("crystal_zinc");
//    public static final ItemRegistryObject<Item> BISMUTH_CRYSTAL = MEKANISM_ITEMS.register("crystal_bismuth");
//    public static final ItemRegistryObject<Item> ALUMINIUM_CRYSTAL = MEKANISM_ITEMS.register("crystal_aluminium");
//    public static final ItemRegistryObject<Item> CHROMIUM_CRYSTAL = MEKANISM_ITEMS.register("crystal_chromium");
//
//    public static final SlurryRegistryObject<Slurry, Slurry> NICKEL_SLURRY = MEKANISM_SLURRIES.register("nickel", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/nickel_base_color")));
//    public static final SlurryRegistryObject<Slurry, Slurry> SILVER_SLURRY = MEKANISM_SLURRIES.register("silver", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/silver_base_color")));
//    public static final SlurryRegistryObject<Slurry, Slurry> ZINC_SLURRY = MEKANISM_SLURRIES.register("zinc", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/zinc_base_color")));
//    public static final SlurryRegistryObject<Slurry, Slurry> BISMUTH_SLURRY = MEKANISM_SLURRIES.register("bismuth", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/bismuth_base_color")));
//    public static final SlurryRegistryObject<Slurry, Slurry> ALUMINIUM_SLURRY = MEKANISM_SLURRIES.register("aluminium", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/aluminium_base_color")));
//    public static final SlurryRegistryObject<Slurry, Slurry> CHROMIUM_SLURRY = MEKANISM_SLURRIES.register("chromium", slurry -> SlurryBuilder.builder(new ResourceLocation("thethirdswan_subsidiaries", "slurry/chromium_base_color")));
}