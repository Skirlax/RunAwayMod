package dev.skyr.thebeast.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ReaperRenderer extends GeoEntityRenderer<ReaperEntity> {


    public ReaperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ReaperModel());
        this.shadowRadius = 0.7F;

    }
    @Override
    public ResourceLocation getTextureLocation(ReaperEntity entity) {
        return new ResourceLocation(TheBeast.MODID, "textures/entity/reaper/reaper.png");
    }
    @Override
    public RenderType getRenderType(ReaperEntity animatable, float partialTicks, PoseStack poseStack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {

        return super.getRenderType(animatable, partialTicks, poseStack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }





    // render player as reaper

}
