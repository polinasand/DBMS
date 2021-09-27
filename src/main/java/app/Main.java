import app.*;
import app.types.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        Schema schema = new Schema(
                Arrays.asList(new Column("col1", Type.INTEGER), new Column("col2", Type.REAL)));
        ArrayList<Row> data = new ArrayList<Row>(Arrays.asList(
                new Row(schema, new ArrayList<Cell>(
                        Arrays.asList(new Cell<Integer>(1), new Cell<Double>(2.9))))));

        db.add(new Table("t3", schema, data));
        db.add(new Table("t4", schema, data));
        Table t1 = db.get("t1");
        Row row = t1.getRow(0);
        System.out.println(db.getName() + ' ' + t1.getName());
        System.out.println(row.getCell(1).toString());
        db.save();
    }
}
