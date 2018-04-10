package PROG2_SS2018.Aufgabe2.Entities.Player;

import PROG2_SS2018.Aufgabe2.Utils.*;

import java.util.Scanner;

public class MasterSquirell extends Squirrel implements UI{

    private final int SID;

    public MasterSquirell(int SID){
        super.name = "MasterSquirell";
        super.ID = 0;
        this.SID = SID;
        this.Energie = 1000;
    }

    public boolean isMineSquirrel(MiniSquirrel sq){
        if(this.SID == sq.getMaster().getSID()){
            return true;
        }
        return false;
    }

    @Override
    public void nextStep() {
        move(this.askMovement());
    }

    public int getSID() {
        return SID;
    }

    //Keine Gut Ausgelagerte Funktion
    public Vector2 askMovement() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Bitte geben sie eine Richtung an: " + '\n' +
                    "1 Rechts" + '\n' +
                    "2 Links" + '\n' +
                    "3 Oben" + '\n' +
                    "4 Unten" + '\n');

            int k = sc.nextInt();
            switch (k) {
                case 1:
                    return new Vector2(1, 0);
                case 2:
                    return new Vector2(-1, 0);
                case 3:
                    return new Vector2(0, 1);
                case 4:
                    return new Vector2(0, -1);
            }

            System.out.println("Dies war kein Echtes Statement!");
        }
    }

}
