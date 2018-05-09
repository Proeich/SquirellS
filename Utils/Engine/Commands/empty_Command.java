package Utils.Engine.Commands;

import Utils.Engine.Engine;

public class empty_Command extends MainCommand {

    public empty_Command(Engine engine){
        super(engine);
    }

    @Override
    public void run(String[] args) {
        engine.writeln("Nothing!");
    }
}
