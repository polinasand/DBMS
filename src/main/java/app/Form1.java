package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form1 extends JFrame implements ActionListener {
    private JButton newDatabaseButton, addTable;
    private JComboBox comboBox1;
    Container c;

    Form1() {

        c = getContentPane();
        newDatabaseButton = new JButton("new database");
        addTable = new JButton("addTable");
        newDatabaseButton.setBounds(0,0,30,10);
        addTable.setBounds(0, 40, 30, 10);

        newDatabaseButton.addActionListener(this);
        addTable.addActionListener(this);

        this.c.add(newDatabaseButton);
        //this.c.add(addTable);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newDatabaseButton) {
            /*JWindow window = new JWindow();
            window.add(new JTextField());
            window.setVisible(true);*/
            this.c.add(comboBox1);


        }

    }
}
