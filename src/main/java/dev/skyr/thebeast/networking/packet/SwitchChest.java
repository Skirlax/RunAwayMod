package dev.skyr.thebeast.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SwitchChest implements PacketTemplate{
    public SwitchChest() {
    }
    public SwitchChest(FriendlyByteBuf buf) {
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
            BlockPos chestPos = new BlockPos(-81,70,105);
            if (level.getBlockState(chestPos).getBlock() == Blocks.CHEST) {
                level.setBlockAndUpdate(new BlockPos(-81, 70, 105), Blocks.AIR.defaultBlockState());
            } else {
                level.setBlockAndUpdate(new BlockPos(-81, 70, 105), Blocks.CHEST.defaultBlockState());
            }
        });

    }
}
