package dev.skyr.thebeast.entity;

import dev.skyr.thebeast.Container;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Rotations;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class MoveEntity {
    public void moveEntity() {
        // get what entity the player is looking at

        var rayTraceResult = Minecraft.getInstance().hitResult;
        if (rayTraceResult == null) {
            return;
        }
        Entity targetedEntity = null;
        // check if rayTraceResult is an entity
        if (rayTraceResult.getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
            targetedEntity = ((net.minecraft.world.phys.EntityHitResult) rayTraceResult).getEntity();

        }
        if (targetedEntity == null) {
            return;
        }

        double mousePosX = Minecraft.getInstance().mouseHandler.xpos();
        double mousePosY = Minecraft.getInstance().mouseHandler.ypos();


        if (Minecraft.getInstance().mouseHandler.isRightPressed()) {
            var rotation = targetedEntity.getRotationVector();
            targetedEntity.setYRot(rotation.y + 5);
        }
        if (Minecraft.getInstance().mouseHandler.isLeftPressed()) {
            Container.revive_all();
        }

    }
}
