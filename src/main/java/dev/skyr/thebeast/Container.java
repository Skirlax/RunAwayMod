package dev.skyr.thebeast;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Hashtable;

public class Container {
    public static Hashtable<String, Boolean> asDisabled = new Hashtable<String, Boolean>();
    public static Hashtable<String, Boolean> isInvulnerable = new Hashtable<String, Boolean>();

    public static Hashtable<Integer, Double[]> reaperGuardsPos = new Hashtable<Integer, Double[]>();
    public static Hashtable<Integer, Entity> hellhuntsTracker = new Hashtable<Integer, Entity>();
    public static BlockPos bottomPlatformPos = new BlockPos(-167, -21, 196);
    public static ArrayList<BlockState> hiddenBlocks = new ArrayList<>();
    public static Hashtable<BlockPos, BlockState> hiddenBlockPos = new Hashtable<>();
    public static BlockPos teleportLocation = new BlockPos(0, 0, 0);
    // gamemode adventure
    public static GameType gameMode = GameType.ADVENTURE;
    // effect hunger
    public static MobEffect effect = MobEffect.byId(17);
    public static ItemStack[] itemStack;
    public static ArrayList<BlockPos> middlePlatform = new ArrayList<>();
    public static BlockPos beaconBlockPos;
    public static BlockState beaconBlock;
    public static boolean isShowPlayerDone = false;
    public static boolean isRemovePlayerDone = false;
    public static boolean shouldTeleportToSpawn = false;
    public static boolean shouldPlayerIntro = false;
    public static boolean stopWaterDrain = false;
    public static int maxPlayers = 1;
    public static int playerIndex = 0;
    public static ArrayList<BlockPos> spawnPoses = new ArrayList<>();


    public static void initialize() {
        isInvulnerable.put("DarkWolfEntity", true);
        isInvulnerable.put("ReaperEntity", true);
        isInvulnerable.put("NotBurningPhantomEntity", true);
        asDisabled.put("DarkWolfEntity", true);
        asDisabled.put("ReaperEntity", true);
        asDisabled.put("NotBurningPhantomEntity", true);
        reaperGuardsPos.put(0, new Double[]{-73.0, 67.0, 104.0,89.8,44.2});
        reaperGuardsPos.put(1, new Double[]{-78.0,67.0,109.0,179.8,36.4});
        reaperGuardsPos.put(2, new Double[]{-83.0,67.0,104.0,-88.7,37.6});
        reaperGuardsPos.put(3, new Double[]{-78.0,67.0,99.0,1.5,34.8});


    }

    public static void revive_specified(String key) {
        // Revives specified species
        isInvulnerable.put(key, false);
        asDisabled.put(key, false);
    }

    public static void revive_all() {
        // Enables AI on ALL spawned entities and makes them vulnerable to damage

        isInvulnerable.put("DarkWolfEntity", false);
        isInvulnerable.put("ReaperEntity", false);
        isInvulnerable.put("NotBurningPhantomEntity", false);
        asDisabled.put("DarkWolfEntity", false);
        asDisabled.put("ReaperEntity", false);
        asDisabled.put("NotBurningPhantomEntity", false);
    }
    public static void putAllToSleep() {
        // Puts all spawned entities to sleep
        isInvulnerable.put("DarkWolfEntity", true);
        isInvulnerable.put("ReaperEntity", true);
        isInvulnerable.put("NotBurningPhantomEntity", true);
        asDisabled.put("DarkWolfEntity", true);
        asDisabled.put("ReaperEntity", true);
        asDisabled.put("NotBurningPhantomEntity", true);
    }

}
