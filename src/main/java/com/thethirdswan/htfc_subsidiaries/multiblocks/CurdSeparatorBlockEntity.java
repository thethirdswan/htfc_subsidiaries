package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.blocks.generic.PoweredMultiblockBlockEntity;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.process.MultiblockProcess;
import blusunrize.immersiveengineering.common.blocks.ticking.IEClientTickableBE;
import blusunrize.immersiveengineering.common.register.IEContainerTypes;
import com.thethirdswan.htfc_subsidiaries.blocks.multiblocks.HTFCSMultiblocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class CurdSeparatorBlockEntity extends PoweredMultiblockBlockEntity<CurdSeparatorBlockEntity, CurdSeparatorRecipe> implements IEBlockInterfaces.IInteractionObjectIE<CurdSeparatorBlockEntity>, IEBlockInterfaces.IBlockBounds, IEClientTickableBE, IEBlockInterfaces.ISoundBE {
    public CurdSeparatorBlockEntity(BlockEntityType<CurdSeparatorBlockEntity> type, BlockPos pos, BlockState state) {
        super(HTFCSMultiblocks.CURD_SEPARATOR, 16000, true, type, pos, state);
    }

    @Override
    protected @Nullable CurdSeparatorRecipe getRecipeForId(Level level, ResourceLocation resourceLocation) {
        return null;
    }

    @Override
    public Set<MultiblockFace> getEnergyPos() {
        return Set.of();
    }

    @Override
    public @Nullable IFluidTank[] getInternalTanks() {
        return new IFluidTank[0];
    }

    @Override
    public @Nullable CurdSeparatorRecipe findRecipeForInsertion(ItemStack itemStack) {
        return null;
    }

    @Override
    public @Nullable int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public @Nullable int[] getOutputTanks() {
        return new int[0];
    }

    @Override
    public boolean additionalCanProcessCheck(MultiblockProcess<CurdSeparatorRecipe> multiblockProcess) {
        return false;
    }

    @Override
    public void doProcessOutput(ItemStack itemStack) {

    }

    @Override
    public void doProcessFluidOutput(FluidStack fluidStack) {

    }

    @Override
    public void onProcessFinish(MultiblockProcess<CurdSeparatorRecipe> multiblockProcess) {

    }

    @Override
    public int getMaxProcessPerTick() {
        return 0;
    }

    @Override
    public int getProcessQueueMaxLength() {
        return 0;
    }

    @Override
    public float getMinProcessDistance(MultiblockProcess<CurdSeparatorRecipe> multiblockProcess) {
        return 0;
    }

    @Override
    public boolean isInWorldProcessingMachine() {
        return false;
    }

    @Override
    public @Nullable NonNullList<ItemStack> getInventory() {
        return null;
    }

    @Override
    public boolean isStackValid(int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public int getSlotLimit(int i) {
        return 0;
    }

    @Override
    public void doGraphicalUpdates() {

    }

    @Override
    public @NotNull VoxelShape getBlockBounds(@Nullable CollisionContext collisionContext) {
        return null;
    }

    @Override
    public @Nullable CurdSeparatorBlockEntity getGuiMaster() {
        return null;
    }

    @Override
    public IEContainerTypes.BEContainer<? super CurdSeparatorBlockEntity, ?> getContainerType() {
        return null;
    }

    @Override
    public boolean canUseGui(Player player) {
        return false;
    }

    @Override
    public boolean shouldPlaySound(String s) {
        return false;
    }

    @Override
    public void tickClient() {

    }
}
