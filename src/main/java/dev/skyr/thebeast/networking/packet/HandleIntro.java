package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.misc.CustomSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HandleIntro implements PacketTemplate{
    private boolean isDone = false;
    private boolean isInProcess = false;

    private float originalSpeed;
    private BlockPos belowPos;
    public HandleIntro() {
    }
    public HandleIntro(FriendlyByteBuf buf) {
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        isInProcess = true;
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            ServerLevel world = player.getLevel();
            world.playSound(null, player.blockPosition(), CustomSounds.go.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (ServerPlayer p : world.players()) {
                p.setNoGravity(true);
                world.setBlockAndUpdate(p.blockPosition().below(), Blocks.BEACON.defaultBlockState());
                belowPos = p.blockPosition().below();
                p.moveTo(p.getX(), p.getY() + 5, p.getZ());
                originalSpeed = player.getSpeed();
                p.setSpeed(0.0f);





            }
//            for (CustomSounds sound : CustomSounds.class.get) {
//                SoundEvent soundEvent = sound.getSoundEvent();
//                BlockPos pos = player.blockPosition();
//                world.playSound(null, pos, soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
//            }

        });
        isDone = true;

    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isInProcess() {
        return isInProcess;
    }
    public BlockPos getBelowPos() {
        return belowPos;
    }
    public float getOriginalSpeed() {
        return originalSpeed;
    }



}
