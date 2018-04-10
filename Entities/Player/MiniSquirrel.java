package PROG2_SS2018.Aufgabe2.Entities.Player;

public class MiniSquirrel extends Squirrel {

    private MasterSquirell master;

    public MiniSquirrel(MasterSquirell master, int Energie) {
        this.name = "MiniSquirrel";
        this.ID = 10;
        this.master = master;
        this.Energie = Energie;
    }


    public MasterSquirell getMaster() {
        return master;
    }

    public void setMaster(MasterSquirell master) {
        this.master = master;
    }
}
