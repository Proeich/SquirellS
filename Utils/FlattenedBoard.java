package Utils;

import Entities.Environment.BadBeast;
import Entities.Environment.Entity;
import Entities.Environment.EntityType;
import Entities.Environment.GoodBeast;
import Entities.Player.MasterSquirell;
import Entities.Player.MiniSquirrel;
import Entities.Player.Squirrel;

import java.util.Random;

class FlattenedBoard implements EntityContext{

    private BoardConfig boardConfig;
    private EntitySet entitySet;

    public FlattenedBoard(EntitySet entitySet, BoardConfig boardConfig){
        this.boardConfig = boardConfig;
        this.entitySet = entitySet;
    }

    public String flatten(Board board){
        StringBuilder sb = new StringBuilder();
        String[][] buffer = new String[board.getBoardConfig().getSize().getX()][board.getBoardConfig().getSize().getY()];
        int index1 = 0;
        int index2 = 0;

        for(Entity entity : board.getEntitySet().getSet()){
            buffer[entity.getPos().getX()][entity.getPos().getY()] = String.valueOf(entity.getPlaySymbol());
        }
        for(String[] s : buffer){
            index2=0;
            for(String ss : s){
                if(ss == null){
                    buffer[index1][index2] = " ";
                }
                index2++;
            }
            index1++;
        }

        index1 = 0;
        index2 = 0;
        for(String s[] : buffer){
            for (String ss : s){
                sb.append(ss).append(" ");
                index2++;
            }
            sb.append('\n');
            index1 ++;
        }
        return sb.toString();
    }

    public Entity[][] getBoard(EntitySet entitySet,Vector2 size){
        Entity[][] Buffer = new Entity[size.getY()][size.getX()];

        for(Entity e : entitySet.getSet()){
            Buffer[e.getPos().getY()][e.getPos().getX()] = e;
        }
        return Buffer;
    }

    public void testDead(Entity entity){
        switch(entity.getType()){
            case Wall:
                return;
            case BadBeast:
                if(entity.getLifes() <= 0){
                    killAndReplace(entity);
                }
                return;
            case GoodBeast:
                if(entity.getLifes() <= 0){
                    killAndReplace(entity);
                }
                return;
            case GoodPLant:
                if(entity.getLifes() <= 0){
                    killAndReplace(entity);
                }
                return;
            case BadPLant:
                if(entity.getLifes() <= 0){
                    killAndReplace(entity);
                }
                return;
            case MasterSquirrel:
                if(entity.getEnergie() <= 0){
                    System.exit(42);
                }
                return;
            case MiniSquirrel:
                if(entity.getEnergie() <= 0){
                    kill(entity);
                }
                return;
        }
    }


    @Override
    public Vector2 getSize() {
        return boardConfig.getSize();
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel) {
        for (Entity e : entitySet.getSet()) {
            if (e == miniSquirrel) {
                Vector2 mmoveVector;
                while(true) {
                    Random rn = new Random();

                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if(testOutrange(miniSquirrel,mmoveVector)){
                        break;
                    }
                }
                if (!testCollide(e, mmoveVector)) {
                    e.move(mmoveVector);
                }
            }
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast) {
        for (Entity e : entitySet.getSet()) {
            if (e == goodBeast) {
                Vector2 mmoveVector;
                while(true) {
                    Random rn = new Random();
                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if(testOutrange(goodBeast,mmoveVector)){
                        break;
                    }
                }
                if (!testCollide(e, mmoveVector)) {
                    e.move(mmoveVector);
                }
            }
        }
    }

    @Override
    public void tryMove(BadBeast badBeast) {
        for (Entity e : entitySet.getSet()) {
            Vector2 mmoveVector;
            if (e == badBeast) {
                try {
                    Vector2 movement = nearestPlayerEntity(badBeast.getPos(), 5).getPos();
                    movement = getMovement(badBeast.getPos(),movement);
                    mmoveVector = calcSquiPos(badBeast,movement);
                    if (mmoveVector != null) {
                        if (!testCollide(e, mmoveVector)) {
                            e.move(mmoveVector);
                        }
                        return;
                    }
                } catch (Exception ignored) {
                    while(true) {
                        Random rn = new Random();
                        mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                        if(testOutrange(badBeast,mmoveVector)){
                            break;
                        }
                    }
                    Random rn = new Random();
                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if (!testCollide(e, mmoveVector)) {
                        e.move(mmoveVector);
                    }
                }
            }
        }
    }

    @Override
    public void tryMove(MasterSquirell masterSquirell) {
        Vector2 mmoveVector;
        while(true) {
            mmoveVector = masterSquirell.askMovement();
            if(testOutrange(masterSquirell,mmoveVector)){
                break;
            }
        }

        if (!testCollide(masterSquirell, mmoveVector)) {
            masterSquirell.move(mmoveVector);
        }
    }

    @Override
    public Squirrel nearestPlayerEntity(Vector2 pos, int distance) {
        Entity[][] field = getBoard(entitySet, boardConfig.getSize());
        try {
            for (int i = 0; i < distance; i++) {
                for (int k = 0; k >= distance; k++) {
                    if (field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MasterSquirrel | field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MiniSquirrel) {
                        return (Squirrel) field[pos.getY() + i][pos.getX() + k];
                    }
                }
            }
            for (int i = 0; i < distance; i++) {
                for (int k = 0; k >= distance; k++) {
                    if (field[pos.getY() - i][pos.getX() - k].getType() == EntityType.MasterSquirrel | field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MiniSquirrel) {
                        return (Squirrel) field[(pos.getY() - i)][(pos.getX() - k)];
                    }
                }
            }
        } catch (Exception ignored) {
            return null;
        }

        return null;
    }

    @Override
    public void kill(Entity entity) {
        entitySet.deleteIn(entity);
    }

    @Override
    public void killAndReplace(Entity entity) {
        entitySet.getEntity(entity).revive();
    }

    @Override
    public EntityType getEntityType(Vector2 pos) {
        return EntityType.getType(entitySet.getEntity(pos).getID());
    }

    private boolean testOutrange(Entity entity, Vector2 move) {
        if (entity.getPos().getY() + move.getY() >= boardConfig.getSize().getY()) {
            return false;
        }
        return entity.getPos().getX() + move.getX() < boardConfig.getSize().getX();
    }


    private Vector2 posNeg(Vector2 in){
        Random rn = new Random();

        if(rn.nextInt(2) == 0){
            return new Vector2(in.getX()*-1,in.getY()*-1);
        }
        return new Vector2(in.getX()*1,in.getY()*1);
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


    private boolean testCollide(Entity entity, Vector2 moveVector) {
        Entity[][] buf = getBoard(entitySet,boardConfig.getSize());
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



}
