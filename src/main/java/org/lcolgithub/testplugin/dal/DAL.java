package org.lcolgithub.testplugin.dal;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.lcolgithub.testplugin.player.PlugPlayer;

import static com.mongodb.client.model.Filters.eq;

public class DAL {

    private static MongoClient mongoClient; // Declare MongoClient as a class-level variable

    // Initialize MongoClient during class initialization
    static {
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
    }

    public static MongoCollection<Document> getCollection() {
        try {
            MongoDatabase database = mongoClient.getDatabase("PluginDev");
            return database.getCollection("Player");
        } catch (MongoException e) {
            Bukkit.getLogger().severe("Error occurred while getting MongoDB collection: " + e.getMessage());
            return null;
        }
    }

    public static PlugPlayer getPlayer(String uuid) {
        Bson filter = Filters.and(Filters.eq("uuid", uuid));
        Document doc = getCollection().find(filter).first();
        if(doc != null)
            return PlugPlayer.fromDocument(doc);

        PlugPlayer p = new PlugPlayer(uuid);
        writePlayer(p);
        return p;
    }

    public static void writePlayer(PlugPlayer p) {
        try {
            getCollection().insertOne(p.toDocument());
        } catch (Exception e) {
            Bukkit.getLogger().severe("Error occurred while writing player: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updatePlayer(PlugPlayer p) {
        Document query = new Document();
        query.put("uuid", p.getUuid());

        Document newDocument = p.toDocument();

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        getCollection().updateOne(query, updateObject);
    }
}