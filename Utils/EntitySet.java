package PROG2_SS2018.Aufgabe2.Utils;
import PROG2_SS2018.Aufgabe2.Entities.Environment.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntitySet {

    private List<Entity> set = new ArrayList<>();

    public void insertIn(Entity in){
        set.add(in);
    }

    public void deleteIn(Entity in){
        set.remove(in);
    }

    public void superStep(){
        for(Entity en : set){
            en.nextStep();
        }
    }

    public List<Entity> getSet() {
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        sb.append("Inhalt des Sets: " + '\n');
        for(Entity en : set){
            sb.append(en.toString() + '\n');
        }
        return sb.toString();
    }
}
