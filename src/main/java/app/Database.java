package app;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
    private HashMap<String, Table> tables;
    private String name;

    public Database(String name) {
        String fileName = name + ".txt";
        try {
            Scanner scanner = new Scanner(fileName);
            if (scanner.hasNext()) {
                Database db = new Gson().fromJson(scanner.next(), Database.class);
                this.name = db.getName();
                this.tables = db.tables;
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Database() {
        this.name = "db1";
        this.tables = new HashMap<>();
    }

    public Table get(String name) {
        return this.tables.getOrDefault(name, null);
    }

    public Collection<Table> getList() {
        return tables.values();
    }

    public String getName() {
        return name;
    }

    public Boolean add(Table table) {
        if (tables.containsKey(table.getName())) {
            System.out.println("Duplicate value for table name");
            return false;
        }
        tables.put(table.getName(), table);
        return true;
    }

    public Boolean delete(String name) {
        if (!tables.containsKey(name)) {
            System.out.println("Table name does not exists");
            return false;
        }
        tables.remove(name);
        return true;
    }

    public Boolean save() {
        String fileName = this.name + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(new Gson().toJson(this));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
