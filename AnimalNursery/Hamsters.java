package AnimalNursery;

import java.util.ArrayList;

public class Hamsters extends Pets{
    public Hamsters(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Hamsters", commands);
    }

    public Hamsters() {
        super(-1, "Null", 12,12,1000,"Hamsters", new ArrayList<>());
    }
}