package dev.skyr.thebeast.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LightningAroundPlayer implements PacketTemplate{
    public LightningAroundPlayer() {

    }
    public LightningAroundPlayer(FriendlyByteBuf buf) {

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
            for (int i = 0; i < 4; i++) {

                // set weather to thunder
                level.getLevelData().setRaining(true);
                level.setRainLevel(1.0F);
                level.thunderLevel = 1;
                level.oThunderLevel = 1;
                level.setDayTime(18000);

                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.setVisualOnly(true);
                lightningBolt.setCause(player);

                EntityType.LIGHTNING_BOLT.spawn(level, null, null, player.blockPosition().offset(1,1,0), MobSpawnType.COMMAND, false, false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
