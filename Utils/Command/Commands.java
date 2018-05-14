package Utils.Command;

import Entities.Environment.Entity;
import Entities.Environment.EntityType;
import Utils.EntitySet;
import Utils.Vector2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Commands {

    private final EntitySet entitySet;

    public Commands(EntitySet entitySet){
        this.entitySet = entitySet;
    }

    public void writeShit(String s){
        System.out.println("(\"Hallo\")" + s);
    }

    public void down(){
        for(Entity e : entitySet.getSet()){
            if(e.getType() == EntityType.MasterSquirrel){
                (e).move(new Vector2(1, 0));
            }
        }
    }
    public void up(){
        for(Entity e : entitySet.getSet()){
            if(e.getType() == EntityType.MasterSquirrel){
                (e).move(new Vector2(-1, 0));
            }
        }
    }

    public void left(){
        for(Entity e : entitySet.getSet()){
            if(e.getType() == EntityType.MasterSquirrel){
                (e).move(new Vector2(0, -1));
            }
        }
    }

    public void right(){
        for(Entity e : entitySet.getSet()){
            if(e.getType() == EntityType.MasterSquirrel){
                (e).move(new Vector2(0, 1));
            }
        }
    }

    public void exit(){
        System.exit(42);
    }

    public boolean masterEnergy(){
        for(Entity e : entitySet.getSet()){
            if(e.getType() == EntityType.MasterSquirrel){
                System.out.println("MasterSquirellEnergie = " + e.getEnergie());
            }
        }
        return false;
    }

    public boolean help(){

        List<String> lis = new ArrayList<>();

        Class<?> e1 = this.getClass();

        for(Method method : e1.getDeclaredMethods()){
            StringBuilder sb = new StringBuilder();

            for(Parameter parameter : method.getParameters()){

                sb.append(parameter.getType().getName()).append(", ");
            }
            lis.add(method.getName() + " Args: (" + sb.toString() + ")");
        }
        StringBuilder sb = new StringBuilder();
        for(String s : lis){
            sb.append(s).append('\n');
        }
        System.out.println(sb.toString());
        return false;
    }


}
