package dev.skyr.thebeast.item;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheBeast.MODID);

    public static final RegistryObject<Item> NOT_BURNING_PHANTOM_EGG = ITEMS.register("not_burning_phantom_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.NOT_BURNING_PHANTOM,0x948e8d, 0x3b3635,
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> REAPER_EGG = ITEMS.register("reaper_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.REAPER,0x000000,0xFF0000,
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> DARK_WOLF_EGG = ITEMS.register("dark_wolf_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_WOLF,0x000000,0x000000,
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> REAPER_EYE_FOLLOWER_EGG = ITEMS.register("reaper_eye_follower_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.REAPER_EYE_FOLLOWER,0x000000,0x00D1FF,
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> ENTITY_MOVER = ITEMS.register("entity_mover",
            () -> new EntityMoverItem(Tiers.WOOD, 0, 0, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static  final RegistryObject<Item> WAKE_US_UP = ITEMS.register("wake_us_up",
            () -> new WakeUsUpItem(new Item.Properties()
                    .tab(CreativeModeTab.TAB_TOOLS)
                    .stacksTo(1)
                    .defaultDurability(1)));
    public static void register(IEventBus bus) {
        ITEMS.register(bus);

    }



}
