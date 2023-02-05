package me.tibinonest.mods.cauldron_dyeing.mixin.sodium;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.blender.ColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.blender.ColorSampler;
import me.jellysquid.mods.sodium.client.model.quad.blender.FlatColorBlender;
import me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockRenderer.class)
public class SodiumBlockRendererMixin {
    @Redirect(method = "renderQuadList", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/model/quad/blender/ColorBlender;getColors(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;Lme/jellysquid/mods/sodium/client/model/quad/ModelQuadView;Lme/jellysquid/mods/sodium/client/model/quad/blender/ColorSampler;Ljava/lang/Object;)[I"))
    private <T> int[] cauldron_dyeing$redirectGetColors(ColorBlender instance, BlockRenderView world, BlockPos pos, ModelQuadView quad, ColorSampler<T> sampler, T state) {
        var blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof WaterCauldronBlockEntity) {
            return new FlatColorBlender().getColors(world, pos, quad, sampler, state);
        }

        return instance.getColors(world, pos, quad, sampler, state);
    }
}
