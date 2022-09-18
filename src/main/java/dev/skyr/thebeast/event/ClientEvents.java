package dev.skyr.thebeast.event;


import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.client.WaterHud;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = TheBeast.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {


    }


    @Mod.EventBusSubscriber(modid = TheBeast.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {

            event.registerAboveAll("water", WaterHud.WATER_HUD);


        }





    }
}