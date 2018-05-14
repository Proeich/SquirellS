package Aufgabe5Bot;

public class MiniSquirellBot implements BotController, BotControllerFactory{


    @Override
    public void nextStep(ControllerContext view) {

    }

    @Override
    public MasterSquirellBot createMasterBotController() {
        return null;
    }

    @Override
    public MiniSquirellBot createMiniBotController() {
        return null;
    }
}
