package dev.skyr.thebeast.event;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.ModEntityTypes;
import dev.skyr.thebeast.entity.custom.DarkWolfEntity;
import dev.skyr.thebeast.entity.custom.NotBurningPhantomEntity;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import dev.skyr.thebeast.item.ModItems;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheBeast.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.REAPER.get(), ReaperEntity.setAttributes());
        event.put(ModEntityTypes.DARK_WOLF.get(), DarkWolfEntity.setAttributes());
        event.put(ModEntityTypes.NOT_BURNING_PHANTOM.get(), NotBurningPhantomEntity.setAttributes());
        event.put(ModEntityTypes.REAPER_EYE_FOLLOWER.get(), ReaperEntity.setAttributes());
//        event.put(ModEntityTypes.DARK_WOLF_AGENT.get(), DarkWolfEntity.setAttributes());
    }



}






