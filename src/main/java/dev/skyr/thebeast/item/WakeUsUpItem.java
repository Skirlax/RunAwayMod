package dev.skyr.thebeast.item;

import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.networking.ModPackets;
import dev.skyr.thebeast.networking.packet.LightningAroundPlayer;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WakeUsUpItem extends Item {

    public WakeUsUpItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            Container.revive_all();
            ModPackets.sendToServer(new LightningAroundPlayer());
        }
        this.setDamage(new ItemStack(this), 1);
        return super.use(level, player, interactionHand);
    }


}
