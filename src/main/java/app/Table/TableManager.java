package app.Table;

import app.Cell.Cell;
import app.Forms.TableModel;
import app.Row.Row;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class TableManager {
    public TableModel table;
    private int rowCount;
    private int colCount;

    String[] columns;
    String[] emptyRow;

    public TableManager(Table t){
        table = new TableModel();
        updateColumns(t);
        initializeTable(t);
    }

    public void initializeTable(Table t) {
        table.setData(getData(t));
        rowCount = t.getRows().size();
        colCount = t.getSchema().getKeys().size();
    }

    private String[][] getData(Table t) {
        int r = t.getRows().size(), c = t.getSchema().getColumns().size();
        String[][] data = new String[r][c];

        if (r == 0) {
            data = new String[1][c];
            int j = 0;
            for(String key : t.getSchema().getKeys()){
                data[0][j++] = t.getColumn(key).getDefault().toString();
            }
            return data;
        }
        for (int i = 0; i < t.getRows().size(); i++) {
            String[] row = new String[t.getSchema().getColumns().size()];
            int s = t.getRows().get(i).size();

            for(int j=0; j<s; j++){
                row[j] = String.valueOf(t.getRows().get(i).getCell(j));
            }
            data[i] = row;
        }
        return data;
    }

    private void updateColumns(Table t) {
        //String[] colNames = new String[t.getSchema().getKeys().size()];
        colCount = t.getSchema().getKeys().size();
        columns = new String[colCount];
        emptyRow = new String[colCount];
        int i = 0;
        for(String col : t.getSchema().getKeys()){
            System.out.println(col);
            columns[i] = col + "(" + t.getColumn(col).getType().name() + ")";
            System.out.println(columns[i]);
            emptyRow[i++] = String.valueOf(t.getColumn(col).getDefault());
        }
        table.setColumnNames(columns);
    }


    public void initColumnSizes(JTable table) {
        TableModel model = (TableModel) table.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < colCount; i++) {
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



    public void insertRow(){
        rowCount += 1;
        String[][] newData = new String[rowCount][colCount];
        System.out.println(table.getRowCount() + " " + colCount);
        for (int i = 0; i < rowCount-1; i++) {
            for (int j=0; j < table.getColumnCount(); j++)
                newData[i][j] = (String)table.getValueAt(i, j);
        }

        table.setData(newData);
    }

    public void deleteRow(int ind){
        System.out.println(rowCount);
        rowCount -= 1;
        String[][] newData = new String[rowCount][colCount];
        for (int i = 0; i < ind; i++) {
            for (int j=0; j<colCount; j++)
                newData[i][j] = (String)table.getValueAt(i, j);
        }
        for (int i = ind+1; i < rowCount; i++) {
            for (int j=0; j<colCount; j++)
                newData[i][j] = (String)table.getValueAt(i, j);
        }
        table.setData(newData);
    }

    public HashMap<Integer, Row> updateData() {
        HashMap<Integer, Row> map = new HashMap<>();
        System.out.println("Chnaged rows "+table.changedRows.size());

        table.changedRows.forEach(ind -> {
            ArrayList<Cell> cells = new ArrayList<Cell>();
            for (int i=0; i<table.getColumnCount(); i++){
                cells.add(new Cell(table.getValueAt(ind, i)));
                System.out.println(table.getValueAt(ind, i));
            }
            map.put(ind, new Row(cells));

        });
        return map;
    }

}

