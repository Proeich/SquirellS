package main;


import Entities.Environment.*;
import Entities.Player.MasterSquirell;

class Launcher {

    public static void main(String[] args) {
        Game game = new Game( new Entities.Environment.Entity[]{new BadBeast(), new GoodPlant(),new GoodBeast(), new MasterSquirell(0)});


        game.run();
    }

}
