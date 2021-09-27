package app;


import app.types.Type;

public class Column {
    private Type type;
    public String name;

    public Column(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
