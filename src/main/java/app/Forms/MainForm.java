package app.Forms;

import app.Columns.Column;
import app.Database.Database;
import app.Database.DatabaseManager;
import app.Row;
import app.Table.Table;
import app.Table.TableManager;
import app.Table.TableOperator;
import app.Types.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;

public class MainForm {

    public JFrame frame;
    private JPanel mainPanel;
    private JPanel createDBPanel;
    private JButton loadDatabaseButton;
    private JButton createDatabaseButton;
    private JButton createTableButton;
    private JButton editDatabaseButton;
    private JButton backToMainFromCreateDB;
    private JPanel editDBPanel;
    private JButton backToMainFromEditDBButton;
    private JPanel showDBPanel;
    private JButton backToMainFromShowDBButton;
    private JPanel createTablePanel;
    private JButton createButton;
    private JButton addColumnButton;
    private JList tablesList;
    private JList columnsList;
    private JButton downloadDatabaseButton;
    private JButton editTableButton;
    private JButton deleteTableButton;
    private JButton addTableButton;
    private JList<String> tablesListInEdit;
    private JPanel editTablePanel;
    private JTable tableView;
    private JButton addRowButton;
    private JButton deleteColumnButton;
    private JButton deleteRowButton;
    private JButton addColumnButton1;
    private JButton saveEditedTableButton;
    private JButton tablesOperation;
    private DatabaseManager dbManager;
    private Database activeDB;
    private Table activeTable;
    private TableManager tableManager;

    private String[] typesList = new String[]{
            "INTEGER",
            "REAL",
            "STRING",
            "CHAR",
            "INTINVL",
            "CHARINVL",
            "TEXTFILE"
    };


    public MainForm() {
        initialize();
        buttonsProcessing();
    }

    private void initialize() {
        dbManager = new DatabaseManager();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        tableView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.8;
        screenSize.width *= 0.8;
        frame.setSize(screenSize);
        addPanelsToTheFrame();
        changePanels();
    }

    private void addPanelsToTheFrame() {
        frame.getContentPane().add(mainPanel, "name_1");
        frame.getContentPane().add(createDBPanel, "name_2");
        frame.getContentPane().add(editDBPanel, "name_3");
        frame.getContentPane().add(showDBPanel, "name_4");
        frame.getContentPane().add(createTablePanel, "name_5");
        frame.getContentPane().add(editTablePanel, "name_6");

    }

    private void buttonsProcessing() {
        createDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = JOptionPane.showInputDialog("Input name for new database:");
                if (name == null)
                    return;
                Database newDb = new Database(name);
                Boolean result = dbManager.add(newDb);
                if(result) {
                    JOptionPane.showMessageDialog(frame, "Database `" + name + "` is created.");
                    changeActivePanel(createDBPanel, mainPanel);
                    activeDB = newDb;
                    updateTablesList();
                } else {
                    JOptionPane.showMessageDialog(frame, "This database already exists.");
                }
            }
        });

        editDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Collection<Database> existedDatabases = dbManager.getList();
                if(existedDatabases.size() == 0 ){
                    JOptionPane.showMessageDialog(frame, "List of databases is empty. Please create or load database.");
                }
                String[] choices = new String[existedDatabases.size() + 1];
                int ind = 0;
                for(Database db: existedDatabases){
                    choices[ind++] = db.getName();
                }
                String name = (String) JOptionPane.showInputDialog(null, "",
                        "Select a database to edit", JOptionPane.QUESTION_MESSAGE, null,
                        choices,
                        choices[1]); // Initial choice

                activeDB = dbManager.get(name);
                updateTablesListInEditDB();
                if(activeDB != null) {
                    JOptionPane.showMessageDialog(frame, "Database `" + name + "` is loaded.");
                    changeActivePanel(editDBPanel, mainPanel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Canceled.");
                }
            }
        });



        addColumnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String name = JOptionPane.showInputDialog("Input a name for the column:");
                String type = (String) JOptionPane.showInputDialog(null, "",
                        "Select a type:", JOptionPane.QUESTION_MESSAGE, null,
                        typesList,
                        typesList[1]);
                Column col = Column.getColumn(name, Type.valueOf(type));
                Boolean result = activeTable.addColumn(col);
                if(result) {
                    JOptionPane.showMessageDialog(frame, "Column `" + name + "`was added.");
                    updateListColumns();
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong name for the column (already exists).");
                }
            }
        });

        addColumnButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("add attr");
                String name = JOptionPane.showInputDialog("Input name for the column:");
                String type = (String) JOptionPane.showInputDialog(null, "",
                        "Select a type", JOptionPane.QUESTION_MESSAGE, null,
                        typesList, // Array of choices
                        typesList[1]);
                Column col = Column.getColumn(name, Type.valueOf(type));
                Boolean result = activeTable.addColumn(col);
                if(result) {
                    JOptionPane.showMessageDialog(frame, "Column `" + name + "`was added.");
                    updateListColumns();


                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong name for column (already exists).");
                }
            }
        });


        createTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = JOptionPane.showInputDialog("Input name for a new table.");
                Table newTable = new Table(name);
                Boolean result = activeDB.add(newTable);
                if(result) {
                    JOptionPane.showMessageDialog(frame, "Table `" + name + "`is created.");
                    changeActivePanel(createTablePanel, createDBPanel);
                    activeTable = newTable;
                    updateTablesList();
                } else {
                    JOptionPane.showMessageDialog(frame, "This table already exists.");
                }
            }
        });


        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changeActivePanel(createDBPanel, createTablePanel);
                activeTable = null;
                updateListColumns();
            }
        });

        addTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = JOptionPane.showInputDialog("Input name for new table:");
                Table newTable = new Table(name);
                Boolean result = activeDB.add(newTable);
                if(result) {
                    JOptionPane.showMessageDialog(frame, "Table `" + name + "`is created");
                    updateTablesListInEditDB();
                } else {
                    JOptionPane.showMessageDialog(frame, "This table already exists.");
                }
            }
        });

        deleteTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String selectedTable = tablesListInEdit.getSelectedValue();
                if(selectedTable==null) {
                    JOptionPane.showMessageDialog(frame, "Select table to delete:");
                } else {
                    Boolean result = activeDB.delete(selectedTable);
                    if(result){
                        updateTablesListInEditDB();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Unable to delete this table.");
                    }
                }
            }
        });

        editTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String selectedTable = tablesListInEdit.getSelectedValue();
                if(selectedTable==null) {
                    JOptionPane.showMessageDialog(frame, "Please select table to edit:");
                }else{
                    activeTable = activeDB.get(selectedTable);
                    System.out.println("active table "+activeTable.getName()+" "+activeTable.getRows().size());
                    if (activeTable != null){

                        tableManager = new TableManager(activeTable);
                        setTableModel();
                        changeActivePanel(editTablePanel, editDBPanel);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to edit selected table.");
                    }
                }
            }
        });

        addRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                    System.out.println(activeTable.getName());
                    if(activeTable != null){
                        tableManager.insertRow();
                        activeTable.addEmptyRow();
                        changeActivePanel(editTablePanel, editDBPanel);
                        tableManager = new TableManager(activeTable);
                        setTableModel();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to add row to this table.");
                    }

            }
        });

        deleteRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = tableView.getSelectedRow();
                if(selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select any row to delete.");
                    return;
                }
                Boolean res = activeTable.deleteRow(selectedRow);
                if(!res) {
                    //System.out.println(activeTable.listRows().size());
                    JOptionPane.showMessageDialog(frame, "Cannot delete the row.");
                } else {
                    System.out.println("SelectedRow = " + String.valueOf(selectedRow));
                    tableManager.deleteRow(selectedRow);
                }
            }
        });


        deleteColumnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] columns = new String[activeTable.getSchema().getColumns().size()];
                int i = 0;
                for(Column column: activeTable.getSchema().getColumns()){
                    columns[i++] = column.name;
                }
                String column = (String) JOptionPane.showInputDialog(null, "",
                        "Select column to delete", JOptionPane.QUESTION_MESSAGE, null,
                        columns, // Array of choices
                        typesList[1]);
                Boolean res = activeTable.deleteColumn(column);
                if(!res) {
                    //System.out.println(activeTable.listRows().size());
                    JOptionPane.showMessageDialog(frame, "Error while deleting column.");
                } else {
                    tableManager = new TableManager(activeTable);
                    setTableModel();

                }
            }
        });

        downloadDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] databases = new String[dbManager.getList().size()];
                int i = 0;
                for (Database db : dbManager.getList()) {
                    databases[i++] = db.getName();
                }
                String db = (String) JOptionPane.showInputDialog(null, "",
                        "Select database to save:", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        databases, // Array of choices
                        typesList[1]);
                Boolean res = dbManager.save(db);
                if (!res) {
                    JOptionPane.showMessageDialog(frame, "Error while saving database.");
                }
            }
        });

        loadDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String dbName = (String) JOptionPane.showInputDialog("Input name of the database:");
                activeDB = dbManager.load(dbName);
                if(activeDB != null) {
                    updateTablesListInEditDB();
                    dbManager.add(activeDB);
                    JOptionPane.showMessageDialog(frame, "Database is loaded.");
                    changeActivePanel(editDBPanel, mainPanel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Canceled.");
                }

            }
        });

        saveEditedTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HashMap<Integer, Row> map = tableManager.updateData();
                map.entrySet().forEach(entry ->
                        activeTable.setRow(entry.getKey(), entry.getValue()));
                activeDB.delete(activeTable.getName());
                dbManager.delete(activeDB.getName());
                activeDB.add(activeTable);
                dbManager.add(activeDB);
                changeActivePanel(editDBPanel, editTablePanel);
            }
        });

        tablesOperation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] tables = new String[activeDB.getList().size()];
                int i=0;
                for (Table table : activeDB.getList()) {
                    tables[i++] = table.getName();
                }

                String _table1 = (String) JOptionPane.showInputDialog(null, "",
                        "Select the first table:", JOptionPane.QUESTION_MESSAGE, null,
                        tables, // Array of choices
                        typesList[1]);

                String _table2 = (String) JOptionPane.showInputDialog(null, "",
                        "Select the second table:", JOptionPane.QUESTION_MESSAGE, null,
                        tables, // Array of choices
                        typesList[1]);

                Table table1 = activeDB.get(_table1);
                Table table2 = activeDB.get(_table2);

                if(table1 != null && table2 != null) {
                    activeTable = TableOperator.operation(table1, table2);
                    if (activeTable == null) {
                        JOptionPane.showMessageDialog(frame, "Error while operation (inconsistent schemas).");
                    } else {
                        tableManager = new TableManager(activeTable);
                        setTableModel();
                        updateTablesList();
                        changeActivePanel(editTablePanel, editDBPanel);
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong tables names.");
                }

            }
        });
}




    private void setTableModel() {
        tableView.setModel(tableManager.table);
        tableManager.initColumnSizes(tableView);
        tableView.setFillsViewportHeight(true);
        mainPanel.setOpaque(true);
        tableView.setVisible(true);
        tableView.setBorder(BorderFactory.createEmptyBorder());
    }


    private void updateListColumns() {
        DefaultListModel<String> model = new DefaultListModel<>();
        columnsList.setModel(model);
        if(activeTable == null){
            return;
        }
        Collection<Column> columnsArrayList = activeTable.getSchema().getColumns();
        Column[] columns = columnsArrayList.toArray(new Column[0]);
        for (int i = 0; i < columns.length; i++) {
            model.add(i, columns[i].name + " : " + columns[i].getType().name());
        }
    }

    private void updateTablesList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Collection<String> tablesNames = activeDB.getKeys();
        String[] tables = tablesNames.toArray(new String[0]);
        for (int i = 0; i < tables.length; i++) {
            model.add(i, tables[i]);
        }
        tablesList.setModel(model);
    }

    private void updateTablesListInEditDB() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Collection<String> tablesNames = activeDB.getKeys();
        String[] tables = tablesNames.toArray(new String[0]);
        for (int i = 0; i < tables.length; i++) {
            System.out.println("Table name = " + tables[i] + "\n");
            model.add(i, tables[i]);
        }
        tablesListInEdit.setModel(model);
    }

    private void changeActivePanel(JPanel newPanel, JPanel previousPanel){
        newPanel.setVisible(true);
        previousPanel.setVisible(false);
    }

    private void changePanels() {
        backToMainFromEditDBButton.addActionListener(e -> changeActivePanel(mainPanel, editDBPanel));
        backToMainFromCreateDB.addActionListener(e -> changeActivePanel(mainPanel, createDBPanel));
        backToMainFromShowDBButton.addActionListener(e -> changeActivePanel(mainPanel, showDBPanel));
    }

}
