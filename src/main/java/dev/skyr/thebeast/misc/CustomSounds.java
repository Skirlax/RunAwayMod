package dev.skyr.thebeast.misc;

import dev.skyr.thebeast.TheBeast;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CustomSounds {
    public static final DeferredRegister<net.minecraft.sounds.SoundEvent> CUSTOM_SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheBeast.MODID);
    public static RegistryObject<net.minecraft.sounds.SoundEvent> one;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> two;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> three;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> four;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> five;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> six;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> seven;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> eight;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> nine;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> ten;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> welcome;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> rulesaresimple;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> rule1;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> rule2;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> rule3;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> goodluck;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> startsin;
    public static RegistryObject<net.minecraft.sounds.SoundEvent> go;

    private static RegistryObject<net.minecraft.sounds.SoundEvent> registerSound(String name) {
        return CUSTOM_SOUNDS.register(name, () -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(TheBeast.MODID, name)));
    }


    public static void register(IEventBus bus) {
        CUSTOM_SOUNDS.register(bus);
    }

    public static void registerAll() {
        one = registerSound("1");
        two = registerSound("2");
        three = registerSound("3");
        four = registerSound("4");
        five = registerSound("5");
        six = registerSound("6");
        seven = registerSound("7");
        eight = registerSound("8");
        nine = registerSound("9");
        ten = registerSound("10");
        welcome = registerSound("welcome");
        rulesaresimple = registerSound("rules");
        rule1 = registerSound("rule1");
        rule2 = registerSound("rule2");
        rule3 = registerSound("rule3");
        goodluck = registerSound("good_luck");
        startsin = registerSound("starts_in");
        go = registerSound("go");
    }


}


