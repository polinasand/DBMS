package app.Storage;

import app.Database.Database;

import java.util.Collection;

public abstract class Storage {

    public abstract Collection<String> getList();
    public abstract void saveDatabase(Database database);
    public abstract Database loadDatabase(String name);

}
