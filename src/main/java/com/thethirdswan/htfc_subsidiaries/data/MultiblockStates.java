package com.thethirdswan.htfc_subsidiaries.data;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.api.multiblocks.TemplateMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import com.google.common.base.Preconditions;
import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import com.thethirdswan.htfc_subsidiaries.multiblocks.CurdSeparatorMultiblock;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.client.model.generators.loaders.OBJLoaderBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiblockStates extends ExtendedBlockstateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public final Map<Block, ModelFile> unsplitModels = new HashMap<>();

    public MultiblockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        createMultiblock(innerObj("models/block/curd_separator.obj"), CurdSeparatorMultiblock.INSTANCE);
        curdseparator();
    }

    private void curdseparator() {
        ResourceLocation texture = modLoc("models/block/curd_separator");
        ResourceLocation modelNormal = modLoc("models/block/curd_separator.obj");
        ResourceLocation modelMirrored = modLoc("models/block/curd_separator.obj");
        BlockModelBuilder normal = multiblockModel(HTFCSBlocks.CURD_SEPARATOR.get(), modelNormal, texture, "", CurdSeparatorMultiblock.INSTANCE, false);
        BlockModelBuilder mirrored = multiblockModel(HTFCSBlocks.CURD_SEPARATOR.get(), modelMirrored, texture, "_mirrored", CurdSeparatorMultiblock.INSTANCE, true);

        createMultiblock(HTFCSBlocks.CURD_SEPARATOR, normal, mirrored, texture);
    }

    private void createMultiblock(Supplier<? extends Block> block, ModelFile masterModel, ModelFile mirroredModel, ResourceLocation particleTexture) {
        createMultiblock(block, masterModel, mirroredModel, IEProperties.FACING_HORIZONTAL, IEProperties.MIRRORED);
    }

//    private void createMultiblock(NongeneratedModels.NongeneratedModel unsplitModel, IETemplateMultiblock multiblock) {
//        createMultiblock(unsplitModel, multiblock, false);
//    }
//
//    private void createMultiblock(NongeneratedModels.NongeneratedModel unsplitModel, IETemplateMultiblock multiblock) {
//        createMultiblock(
//                multiblock::getBlock,
//                split(unsplitModel, multiblock),
//                split(mirror(unsplitModel, innerModels), multiblock),
//                IEProperties.FACING_HORIZONTAL, IEProperties.MIRRORED
//        );
//    }
//
    private void createMultiblock(Supplier<? extends Block> block, ModelFile masterModel, @Nullable ModelFile mirroredModel,
                                  EnumProperty<Direction> facing, @Nullable Property<Boolean> mirroredState) {
        unsplitModels.put(block.get(), masterModel);
        Preconditions.checkArgument((mirroredModel == null) == (mirroredState == null));
        VariantBlockStateBuilder builder = getVariantBuilder(block.get());
        boolean[] possibleMirrorStates;
        if (mirroredState != null) {
            possibleMirrorStates = new boolean[]{false, true};
        } else {
            possibleMirrorStates = new boolean[1];
        }
        for (boolean mirror : possibleMirrorStates) {
            for (Direction direction : facing.getPossibleValues()) {
                final int angleY;
                final int angleX;
                if (facing.getPossibleValues().contains(Direction.UP)) {
                    angleX = -90 * direction.getStepY();
                    if (direction.getAxis() != Direction.Axis.Y) {
                        angleY = getAngle(direction, 180);
                    } else {
                        angleY = 0;
                    }
                } else {
                    angleY = getAngle(direction, 180);
                    angleX = 0;
                }

                ModelFile model = mirror ? mirroredModel : masterModel;
                VariantBlockStateBuilder.PartialBlockstate partialState = builder.partialState().with(facing, direction);
                if (mirroredState != null) {
                    partialState = partialState.with(mirroredState, mirror);
                }
                partialState.setModels(new ConfiguredModel(model, angleX, angleY, true));
            }
        }
    }

    private BlockModelBuilder multiblockModel(Block block, ResourceLocation model, ResourceLocation texture, String add, TemplateMultiblock multiblock, boolean mirror) {
        UnaryOperator<BlockPos> transform = UnaryOperator.identity();
        if (mirror) {
            Vec3i size = multiblock.getSize(null);
            transform = p -> new BlockPos(size.getX() - p.getX() - 1, p.getY(), p.getZ());
        }
        final Vec3i offset = multiblock.getMasterFromOriginOffset();

        Stream<Vec3i> partsStream = multiblock.getStructure(null).stream().filter(info -> !info.state.isAir()).map(info -> info.pos).map(transform).map(p -> p.subtract(offset));
        BlockModelBuilder base = this.models().withExistingParent(Objects.requireNonNull(block.getRegistryName()).getPath() + add, mcLoc("block"))
                .customLoader(OBJLoaderBuilder::begin)
                .modelLocation(model)
                .detectCullableFaces(false)
                .flipV(true)
                .end()
                .texture("texture", texture)
                .texture("particle", texture);
        BlockModelBuilder split = this.models().withExistingParent(Objects.requireNonNull(block.getRegistryName()).getPath() + "_split", mcLoc("block"))
                .customLoader(SplitModelBuilder::begin)
                .innerModel(base)
                .parts(partsStream.collect(Collectors.toList()))
                .end();

        return split;
    }

//    private ModelFile split(NongeneratedModels.NongeneratedModel location, TemplateMultiblock mb) {
//        return split(location, mb, false);
//    }
//
//    private ModelFile split(NongeneratedModels.NongeneratedModel location, TemplateMultiblock mb, boolean mirror) {
//        return split(location, mb, mirror, false);
//    }
//
//    private ModelFile split(NongeneratedModels.NongeneratedModel location, TemplateMultiblock mb, boolean mirror, boolean dynamic) {
//        UnaryOperator<BlockPos> transform = UnaryOperator.identity();
//        if (mirror) {
//            Vec3i size = mb.getSize(null);
//            transform = p -> new BlockPos(size.getX()-p.getX(), p.getY(), p.getZ());
//        }
//        return split(location, mb, transform, dynamic);
//    }

//    private ModelFile split(NongeneratedModels.NongeneratedModel name, TemplateMultiblock multiblock, UnaryOperator<BlockPos> transform, boolean dynamic) {
////        LOGGER.info("im gonna kms {}", CurdSeparatorMultiblock.STRUCTURE);
//        final Vec3i offset = multiblock.getMasterFromOriginOffset();
//        Stream<Vec3i> partsStream = multiblock.getStructure(null)
//                .stream()
//                .filter(info -> !info.state.isAir())
//                .map(info -> info.pos)
//                .map(transform)
//                .map(p -> p.subtract((offset)));
//
//        return split(name, partsStream.collect(Collectors.toList()));
//    }
}
