package Entities.Environment;

public enum EntityType {
    BadBeast, GoodBeast, GoodPLant, BadPLant, Wall, MiniSquirrel, MasterSquirrel,;

    public static EntityType getType(int ID) {
        switch (ID) {
            case 1:
                return BadBeast;
            case 2:
                return BadPLant;
            case 3:
                return GoodBeast;
            case 4:
                return GoodPLant;
            case 5:
                return Wall;
            case 0:
                return MasterSquirrel;
            case 10:
                return MiniSquirrel;
            default:
                return Wall;
        }
    }

    public static int getDanger(EntityType e) {
        switch (e) {
            case BadBeast:
                return 2;
            case Wall:
                return 1;
            case GoodBeast:
                return 1;
            case BadPLant:
                return 2;
            case GoodPLant:
                return 1;
            case MiniSquirrel:
                return 0;
            case MasterSquirrel:
                return 0;
                default:
                    return 0;
        }


    }
}
