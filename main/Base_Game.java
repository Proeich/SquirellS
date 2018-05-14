package main;

import Entities.Environment.*;
import Entities.Player.MasterSquirell;
import Utils.*;
import Utils.Command.*;
import main.Starter.Starter;

public class Base_Game implements Starter {

    private EntitySet set;
    private Board board;
    private UI ui;

    public Base_Game(){
        Entity[] in = {new GoodBeast(), new GoodPlant(), new BadBeast(), new MasterSquirell(0)};
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
