package main;

import main.Starter.Starter;

class Launcher {

    public static void main(String[] args) {

        Starter starter = new Thread_Game();
        starter.run();

        /*
        Game_BotImpl game_botImpl = new Game_BotImpl(new Entities.Environment.Entity[]{new BadBeast(), new GoodPlant(),new GoodBeast(), new MasterSquirellBot()});
        game_botImpl.run();
        */

    }

}
