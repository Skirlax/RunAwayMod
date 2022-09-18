package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ReaperSceneManager implements PacketTemplate{
    public ReaperSceneManager() {

    }
    public ReaperSceneManager(FriendlyByteBuf buf) {

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
            player.setNoGravity(true);
            BlockPos playerPos = player.blockPosition();
            player.teleportTo(playerPos.getX(),-64,playerPos.getZ()); //teleport to the bottom of the world
//            level.setBlockAndUpdate(playerPos, level.getBlockState(playerPos).setValue(BlockStateProperties.WATERLOGGED, true)); //set the block the player is standing on to waterlogged
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,999999,1,false,false)); //add blindness to forever
            player.setSpeed(0.0f);
//            String[] messages = new String[]{"So you are the strongest one here?\n",
//                    "The first one with diamond armor AND a diamond sword?",""};





        });

    }
}
