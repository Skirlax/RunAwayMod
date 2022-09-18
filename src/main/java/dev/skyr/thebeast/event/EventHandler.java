package dev.skyr.thebeast.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.client.WaterHud;
import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.entity.MoveEntity;
import dev.skyr.thebeast.entity.client.ReaperModel;
import dev.skyr.thebeast.entity.client.ReaperRenderer;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import dev.skyr.thebeast.item.ModItems;
import dev.skyr.thebeast.misc.CustomSounds;
import dev.skyr.thebeast.nbthandle.IntArraySave;
import dev.skyr.thebeast.nbthandle.SpawnPos;
import dev.skyr.thebeast.nbthandle.SpawnPosProvider;
import dev.skyr.thebeast.networking.ModPackets;
import dev.skyr.thebeast.networking.packet.*;
import dev.skyr.thebeast.water.Water;
import dev.skyr.thebeast.water.WaterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockCollisions;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.sound.SoundEngineLoadEvent;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.checkerframework.checker.units.qual.C;


import javax.json.Json;
import javax.json.JsonWriter;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = TheBeast.MODID)
public class EventHandler {
    private boolean firstLoad = true;
    private boolean hellHoundsSpawned = false;
    private boolean giveBlindness = false;
    private boolean doFlyCatch = false;
    private boolean removedIsland = false;
    private HandleIntro handleIntro = new HandleIntro();
    private boolean isInDyingProcess = false;
    private ShowDyingPlayer showDyingPlayer = new ShowDyingPlayer();
    private RemovePlayer removePlayer = new RemovePlayer();
    private StopWatch stopWatch = new StopWatch();
    private final StopWatch drinkingCooldown = new StopWatch();
    private final StopWatch refillChestWatch = new StopWatch();

    private final StopWatch teleportPlayerWatch = new StopWatch();




    private double reducedBy = 0.0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        BlockPos chestPos = new BlockPos(-81,70,105);
        ChestBlock chestBlock = null;
        if (player.level.getBlockState(chestPos).getBlock() instanceof ChestBlock && refillChestWatch.isStopped()) {
            chestBlock = (ChestBlock) player.level.getBlockState(chestPos).getBlock();
        }
        if (Container.shouldTeleportToSpawn && Minecraft.getInstance().getConnection() != null) {
            ModPackets.sendToServer(new TpTo());

            Container.shouldTeleportToSpawn = false;


        }
        teleportPlayer();

        // get chestblock at chestpos


        if (chestBlock != null && Minecraft.getInstance().getConnection() != null) {
            if (ChestBlock.getContainer(chestBlock, chestBlock.defaultBlockState(), player.level, chestPos, false).isEmpty()) {
                ModPackets.sendToServer(new SwitchChest());
                chestBlock = null;
                refillChestWatch.start();
            }

        }


        if (refillChestWatch.isStopped() && Minecraft.getInstance().getConnection() != null && chestBlock != null) {


            ArrayList<Item> itemsToAdd = new ArrayList<>(Arrays.asList(Items.IRON_SWORD, Items.IRON_AXE, Items.IRON_PICKAXE, Items.COOKED_PORKCHOP, Items.WATER_BUCKET,
                    Items.LAVA_BUCKET, Items.STONE, Items.DIAMOND_BOOTS,
                    Items.DIAMOND_LEGGINGS, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_HELMET));
            for (Item item : itemsToAdd) {
                if (item.getDefaultInstance().isStackable()) {
                   ChestBlock.getContainer(chestBlock,chestBlock.defaultBlockState(),player.level,
                            chestPos,false).setItem(itemsToAdd.indexOf(item),new ItemStack(item,32));
                } else {
                    ChestBlock.getContainer(chestBlock,chestBlock.defaultBlockState(),player.level,
                            chestPos,false).setItem(itemsToAdd.indexOf(item),new ItemStack(item));
                }
            }



        }
        if (refillChestWatch.getTime(TimeUnit.MINUTES) >= 30) {
            ModPackets.sendToServer(new SwitchChest());
            refillChestWatch.reset();
        }

        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(WaterProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if(thirst.getThirst() > 0 && shouldReduceThirst(event.player)) {
                    thirst.subtractThirst(1);
                    ModPackets.sendToPlayer(new WaterDataSync(thirst.getThirst()), ((ServerPlayer) event.player));

                }
                else if (thirst.getThirst() == 0 && Minecraft.getInstance().getConnection() != null) {
                    ModPackets.sendToServer(new HurtWhenNoWater());
                }
            });
        }





        if (player.getMainHandItem().getItem() == ModItems.ENTITY_MOVER.get()) {
            MoveEntity moveEntity = new MoveEntity();
            moveEntity.moveEntity();
        }
        if (player.position().distanceTo(new Vec3(100, 100, 100)) < 2 && Minecraft.getInstance().getConnection() != null) {
            Container.teleportLocation = new BlockPos(-77,57.3,100);
            ModPackets.sendToServer(new TpTo());
            ModPackets.sendToServer(new PreparePlayer());

            doFlyCatch = true;
            giveBlindness = true;

        }
        if (player.position().y <= 55.5 && giveBlindness) {
            ModPackets.sendToServer(new GiveBlindness());
            giveBlindness = false;

        }
        if (drinkingCooldown.getTime(TimeUnit.SECONDS) >= 1.0) {
            drinkingCooldown.reset();
        }


        if (Minecraft.getInstance().mouseHandler.isRightPressed()) {
            if (player.getMainHandItem().getItem() == Items.AIR && drinkingCooldown.isStopped()) {
                ModPackets.sendToServer(new DrinkWater());

                drinkingCooldown.start();


            }
        }
        if (player.position().y <= 0 && doFlyCatch) {
            Container.teleportLocation = new BlockPos(200, 90, 200);
            ModPackets.sendToServer(new TpTo());
            doFlyCatch = false;
        }
        if (player.level.isNight() && Minecraft.getInstance().getConnection() != null && !hellHoundsSpawned) {
            hellHoundsSpawned = true;
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            ModPackets.sendToServer(new SummonHellHounds());
//            player.getLevel().playSound(player, player.blockPosition(), new SoundEvent(new ResourceLocation(TheBeast.MODID,"sounds/dragon.ogg")), SoundSource.AMBIENT, 60.0f, 1.0f);
        }


        if (Container.isShowPlayerDone) {
            Container.isShowPlayerDone = false;
            if (!stopWatch.isStarted()) {
                stopWatch.start();
            }



        }
        if (stopWatch.getTime(TimeUnit.SECONDS) >= 11) {

            ModPackets.sendToServer(removePlayer);
            stopWatch.reset();
        }
        if (Container.isRemovePlayerDone) {
            removePlayer.resetDone();
            isInDyingProcess = false;
            Container.isRemovePlayerDone = false;
        }
        if (player.getMainHandItem().getItem() == Items.AIR && Minecraft.getInstance().mouseHandler.isRightPressed()) {
            HitResult hitResult = player.pick(2, 1, false); // 5 is the max distance, 1 is the delta time, false is if you want to check liquids
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = new BlockPos(hitResult.getLocation());
                if (player.getLevel().isWaterAt(blockPos)) {
//                    ModPackets.sendToPlayer(new WaterDataSync());
                }
            }

        }
        }

        @SubscribeEvent
        public void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ReaperEntity) {
            // remove whole ai task and replace the
            ReaperEntity reaperEntity = (ReaperEntity) event.getEntity();

        }
        }

        @SubscribeEvent
        // on player join
        public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {


            if (!event.getEntity().level.isClientSide) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(WaterProvider.PLAYER_THIRST).ifPresent(thirst -> {
                        ModPackets.sendToPlayer(new WaterDataSync(thirst.getThirst()), player);
                    });
                }}

                JsonObject jsonObject = new JsonObject();
                try {
                    int coord_num = event.getEntity().getLevel().players().toArray().length;
                    jsonObject = JsonParser.parseReader(new FileReader("../src/main/resources/assets/thebeast/customjson/coords"+coord_num +".json")).getAsJsonObject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Container.teleportLocation = new BlockPos(jsonObject.get("x").getAsInt(), jsonObject.get("y").getAsInt(), jsonObject.get("z").getAsInt());
                Container.shouldTeleportToSpawn = true;

                if (event.getEntity().getLevel().players().toArray().length == Container.maxPlayers && Minecraft.getInstance().getConnection() != null) {
                    Container.shouldPlayerIntro = true;
                }



        }
    @SubscribeEvent
    public void onPlayerDied(net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent event) {
        ModPackets.sendToServer(new RemovePlayer());

    }



    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ArrayList<Item> neededItems = new ArrayList<>(Arrays.asList(Items.DIAMOND_HELMET,Items.DIAMOND_CHESTPLATE,
        Items.DIAMOND_LEGGINGS,Items.DIAMOND_BOOTS, Items.DIAMOND_SWORD));
        for (Item item : neededItems) {
            if (!event.getEntity().getInventory().contains(new ItemStack(item))) {
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(WaterProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(TheBeast.MODID, "properties"), new WaterProvider());

            }
        }
    }

    private void teleportPlayer() {
        if (Minecraft.getInstance().getConnection() == null) {
            return;
        }
        float originalSpeed = 0.0f;
        BlockPos belowPos = null;
        if (!handleIntro.isInProcess()) {
            ModPackets.sendToServer(handleIntro);
        }
        if (handleIntro.isDone()) {
            teleportPlayerWatch.start();
            handleIntro.setDone(false);
            originalSpeed = handleIntro.getOriginalSpeed();
            belowPos = handleIntro.getBelowPos();

        }
        if (teleportPlayerWatch.getTime(TimeUnit.SECONDS) >= 7) {
            teleportPlayerWatch.reset();
            ModPackets.sendToServer(new RandomPlayerTp(originalSpeed,belowPos));
        }

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesLevel(AttachCapabilitiesEvent<Level> event) {

//        if (!event.getObject().getCapability(SpawnPosProvider.PLAYER_SPAWN_POS).isPresent()) {
//
//            int spawnPlatformIndex = 0;
//            ArrayList<Integer> spawnPoses = new ArrayList<>();
//            int x_pos = -80;
//            int z_pos = 105;
//            int radius = 25;
//            for (int x = x_pos - radius; x <= radius + x_pos; x++) {
//                for (int z = z_pos - radius; z <= radius + z_pos; z++) {
//                    if (event.getObject().getBlockState(new BlockPos(x,68,z)).getBlock() == Blocks.WAXED_COPPER_BLOCK) {
//                        spawnPoses.add(x);
//                        spawnPoses.add(68);
//                        spawnPoses.add(z);
//
//
//
//                    }
//
//                }
//
//            }
//            int[] spawnPosesArray = new int[spawnPoses.size()];
//            for (int i = 0; i < spawnPoses.size(); i++) {
//                spawnPosesArray[i] = spawnPoses.get(i);
//            }
//            event.addCapability(new ResourceLocation(TheBeast.MODID, "spawn_pos"), new SpawnPosProvider(spawnPosesArray));
//
//
//        }
//        else {
//            // remove the capability
//            event.getCapabilities().remove(new ResourceLocation(TheBeast.MODID, "spawn_pos"));
//        }


    }

    @SubscribeEvent
    public static void onChangeGameMode(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (event.getCurrentGameMode() == GameType.SURVIVAL) {

//            ModPackets.sendToPlayer(new ClearWaterOverlay(), event.getEntity());
            Container.stopWaterDrain = true;
            WaterHud.waterOverlayVisible = false;
        }
        else {
            WaterHud.waterOverlayVisible = true;
            Container.stopWaterDrain = false;
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(WaterProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(WaterProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }




    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(Water.class);
    }




    private boolean shouldReduceThirst(Player player) {
        if (reducedBy >= 10.0) {
            reducedBy = 0.0;
            return true;
        }
        if ((int) player.moveDist == 0 || (int) player.walkDist == 0) {
            return false;
        }
        if (player.isSprinting() && (int) player.moveDist % 2 == 0) {
            reducedBy += 0.05;
        }
        if ((int)player.walkDist % 2 == 0) {
            reducedBy += 0.01;
        }
        if (player.isSwimming() && (int) player.moveDist % 4 == 0) {
            reducedBy += 0.015;
        }
        return false;
    }




    }
    // on player died





