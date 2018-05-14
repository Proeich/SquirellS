package Utils.Command;

import Utils.EntitySet;
import Utils.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class CommandScanner implements UI {

    private final Commands commands;
    private final EntitySet entitySet;

    public CommandScanner(EntitySet entitySet){
        this.entitySet = entitySet;
        commands = new Commands(entitySet);
    }

    private String[] read() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String a = bf.readLine();
        String[] buf = a.split(" ");
        return buf;
    }

    public boolean execCommand(String command){
        try {
            Method method = commands.getClass().getMethod(command);
            Object reval = method.invoke(commands);

            if(reval == null){
                return true;
            }
            return (Boolean)reval;
        }catch (NoSuchMethodException e1){
            System.out.println("No Such Utils.Command");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean execCommand(String command, Object Arguments){
        try {
            Method method = commands.getClass().getMethod(command, Arguments.getClass());
            Object reval = method.invoke(commands, Arguments);
            return true;
        }catch (NoSuchMethodException e1){
            System.out.println("No Such Utils.Command");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void getCommand() {
        while(true){
            try {
                System.out.print("Bitte geben sie einen Befehl ein!" + '\n' + "<help> für alle Befehle!"+'\n');
                String[] p = read();
                if(p.length < 2){
                    if(execCommand(p[0])){
                        break;
                    }
                }else{
                    if(execCommand(p[0],p[1])){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void message(String string) {

    }
}
