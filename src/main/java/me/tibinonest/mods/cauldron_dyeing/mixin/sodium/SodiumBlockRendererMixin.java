package me.tibinonest.mods.cauldron_dyeing.mixin.sodium;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadColorProvider;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.blender.BiomeColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.blender.FlatBiomeColorBlender;
import me.jellysquid.mods.sodium.client.render.pipeline.BlockRenderer;
import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockRenderer.class)
public class SodiumBlockRendererMixin {
    @Redirect(method = "renderQuad", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/model/quad/blender/BiomeColorBlender;getColors(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;Lme/jellysquid/mods/sodium/client/model/quad/ModelQuadView;Lme/jellysquid/mods/sodium/client/model/quad/ModelQuadColorProvider;Ljava/lang/Object;)[I"))
    private <T> int[] redirectGetColors(BiomeColorBlender instance, BlockRenderView world, BlockPos pos, ModelQuadView quad, ModelQuadColorProvider<T> handler, T fluidState) {
        var blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof WaterCauldronBlockEntity) {
            var flatBiomeColorBlender = new FlatBiomeColorBlender();
            return flatBiomeColorBlender.getColors(world, pos, quad, handler, fluidState);
        }

        return instance.getColors(world, pos, quad, handler, fluidState);
    }
}
