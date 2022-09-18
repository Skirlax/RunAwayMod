package dev.skyr.thebeast.entity.client;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.entity.custom.ReaperEntity;
import dev.skyr.thebeast.entity.custom.ReaperEyeFollower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReaperEyeFollowerModel extends AnimatedGeoModel<ReaperEyeFollower> {

    @Override
    public ResourceLocation getModelResource(ReaperEyeFollower object) {
        return new ResourceLocation(TheBeast.MODID, "geo/reaper_eye_follower.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ReaperEyeFollower object) {
        return new ResourceLocation(TheBeast.MODID, "textures/entity/reaper_eye_follower/reaper_eye_follower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ReaperEyeFollower animatable) {
        return new ResourceLocation(TheBeast.MODID, "animations/reaper_eye_follower.animation.json");
    }
}
