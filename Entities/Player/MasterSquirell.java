package Entities.Player;

import Aufgabe5Bot.BotController;
import Aufgabe5Bot.ControllerContext;
import Aufgabe5Bot.MasterSquirellBot;
import Utils.UI;
import Utils.Vector2;

import java.util.Scanner;

public class MasterSquirell extends Squirrel implements BotController{

    private final int SID;

    public MasterSquirell(int SID){
        super.name = "MasterSquirell";
        this.playSymbol = 'M';
        this.SID = SID;
        this.Energie = 1000;
    }

    public boolean isMineSquirrel(MiniSquirrel miniSquirrel){
        return miniSquirrel.getMaster() == this;
    }

    @Override
    public void nextStep() {
        move(askMovement());
    }

    public int getSID() {
        return SID;
    }

    public Vector2 askMovement() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Bitte geben sie eine Richtung an: " + '\n' +
                    "1 Unten" + '\n' +
                    "2 Oben" + '\n' +
                    "3 Rechts" + '\n' +
                    "4 Links" + '\n');

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

    @Override
    public void nextStep(ControllerContext view) {

    }
}
