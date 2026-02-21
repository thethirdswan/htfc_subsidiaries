package com.thethirdswan.htfc_subsidiaries.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NongeneratedModels extends ModelProvider<NongeneratedModels.NongeneratedModel> {
    public NongeneratedModels(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, "htfc_subsidiaries", "block", NongeneratedModel::new, exFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    @Override
    public String getName() {
        return "HTFC Subsidiaries Non-generated Models";
    }


    public static class NongeneratedModel extends ModelBuilder<NongeneratedModel> {
        protected NongeneratedModel(ResourceLocation out, ExistingFileHelper exFileHelper) {
            super(out, exFileHelper);
        }
    }
}
