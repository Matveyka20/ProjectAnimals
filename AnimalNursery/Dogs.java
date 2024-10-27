package AnimalNursery;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dogs extends Pets {
    public Dogs(int id, String name, int bDay, int bMonth, int bYear, ArrayList<String> commands) {
        super(id, name, bDay, bMonth, bYear, "Dogs", commands);
    }
    public Dogs(){
        super(-1,"Null",12,12,1000, "Dogs", new ArrayList<String>());
    }
}
