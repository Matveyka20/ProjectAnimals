package AnimalNursery;

import java.util.ArrayList;

public abstract class PackAnimals extends Animals{
    public PackAnimals(int id, String name, int bDay, int bMonth, int bYear, String type_animal, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, false, type_animal, commands);
    }
}