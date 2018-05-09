package Utils.Engine.Commands;

import Utils.Engine.Engine;

public class move_Command extends MainCommand {

        public move_Command(Engine engine){
            super(engine);
        }

        @Override
        public void run(String[] args) {
            this.engine.getBoard().tryMove(this.engine.getMasterSquirell());
            this.engine.setEnd(true);
        }
    }
