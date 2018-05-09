package Utils.Engine.Commands;

import Utils.Engine.Engine;

public abstract class MainCommand {

    protected Engine engine;
    public MainCommand(Engine engine){
        this.engine = engine;
    }

    public void run(String[] args){}


}
