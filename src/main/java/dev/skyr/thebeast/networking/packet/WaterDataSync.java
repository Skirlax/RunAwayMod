package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.client.WaterData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WaterDataSync implements PacketTemplate{
    private final int thirst;
    public WaterDataSync(int thirst) {
        this.thirst = thirst;
    }

    public WaterDataSync(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        WaterData.setPlayerThirst(thirst);

    }
}
