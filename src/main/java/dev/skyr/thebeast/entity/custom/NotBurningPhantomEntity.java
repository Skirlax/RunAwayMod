package dev.skyr.thebeast.entity.custom;

import dev.skyr.thebeast.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;

public class NotBurningPhantomEntity extends Phantom {

    public NotBurningPhantomEntity(EntityType<? extends Phantom> p_33101_, Level p_33102_) {
        super(p_33101_, p_33102_);
    }
    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.FLYING_SPEED,1.25D)
                .build();
    }



    @Override
    protected boolean isSunBurnTick() {
        return false;
    }


    @Override
    public boolean isNoAi() {
        return Container.asDisabled.get("NotBurningPhantomEntity");
    }

    @Override
    public void setNoAi(boolean p_21558_) {
        super.setNoAi(Container.asDisabled.get("NotBurningPhantomEntity"));
    }

    @Override
    public boolean isInvulnerable() {
        return Container.isInvulnerable.get("NotBurningPhantomEntity");
    }

    @Override
    public void setInvulnerable(boolean p_20332_) {
        super.setInvulnerable(Container.isInvulnerable.get("NotBurningPhantomEntity"));
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (Container.isInvulnerable.get("NotBurningPhantomEntity")) {
            return false;
        }
        return super.hurt(p_21016_, p_21017_);
    }
}
