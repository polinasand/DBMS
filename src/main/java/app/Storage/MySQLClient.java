package app.Storage;

import app.Database.Database;
import app.Row.Row;
import app.Table.Table;
import app.util.Deserializer;
import app.util.Serializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLClient extends Storage{
    private String url = "jdbc:mysql://localhost:3306?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "root";
    private String password = "hellokitty";
    private Statement statement;
    private Connection connection = null;

    public MySQLClient() {}
    protected void open() {

        try {

            if (connection == null) {
                //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                connection = DriverManager.getConnection(url, user, password);

            }
            statement = connection.createStatement();
        } catch (Exception ex) {

        }

    }

    protected void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public Collection<String> getList() {
        ArrayList<String> result = new ArrayList<>();
        try {
            this.open();
            String sql = "SHOW DATABASES;";
            try {
                ResultSet names = statement.executeQuery(sql);
                while (names.next()) {
                    result.add(names.getString("Database"));
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveDatabase(Database database) {
        String name = database.getName();
        try {
            this.open();
            String sql = String.format("DROP DATABASE IF EXISTS %s", name);
            try {
                sql = String.format("CREATE DATABASE %s;", name);
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("Database exists!!!!");
                e.printStackTrace();
                return;
            }
            for (Table table : database.getList()) {
                sql = String.format("CREATE TABLE %1s.%2s (data JSON);",  name, table.getName());
                System.out.println(sql);
                statement.executeUpdate(sql);
                for (Row row : table.getRows()) {
                    sql = String.format("INSERT INTO %1s.%2s VALUES('%3s');",  name,table.getName(), Serializer.toJson(row));
                    statement.executeUpdate(sql);
                }

            }
            System.out.println("Saved Successfully! Congrats!");
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public Database loadDatabase(String name) {
        Database database = new Database(name);
        String sql;
        try {
            this.open();
            sql = String.format("SHOW TABLES FROM %s", name);
            ResultSet tables = statement.executeQuery(sql);

            String tableName = String.format("Tables_in_%s", name);

            ArrayList<String> tableNames = new ArrayList<>();

            while(tables.next()) {
                tableNames.add(tables.getString(tableName));
                //System.out.println(tables.getString(tableName));
            }

            tables.close();

            for (String table : tableNames) {
                sql = String.format("SELECT data FROM %1s.%2s", name, table);
                ResultSet rows = statement.executeQuery(sql);

                Table t = new Table(table);
                while (rows.next()) {
                    System.out.println(rows.getString("data"));
                    t.addRow(Deserializer.getJson().fromJson(rows.getString("data"), Row.class));

                }
                database.add(t);
                rows.close();
            }
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return database;
    }
}
