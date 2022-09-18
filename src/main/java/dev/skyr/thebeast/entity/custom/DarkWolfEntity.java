package dev.skyr.thebeast.entity.custom;

import dev.skyr.thebeast.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class DarkWolfEntity extends Wolf {
    public DarkWolfEntity(EntityType<? extends Wolf> entityType, Level level) {
        super(entityType, level);

    }
    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.25D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 1000.0D)
                .add(Attributes.JUMP_STRENGTH,1.5D)
                .build();
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new DarkWolfAgent(this));

    }

    @Override
    public boolean isInvulnerable() {
        return Container.isInvulnerable.get("DarkWolfEntity");
    }

    @Override
    public void setInvulnerable(boolean p_20332_) {
        super.setInvulnerable(Container.isInvulnerable.get("DarkWolfEntity"));
    }

//    @Override
//    public boolean isNoAi() {
//        return Container.asDisabled.get("DarkWolfEntity");
//    }
//
//    @Override
//    public void setNoAi(boolean p_21558_) {
//        super.setNoAi(Container.asDisabled.get("DarkWolfEntity"));
//    }

//    @Override
//    public boolean hurt(DamageSource p_30386_, float p_30387_) {
//        if (Container.isInvulnerable.get("DarkWolfEntity")) {
//            return false;
//        }
//        return super.hurt(p_30386_, p_30387_);
//    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (this.level.isDay() || this.isOnFire()) {
            entity.setSecondsOnFire(8);
        }
        return super.doHurtTarget(entity);
    }
}
