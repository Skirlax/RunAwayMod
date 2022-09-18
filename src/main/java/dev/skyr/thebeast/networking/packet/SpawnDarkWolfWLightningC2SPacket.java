package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.entity.custom.DarkWolfEntity;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnDarkWolfWLightningC2SPacket implements PacketTemplate {
    public SpawnDarkWolfWLightningC2SPacket() {
    }
    public SpawnDarkWolfWLightningC2SPacket(FriendlyByteBuf buf) {
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
//            Vec3 position = player.position();
//            position.x =
            ModEntityTypes.DARK_WOLF.get().spawn(level, null, null, player.blockPosition().offset(3,3,0), MobSpawnType.COMMAND, false, false);
            EntityType.LIGHTNING_BOLT.spawn(level, null, null, player.blockPosition().offset(3,3,0), MobSpawnType.COMMAND, false, false);

        });
    }
}
