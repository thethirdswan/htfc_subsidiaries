package com.thethirdswan.htfc_subsidiaries.data;

import blusunrize.immersiveengineering.client.models.split.SplitModelLoader;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.loaders.OBJLoaderBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendedBlockstateProvider extends BlockStateProvider {

    protected static final Map<ResourceLocation, String> generatedParticleTextures = new HashMap<>();
    protected final NongeneratedModels innerModels;
    protected final ExistingFileHelper existingFileHelper;

    public ExtendedBlockstateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, "htfc_subsidiaries", exFileHelper);
        this.existingFileHelper = exFileHelper;
        this.innerModels = new NongeneratedModels(gen, exFileHelper);
    }

    protected int getAngle(Direction direction, int offset) {
        return (int)((direction.toYRot() + offset) % 360);
    }

//    protected BlockModelBuilder splitModel(String name, NongeneratedModels.NongeneratedModel model, List<Vec3i> parts) {
//        BlockModelBuilder result = models().withExistingParent(name, mcLoc("block"))
//                .customLoader(SplitModelBuilder::begin)
//                .innerModel(model)
//                .parts(parts)
//                .end();
//        addParticleTextureFrom(result, model);
//        return result;
//
//    }

    protected NongeneratedModels.NongeneratedModel innerObj(String loc) {
        Preconditions.checkArgument(loc.endsWith(".obj"));
        return obj(loc.substring(0, loc.length() - 4), modLoc(loc), innerModels);
    }

    protected <T extends ModelBuilder<T>> T obj(String name, ResourceLocation model, ModelProvider<T> provider) {
        return obj(name, model, ImmutableMap.of(), provider);
    }

    protected <T extends ModelBuilder<T>> T obj(String name, ResourceLocation model, Map<String, ResourceLocation> textures, ModelProvider<T> provider) {
        return obj(provider.withExistingParent(name, mcLoc("block")), model, textures);
    }

    protected <T extends ModelBuilder<T>> T obj(T base, ResourceLocation model, Map<String, ResourceLocation> textures) {
        T ret = base
                .customLoader(OBJLoaderBuilder::begin)
                .detectCullableFaces(false)
                .modelLocation(model)
                .flipV(true)
                .end();

        for (Map.Entry<String, ResourceLocation> entry : textures.entrySet()) {
            ret.texture(entry.getKey(), entry.getValue());
        }
            return ret;
    }

    protected <T extends ModelBuilder<T>> T mirror(NongeneratedModels.NongeneratedModel inner, ModelProvider<T> provider) {
        return provider.getBuilder(inner.getLocation().getPath() + "_mirrored")
                .customLoader(MirroredModelBuilder::begin)
                .inner(inner)
                .end();
    }

    protected void addParticleTextureFrom(BlockModelBuilder result, ModelFile model) {
        String particles = generatedParticleTextures.get(model.getLocation());
        if (particles != null) {
            result.texture("particle", particles);
            generatedParticleTextures.put(result.getLocation(), particles);
        }
    }

//    protected ModelFile split(NongeneratedModels.NongeneratedModel baseModel, List<Vec3i> parts) {
//        return splitModel(baseModel.getLocation().getPath() + "_split", baseModel, parts);
//    }

    @Override
    protected void registerStatesAndModels() {

    }
}
