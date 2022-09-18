package dev.skyr.thebeast.networking.packet;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.skyr.thebeast.Container;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RotateMiddlePlatform implements PacketTemplate {
    public RotateMiddlePlatform() {
    }
    public RotateMiddlePlatform(FriendlyByteBuf buf) {
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            for (BlockPos blockPos : Container.middlePlatform) {

                BlockPos newBLockPos = rotatePointAroundPivot(blockPos, new BlockPos(-80, 66, 105), 5);
                level.setBlockAndUpdate(newBLockPos, level.getBlockState(blockPos));
            }




        });

    }
    public BlockPos rotatePointAroundPivot(BlockPos point, BlockPos pivot, double angleInDegrees) {
        double angle = Math.toRadians(angleInDegrees); // 90 degrees
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double x = point.getX() - pivot.getX();
        double z = point.getZ() - pivot.getZ();
        double newX = x * cos - z * sin;
        double newZ = x * sin + z * cos;
        return new BlockPos(newX + pivot.getX(), point.getY(), newZ + pivot.getZ());
    }
}
