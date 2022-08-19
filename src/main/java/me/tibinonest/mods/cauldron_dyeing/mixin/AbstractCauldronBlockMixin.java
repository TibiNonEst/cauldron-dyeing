package me.tibinonest.mods.cauldron_dyeing.mixin;

import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin implements BlockEntityProvider {
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof AbstractCauldronBlock cauldron && cauldron.canBeFilledByDripstone(Fluids.WATER)) {
            return new WaterCauldronBlockEntity(pos, state);
        }

        return null;
    }
}
