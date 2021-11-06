package app.Storage;

import app.Database.Database;
import app.Table.Table;
import app.util.Deserializer;
import app.util.Serializer;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;



public class MongoDBClient extends Storage{
    private static MongoClient client;

    public MongoDBClient() {
        client = new MongoClient("localhost", 27017);
    }

    public Collection<String> getList() {
        ArrayList<String> databases = new ArrayList<>();

        for (String name : client.listDatabaseNames()) {
            databases.add(name);
        }
        return databases;
    }

    public void saveDatabase(Database database) {
        MongoDatabase mongoDB = client.getDatabase(database.getName());

        for (Table table : database.getList()) {
            mongoDB.createCollection(table.getName());
            MongoCollection<Document> collection = mongoDB.getCollection(table.getName());
            collection.insertOne(Document.parse(Serializer.toJson(table)));
        }
    }

    public Database loadDatabase(String name) {
        MongoDatabase mongoDB = client.getDatabase(name);
        Database database = new Database(name);

        for (String tableName : mongoDB.listCollectionNames()) {
            Document tableDocument = mongoDB.getCollection(tableName).find().first();

            Table table = Deserializer.getJson().fromJson(tableDocument.toJson(), Table.class);
            database.add(table);
        }
        return database;
    }

}
