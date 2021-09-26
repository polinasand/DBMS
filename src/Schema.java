import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Schema {
    HashMap<String, Column> columns;
    public Collection<Column> getColumns() {
        return columns.values();
    }
    public Schema() {
        columns = new HashMap<>();
    }

    public Schema(List<Column> _columns) {
        this.columns = new HashMap<>();
        _columns.forEach(column -> this.columns.put(column.name, column));
    }


}
