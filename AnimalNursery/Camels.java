package AnimalNursery;

import AnimalNursery.PackAnimals;
import Controller.CtrlFunc;

import java.util.ArrayList;

public class Camels extends PackAnimals {
    public Camels(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Camels", commands);
    }

    public Camels() {
        super(-1, "Null", 12, 12, 1000, "Camels", new ArrayList<>());
    }

}
