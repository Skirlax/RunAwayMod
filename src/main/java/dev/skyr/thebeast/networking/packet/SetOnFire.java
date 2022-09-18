package dev.skyr.thebeast.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class SetOnFire implements PacketTemplate{
    public SetOnFire() {
    }
    public SetOnFire(FriendlyByteBuf buf) {
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

            BlockPos pos = player.blockPosition();
            Direction direction = player.getDirection();
            BlockPos pos2 = pos.relative(direction);
            level.setBlockAndUpdate(pos2.above(), Blocks.FIRE.defaultBlockState());

        });


    }
}
