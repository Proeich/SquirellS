package main;


import Aufgabe5Bot.MasterSquirellBot;
import Entities.Environment.*;
import Entities.Player.MasterSquirell;
import main.Game;
import main.Starter.Starter;

class Launcher {

    public static void main(String[] args) {

        Starter starter = new Game(new Entities.Environment.Entity[]{new BadBeast(), new GoodPlant(),new GoodBeast(), new MasterSquirell(0)});
        starter.run();

        /*
        Game_BotImpl game_botImpl = new Game_BotImpl(new Entities.Environment.Entity[]{new BadBeast(), new GoodPlant(),new GoodBeast(), new MasterSquirellBot()});
        game_botImpl.run();
        */

    }

}
