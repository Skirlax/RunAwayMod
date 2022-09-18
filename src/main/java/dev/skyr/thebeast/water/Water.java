package dev.skyr.thebeast.water;

import dev.skyr.thebeast.Container;
import net.minecraft.nbt.CompoundTag;

public class Water {

    private int thirst;
    private final int maxThirst = 10;
    private final int minThirst = 0;

    public int getThirst() {
        return thirst;
    }
    public void addThirst(int thirst) {
        if (Container.stopWaterDrain) {
            if (this.thirst == 0) {
                this.thirst = 1;
            }
            return;
        }
        this.thirst = Math.min(this.thirst + thirst, maxThirst);
    }
    public void subtractThirst(int thirst) {
        if (Container.stopWaterDrain) {
            if (this.thirst == 0) {
                this.thirst = 1;
            }
            return;
        }
        this.thirst = Math.max(this.thirst - thirst, minThirst);
    }
    public void copyFrom(Water source) {
        this.thirst = source.thirst;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("water", thirst);
    }

    public void loadNBTData(CompoundTag nbt) {
        thirst = nbt.getInt("water");
    }

}
