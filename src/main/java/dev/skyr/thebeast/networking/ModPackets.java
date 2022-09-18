package dev.skyr.thebeast.networking;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.client.WaterData;
import dev.skyr.thebeast.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TheBeast.MODID, "packets"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

//        net.messageBuilder(SpawnDarkWolfC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .encoder(SpawnDarkWolfC2SPacket::toBytes)
//                .decoder(SpawnDarkWolfC2SPacket::new)
//                .consumerMainThread(SpawnDarkWolfC2SPacket::handle)
//                .add();
        net.messageBuilder(SummonGuardians.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SummonGuardians::toBytes)
                .decoder(SummonGuardians::new)
                .consumerMainThread(SummonGuardians::handle)
                .add();
        net.messageBuilder(LightningAroundPlayer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(LightningAroundPlayer::toBytes)
                .decoder(LightningAroundPlayer::new)
                .consumerMainThread(LightningAroundPlayer::handle)
                .add();
        net.messageBuilder(TpTo.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(TpTo::toBytes)
                .decoder(TpTo::new)
                .consumerMainThread(TpTo::handle)
                .add();
        net.messageBuilder(PreparePlayer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(PreparePlayer::toBytes)
                .decoder(PreparePlayer::new)
                .consumerMainThread(PreparePlayer::handle)
                .add();
        net.messageBuilder(GiveBlindness.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(GiveBlindness::toBytes)
                .decoder(GiveBlindness::new)
                .consumerMainThread(GiveBlindness::handle)
                .add();
        net.messageBuilder(SummonHellHounds.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SummonHellHounds::toBytes)
                .decoder(SummonHellHounds::new)
                .consumerMainThread(SummonHellHounds::handle)
                .add();

        net.messageBuilder(RemoveIsland.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(RemoveIsland::toBytes)
                .decoder(RemoveIsland::new)
                .consumerMainThread(RemoveIsland::handle)
                .add();

        net.messageBuilder(CreateMiddleIsland.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(CreateMiddleIsland::toBytes)
                .decoder(CreateMiddleIsland::new)
                .consumerMainThread(CreateMiddleIsland::handle)
                .add();
        net.messageBuilder(ShowDyingPlayer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(ShowDyingPlayer::toBytes)
                .decoder(ShowDyingPlayer::new)
                .consumerMainThread(ShowDyingPlayer::handle)
                .add();
        net.messageBuilder(RemovePlayer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(RemovePlayer::toBytes)
                .decoder(RemovePlayer::new)
                .consumerMainThread(RemovePlayer::handle)
                .add();
        net.messageBuilder(RotateMiddlePlatform.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(RotateMiddlePlatform::toBytes)
                .decoder(RotateMiddlePlatform::new)
                .consumerMainThread(RotateMiddlePlatform::handle)
                .add();
        net.messageBuilder(WaterDataSync.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(WaterDataSync::toBytes)
                .decoder(WaterDataSync::new)
                .consumerMainThread(WaterDataSync::handle)
                .add();
        net.messageBuilder(DrinkWater.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(DrinkWater::toBytes)
                .decoder(DrinkWater::new)
                .consumerMainThread(DrinkWater::handle)
                .add();
        net.messageBuilder(HurtWhenNoWater.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(HurtWhenNoWater::toBytes)
                .decoder(HurtWhenNoWater::new)
                .consumerMainThread(HurtWhenNoWater::handle)
                .add();
        net.messageBuilder(ClearWaterOverlay.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClearWaterOverlay::toBytes)
                .decoder(ClearWaterOverlay::new)
                .consumerMainThread(ClearWaterOverlay::handle)
                .add();
        net.messageBuilder(SwitchChest.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SwitchChest::toBytes)
                .decoder(SwitchChest::new)
                .consumerMainThread(SwitchChest::handle)
                .add();
        net.messageBuilder(HandleIntro.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(HandleIntro::toBytes)
                .decoder(HandleIntro::new)
                .consumerMainThread(HandleIntro::handle)
                .add();
        net.messageBuilder(RandomPlayerTp.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(RandomPlayerTp::toBytes)
                .decoder(RandomPlayerTp::new)
                .consumerMainThread(RandomPlayerTp::handle)
                .add();





    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, Player player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), message);
    }


}
