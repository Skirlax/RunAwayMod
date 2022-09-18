package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.Container;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShowDyingPlayer implements PacketTemplate {
    private boolean isDone = false;
    public ShowDyingPlayer() {
    }
    public ShowDyingPlayer(FriendlyByteBuf buf) {
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            ServerLevel world = player.getLevel();



            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1000000, 255, false, false));




            player.setJumping(false);
            player.setInvulnerable(true);
            BlockPos pos = player.blockPosition();

            BlockState original = world.getBlockState(pos.below());
            Container.beaconBlock = original;
            Container.beaconBlockPos = pos.below();
            // update the world
            world.setBlockAndUpdate(pos.below(), Blocks.BEACON.defaultBlockState());

            player.moveTo(pos.getX(), pos.getY() + 5, pos.getZ());
            player.setNoGravity(true);


            // update world

            this.isDone = true;
            Container.isShowPlayerDone = true;






        });


    }
    public boolean isDone() {
        return this.isDone;
    }
    public void resetDone() {
        this.isDone = false;
    }
}
