package Aufgabe5Bot;

public interface BotControllerFactory {

    /**
     * Factory Methode um einen Botcontroller zu erstellen.
     * @return
     */

    public MasterSquirellBot createMasterBotController();
    public MiniSquirellBot createMiniBotController();

}
