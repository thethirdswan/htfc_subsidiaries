package com.thethirdswan.htfc_subsidiaries.setup;

import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import blusunrize.immersiveengineering.api.IETags;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.api.crafting.builders.ArcFurnaceRecipeBuilder;
import blusunrize.immersiveengineering.api.crafting.builders.MixerRecipeBuilder;
import blusunrize.immersiveengineering.api.crafting.builders.RefineryRecipeBuilder;
import com.eerussianguy.firmalife.common.items.FLItems;
import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.Registrate;
import com.thethirdswan.htfc_subsidiaries.tfcrecipes.Heating;
import com.thethirdswan.htfc_subsidiaries.tfcrecipes.SpindleHeadAnvil;
import com.thethirdswan.htfc_subsidiaries.tfcrecipes.SpindleWithForgeBonus;
import com.thethirdswan.htfc_subsidiaries.tfcrecipes.YarnsAndSpindles;
import com.thethirdswan.htfc_subsidiaries.mekanism.MiscOreResource;
import me.desht.pneumaticcraft.common.PneumaticCraftTags;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.shared.TinkerMaterials;

import static blusunrize.immersiveengineering.api.utils.TagUtils.createItemWrapper;
import static com.thethirdswan.htfc_subsidiaries.Registrate.*;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    // pattern to obtain metal type
    final Pattern pattern = Pattern.compile("^[A-Za-z]+");
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registrate.TEMPERATURE_REGULATOR_UPGRADE.get())
                .pattern("cpc")
                .pattern("vav")
                .pattern("cpc")
                .define('p', PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON)
                .define('c', PneumaticCraftTags.Items.UPGRADE_COMPONENTS)
                .define('v', new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("pneumaticcraft", "vortex_tube"))).getItem())
                .define('a', new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("compressedcreativity", "industrial_air_blower"))).getItem())
                .group("htfc_subsidiaries")
                .unlockedBy("upgrades", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .save(consumer);

        subsidiariesRecipes(consumer);
        buildMekOreProcessingRecipes(consumer);
        buildArcFurnaceRecipes(consumer);
        buildIndustrialCheeseRecipes(consumer);
    }

    protected void subsidiariesRecipes(Consumer<FinishedRecipe> consumer) {
        Recipe<CraftingContainer> wool_yarn_recipe = new ShapelessRecipe(new ResourceLocation("tfc", "wool_yarn"), "htfc_subsidiaries", new ItemStack(TFCItems.WOOL_YARN.get(), 8), NonNullList.of(Ingredient.of(ItemTags.SPINDLES), Ingredient.of(TFCItems.WOOL.get())));
        Recipe<CraftingContainer> pineapple_yarn_recipe = new ShapelessRecipe(new ResourceLocation("firmalife", "pineapple_yarn"), "htfc_subsidiaries", new ItemStack(FLItems.PINEAPPLE_YARN.get(), 8), NonNullList.of(Ingredient.of(ItemTags.SPINDLES), Ingredient.of(FLItems.PINEAPPLE_FIBER.get())));

        FinishedRecipe f_wool_yarn_recipe = new YarnsAndSpindles(new ResourceLocation("htfc_subsidiaries:wool_yarn"), wool_yarn_recipe);
        FinishedRecipe f_pineapple_yarn_recipe = new YarnsAndSpindles(new ResourceLocation("htfc_subsidiaries:pineapple_yarn"), pineapple_yarn_recipe);
        FinishedRecipe spindle_head_recipe = new SpindleHeadAnvil(new ResourceLocation("htfc_subsidiaries:wrought_iron_spindle_head"));
        FinishedRecipe spindle_recipe = new SpindleWithForgeBonus(new ResourceLocation("htfc_subsidiaries:wrought_iron_spindle"));

        consumer.accept(f_wool_yarn_recipe);
        consumer.accept(f_pineapple_yarn_recipe);
        consumer.accept(spindle_head_recipe);
        consumer.accept(spindle_recipe);
        consumer.accept(new Heating(new ResourceLocation("htfc_subsidiaries:heating/wrought_iron_spindle_head"), "htfc_subsidiaries:wrought_iron_spindle_head", 1535, "tfc:metal/cast_iron"));
        consumer.accept(new Heating(new ResourceLocation("htfc_subsidiaries:heating/wrought_iron_spindle"), "htfc_subsidiaries:wrought_iron_spindle", 1535, "tfc:metal/cast_iron"));
    }

    protected void buildMekOreProcessingRecipes(Consumer<FinishedRecipe> consumer) {
        // general ore processing
        for (ItemRegistryObject<Item> resultItem : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = dirty_dusts.get(metal_type + "_DUST_DIRTY");
                    ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, new ResourceLocation("htfc_subsidiaries:dust/" + metal_type.toLowerCase() + "/from_dirty_dust"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : dirty_dusts.values()) {
            Set<String> keySet = dirty_dusts.keySet();
            for (String key : keySet) {
                if (dirty_dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = clumps.get(metal_type + "_CLUMP");
                    ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, new ResourceLocation("htfc_subsidiaries:dirty_dust/" + metal_type.toLowerCase() + "/from_clump"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : clumps.values()) {
            Set<String> keySet = clumps.keySet();
            for (String key : keySet) {
                if (clumps.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = shards.get(metal_type + "_SHARD");
                    ItemStackChemicalToItemStackRecipeBuilder.purifying(
                            IngredientCreatorAccess.item().from(inputItem),
                            IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN, 1),
                            resultItem.getItemStack()
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:clump/" + metal_type.toLowerCase() + "/from_shard"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : shards.values()) {
            Set<String> keySet = shards.keySet();
            for (String key : keySet) {
                if (shards.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = crystals.get(metal_type + "_CRYSTAL");
                    ItemStackChemicalToItemStackRecipeBuilder.injecting(
                            IngredientCreatorAccess.item().from(inputItem),
                            IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE, 1),
                            resultItem.getItemStack()
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:shard/" + metal_type.toLowerCase() + "/from_crystal"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : crystals.values()) {
            Set<String> keySet = crystals.keySet();
            for (String key : keySet) {
                if (crystals.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    SlurryRegistryObject<Slurry, Slurry> slurry = slurries.get(metal_type + "_SLURRY");
                    ChemicalCrystallizerRecipeBuilder.crystallizing(IngredientCreatorAccess.slurry().from(slurry.getCleanSlurry(), 200), resultItem.getItemStack()).build(consumer, new ResourceLocation("htfc_subsidiaries:crystal/" + metal_type.toLowerCase() + "/from_slurry"));
                }
            }
        }
        for (SlurryRegistryObject<Slurry, Slurry> slurry : slurries.values()) {
            Set<String> keySet = slurries.keySet();
            for (String key : keySet) {
                if (slurries.get(key) == slurry) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    FluidSlurryToSlurryRecipeBuilder.washing(
                            IngredientCreatorAccess.fluid().from(FluidTags.WATER, 5),
                            IngredientCreatorAccess.slurry().from(slurry.getDirtySlurry(), 1),
                            slurry.getCleanSlurry().getStack(1)
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:slurry/" + metal_type.toLowerCase() + "/clean_slurry"));
                }
            }
        }
        // from ore chunks
        // clumps
        for (ItemRegistryObject<Item> resultItem : clumps.values()) {
            Set<String> keySet = clumps.keySet();
            for (String key : keySet) {
                if (clumps.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ItemStackChemicalToItemStackRecipeBuilder.purifying(
                            IngredientCreatorAccess.item().from(inputItem, 10),
                            IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN, 1),
                            resultItem.getItemStack()
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:clump/" + metal_type.toLowerCase() + "/from_chunks"));
                }
            }
        }
        // shards
        for (ItemRegistryObject<Item> resultItem : shards.values()) {
            Set<String> keySet = shards.keySet();
            for (String key : keySet) {
                if (shards.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ItemStackChemicalToItemStackRecipeBuilder.injecting(
                            IngredientCreatorAccess.item().from(inputItem, 5),
                            IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE, 1),
                            resultItem.getItemStack()
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:shard/" + metal_type.toLowerCase() + "/from_chunks"));
                }
            }
        }
        // dirty slurry
        for (SlurryRegistryObject<Slurry, Slurry> slurry : slurries.values()) {
            Set<String> keySet = slurries.keySet();
            for (String key : keySet) {
                if (slurries.get(key) == slurry) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ChemicalDissolutionRecipeBuilder.dissolution(
                            IngredientCreatorAccess.item().from(inputItem, 20),
                            IngredientCreatorAccess.gas().from(MekanismGases.SULFURIC_ACID, 1),
                            slurry.getDirtySlurry().getStack(1_000)
                    ).build(consumer, new ResourceLocation("htfc_subsidiaries:slurry/" + metal_type.toLowerCase() + "/dirty_slurry"));
                }
            }
        }
    }

    protected void buildArcFurnaceRecipes(Consumer<FinishedRecipe> consumer) {
        for (ItemRegistryObject<Item> resultItem : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    TagKey<Item> metal_ingot = MiscOreResource.getItemTagFromName(metal_type.toLowerCase());
                    ArcFurnaceRecipeBuilder.builder(metal_ingot, 1)
                            .addIngredient("input", resultItem)
                            .setTime(100)
                            .setEnergy(51200)
                            .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/" + metal_type.toLowerCase()));
                }
            }
        }
        // TC ingots (except pig iron)
        ArcFurnaceRecipeBuilder.builder(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/hepatizon")), 2)
                .addIngredient("input", new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/copper")), 2))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "storage_blocks/quartz"))))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, IETags.getIngot("cobalt"))))
                .setTime(100)
                .setEnergy(51200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/hepatizon"));
        ArcFurnaceRecipeBuilder.builder(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/slimesteel")), 2)
                .addIngredient("input", new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/wrought_iron")), 2))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "slimeball/sky"))))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("tconstruct", "seared_brick"))))
                .setTime(100)
                .setEnergy(51200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/slimesteel"));
        ArcFurnaceRecipeBuilder.builder(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/amethyst_bronze")), 2)
                .addIngredient("input", new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/copper"))))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "gems/amethyst"))))
                .setTime(100)
                .setEnergy(51200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/amethyst_bronze"));
        ArcFurnaceRecipeBuilder.builder(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/queens_slime")), 2)
                .addIngredient("input", new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, IETags.getIngot("cobalt"))))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/gold"))))
                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", "magma_cream"))))
                .setTime(100)
                .setEnergy(51200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/queens_slime"));
//        ArcFurnaceRecipeBuilder.builder(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/hepatizon")), 2)
//                .addIngredient("input", new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "ingots/copper")), 2))
//                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "storage_blocks/quartz"))))
//                .addInput(new IngredientWithSize(new TagKey<Item>(Registry.ITEM_REGISTRY, IETags.getIngot("cobalt"))))
//                .setTime(100)
//                .setEnergy(51200)
//                .build(consumer, new ResourceLocation("htfc_subsidiaries:arc_furnace/hepatizon"));
    }

    protected void buildIndustrialCheeseRecipes(Consumer<FinishedRecipe> consumer) {
        // curdled milk
        MixerRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("tfc:curdled_milk")).defaultFluidState().getType(), 2000))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("minecraft:milk")).defaultFluidState().getType(), 2000))
                .addInput(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("firmalife", "rennet"))).getItem())
                .setEnergy(3200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "mixing/curdled_milk"));
        MixerRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:curdled_goat_milk")).defaultFluidState().getType(), 2000))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:goat_milk")).defaultFluidState().getType(), 2000))
                .addInput(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("firmalife", "rennet"))).getItem())
                .setEnergy(3200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "mixing/curdled_goat_milk"));
        MixerRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:curdled_yak_milk")).defaultFluidState().getType(), 2000))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:yak_milk")).defaultFluidState().getType(), 2000))
                .addInput(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("firmalife", "rennet"))).getItem())
                .setEnergy(3200)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "mixing/curdled_yak_milk"));

        // cream
        RefineryRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:cream")).defaultFluidState().getType(), 100))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:yak_milk")).defaultFluidState().getType(), 100))
                .addCatalyst(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("firmalife", "cheesecloth")))
                .setEnergy(100)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "refinery/cream/yak_milk"));
        RefineryRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:cream")).defaultFluidState().getType(), 100))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("minecraft:milk")).defaultFluidState().getType(), 100))
                .addCatalyst(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("firmalife", "cheesecloth")))
                .setEnergy(100)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "refinery/cream/milk"));
        RefineryRecipeBuilder.builder(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:cream")).defaultFluidState().getType(), 100))
                .addFluid(new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("firmalife:goat_milk")).defaultFluidState().getType(), 100))
                .addCatalyst(new TagKey<Item>(Registry.ITEM_REGISTRY, new ResourceLocation("firmalife", "cheesecloth")))
                .setEnergy(100)
                .build(consumer, new ResourceLocation("htfc_subsidiaries", "refinery/cream/goat_milk"));
    }
}