package dev.skyr.thebeast.entity.client;

import dev.skyr.thebeast.TheBeast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Phantom;

public class NotBurningPhantomRenderer extends PhantomRenderer {
    public NotBurningPhantomRenderer(EntityRendererProvider.Context p_174338_) {
        super(p_174338_);
    }

    @Override
    public ResourceLocation getTextureLocation(Phantom p_115679_) {
        return new ResourceLocation(TheBeast.MODID,"textures/entity/phantom/phantom.png");
    }
}
