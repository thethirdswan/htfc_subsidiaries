package com.thethirdswan.htfc_subsidiaries.mekanism;

import com.thethirdswan.htfc_subsidiaries.setup.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tfcorewashing.init.TfcorewashingModItems;

public enum MiscOreResource {
    // has no uses for now, was trying to register slurries in a different way
    NICKEL("nickel", ItemTags.NICKEL_INGOT, TfcorewashingModItems.CHUNKS_GARNIERITE),
    SILVER("silver", ItemTags.SILVER_INGOT, TfcorewashingModItems.CHUNKS_SILVER),
    ZINC("zinc", ItemTags.ZINC_INGOT, TfcorewashingModItems.CHUNKS_SPHALERITE),
    ALUMINUM("aluminum", ItemTags.ALUMINUM_INGOT, TfcorewashingModItems.CHUNKS_BAUXITE),
    CHROMIUM("chromium", ItemTags.CHROMIUM_INGOT, TfcorewashingModItems.CHUNKS_CHROMITE),
    BISMUTH("bismuth", ItemTags.BISMUTH_INGOT, TfcorewashingModItems.CHUNKS_BISMUTHINITE),;

    public final String name;
    public final TagKey<Item> tag;
    public final RegistryObject<Item> chunkItem;

    MiscOreResource(String name, TagKey<Item> ingotTag, RegistryObject<Item> chunkItem) {
        this.name = name;
        this.tag = ingotTag;
        this.chunkItem = chunkItem;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public static @Nullable TagKey<Item> getItemTagFromName(String name) {
        for (MiscOreResource item : MiscOreResource.values()) {
            if (item.name.equals(name)) {
                return item.tag;
            }
        }
        return null;
    }

    public static Item getChunkItemFromName(String name) {
        for (MiscOreResource item : MiscOreResource.values()) {
            if (item.name.equals(name)) {
                return item.chunkItem.get();
            }
        }
        return null;
    }
}
