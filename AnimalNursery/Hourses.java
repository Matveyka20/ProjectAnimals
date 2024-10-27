package AnimalNursery;

import java.util.ArrayList;

public class Hourses extends PackAnimals{
    public Hourses(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Hourses", commands);
    }

    public Hourses() {
        super(-1,"Null",12,12,1000, "Hourses", new ArrayList<>());
    }
}