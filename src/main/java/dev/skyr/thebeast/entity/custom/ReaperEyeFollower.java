package dev.skyr.thebeast.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ReaperEyeFollower extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public ReaperEyeFollower(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)

                .build();
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
    }

    @Override
    public void move(MoverType p_19973_, Vec3 p_19974_) {}

    @Override
    public boolean isInvulnerable() {
        return true;

    }

    @Override
    public void setInvulnerable(boolean p_20332_) {
        super.setInvulnerable(true);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float p_21017_) {
        return damageSource.isCreativePlayer();
    }

    @Override
    public void checkDespawn() {}


}
