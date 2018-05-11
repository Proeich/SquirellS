package main;

import Entities.Environment.*;
import Utils.*;
import Utils.Command.*;

public class Game{

    private EntitySet set;
    private Board board;
    private UI ui;

    public Game(){}

    public Game(Entity[] in){
        set = new EntitySet();
        board = new Board(this.set);
        for(Entity en : in){
            en.setFirstPos(board);
            set.insertIn(en);
        }
        ui = new CommandScanner(set);
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
        ui.getCommand();
    }
}
