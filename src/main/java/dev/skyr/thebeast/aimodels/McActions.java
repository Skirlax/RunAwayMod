package dev.skyr.thebeast.aimodels;

import java.util.Hashtable;

public class McActions {
    private Hashtable<String, Double[]> actions = new Hashtable<>();

    public static Hashtable<String,Double[]> createAndGet() {
        Hashtable<String, Double[]> actions = new Hashtable<>();
        actions.put("MoveForward",new Double[]{1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0});
        actions.put("MoveBackward",new Double[]{0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0});
        actions.put("MoveLeft",new Double[]{0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0});
        actions.put("MoveRight",new Double[]{0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0});
        actions.put("Jump",new Double[]{0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0});
        actions.put("Attack",new Double[]{0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0});
        actions.put("Sprint",new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0});
        return actions;
    }


}

