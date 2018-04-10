package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Utils.Vector2;

import java.util.Random;

public class Entity {

    protected String name;
    protected int ID;
    protected int Energie;
    protected Vector2 pos;

    public Entity(){
        Random rn = new Random();
        int k = rn.nextInt(5);
        pos = new Vector2(k,k);
    }

    public void updateEnergy(int inp){
        Energie+=inp;
    }

    public void nextStep(){
        Random rn = new Random();
        switch(rn.nextInt(9)){
            case 0:
                move(new Vector2(0,1));
                return;
            case 1:
                move(new Vector2(0,-1));
                return;
            case 2:
                move(new Vector2(1,0));
                return;
            case 3:
                move(new Vector2(-1,0));
                return;
            case 4:
                move(new Vector2(1,1));
                return;
            case 5:
                move(new Vector2(1,-1));
                return;
            case 6:
                move(new Vector2(-1,1));
                break;
            case 7:
                move(new Vector2(-1,-1));
                return;
            case 8:
                move(new Vector2(0,0));
                return;

        }
    }

    public void move(Vector2 in){

        this.pos.setX(this.pos.getX() + in.getX());
        this.pos.setY(this.pos.getY() + in.getY());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: " + ID + '\n');
        sb.append("Name: " + name + '\n');
        sb.append("Energie: " + Energie + '\n');
        sb.append("X: " + pos.getX() + "; Y: " + pos.getY() + '\n');
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
