package dev.skyr.thebeast.entity.custom;

import dev.skyr.thebeast.aimodels.EntityBrain;
import dev.skyr.thebeast.aimodels.McStates;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class DarkWolfAgent extends Goal {
    private int episodes = 50000;
    private int maxSteps = 500;
    private double learningRate = 0.8;
    private double gamma = 0.95;
    private double reward = 0.0;

    private double epsilon = 0.65;
    private McStates mcStates;

    private Double[] currentState;
    private Hashtable<String, Double[]> states  = new Hashtable<>();
    private Hashtable<String, Double> rewards = new Hashtable<>();

    private Player target;
    EntityBrain entityBrain;
    private double[][] Q;

    private DarkWolfEntity me;

    public DarkWolfAgent(DarkWolfEntity darkWolf) {
        me = darkWolf;
        target = me.level.players().get(0);
        mcStates = new McStates(me);
        states = mcStates.get();
        entityBrain = new EntityBrain(me, target);





    }

    @Override
    public void start() {
        entityBrain.train();
        entityBrain.saveToFile();
        currentState = entityBrain.run(null);
    }

    @Override
    public void tick() {
        currentState = entityBrain.run(currentState);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public boolean canUse() {
        return true;
    }
}



