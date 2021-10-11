import app.Cell.Cell;
import app.Columns.IntInvlColumn;
import app.Columns.RealColumn;
import app.Columns.StringColumn;
import app.Row;
import app.Schema;
import app.Table.Table;
import app.Table.TableOperator;
import app.Types.IntInvl;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class TableTest {
    private static Table table1;
    private static Table table2;
    ArrayList<Row> data1, data2;

    ArrayList<Row> expected;
    private static Schema schema, anotherSchema;

    @Before
    public void setUp() {
        schema = new Schema(
                Arrays.asList(new StringColumn("col1"), new RealColumn("col2")));
    }
    @Test
    public void operationTest1() {
        data1 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(2)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(3))))
        ));

        data2 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(3)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(10), new Cell<>(10)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(10), new Cell<>("str"))))
        ));
        this.expected = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(2))))
        ));

        table1 = new Table("t1", schema, data1);
        table2 = new Table("t2", schema, data2);


        String actual = new Gson().toJson(TableOperator.operation(table1, table2).getRows());
        String expected = new Gson().toJson(this.expected);
        assertEquals(expected, actual);
    }

    @Test
    public void operationTest2() {
        data1 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(2)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(3))))
        ));

        data2 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(3)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(2))))
        ));
        this.expected = data1;

        table1 = new Table("t1", schema, data1);
        table2 = new Table("t2", schema, data2);


        String actual = new Gson().toJson(TableOperator.operation(table1, table2).getRows());
        String expected = new Gson().toJson(this.expected);
        assertEquals(expected, actual);
    }

    @Test
    public void operationTest3() {
        anotherSchema = new Schema(
                Arrays.asList(new RealColumn("col1"), new IntInvlColumn("col2")));

        data1 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(new IntInvl(3, 5))))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(new IntInvl(3, 5)))))
        ));

        data2 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(3)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str2"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(2))))
        ));
        this.expected = new ArrayList<>();

        table1 = new Table("t1", schema, data1);
        table2 = new Table("t2", schema, data2);


        String actual = new Gson().toJson(TableOperator.operation(table1, table2).getRows());
        String expected = new Gson().toJson(this.expected);
        assertEquals(expected, actual);
    }

    @Test
    public void DataValidationTest1() {
        data1 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(3)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(10), new Cell<>(10)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(4), new Cell<>("str2")))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(new IntInvl(1, 4)), new Cell<>("str4"))))
        ));

        this.expected = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(3)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str1"), new Cell<>(1)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(10), new Cell<>(10))))
        ));

        table1 = new Table("t1", schema, data1);
        String actual = new Gson().toJson(table1.getRows());
        String expected = new Gson().toJson(this.expected);
        assertEquals(expected, actual);
    }


    @Test
    public void DataValidationTest2() {
        data1 = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>("str3"), new Cell<>(new IntInvl(3, 5))))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(new IntInvl(2, 1)), new Cell<>(2)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(2), new Cell<>("str2")))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(new IntInvl(1, 4)), new Cell<>(5))))
        ));

        this.expected = new ArrayList<>(Arrays.asList(
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(new IntInvl(2, 1)), new Cell<>(2)))),
                new Row(new ArrayList<>(
                        Arrays.asList(new Cell<>(new IntInvl(1, 4)), new Cell<>(5))))
        ));

        table1 = new Table("t1", schema, data1);
        String actual = new Gson().toJson(table1.getRows());
        String expected = new Gson().toJson(this.expected);
        assertEquals(expected, actual);
    }

}
