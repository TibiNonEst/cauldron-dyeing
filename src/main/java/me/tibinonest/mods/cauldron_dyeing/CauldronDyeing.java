package me.tibinonest.mods.cauldron_dyeing;

import me.tibinonest.mods.cauldron_dyeing.block.WaterCauldronBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

public class CauldronDyeing implements ModInitializer {
    public static final String MOD_ID = "cauldron_dyeing";

    public static final BlockEntityType<WaterCauldronBlockEntity> WATER_CAULDRON_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(WaterCauldronBlockEntity::new, Blocks.WATER_CAULDRON).build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "water_cauldron_entity"), WATER_CAULDRON_BLOCK_ENTITY);
    }

    @Environment(EnvType.CLIENT)
    public static int getColor(BlockRenderView world, BlockPos pos) {
        var blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof WaterCauldronBlockEntity waterCauldron && waterCauldron.getColor() != -1) {
            return waterCauldron.getColor();
        }

        return BiomeColors.getWaterColor(world, pos);
    }

    @Environment(EnvType.CLIENT)
    public static void rebuildBlock(BlockPos pos) {
        MinecraftClient.getInstance().worldRenderer.scheduleBlockRenders(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ());
    }
}
