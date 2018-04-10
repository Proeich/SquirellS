package PROG2_SS2018.Aufgabe2.Entities.Environment;

public class Wall extends Entity {

    public Wall(){
        super();
        this.ID = 5;
        this.name = "Wall";
        this.Energie = -10;
    }

    @Override
    public void nextStep() {

    }
}
