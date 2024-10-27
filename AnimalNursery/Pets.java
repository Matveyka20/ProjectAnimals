package AnimalNursery;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pets extends Animals {
    public Pets(int id, String name, int bDay, int bMonth, int bYear, String type_animal,
                ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, true, type_animal, commands);
    }

}