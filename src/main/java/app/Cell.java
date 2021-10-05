package app;

public class Cell<T> {
    private T value;
    private T type;
    public Cell(T value) {
        this.value = value;
        this.type = value;

        System.out.println("created cell with value" + value+value.getClass());
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {

        return this.value;
    }
    public T getType() {

        return this.type;
    }


    @Override
    public String toString() {
        return value.toString();
    }

}
