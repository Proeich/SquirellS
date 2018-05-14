package Aufgabe5Bot;

import Entities.Environment.Entity;
import Entities.Environment.EntityType;
import Entities.Player.MasterSquirell;
import Entities.Player.Squirrel;
import Utils.Vector2;

import java.util.ArrayList;
import java.util.List;

public class MasterSquirellBot extends MasterSquirell implements BotController, BotControllerFactory{

    public MasterSquirellBot(){
        super(0);
    }

    @Override
    public void nextStep(ControllerContext view) {
        EntityType[][] map = new EntityType[3][3];

        List<Vector2> dangerZone = new ArrayList<>();
        List<Vector2> wealthZone = new ArrayList<>();

        Vector2 param = view.getViewUpperRight();
        param.setX(param.getX() - 2);

        for(int y = 0; y <= 3; y++){
            for(int x = 0; x <= 3; x++){
               map[y][x] = view.getEntityAt(new Vector2(param.getX() + x,param.getY() + y));
            }
        }

        for(int y = 0; y <= 3; y++){
            for(int x = 0; x <= 3; x++){
                switch(map[y][x]){
                    case MasterSquirrel:
                        break;
                    case MiniSquirrel:
                        break;
                    case GoodPLant:
                        wealthZone.add(new Vector2(x,y));
                    case BadPLant:
                        dangerZone.add(new Vector2(x,y));
                    case GoodBeast:
                        wealthZone.add(new Vector2(x,y));
                    case Wall:
                        dangerZone.add(new Vector2(x,y));
                    case BadBeast:
                        dangerZone.add(new Vector2(x,y));
                }
            }
        }

        //CalcMove
        int right = 0;
        int left = 0;
        int up = 0;
        int down = 0;

        Vector2 moveMent = new Vector2(0,0);

        for(Vector2 vector2 : dangerZone){
            if(vector2.getY() == 2){
                down += EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getY() == 0){
                up += EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 0){
                left += EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 2){
                right += EntityType.getDanger(view.getEntityAt(vector2));
            }
        }

        for(Vector2 vector2 : wealthZone){
            if(vector2.getY() == 2){
                down -= EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getY() == 0){
                up -= EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 0){
                left -= EntityType.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 2){
                right -= EntityType.getDanger(view.getEntityAt(vector2));
            }
        }

        if(up > down){
            moveMent.setY(-1);
        }else if(up < down){
            moveMent.setY(1);
        }else if(up == down){
            moveMent.setY(0);
        }

        if(right > left){
            moveMent.setX(1);
        }else if(right < left){
            moveMent.setX(-1);
        }else if(right == left){
            moveMent.setX(0);
        }

        view.move(moveMent);

    }

    @Override
    public MasterSquirellBot createMasterBotController() {
        return this;
    }

    @Override
    public MiniSquirellBot createMiniBotController() {
        return null;
    }
}
