package app;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class DatabaseManager {
    public HashMap<String, Database> databases;
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
                System.out.println(data);
                return new Gson().fromJson(data, Database.class);

            }
            scanner.close();
        } catch (IllegalStateException exception) {
            System.out.println(exception);
            exception.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return new Database();
    }

    public Boolean save(Database db) {
        String fileName = db.getName() + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(new Gson().toJson(db));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
