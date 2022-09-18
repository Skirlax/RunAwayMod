package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.water.WaterProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HurtWhenNoWater implements PacketTemplate{
    public HurtWhenNoWater() {
    }
    public HurtWhenNoWater(FriendlyByteBuf buf) {
    }
    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.getCapability(WaterProvider.PLAYER_THIRST).ifPresent(water -> {
                if (water.getThirst() == 0) {
                    player.hurt(DamageSource.DRY_OUT, 1);
                }
            });

        });

    }
}
