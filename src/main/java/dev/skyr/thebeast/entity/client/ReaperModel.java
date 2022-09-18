package dev.skyr.thebeast.entity.client;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReaperModel extends AnimatedGeoModel<ReaperEntity> {
    @Override
    public ResourceLocation getModelResource(ReaperEntity object) {
        return new ResourceLocation(TheBeast.MODID, "geo/reaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ReaperEntity object) {
        return new ResourceLocation(TheBeast.MODID, "textures/entity/reaper/reaper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ReaperEntity animatable) {
        return new ResourceLocation(TheBeast.MODID, "animations/reaper.animation.json");
    }
}
