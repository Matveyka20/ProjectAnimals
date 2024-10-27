package AnimalNursery;

import java.util.ArrayList;

public class Cats extends Pets{
    public Cats(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Cats", commands);
    }

    public Cats() {
        super(-1, "Null",12,12,1000, "Cats", new ArrayList<String>());
    }
}
