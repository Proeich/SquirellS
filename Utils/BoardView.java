package Utils;

import Entities.Environment.EntityType;

public interface BoardView {

    EntityType getEntityType(Vector2 pos);
    Vector2 getSize();

}
