package PROG2_SS2018.Aufgabe2;

import PROG2_SS2018.Aufgabe2.Entities.Environment.*;
import PROG2_SS2018.Aufgabe2.Entities.Player.*;
import PROG2_SS2018.Aufgabe2.Utils.*;

public class Engine {


    int k = 2;

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

    public Engine(Entity[] in){
        set = new EntitySet();
        for(Entity en : in){
            set.insertIn(en);
        }

    }

    public void run(){
        while(true){
            processInput();
            render();
            update();
        }
    }

    private void update(){}

    public void render(){
        System.out.println(set.toString());
    }

    private void processInput(){
        set.superStep();
    }




}
