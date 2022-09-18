package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.Container;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.ConcurrentModificationException;
import java.util.function.Supplier;

public class TpTo implements PacketTemplate{
    public TpTo() {
    }
    public TpTo(FriendlyByteBuf buf) {

    }
    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.teleportTo(Container.teleportLocation.getX(), Container.teleportLocation.getY(), Container.teleportLocation.getZ());
        });

    }
}
