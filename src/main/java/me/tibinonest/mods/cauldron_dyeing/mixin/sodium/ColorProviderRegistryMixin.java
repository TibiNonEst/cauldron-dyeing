package me.tibinonest.mods.cauldron_dyeing.mixin.sodium;

import me.jellysquid.mods.sodium.client.model.color.ColorProvider;
import me.jellysquid.mods.sodium.client.model.color.ColorProviderRegistry;
import me.jellysquid.mods.sodium.client.model.color.DefaultColorProviders;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ColorProviderRegistry.class)
public abstract class ColorProviderRegistryMixin {
    @Shadow protected abstract void registerBlocks(ColorProvider<BlockState> resolver, Block... blocks);

    @Redirect(method = "installOverrides", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/model/color/ColorProviderRegistry;registerBlocks(Lme/jellysquid/mods/sodium/client/model/color/ColorProvider;[Lnet/minecraft/block/Block;)V", ordinal = 2))
    private void cauldron_dyeing$modifyOverrides(ColorProviderRegistry instance, ColorProvider<BlockState> resolver, Block... blocks) {
        this.registerBlocks(DefaultColorProviders.WaterColorProvider.BLOCKS, Blocks.WATER, Blocks.BUBBLE_COLUMN);
    }
}
