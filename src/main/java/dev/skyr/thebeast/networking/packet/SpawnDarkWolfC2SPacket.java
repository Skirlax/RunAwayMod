package dev.skyr.thebeast.networking.packet;

import com.google.common.graph.Network;
import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnDarkWolfC2SPacket implements PacketTemplate {
    public SpawnDarkWolfC2SPacket() {

    }

    public SpawnDarkWolfC2SPacket(FriendlyByteBuf buf) {
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
            ModEntityTypes.DARK_WOLF.get().spawn(level, null, null, player.blockPosition(), MobSpawnType.COMMAND, false, false);

        });
    }
}

