package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SummonGuardians implements PacketTemplate{
    public SummonGuardians() {

    }
    public SummonGuardians(FriendlyByteBuf buf) {

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
            for (Double[] value : Container.reaperGuardsPos.values()) {
                BlockPos pos = new BlockPos(value[0], value[1], value[2]);
                double rotationX = value[3];
                double rotationY = value[4];
                Entity entity = ModEntityTypes.REAPER.get().spawn(level, null, null,pos , MobSpawnType.COMMAND, false, false);
                entity.setYRot((float) rotationY);
                entity.setXRot((float) rotationX);


            }

        });

    }
}
