package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.entity.custom.DarkWolfAgent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnDarkWolfAgent implements PacketTemplate{

    public SpawnDarkWolfAgent() {
    }
    public SpawnDarkWolfAgent(FriendlyByteBuf buf) {

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
//            ModEntityTypes.DARK_WOLF_AGENT.get().spawn(level, null, null, player.blockPosition().offset(3,0,3), MobSpawnType.COMMAND, false, false);
        });

    }

}
