package AnimalNursery;

import java.util.Arrays;

public enum PetsCommands {
    Сидеть, Лежать, Лапу, Голос, Место, Взять, Но, Стоять;
    public String getcommand(int n){
        return Arrays.stream(PetsCommands.values()).toList().get(n-1).toString();
    }
}