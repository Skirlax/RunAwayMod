package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.ModEntityTypes;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class SummonHellHounds implements PacketTemplate {
    public SummonHellHounds() {
    }
    public SummonHellHounds(FriendlyByteBuf buf) {
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            int multiplier = 10;
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            for (int i = 0; i < 3; i ++) {
                BlockPos playerPos = player.blockPosition();
                Direction direction = player.getDirection().getOpposite();
                BlockPos spawnPos = playerPos.relative(direction, 10);



                EntityType.LIGHTNING_BOLT.spawn(level, null, null,spawnPos.offset(multiplier, 0,0), MobSpawnType.COMMAND, false, false);
                Entity hound = ModEntityTypes.DARK_WOLF.get().spawn(level, null, null, spawnPos.offset(multiplier,0,0), MobSpawnType.COMMAND, false, false);
                Container.hellhuntsTracker.put(hound.getId(), hound);

                multiplier += 10;


                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // make lightning bolt visual only



            }
            player.rotate(Rotation.CLOCKWISE_180);
            Container.asDisabled.put("DarkWolfEntity", false);
            Container.isInvulnerable.put("DarkWolfEntity", false);

        });

    }
}
