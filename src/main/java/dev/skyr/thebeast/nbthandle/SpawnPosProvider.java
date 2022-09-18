package dev.skyr.thebeast.nbthandle;

import dev.skyr.thebeast.water.Water;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnPosProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private int[] spawnPosCoords = new int[3];
    public SpawnPosProvider(int[] spawnPosCoordsLoc) {
        spawnPosCoords = spawnPosCoordsLoc;

    }
    public static Capability<SpawnPos> PLAYER_SPAWN_POS = CapabilityManager.get(new CapabilityToken<>() { });
    private SpawnPos spawnPos = null;

    private final LazyOptional<SpawnPos> optional = LazyOptional.of(this::createPlayerSpawnPos);


    private SpawnPos createPlayerSpawnPos() {
        if(this.spawnPos == null) {
            this.spawnPos = new SpawnPos(spawnPosCoords);
        }

        return this.spawnPos;
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_SPAWN_POS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSpawnPos().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSpawnPos().loadNBTData(nbt);
    }
}
