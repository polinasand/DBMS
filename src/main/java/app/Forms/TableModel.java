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

    private Object[][] data;
    public  void setData(Object[][] data){
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

    public Object getValueAt(int row, int col) {
        //System.out.println("In get value at!! + row = " + String.valueOf(row)  + " col = " + String.valueOf(col) + "\n");
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true; //r != 0;
    }

    public void setValueAt(Object value, int row, int col) {
        System.out.println("In set value at!! + row = " + String.valueOf(row)  + " col = " + String.valueOf(col) + "\n");
        data[row][col] = value;
        fireTableCellUpdated(row, col);
        changedRows.add(row);
    }


}
