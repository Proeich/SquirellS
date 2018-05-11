package Utils;

import Entities.Environment.*;
import Entities.Environment.EntityType;
import Entities.Player.MasterSquirell;
import Entities.Player.MiniSquirrel;
import Entities.Player.Squirrel;
import java.util.Random;

public class Board implements BoardView {


    private BoardConfig boardConfig;
    private EntitySet entitySet;
    private FlattenedBoard flattenedBoard;
    private int timer;

    public Board(EntitySet entitySet) {
        timer = 4;
        this.entitySet = entitySet;
        boardConfig = new BoardConfig();
        flattenedBoard = new FlattenedBoard(entitySet, boardConfig);
        setWalls();
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Round: ").append(timer).append('\n');
        for (Entity e : entitySet.getSet()) {

            if (e.getType() != EntityType.Wall) {
                sb.append("Name: ")
                        .append(e.getName())
                        .append("Energie: ")
                        .append(e.getEnergie())
                        .append("Lifes: ")
                        .append(e.getLifes())
                        .append("Stunned?: ")
                        .append(e.getStunned())
                        .append('\n');
            }
        }
        sb.append(flattenedBoard.flatten(this));

        return sb.toString();
    }

    public void superStep() {
        for (Entity e : entitySet.getSet()) {
            if (e.getStunned()) {
                e.setStunned(false);
            } else {
                switch (EntityType.getType(e.getID())) {
                    case GoodBeast:
                        if (timer % 4 == 0) {
                           flattenedBoard.tryMove((GoodBeast) e);
                        }
                        break;
                    case BadBeast:
                        if (timer % 4 == 0) {
                            flattenedBoard.tryMove((BadBeast) e);
                        }
                        break;
                    case MiniSquirrel:
                        if (timer % 4 == 0) {
                            flattenedBoard.tryMove((MiniSquirrel) e);
                        }
                        break;
                    case MasterSquirrel:
                        break;
                }
                try {
                    for (Entity entity : entitySet.getSet()) {
                        flattenedBoard.testDead(entity);
                    }
                }catch (Exception e1){e1.printStackTrace();}
            }
        }
    }



    private Entity[][] getBoard() {
        return flattenedBoard.getBoard(entitySet, boardConfig.getSize());
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public void setEntitySet(EntitySet entitySet) {
        this.entitySet = entitySet;
    }

    private void setWalls() {

        for (int i = 0; i < boardConfig.getSize().getX(); i++) {
            entitySet.insertIn(new Wall(new Vector2(i, 0)));
        }

        for (int i = 0; i < boardConfig.getSize().getY(); i++) {
            entitySet.insertIn(new Wall(new Vector2(0, i)));
        }

        for (int i = boardConfig.getSize().getY() - 1; i > 0; i--) {
            entitySet.insertIn(new Wall(new Vector2(boardConfig.getSize().getX() - 1, i)));
        }

        for (int i = boardConfig.getSize().getX() - 1; i > 0; i--) {
            entitySet.insertIn(new Wall(new Vector2(i, boardConfig.getSize().getY() - 1)));
        }

    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    @Override
    public Vector2 getSize() {
        return boardConfig.getSize();
    }

    private Vector2 posNeg(Vector2 in){
        Random rn = new Random();

        if(rn.nextInt(2) == 0){
            return new Vector2(in.getX()*-1,in.getY()*-1);
        }
        return new Vector2(in.getX()*1,in.getY()*1);
    }

    @Override
    public EntityType getEntityType(Vector2 pos) {
        return EntityType.getType(entitySet.getEntity(pos).getID());
    }

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }
}

