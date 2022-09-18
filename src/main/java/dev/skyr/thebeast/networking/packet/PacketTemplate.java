package dev.skyr.thebeast.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface PacketTemplate {
    void toBytes(FriendlyByteBuf buf);
    void handle(Supplier<NetworkEvent.Context> supplier);
}
