package app.Database;

import app.util.Deserializer;
import app.util.Serializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class DatabaseManager {
    private static DatabaseManager instance;
    private HashMap<String, Database> databases;

    public DatabaseManager() {
        this.instance = this;
        this.databases = new HashMap<>();
    }

    public static DatabaseManager getInstance() {

        if (instance == null)
            return new DatabaseManager();
        return instance;
    }

    public Database get(String name) {
        return this.databases.getOrDefault(name, null);
    }

    public Collection<Database> getList() {
        return databases.values();
    }

    public Boolean add(Database db) {
        if (databases.containsKey(db.getName())) {
            System.out.println("Duplicate value for database name");
            return false;
        }
        databases.put(db.getName(), db);
        return true;
    }

    public Boolean delete(String name) {
        if (!databases.containsKey(name)) {
            System.out.println("Table name does not exists");
            return false;
        }
        databases.remove(name);
        return true;
    }

    public Database load(String name) {

        String fileName = name + ".txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            if (scanner.hasNext()) {
                String data = scanner.next();
                 return Deserializer.getJson().fromJson(data, Database.class);

            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new Database();
    }

    public Boolean save(String name) {

        if (!this.databases.containsKey(name))
            return false;

        String fileName = name + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write(Serializer.toJson(this.databases.get(name)));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
