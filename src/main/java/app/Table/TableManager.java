package app.Table;

import app.Forms.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;


public class TableManager {
    public TableModel table;
    private int numOfColumns;
    private Connection connection;
    ArrayList<Object[]> dataArrayList = new ArrayList<>();
    Object[] columns;
    Object[] emptyRow;

    public TableManager(Table t){
        table = new TableModel();
        updateColumns(t);
        initializeTable(t);
    }

    public void initializeTable(Table t) {
        table.setData(getData(t));
    }

    private Object[][] getData(Table t) {
        Object[][] data = new Object[t.getRows().size()][t.getSchema().getColumns().size()];
        for (int i = 0; i < t.getRows().size(); i++) {
            Object[] row = new Object[t.getSchema().getColumns().size()];
            int j = 0;
            int s = t.getRows().get(i).size();
            for(j=0; j<s; ){
                row[j++] = t.getRows().get(i).getCell(j);
            }
            data[i] = row;
        }
        return data;
    }

    private void updateColumns(Table t) {
        String[] colNames = new String[t.getSchema().getKeys().size()];
        columns = new Object[t.getSchema().getKeys().size()];
        emptyRow = new Object[t.getSchema().getKeys().size()];
        int i = 0;
        for(String col : t.getSchema().getKeys()){
            columns[i] = col;
            colNames[i] = col + "(" + t.getColumn(col).getType().name() + ")";
            emptyRow[i++] = t.getColumn(col).getDefault();
        }
        table.setColumnNames(colNames);
    }


    public void initColumnSizes(JTable table) {
        TableModel model = (TableModel) table.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < numOfColumns; i++) {
            column = table.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                            table, columns[i],
                            false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }


    public void setUpDaysColumn(TableColumn daysColumn) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Понеділок");
        comboBox.addItem("Вівторок");
        comboBox.addItem("Середа");
        comboBox.addItem("Четвер");
        comboBox.addItem("П'ятниця");
        comboBox.addItem("Субота");
        comboBox.addItem("Неділя");
        daysColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        daysColumn.setCellRenderer(renderer);
    }


    public void insertRow(){
        Object[][] newData = new Object[dataArrayList.size()+1][numOfColumns];
        dataArrayList.add(emptyRow);
        for (int i = 0; i < dataArrayList.size(); i++) {
            newData[i] = dataArrayList.get(i);
        }
        table.setData(newData);
    }

    public void deleteRow(int ind){

        Object[][] newData = new Object[dataArrayList.size()-1][numOfColumns];
        for (int i = 0; i < ind; i++) {
            newData[i] = dataArrayList.get(i);
        }
        for (int i = ind+1; i < dataArrayList.size(); i++) {
            newData[i-1] = dataArrayList.get(i);
        }
        table.setData(newData);
    }


}

