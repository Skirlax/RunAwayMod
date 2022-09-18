package dev.skyr.thebeast.entity.custom;

import dev.skyr.thebeast.Container;
import dev.skyr.thebeast.networking.ModPackets;
import dev.skyr.thebeast.networking.packet.SpawnDarkWolfWLightningC2SPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ReaperEntity extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private boolean isAttackingPlayer = false;
    private boolean canSummon = true;


    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)

                .build();
    }
    public ReaperEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    protected void registerGoals() {

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void registerControllers(AnimationData data) {

        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        Player player = this.level.getNearestPlayer(this, 50);




        if (player == null) {
            return PlayState.CONTINUE;
        }

        if(this.isWithinMeleeAttackRange(player) && player.isAlive() && player.canBeSeenAsEnemy()) {
            if (this.getHealth() < 10) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("callmob", false));
                if (this.canSummon) {
                    for (int x = 0; x < 2; x++) {
                        ModPackets.sendToServer(new SpawnDarkWolfWLightningC2SPacket());
                    }
                    this.canSummon = false;
                }
                return PlayState.CONTINUE;
            }
            if (player.getHealth() < 5) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("heavyslash", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("slashone", true));

            return PlayState.CONTINUE;
        }

        if (event.isMoving() && player.canBeSeenAsEnemy() && this.distanceTo(player) <= 20) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("run", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }



        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean isInvulnerable() {
        return Container.isInvulnerable.get("ReaperEntity");

    }

    @Override
    public void setInvulnerable(boolean p_20332_) {
        super.setInvulnerable(Container.isInvulnerable.get("ReaperEntity"));
    }

    @Override
    public boolean isNoAi() {
        return Container.asDisabled.get("ReaperEntity");
    }

    @Override
    public void setNoAi(boolean p_21558_) {
        super.setNoAi(Container.asDisabled.get("ReaperEntity"));
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (Container.isInvulnerable.get("ReaperEntity")) {
            return false;
        }
        return super.hurt(p_21016_, p_21017_);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }
}
