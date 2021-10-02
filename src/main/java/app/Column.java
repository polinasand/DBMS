package app;


public class Column<T> {
    private Class<T> type;
    public String name;

    public Column(String name) {
        this.name = name;

    }

    public Class<T> getType() {
        return type;
    }
    public String getName() {
        return name;
    }

}
