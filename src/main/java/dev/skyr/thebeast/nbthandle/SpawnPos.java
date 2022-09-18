package dev.skyr.thebeast.nbthandle;

import net.minecraft.nbt.CompoundTag;

public class SpawnPos {
    private int[] spawnPosCoords = new int[3];
    public SpawnPos(int[] spawnPosCoordsLoc) {
        spawnPosCoords = spawnPosCoordsLoc;
    }

    public int[] playerSpawnPos = spawnPosCoords;
    public void setPlayerSpawnPos(int x, int y, int z) {
        playerSpawnPos[0] = x;
        playerSpawnPos[1] = y;
        playerSpawnPos[2] = z;
    }
    public void saveNBTData(CompoundTag nbt) {
        nbt.putIntArray("spawnPos", playerSpawnPos);
    }

    public void loadNBTData(CompoundTag nbt) {
        playerSpawnPos = nbt.getIntArray("spawnPos");
    }
}
