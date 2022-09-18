package dev.skyr.thebeast.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RandomPlayerTp implements PacketTemplate{
    private float originalSpeed;
    private BlockPos belowPos;
    public RandomPlayerTp(float originalSpeed,BlockPos belowPos) {
        this.originalSpeed = originalSpeed;
        this.belowPos = belowPos;
    }

    public RandomPlayerTp(FriendlyByteBuf buf) {
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel world = player.getLevel();
            for (ServerPlayer p : world.players()) {

                p.setNoGravity(false);

                BlockPos randomPos = world.getBlockRandomPos(player.getBlockX(), player.getBlockY(), player.getBlockZ(), 500);
                player.setSpeed(originalSpeed);
                player.randomTeleport(world.getRandom().nextDouble(), world.getRandom().nextDouble(), world.getRandom().nextDouble(), true);
                // set to default speed
                world.setBlockAndUpdate(belowPos, Blocks.AIR.defaultBlockState());
            }

        });


    }
}
