package dev.skyr.thebeast.entity;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheBeast.MODID);
    //reaper
    public static final RegistryObject<EntityType<ReaperEntity>> REAPER = ENTITY_TYPES.register("reaper", () -> EntityType.Builder.of(ReaperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F)
            .build(new ResourceLocation(TheBeast.MODID, "reaper").toString()));

    public static final RegistryObject<EntityType<DarkWolfEntity>> DARK_WOLF = ENTITY_TYPES.register("dark_wolf", () -> EntityType.Builder.of(DarkWolfEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F)
            .build(new ResourceLocation(TheBeast.MODID, "dark_wolf").toString()));
    public static final RegistryObject<EntityType<NotBurningPhantomEntity>> NOT_BURNING_PHANTOM = ENTITY_TYPES.register("not_burning_phantom", () -> EntityType.Builder.of(NotBurningPhantomEntity::new, MobCategory.MONSTER).sized(2.7F, 1.5F)
            .build(new ResourceLocation(TheBeast.MODID, "not_burning_phantom").toString()));
    public static final RegistryObject<EntityType<ReaperEyeFollower>> REAPER_EYE_FOLLOWER = ENTITY_TYPES.register("reaper_eye_follower", () ->
            EntityType.Builder.of(ReaperEyeFollower::new,MobCategory.MONSTER).sized(3.0F, 3.0F).build(
                    new ResourceLocation(TheBeast.MODID, "reaper_eye_follower").toString()));
//    public static final RegistryObject<EntityType<DarkWolfAgent>> DARK_WOLF_AGENT = ENTITY_TYPES.register("dark_wolf_agent", () -> EntityType.Builder.of(DarkWolfAgent::new, MobCategory.MONSTER).sized(0.6F, 1.95F)
//            .build(new ResourceLocation(TheBeast.MODID, "dark_wolf_agent").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
