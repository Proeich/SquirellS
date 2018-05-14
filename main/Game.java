package main;

import Entities.Environment.*;
import Utils.*;
import Utils.Command.*;
import main.Starter.Starter;

public class Game implements Starter {

    private EntitySet set;
    private Board board;
    private UI ui;

    public Game(Entity[] in){
        set = new EntitySet();
        board = new Board(this.set);
        for(Entity en : in){
            en.setFirstPos(board);
            set.insertIn(en);
        }
        ui = new CommandScanner(set);
    }

    @Override
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
