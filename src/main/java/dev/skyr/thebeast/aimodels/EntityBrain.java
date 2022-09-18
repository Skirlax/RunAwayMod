package dev.skyr.thebeast.aimodels;

import dev.skyr.thebeast.entity.custom.DarkWolfAgent;
import dev.skyr.thebeast.entity.custom.DarkWolfEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EntityBrain {
    private DarkWolfEntity me;
    private Player target;
    private int episodes = 5000;
    private int steps = 100;
    private double epsilon = 0.8;

    private double learningRate = 0.8;
    private double gamma = 0.95;

    private Hashtable<String, Double[]> actions;

    private Hashtable<String, Double[]> states;
    private Hashtable<String, Double> rewards;
    Double[][] Q;
    public EntityBrain(DarkWolfEntity entity, Player target) {
        me = entity;
        states = McStates.createAndGet(me);
        actions = McActions.createAndGet();
        rewards = McRewards.createAndGet();

        Q = new Double[states.size()][actions.size()];
        for (int i = 0; i < states.size(); i++) {
            for (int j = 0; j < actions.size(); j++) {
                Q[i][j] = 0.0;
            }
        }
        this.target = target;

    }

    public void train() {
        Random random = new Random();
        for (int episode = 0; episode < episodes; episode++) {
            Double[] currentState = states.get("MyPos");
            for (int step = 0; step < steps; step++) {
                boolean isDead = me.isDeadOrDying();
                boolean isTargetDead = target.isDeadOrDying();
                Double[] action;
                if (random.nextDouble() < epsilon) {
                    action = (Double[]) actions.values().toArray()[random.nextInt(actions.size())];

                } else {
                    action = getBestAction(currentState);
                }
                currentState = getDoubles(currentState, isDead, isTargetDead, action);
                if (isDead || isTargetDead) {
                    epsilon -= 0.001;
                    break;
                }


            }
        }
    }
    public Double[] run(Double[] state) {
        if (state == null) {
            state = states.get("MyPos");
        }

        boolean isDead = me.isDeadOrDying();
        boolean isTargetDead = target.isDeadOrDying();
        Double[] action = getBestAction(state);
        state = getDoubles(state, isDead, isTargetDead, action);
        if (isDead || isTargetDead) {
            return null;
        }
        return state;


    }

    private Double[] getDoubles(Double[] currentState, boolean isDead, boolean isTargetDead, Double[] action) {
        Double[] nextState = getNextState(action);
        double reward = McRewards.getReward(action, rewards, isDead, isTargetDead);
        int stateIndex = ArrayUtils.indexOf(states.values().toArray(), currentState);
        int actionIndex = ArrayUtils.indexOf(actions.values().toArray(), action);
        Q[stateIndex][actionIndex] = Q[stateIndex][actionIndex] + learningRate * (reward + gamma * arrayMax(Q[stateIndex]) - Q[stateIndex][actionIndex]);
        currentState = nextState;
        return currentState;
    }

    public void saveToFile() {
        try {
            File file = new File("../src/main/resources/assets/thebeast/qtables/Q.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(Arrays.deepToString(Q));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile() {
        try {
            File file = new File("../src/main/resources/assets/thebeast/qtables/Q.txt");
            Scanner scanner = new Scanner(file);
            String data = scanner.nextLine();
            String[] rows = data.split("],");
            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(",");
                for (int j = 0; j < cols.length; j++) {
                    Q[i][j] = Double.parseDouble(cols[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private Double[] getBestAction(Double[] state) {
        double bestValue = 0;
        Double[] bestAction = (Double[]) actions.values().toArray()[0];
        int stateIndex = ArrayUtils.indexOf(states.values().toArray(), state);
        for (int i = 0; i < actions.size(); i++) {
            if (Q[stateIndex][i] > bestValue) {
                bestValue = Q[stateIndex][i];
                bestAction = (Double[]) actions.values().toArray()[i];
            }
        }
        return bestAction;

    }
    private Double arrayMax(Double[] array) {
        return Collections.max(Arrays.asList(array));


    }
    private Double[] getNextState(Double[] action) {
        Double[] affectedState = null;
        if (action[0] == 1.0 || action[1] == 1.0 || action[2] == 1.0 || action[3] == 1.0) {
            Direction direction = me.getDirection();
            me.moveTo(me.getX() + direction.getStepX(), me.getY() + direction.getStepY(), me.getZ() + direction.getStepZ());
            affectedState = states.get("MyPos");
        }
        else if (action[4] == 1.0) {
            me.getJumpControl().jump();
            affectedState = states.get("MyPos");
        }
        else if(action[5] == 1.0) {
            if (me.isWithinMeleeAttackRange(target)) {
                me.doHurtTarget(target);
            }
            affectedState = states.get("TargetHealth");
        }
        else if (action[6] == 1.0) {
            me.setSprinting(true);
            affectedState = states.get("MyPos");
        }
        return affectedState;




    }
}
