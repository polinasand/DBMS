import types.Type;

public class Column {
    private Type type;
    public String name;

    Column(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
