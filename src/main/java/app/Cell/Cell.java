package app.Cell;

public class Cell<T> {
    private T value;
    public Cell(T value) {
        this.value = value;

        System.out.println("created cell with value" + value+value.getClass());
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {

        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
