package me.tibinonest.mods.cauldron_dyeing.mixin;

import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", ordinal = 271))
    private static Block redirectWaterCauldron(String id, Block block) {
        return Registry.register(Registry.BLOCK, id, new WaterCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON), WaterCauldronBlock.RAIN_PREDICATE, CauldronBehavior.WATER_CAULDRON_BEHAVIOR));
    }
}
