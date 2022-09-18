package dev.skyr.thebeast.entity.client;

import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.TheBeast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class DarkWolfRenderer extends WolfRenderer {

    public DarkWolfRenderer(EntityRendererProvider.Context context) {

        super(context);


    }



    @Override
    public ResourceLocation getTextureLocation(Wolf p_116526_) {
        return new ResourceLocation(TheBeast.MODID, "textures/entity/dark_wolf/dark_wolf.png");
    }
}

