package Utils;

import Entities.Environment.*;
import Entities.Player.MasterSquirell;
import Entities.Player.MiniSquirrel;
import Entities.Player.Squirrel;

public interface EntityContext {

    Vector2 getSize();
    void tryMove(MiniSquirrel miniSquirrel);
    void tryMove(GoodBeast goodBeast);
    void tryMove(BadBeast BadBeast);
    void tryMove(MasterSquirell masterSquirell);
    Squirrel nearestPlayerEntity(Vector2 pos, int distance);

    void kill(Entity entity);
    void killAndReplace(Entity entity);
    EntityType getEntityType(Vector2 pos);

}
