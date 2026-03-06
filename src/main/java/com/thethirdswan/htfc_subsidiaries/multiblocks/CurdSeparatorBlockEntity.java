package com.thethirdswan.htfc_subsidiaries.multiblocks;

import blusunrize.immersiveengineering.api.utils.shapes.CachedShapesWithTransform;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.blocks.generic.PoweredMultiblockBlockEntity;
import blusunrize.immersiveengineering.common.blocks.multiblocks.process.MultiblockProcess;
import blusunrize.immersiveengineering.common.blocks.multiblocks.process.MultiblockProcessInMachine;
import blusunrize.immersiveengineering.common.blocks.ticking.IEClientTickableBE;
import blusunrize.immersiveengineering.common.util.MultiblockCapability;
import blusunrize.immersiveengineering.common.util.Utils;
import blusunrize.immersiveengineering.common.util.orientation.RelativeBlockFace;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.thethirdswan.htfc_subsidiaries.setup.HTFCSContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CurdSeparatorBlockEntity extends PoweredMultiblockBlockEntity<CurdSeparatorBlockEntity, CurdSeparatorRecipe> implements HTFCSContainerProvider<CurdSeparatorBlockEntity>, IEBlockInterfaces.IBlockBounds, IEClientTickableBE, IEBlockInterfaces.ISoundBE {
    private static final Logger LOGGER = LogUtils.getLogger();
    public final NonNullList<ItemStack> inv = NonNullList.withSize(8, ItemStack.EMPTY);
    public FluidTank[] tanks = new FluidTank[]{
            new FluidTank(24 * FluidAttributes.BUCKET_VOLUME),
    };

    public CurdSeparatorBlockEntity(BlockEntityType<CurdSeparatorBlockEntity> type, BlockPos pos, BlockState state) {
        super(CurdSeparatorMultiblock.INSTANCE, 16000, true, type, pos, state);
    }

    @Override
    protected @Nullable CurdSeparatorRecipe getRecipeForId(Level level, ResourceLocation resourceLocation) {
        return CurdSeparatorRecipe.RECIPES.getById(level, resourceLocation);
    }

    @Override
    public Set<BlockPos> getRedstonePos() {
        return Set.of(new BlockPos(new Vec3i(2, 1, 2)));
    }

    @Override
    public Set<MultiblockFace> getEnergyPos() {
        return Set.of(new MultiblockFace(0, 1, 0, RelativeBlockFace.UP));
    }

    @Override
    public @Nullable IFluidTank[] getInternalTanks() {
        return tanks;
    }

    @Override
    public @Nullable CurdSeparatorRecipe findRecipeForInsertion(ItemStack itemStack) {
        return null;
    }

    private static final MultiblockFace outputOffset = new MultiblockFace(2, 0, 1, RelativeBlockFace.FRONT);
    private static final MultiblockFace inputOffset = new MultiblockFace(2, 1, 0, RelativeBlockFace.UP);

    private final MultiblockCapability<IFluidHandler> curdledInput = MultiblockCapability.make(this, be -> be.curdledInput, CurdSeparatorBlockEntity::master, registerFluidInput(tanks[0]));
//    private final MultiblockCapability<IItemHandler> curdOutput = MultiblockCapability.make(this, be -> be.curdOutput, CurdSeparatorBlockEntity::master, regis)
    private final MultiblockCapability<IFluidHandler> fluidView = MultiblockCapability.make(this, be -> be.fluidView, CurdSeparatorBlockEntity::master, registerFluidView(tanks[0]));

    @Override
    public @NotNull <C> LazyOptional<C> getCapability(@NotNull Capability<C> capability, @Nullable Direction side) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (side == null) {
                return fluidView.getAndCast();
            }
            MultiblockFace relativeFace = asRelativeFace(side);
            if (inputOffset.equals(relativeFace)) {
                return curdledInput.getAndCast();
            }
        }
        return super.getCapability(capability, side);
    }

    @Override
    public @Nullable int[] getOutputSlots() {
        return new int[]{2, 3, 4, 5, 6, 7};
    }

    @Override
    public @Nullable int[] getOutputTanks() {
        return null;
    }

    @Override
    public boolean additionalCanProcessCheck(MultiblockProcess<CurdSeparatorRecipe> multiblockProcess) {
        return false;
    }

    @Override
    public void doProcessOutput(ItemStack itemStack) {
//        itemStack = Utils.insertStackIntoInventory(this.inv, itemStack, false);

    }

    @Override
    public void doProcessFluidOutput(FluidStack fluidStack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onProcessFinish(MultiblockProcess<CurdSeparatorRecipe> multiblockProcess) {

    }

    @Override
    public int getMaxProcessPerTick() {
        return 1;
    }

    @Override
    public int getProcessQueueMaxLength() {
        return 1;
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
        return inv;
    }

    @Override
    public boolean isStackValid(int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public int getSlotLimit(int i) {
        return 32;
    }

    @Override
    public void doGraphicalUpdates() {
        this.setChanged();
        this.markContainingBlockForUpdate(null);
    }

    @Override
    public @Nullable CurdSeparatorBlockEntity getGuiMaster() {
        return master();
    }

    public HTFCSContainerProvider.BEContainerHTFCS<? super CurdSeparatorBlockEntity, ?> getContainerTypeHTFCS() {
        return HTFCSContainers.CURD_SEPARATOR;
    }

    @NotNull
    @Override
    public VoxelShape getBlockBounds(@Nullable CollisionContext collisionContext) {
        return SHAPES.get(this.posInMultiblock, Pair.of(getFacing(), getIsMirrored()));
    }
    public static final AABB FULL = new AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

    // todo add custom shape to multiblock
    private static final CachedShapesWithTransform<BlockPos, Pair<Direction, Boolean>> SHAPES = CachedShapesWithTransform.createForMultiblock(CurdSeparatorBlockEntity::getShape);
    private static List<AABB> getShape(BlockPos posInMultiblock){
        final int x = posInMultiblock.getX();
        final int y = posInMultiblock.getY();
        final int z = posInMultiblock.getZ();

        List<AABB> main = new ArrayList<>();

        // Use default cube shape for now.
        main.add(FULL);
        return main;
    }

    @Override
    public void readCustomNBT(CompoundTag nbt, boolean descPacket) {
        super.readCustomNBT(nbt, descPacket);
        if (!descPacket) {
            tanks[0].readFromNBT(nbt.getCompound("tank0"));
            ContainerHelper.loadAllItems(nbt, inv);
        }
    }

    @Override
    public void writeCustomNBT(CompoundTag nbt, boolean descPacket) {
        super.writeCustomNBT(nbt, descPacket);
        if (!descPacket) {
            nbt.put("tank0", tanks[0].writeToNBT(new CompoundTag()));
            ContainerHelper.saveAllItems(nbt, inv);
        }
    }

    @Override
    public boolean canUseGui(Player player) {
        return formed;
    }

    @Override
    public boolean shouldPlaySound(String s) {
        return false;
    }

    // todo figure out why it's not producing items
    @Override
    public void tickServer() {
        super.tickServer();
        boolean update = false;
        LOGGER.info("the server is ticking, process queue: {}", processQueue.size());
        if (energyStorage.getEnergyStored() > 0 && processQueue.size() < this.getProcessQueueMaxLength()) {
            LOGGER.info("passed first if block");
            if (tanks[0].getFluidAmount() > 0) {
                LOGGER.info("tank fluid amount greater that 0");
                CurdSeparatorRecipe recipe = CurdSeparatorRecipe.findRecipe(level, tanks[0].getFluid());
                LOGGER.info("got recipe: {}", recipe);
                if (recipe != null) {
                    MultiblockProcessInMachine<CurdSeparatorRecipe> process = new MultiblockProcessInMachine<>(recipe, this::getRecipeForId)
                            .setInputTanks(0);
                    if (this.addProcessToQueue(process, true)) {
                        this.addProcessToQueue(process, false);
                        update = true;
                    }
                }
            }
        }

        Direction facing = getFacing();
//        update |= ItemUtils.

        if (update) {
            this.setChanged();
            this.markContainingBlockForUpdate(null);
        }
    }

    @Override
    public void tickClient() {

    }
}
