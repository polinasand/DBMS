package app.Storage;

import app.Database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MongoDBClient extends Storage{
    private String url = "jdbc:mysql://localhost:3306?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "root";
    private String password = "hellokitty";
    private Statement statement;
    private Connection connection = null;

    public MongoDBClient() {}
    protected void open() {
    }

    protected void close() throws SQLException {

    }

    public Collection<String> getList() {
        return new ArrayList<>();
    }

    public void saveDatabase(Database database) {

    }

    public Database loadDatabase(String name) {
        return new Database();
    }
}
