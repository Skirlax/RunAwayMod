package dev.skyr.thebeast.aimodels;

import java.util.Hashtable;

public class McRewards {


    public static Hashtable<String,Double> createAndGet() {
        Hashtable<String, Double> rewards = new Hashtable<>();
        rewards.put("Move",0.9);
        rewards.put("Jump",0.8);
        rewards.put("Attack",5.0);
        rewards.put("Sprint",1.0);
        rewards.put("Stop",0.0);
        rewards.put("Kill",100.0);
        rewards.put("Death",-100.0);
        return rewards;

    }

    public static double getReward(Double[] action, Hashtable<String,Double> rewards,boolean isDead,boolean killed) {
        double reward = 0.0;
        if (killed) {
            return rewards.get("Kill");
        }
        if (isDead) {
            return rewards.get("Death");
        }
        if (action[0] == 1.0 || action[1] == 1.0 || action[2] == 1.0 || action[3] == 1.0) {
            return rewards.get("Move");
        }
        if (action[4] == 1.0) {
            return rewards.get("Jump");
        }
        if (action[5] == 1.0) {
             return rewards.get("Attack");

        }
        if (action[6] == 1.0) {
            return rewards.get("Sprint");
        }
        return rewards.get("Stop");

    }
}
