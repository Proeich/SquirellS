package Entities.Environment;

import Utils.Vector2;

class BadPlant extends Entity{

    public BadPlant(){
        super();
        this.lifes = 1;
        this.playSymbol = 'b';
        this.ID = 2;
        this.name = "BadPlant";
        this.Energie = -100;
    }


    @Override
    public int onHit() {
        return -15;
    }

    @Override
    public void revive() {
        super.revive();
        this.lifes = 1;
        this.playSymbol = 'b';
        this.ID = 2;
        this.name = "BadPlant";
        this.Energie = -100;
    }

    @Override
    public void move(Vector2 in) {
    }
}
