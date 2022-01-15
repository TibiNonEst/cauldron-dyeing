package me.tibinonest.mods.cauldron_dyeing.mixin;

import me.tibinonest.mods.cauldron_dyeing.block.CauldronBehaviorExtended;
import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {
    @Inject(method = "method_32209", at = @At("HEAD"), cancellable = true)
    private static void injectCleanDyeableItem(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        var blockEntity = world.getBlockEntity(pos);
        var item = stack.getItem();

        if (item instanceof DyeableItem dyeableItem && blockEntity instanceof WaterCauldronBlockEntity waterCauldron && waterCauldron.getColor() != -1) {
            dyeableItem.setColor(stack, waterCauldron.getColor());
            if (!world.isClient) {
                player.incrementStat(Stats.USE_CAULDRON);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            }

            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }

    @Inject(method = "method_32217", at = @At(value = "TAIL"))
    private static void injectFillWithWater(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {
        var blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof WaterCauldronBlockEntity waterCauldron) {
            waterCauldron.resetColor();
        }
    }

    @Inject(method = "registerBehavior", at = @At("TAIL"))
    private static void injectRegisterBehavior(CallbackInfo ci) {
        var dyeMap = DyeItemAccessor.getDyeMap();
        for (Map.Entry<DyeColor, DyeItem> dyeItem : dyeMap.entrySet()) {
            CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(dyeItem.getValue(), CauldronBehaviorExtended.DYE_WATER);
        }
    }
}
