package com.thethirdswan.htfc_subsidiaries.data;

import blusunrize.immersiveengineering.client.models.mirror.MirroredModelLoader;
import blusunrize.immersiveengineering.data.models.NongeneratedModels;
import com.google.gson.JsonObject;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MirroredModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
    public static <T extends ModelBuilder<T>> MirroredModelBuilder<T> begin(T parent, ExistingFileHelper existingFileHelper) {
        return new MirroredModelBuilder<>(parent, existingFileHelper);
    }

    protected MirroredModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(MirroredModelLoader.ID, parent, existingFileHelper);
    }

    private NongeneratedModels.NongeneratedModel inner;

    public MirroredModelBuilder<T> inner(NongeneratedModels.NongeneratedModel inner) {
        this.inner = inner;
        return this;
    }

    @Override
    public JsonObject toJson(JsonObject json) {
        JsonObject result = super.toJson(json);
        result.add(MirroredModelLoader.INNER_MODEL, inner.toJson());
        return result;
    }
}
