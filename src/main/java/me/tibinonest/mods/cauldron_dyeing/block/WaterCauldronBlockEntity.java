package me.tibinonest.mods.cauldron_dyeing.block;

import com.google.common.primitives.Ints;
import me.tibinonest.mods.cauldron_dyeing.CauldronDyeing;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class WaterCauldronBlockEntity extends BlockEntity {
    private final int[] EMPTY_NBT = new int[] {-1, -1, -1};
    private int[] color;

    public WaterCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(CauldronDyeing.WATER_CAULDRON_BLOCK_ENTITY, pos, state);
        color = null;
    }

    // Formula taken from https://minecraft.fandom.com/wiki/Dye#Dyeing_armor and DyeableItem#blendAndSetColor
    public void setColor(DyeColor dyeColor) {
        var colorComponents = dyeColor.getColorComponents();

        var newColor = new int[3];
        newColor[0] = (int) (colorComponents[0] * 255.0f);
        newColor[1] = (int) (colorComponents[1] * 255.0f);
        newColor[2] = (int) (colorComponents[2] * 255.0f);

        if (color != null) {
            var avgColor = new int[3];
            avgColor[0] = (color[0] + newColor[0]) / 2;
            avgColor[1] = (color[1] + newColor[1]) / 2;
            avgColor[2] = (color[2] + newColor[2]) / 2;

            var avgMax = (Ints.max(color) + Ints.max(newColor)) / 2.0f;

            var maxOfAvg = (float) Ints.max(avgColor);
            var gainFactor = avgMax / maxOfAvg;

            color[0] = (int) (avgColor[0] * gainFactor);
            color[1] = (int) (avgColor[1] * gainFactor);
            color[2] = (int) (avgColor[2] * gainFactor);
        } else {
            color = newColor;
        }

        markDirty();
    }

    public int getColor() {
        return color != null ? (color[0] << 16) + (color[1] << 8) + color[2] : -1;
    }

    public void resetColor() {
        color = null;
        markDirty();
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putIntArray("color", color != null ? color : EMPTY_NBT);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        var value = tag.getIntArray("color");
        color = value != EMPTY_NBT ? color : null;
        markDirty();
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return toNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.of(this);
    }

    @Override
    public void markDirty() {
        if (world != null) {
            if (world.isClient()) {
                CauldronDyeing.rebuildBlock(pos);
            } else {
                ((ServerWorld) world).getChunkManager().markForUpdate(pos);
            }
            super.markDirty();
        }
    }
}
