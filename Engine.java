package PROG2_SS2018.Aufgabe2;

import PROG2_SS2018.Aufgabe2.Entities.Environment.*;
import PROG2_SS2018.Aufgabe2.Entities.Player.*;
import PROG2_SS2018.Aufgabe2.Utils.*;

public class Engine {

    int y = 9;
    int ppp = 7;
    public static void main(String[] args){
        Engine gg = new Engine(
                new Entity[]{new GoodBeast(),
                new BadBeast(),
                new MasterSquirell(1)}
                ){};

        gg.run();

    }

    private EntitySet set;

    public Engine(){}

    private Engine(Entity[] in){
        set = new EntitySet();
        for(Entity en : in){
            set.insertIn(en);
        }

    }

    private void run(){
        while(true){
            if(processInput())
                break;

            render();
            update();
        }
    }

    private void update(){}

    private void render(){
        System.out.println(set.toString());
    }

    private boolean processInput(){
        set.superStep();
        return false;
    }




}
