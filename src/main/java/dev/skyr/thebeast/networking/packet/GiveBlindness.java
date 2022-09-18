package dev.skyr.thebeast.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GiveBlindness implements PacketTemplate{
    public GiveBlindness() {
    }
    public GiveBlindness(FriendlyByteBuf buf) {

    }
    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            MobEffect blindness = MobEffect.byId(15);
            player.addEffect(new MobEffectInstance(blindness, 1000000, 1, false, false));

            });
        }

    }

