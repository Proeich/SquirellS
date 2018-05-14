package main;

import Entities.Environment.Entity;
import Utils.Board;
import Utils.EntitySet;
import main.Starter.Starter;

public class Game_BotImpl implements Starter {

    private EntitySet set;
    private Board board;

    public Game_BotImpl(){}

    public Game_BotImpl(Entity[] in){
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
            processInput();
            update();
        }
    }

    private void update(){
        board.superStep();
    }

    private void render(){
        System.out.println(board.show());
    }

    private void processInput(){
    }
}
