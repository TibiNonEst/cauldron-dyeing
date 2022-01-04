package me.tibinonest.mods.cauldron_dyeing.block;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.DyeItem;
import net.minecraft.util.ActionResult;

public interface CauldronBehaviorExtended {
    CauldronBehavior DYE_WATER = (state, world, pos, player, hand, stack) -> {
        var blockEntity = world.getBlockEntity(pos);
        var item = stack.getItem();

        if (item instanceof DyeItem dyeItem && blockEntity instanceof WaterCauldronBlockEntity waterCauldron) {
            waterCauldron.setColor(dyeItem.getColor());
            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    };
}
