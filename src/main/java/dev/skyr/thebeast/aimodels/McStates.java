package dev.skyr.thebeast.aimodels;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Hashtable;

public class McStates {
    private LivingEntity me;

    private Hashtable<String,Double[]> states = new Hashtable<>();
    public McStates(LivingEntity entity) {
        me = entity;
        create();

    }
    public static Hashtable<String,Double[]> createAndGet(LivingEntity entity) {
        McStates mcStates = new McStates(entity);
        return mcStates.get();
    }

    public static Hashtable<String, Double[]> update(LivingEntity entity) {
        McStates mcStates = new McStates(entity);
        return mcStates.get();
    }


    private void create() {
        if (me.level.getNearestPlayer(me,300) != null) {
            Player target = me.level.getNearestPlayer(me, 300);
            states.put("TargetPos",new Double[]{target.getX(),target.getY(),target.getZ()});
            states.put("MyPos",new Double[]{target.getX(),target.getY(),target.getZ()});
            states.put("Health",new Double[]{(double) me.getHealth()});
            states.put("TargetHealth",new Double[]{(double) target.getHealth()});






        }
    }
    public Hashtable<String, Double[]> get() {
        return states;
    }

}
