package main;

import Entities.Environment.*;
import Utils.Engine.*;
import Utils.*;

public class Game{

    private EntitySet set;
    private Board board;
    private Engine engine;

    public Game(){}

    public Game(Entity[] in){
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
