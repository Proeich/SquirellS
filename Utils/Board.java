package Utils;

import Entities.Environment.*;
import Entities.Environment.EntityType;
import Entities.Player.MasterSquirell;
import Entities.Player.MiniSquirrel;
import Entities.Player.Squirrel;
import Utils.Engine.Engine;

import java.util.Random;

public class Board implements BoardView {


    private BoardConfig boardConfig;
    private EntitySet entitySet;
    private FlattenedBoard flattenedBoard;
    private int timer;
    private Engine engine;

    public Board(EntitySet entitySet) {
        this.engine = new Engine(this);
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
        this.engine = new Engine(this);
        try {
            for (Entity entity : entitySet.getSet()) {
                flattenedBoard.testDead(entity);
            }
        }catch (Exception e1){e1.printStackTrace();}

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

    private Vector2 getMovement(Vector2 badBeastV,Vector2 squirrelV){
        Vector2 v2 = new Vector2(0,0);
        if (badBeastV.getX() > squirrelV.getX()){
            v2.setX(-1);
        }
        if(badBeastV.getY() > squirrelV.getY()){
            v2.setY(-1);
        }
        if (badBeastV.getX() < squirrelV.getX()){
            v2.setX(1);
        }
        if(badBeastV.getY() < squirrelV.getY()){
            v2.setY(1);
        }
        return v2;

    }

    private Vector2 posNeg(Vector2 in){
        Random rn = new Random();

        if(rn.nextInt(2) == 0){
            return new Vector2(in.getX()*-1,in.getY()*-1);
        }
        return new Vector2(in.getX()*1,in.getY()*1);
    }

    private Vector2 calcSquiPos(BadBeast badBeast, Vector2 movement){

        Vector2 mmoveVector = new Vector2(0,0);
        if(movement.getY() > badBeast.getPos().getY() && movement.getX() > badBeast.getPos().getX()){
            mmoveVector = new Vector2(1,1);
        }
        if(movement.getY() < badBeast.getPos().getY() && movement.getX() < badBeast.getPos().getX()){
            mmoveVector = new Vector2(-1,-1);
        }
        if(movement.getY() < badBeast.getPos().getY() && movement.getX() > badBeast.getPos().getX()){
            mmoveVector = new Vector2(-1,1);
        }
        if(movement.getY() > badBeast.getPos().getY() && movement.getX() < badBeast.getPos().getX()){
            mmoveVector = new Vector2(1,-1);
        }
        return mmoveVector;

    }

    @Override
    public EntityType getEntityType(Vector2 pos) {
        return EntityType.getType(entitySet.getEntity(pos).getID());
    }

    private boolean testCollide(Entity entity, Vector2 moveVector) {
        Entity[][] buf = getBoard();
        if(entity.getPos().getY() + moveVector.getY() >= boardConfig.getSize().getY() | entity.getPos().getX() + moveVector.getX() >= boardConfig.getSize().getY()){
            return false;
        }
        if(moveVector.getY() == 0 && moveVector.getX() == 0){
            return false;
        }

        if (buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()] != null) {
            switch (buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()].getType()) {
                case GoodPLant:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithFriendly(entitySet, entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                    return true;
                case BadPLant:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithHarmful(entitySet, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()], entity);
                    return true;
                case GoodBeast:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithFriendly(entitySet, entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                    return true;
                case BadBeast:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithHarmful(entitySet, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()], entity);
                    return true;
                case MiniSquirrel:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        return true;
                    }
                case MasterSquirrel:
                case Wall:
                    entitySet.collideWithWall(entity);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public void spawnMiniSquirell(MasterSquirell masterSquirell, int energie){
        MiniSquirrel buf = new MiniSquirrel(masterSquirell, energie);
        Random rn = new Random();
        int x = rn.nextInt(2);
        int y = rn.nextInt(2);
        buf.setPos(new Vector2(masterSquirell.getPos().getX(),masterSquirell.getPos().getY()));
        while(!testCollide(buf, new Vector2(x,y))){
            x = rn.nextInt(2);
            y = rn.nextInt(2);
        }
        this.entitySet.insertIn(buf, new Vector2(x,y));
    }

    private boolean testOutrange(Entity entity, Vector2 move) {
        if (entity.getPos().getY() + move.getY() >= boardConfig.getSize().getY()) {
            return false;
        }
        return entity.getPos().getX() + move.getX() < boardConfig.getSize().getX();
    }
}

