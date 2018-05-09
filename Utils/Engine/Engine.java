package Utils.Engine;

import Entities.Player.MasterSquirell;
import Utils.Engine.Commands.*;
import Utils.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Engine {

    private Board board;
    private Boolean end;

    private HashMap<String, CompiledValue> hashe = new HashMap();
    private MasterSquirell masterSquirell;
    private CommandScanner cs = new CommandScanner();

    private String txt;


    public Engine(Board board){
        fillHashe();
        this.board = board;
    }

    public void run(){
            end = false;
            while(!end){
                txt = cs.read();
                execCommand(txt);
            }
    }

    public void run(MasterSquirell masterSquirell){
        this.masterSquirell = masterSquirell;
        end = false;
        while(!end){
            txt = cs.read();
            execCommand(txt);
        }
        this.board.superStep();
    }

    public void writeln(String n){
        System.out.println(n);
    }
    public void write(String n){
        System.out.print(n);
    }

    private void fillHashe(){
        hashe.put("exit", new CompiledValue(new exit_Command(this), "<int> Ignored"));
        hashe.put("empty", new CompiledValue(new empty_Command(this), "<int> Ignored"));
        hashe.put("help", new CompiledValue(new help_Command(this), "<int> Ignored"));
        hashe.put("move", new CompiledValue(new move_Command(this), "<int> Ignored"));
        hashe.put("spawnMini", new CompiledValue(new miniSquirellSpawn_Command(this), "<int> Energie"));
    }

    public List<String> getHashes(){
        List<String> lis = new ArrayList<>();
        int index = 0;
        Set<String> set = hashe.keySet();
        for(String s : set){
            lis.add(s);
        }
        return lis;
    }

    private void execCommand(String string){
        hashe.getOrDefault(cs.getCommand(string),hashe.get("empty")).run(cs.getArgs(txt).split(" "));
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public MasterSquirell getMasterSquirell() {
        return masterSquirell;
    }

    public void setMasterSquirell(MasterSquirell masterSquirell) {
        this.masterSquirell = masterSquirell;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Boolean getEnd() {
        return end;
    }

    public CompiledValue getHashe(String key){
        return hashe.get(key);
    }
}
