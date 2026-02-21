package com.thethirdswan.htfc_subsidiaries.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public record IHeatProperty(float heat, int forging, int welding) {}
