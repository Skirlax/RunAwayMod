package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.networking.ModPackets;
import dev.skyr.thebeast.water.WaterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class DrinkWater implements PacketTemplate{

    public DrinkWater() {
    }
    public DrinkWater(FriendlyByteBuf buf) {
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
            if (hasWaterAroundThem(player, world)) {

                world.playSound(null, player.getOnPos(), SoundEvents.HONEY_DRINK, SoundSource.PLAYERS, 0.5f, 0.1F);
                player.getCapability(WaterProvider.PLAYER_THIRST).ifPresent(water -> {
                    water.addThirst(1);
                    ModPackets.sendToPlayer(new WaterDataSync(water.getThirst()), player);
                });




            }
            else {
                player.getCapability(WaterProvider.PLAYER_THIRST).ifPresent(water -> {
                    ModPackets.sendToPlayer(new WaterDataSync(water.getThirst()), player);
            });
            }



        });

    }
    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level) {
        HitResult result = Minecraft.getInstance().hitResult;
        if (result != null) {
            if (result.getType() == HitResult.Type.BLOCK) {
                return level.getBlockState(new BlockPos(result.getLocation())).getBlock() == Blocks.WATER;
            }
        }
        return false;
    }
}
