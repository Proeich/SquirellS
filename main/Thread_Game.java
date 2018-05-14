package main;

import Entities.Environment.*;
import Entities.Player.MasterSquirell;
import Utils.*;
import main.Starter.Starter;

public class Thread_Game implements Starter{

    private EntitySet set;
    private Board board;
    public static final int FPS = 10;
    private boolean synchron;

     public Thread_Game(){
         Entity[] in = {new GoodBeast(), new GoodPlant(), new BadBeast(), new MasterSquirell(0)};
         set = new EntitySet();
        board = new Board(this.set);
        for(Entity en : in){
            en.setFirstPos(board);
            set.insertIn(en);
        }
    }

    public void run(){
        while(true){
            render();

            if(!synchron)
                try{
                Thread.sleep(1000/FPS);
                }catch(InterruptedException e){}
            update();
            processInput();
        }
    }

    private void update(){
        board.superStep();
    }

    private void render(){
        System.out.println(board.show());
    }

    private boolean processInput(){
        return true;
    }
}
