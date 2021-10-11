package app.Forms;

import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.Set;

public class TableModel extends AbstractTableModel {
    public Set<Integer> changedRows = new HashSet<>();
    private String[] columnNames;

    public void setColumnNames(String[] columns){
        this.columnNames = columns;
    }

    private String[][] data;
    public  void setData(String[][] data){
        this.data = data;
        fireTableStructureChanged();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return this.data[rowIndex][columnIndex];
    }



    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }


    @Override
    public void setValueAt(Object value, int row, int col) {
        System.out.println("In set value at row = " + String.valueOf(row)  + " col = " + String.valueOf(col) + "\n");
        this.data[row][col] = (String) value;
        fireTableCellUpdated(row, col);
        changedRows.add(row);

    }


}
