package dev.skyr.thebeast.networking.packet;

import dev.skyr.thebeast.Container;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RemovePlayer implements PacketTemplate {
    private boolean isDone = false;
    public RemovePlayer() {
    }
    public RemovePlayer(FriendlyByteBuf buf) {
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
            player.setGameMode(GameType.SPECTATOR);
            for (ServerPlayer player1 : world.players()) {
                String message = "Player "+player.getDisplayName()+" has been eliminated!";
                player1.sendSystemMessage(Component.literal(message));
            }
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, new BlockPos(-81,69 ,105), null, false, false);
            this.isDone = true;
            Container.isRemovePlayerDone = true;

        });

    }
    public boolean isDone() {
        return this.isDone;
    }
    public void resetDone() {
        this.isDone = false;
    }

}
