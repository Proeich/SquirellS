package Aufgabe5Bot;


import Entities.Environment.Entity;
import Entities.Environment.EntityType;
import Utils.EntityContext;
import Utils.EntitySet;
import Utils.Vector2;

public class ControllerContextImpl implements ControllerContext{

    private final EntitySet entitySet;
    private final Entity entity;

    public ControllerContextImpl(EntitySet entitySet, Entity entity){
        this.entitySet = entitySet;
        this.entity = entity;
    }

    @Override
    public Vector2 getViewLowerLeft() {
        return null;
    }

    @Override
    public Vector2 getViewUpperRight() {
        return null;
    }

    @Override
    public EntityType getEntityAt(Vector2 vector2) {
        return entitySet.getEntity(vector2).getType();
    }

    @Override
    public void move(Vector2 vector2) {
        entity.move(vector2);
    }

    @Override
    public void spawnMiniBot(Vector2 direction, int energy) {

    }

    @Override
    public int getEnergy() {
        return 0;
    }
}
