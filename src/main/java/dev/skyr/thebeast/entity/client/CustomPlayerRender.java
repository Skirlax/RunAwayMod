package dev.skyr.thebeast.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CustomPlayerRender extends GeoEntityRenderer<ReaperEntity> {


    public CustomPlayerRender(EntityRendererProvider.Context renderManager, AnimatedGeoModel<ReaperEntity> modelProvider) {
        super(renderManager, modelProvider);
    }

    @Override
    public ResourceLocation getTextureLocation(ReaperEntity entity) {
        return new ResourceLocation(TheBeast.MODID, "textures/entity/reaper/reaper.png");
    }


}

