package me.tibinonest.mods.cauldron_dyeing.mixin;

import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeColors.class)
public class BiomeColorsMixin {
    @Inject(method = "getWaterColor", at = @At("HEAD"), cancellable = true)
    private static void cauldron_dyeing$modifyWaterColor(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        var blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof WaterCauldronBlockEntity waterCauldron && waterCauldron.getColor() != -1) {
            cir.setReturnValue(waterCauldron.getColor());
        }
    }
}
