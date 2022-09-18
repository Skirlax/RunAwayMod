package dev.skyr.thebeast.networking.packet;

import com.mojang.blaze3d.shaders.Effect;
import dev.skyr.thebeast.Container;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PreparePlayer implements PacketTemplate{
    public PreparePlayer() {
    }
    public PreparePlayer(FriendlyByteBuf buf) {

    }
    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.setGameMode(Container.gameMode);
            player.getFoodData().setFoodLevel(20);
            player.addEffect(new MobEffectInstance(Container.effect, 1000000, 1, false, false));
            player.setHealth(20);
            // save player inventory content to variable and clear it
            Container.itemStack = player.inventoryMenu.getItems().toArray(new ItemStack[0]);
            for (ItemStack itemStack : player.inventoryMenu.getItems()) {
                itemStack.setCount(0);
            }
            player.inventoryMenu.broadcastChanges();



        });

    }
}
