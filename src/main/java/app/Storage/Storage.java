package app.Storage;

import app.Database.Database;

import java.sql.SQLException;
import java.util.Collection;

public abstract class Storage {
    protected abstract void open();
    protected abstract void close() throws SQLException;

    public abstract Collection<String> getList();
    public abstract void saveDatabase(Database database);
    public abstract Database loadDatabase(String name);

}
