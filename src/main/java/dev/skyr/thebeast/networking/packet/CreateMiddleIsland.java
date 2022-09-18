package dev.skyr.thebeast.networking.packet;

import com.mojang.datafixers.TypeRewriteRule;
import dev.skyr.thebeast.Container;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class CreateMiddleIsland implements PacketTemplate{
    public CreateMiddleIsland() {
    }
    public CreateMiddleIsland(FriendlyByteBuf buf) {
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

            for (int i =0; i < 2; i++) {


                ArrayList<BlockPos> points = pointsRectangle(25, 66 + i, -80, 105,level);
                for (BlockPos pos : points) {
                    level.setBlock(pos, Blocks.IRON_BLOCK.defaultBlockState(), 3);
                    Container.middlePlatform.add(pos);
                }
            }

        });

    }

    private boolean isOnCircle(int x, int y, int radius) {
        double eq_part = Math.pow(x,2) + Math.pow(y,2);
        return eq_part <= Math.pow(radius,2);

    }
    private ArrayList<BlockPos> pointsRectangle(int radius,int y, int x_pos, int z_pos, ServerLevel level) {
        ArrayList<BlockPos> points = new ArrayList<>();
        for (int x = x_pos -radius; x <= radius + x_pos; x++) {
            for (int z =z_pos  -radius; z <= radius + z_pos; z++) {
                level.setBlockAndUpdate(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState());
                if (isOnCircle(Math.max(x,x_pos) - Math.min(x,x_pos), Math.max(z,z_pos) - Math.min(z,z_pos), radius)) {
                    points.add(new BlockPos(x, y, z));
                }
            }
        }
        return points;
    }
}
