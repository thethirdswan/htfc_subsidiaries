package com.thethirdswan.thethirdswan_subsidiaries.mekanism;

import com.thethirdswan.thethirdswan_subsidiaries.setup.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum MiscOreResource {
    // has no uses for now, was trying to register slurries in a different way
    NICKEL("nickel", ItemTags.NICKEL_INGOT),
    SILVER("silver", ItemTags.SILVER_INGOT),
    ZINC("zinc", ItemTags.ZINC_INGOT),
    ALUMINUM("aluminum", ItemTags.ALUMINUM_INGOT),
    CHROMIUM("chromium", ItemTags.CHROMIUM_INGOT),
    BISMUTH("bismuth", ItemTags.BISMUTH_INGOT),;

    public final String name;
    public final TagKey<Item> tag;

    MiscOreResource(String name, TagKey<Item> ingotTag) {
        this.name = name;
        this.tag = ingotTag;
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
}
