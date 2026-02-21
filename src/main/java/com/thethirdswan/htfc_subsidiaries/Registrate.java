package com.thethirdswan.htfc_subsidiaries;

import com.mojang.logging.LogUtils;

import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import com.thethirdswan.htfc_subsidiaries.mekanism.MiscOreResource;
import com.thethirdswan.htfc_subsidiaries.pnc.items.TemperatureRegulatorUpgrade;
import com.thethirdswan.htfc_subsidiaries.setup.BlockEntities;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSBlocks;
import me.desht.pneumaticcraft.api.item.PNCUpgrade;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.thethirdswan.htfc_subsidiaries.Main.ITEM_GROUP;
import static me.desht.pneumaticcraft.api.PneumaticRegistry.RL;


public class Registrate {
    private static final Logger LOGGER = LogUtils.getLogger();
//    public static final DeferredRegister<IHeatRegistry> ITEM_HEAT = DeferredRegister.create(new ResourceLocation("htfc_subsidiaries", "item_heats"), "htfc_subsidiaries");
//    public static final Supplier<IForgeRegistry<IHeatRegistry>> ITEM_HEAT_REGISTRY = ITEM_HEAT.makeRegistry(IHeatRegistry.class, RegistryBuilder::new);

//    public static final DeferredRegister<IHeatSerializer> ITEM_HEAT_SERIALIZER = DeferredRegister.create(new ResourceLocation("htfc_subsidiaries", "item_heat_serializer"), "htfc_subsidiaries");
//    public static final RegistryObject<IHeatSerializer> I_HEAT_SERIALIZER = ITEM_HEAT_SERIALIZER.register("item_heat", IHeatSerializer::new);
//    public static final RegistryBuilder<IHeatProperty> iHeatBuilder = new RegistryBuilder<IHeatProperty>().dataPackRegistry(IHeatSerializer.CODEC);



    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "htfc_subsidiaries");

    public static final DeferredRegister<PNCUpgrade> PNCUPGRADES = DeferredRegister.create(RL("upgrades"), "htfc_subsidiaries");

    public static final ItemDeferredRegister MEKANISM_ITEMS = new ItemDeferredRegister("htfc_subsidiaries");
    public static final SlurryDeferredRegister MEKANISM_SLURRIES = new SlurryDeferredRegister("htfc_subsidiaries");

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        PNCUPGRADES.register(eventBus);
        MEKANISM_ITEMS.register(eventBus);
        MEKANISM_SLURRIES.register(eventBus);
//        ITEM_HEAT.register(eventBus);
        mekItemInit();
        BlockEntities.BLOCK_ENTITIES.register(eventBus);
    }
    public static final RegistryObject<Item> WROUGHT_IRON_SPINDLE = ITEMS.register("wrought_iron_spindle", () -> new Item(new Item.Properties().tab(ITEM_GROUP).defaultDurability(2200)));
    public static final RegistryObject<Item> WROUGHT_IRON_SPINDLE_HEAD = ITEMS.register("wrought_iron_spindle_head", () -> new Item(new Item.Properties().tab(ITEM_GROUP)));

//    public static final RegistryObject<IHeatRegistry> TEST = ITEM_HEAT.register("spindle_head_heating", () -> new IHeatRegistry());


    public static final RegistryObject<PNCUpgrade> TEMPERATURE_REGULATOR = PNCUPGRADES
            .register("temperature_regulator", () ->
                    new PNCUpgrade(1));
    public static final RegistryObject<Item> TEMPERATURE_REGULATOR_UPGRADE = ITEMS
            .register("temperature_regulator_upgrade", () -> new TemperatureRegulatorUpgrade(new Item.Properties().tab(ITEM_GROUP), TEMPERATURE_REGULATOR, 1));

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
            slurries.put(resource.name() + "_SLURRY", MEKANISM_SLURRIES.register(resource.getName(), Slurry -> SlurryBuilder.builder(new ResourceLocation("htfc_subsidiaries", "slurry/" + resource.getName() + "_base_color"))));
        }
    }
}