package com.thethirdswan.thethirdswan_subsidiaries.setup;

import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import blusunrize.immersiveengineering.api.crafting.builders.ArcFurnaceRecipeBuilder;
import com.mojang.logging.LogUtils;
import com.thethirdswan.thethirdswan_subsidiaries.Registrate;
import com.thethirdswan.thethirdswan_subsidiaries.mekanism.MiscOreResource;
import me.desht.pneumaticcraft.common.PneumaticCraftTags;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidSlurryToSlurryRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import static com.thethirdswan.thethirdswan_subsidiaries.Registrate.*;


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
                .group("thethirdswan_subsidiaries")
                .unlockedBy("upgrades", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .save(consumer);
        buildMekOreProcessingRecipes(consumer);
        buildArcFurnaceRecipes(consumer);
    }

    protected void buildMekOreProcessingRecipes(Consumer<FinishedRecipe> consumer) {
        for (ItemRegistryObject<Item> resultItem : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = dirty_dusts.get(metal_type + "_DUST_DIRTY");
                    ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:dust/" + metal_type.toLowerCase() + "/from_dirty_dust"));
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
                    ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:dirty_dust/" + metal_type.toLowerCase() + "/from_clump"));
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
                    ).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:clump/" + metal_type.toLowerCase() + "/from_shard"));
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
                    ).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:shard/" + metal_type.toLowerCase() + "/from_crystal"));
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
                    ChemicalCrystallizerRecipeBuilder.crystallizing(IngredientCreatorAccess.slurry().from(slurry.getCleanSlurry(), 200), resultItem.getItemStack()).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:crystal/" + metal_type.toLowerCase() + "/from_slurry"));
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
                    ).build(consumer, new ResourceLocation("thethirdswan_subsidiaries:slurry/" + metal_type.toLowerCase() + "/clean_slurry"));
                }
            }
        }
    }

    protected void buildArcFurnaceRecipes(Consumer<FinishedRecipe> consumer) {
//        ArcFurnaceRecipeBuilder.builder(ItemTags.NICKEL_INGOT, 1)
//                .addIngredient("input", Registrate.NICKEL_DUST)
//                .setTime(200)
//                .setEnergy(51200)
//                .build(consumer, new ResourceLocation("thethirdswan_subsidiaries:arc_furnace/nickel"));
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
                            .setTime(200)
                            .setEnergy(51200)
                            .build(consumer, new ResourceLocation("thethirdswan_subsidiaries:arc_furnace/" + metal_type.toLowerCase()));
                }
            }
        }
    }
}