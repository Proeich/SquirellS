package Aufgabe5Bot;

import Entities.Environment.EntityType;
import Utils.Vector2;

public interface ControllerContext {

    public Vector2 getViewLowerLeft();
    public Vector2 getViewUpperRight();
    public EntityType getEntityAt(Vector2 vector2);
    public void move(Vector2 vector2);
    public void spawnMiniBot(Vector2 direction, int energy);
    public int getEnergy();

}
