package me.tibinonest.mods.cauldron_dyeing.mixin;

import me.tibinonest.mods.cauldron_dyeing.CauldronDyeing;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockColors.class)
public class BlockColorsMixin {
    @Inject(method = "create", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void cauldron_dyeing$modifyCreate(CallbackInfoReturnable<BlockColors> cir, BlockColors blockColors) {
        blockColors.registerColorProvider((state, world, pos, tintIndex) -> world != null && pos != null ? CauldronDyeing.getColor(world, pos) : -1, Blocks.WATER_CAULDRON);
    }
}
