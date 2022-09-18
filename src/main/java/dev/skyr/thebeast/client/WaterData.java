package dev.skyr.thebeast.client;

public class WaterData {
    private static int playerThirst = 0;
    public static void setPlayerThirst(int thirst) {
        WaterData.playerThirst = thirst;
    }
    public static int getPlayerThirst() {
        return playerThirst;
    }
}
