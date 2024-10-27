package AnimalNursery;

import java.util.ArrayList;

public class Donkeys extends PackAnimals{
    public Donkeys(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Donkeys", commands);
    }

    public Donkeys() {
        super(-1, "Null", 12, 12, 1000, "Donkeys", new ArrayList<>());
    }
}
