package dev.skyr.thebeast.nbthandle;

import net.minecraft.nbt.CompoundTag;

public class IntArraySave {
    public void saveNBTData(CompoundTag nbt,int[] array, String name) {
        nbt.putIntArray(name, array);
    }

    public int[] loadNBTData(CompoundTag nbt, String name) {
        return nbt.getIntArray(name);
    }
}
