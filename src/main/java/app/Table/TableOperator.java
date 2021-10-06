package app.Table;


import app.Row;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TableOperator {
    public static Table operation(Table table1, Table table2) {
        String schema1 = new Gson().toJson(table1.getSchema());
        String schema2 = new Gson().toJson(table2.getSchema());

        if (!schema1.equals(schema2))
            return new Table();
        ArrayList<Row> rows1 = new ArrayList<>(table1.getRows());

        for (int i=0; i<rows1.size(); i++) {
            if (table2.contains(rows1.get(i))) {
                rows1.remove(i);

            }
        }
        return new Table("diff_"+table1.getName()+"_"+table2.getName(), table1.getSchema(), rows1);
    }

}
