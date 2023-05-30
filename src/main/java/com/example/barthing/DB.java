package com.example.barthing;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DB() {
        String connectionString = "mongodb+srv://barThing:QTrDlwY7lzcxkgSR@cluster0.labvfko.mongodb.net/?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public void setDatabase(String database) {
        this.database = mongoClient.getDatabase(database);
    }

    public ArrayList<Drug> getDrugs() {
        ArrayList<Drug> allDrugs = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("drugs");

        FindIterable<Document> iterDoc = collection.find();

        for (Document element : iterDoc) {
            String name = element.getString("Name");
            int price = Integer.parseInt(element.getString("Price"));
            String id = element.getString("ID");
            int count = Integer.parseInt(element.getString("Count"));


            allDrugs.add(new Drug(name, price, id, count));
        }

        return allDrugs;
    }

    public boolean addDrugToDB(String Name, String Price, String ID, String Count) {
        Map<String, String> input = new HashMap<>();
        input.put("Name", Name);
        input.put("Price", Price);
        input.put("ID", ID);
        input.put("Count", Count);
        Document document = new Document(input);
        MongoCollection<Document> collection = database.getCollection("drugs");
        return collection.insertOne(document).wasAcknowledged();
    }

    public ArrayList<Drug> searchDrugs(String SearchName, String col) {
        ArrayList<Drug> allDrugs = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("drugs");
        System.out.println(col);
        System.out.println(SearchName);
        FindIterable<Document> iterDoc = collection.find(Filters.eq(col, SearchName));

        for (Document element : iterDoc) {
            String name = element.getString("Name");
            int price = Integer.parseInt(element.getString("Price"));
            String id = element.getString("ID");
            int count = Integer.parseInt(element.getString("Count"));


            allDrugs.add(new Drug(name, price, id, count));
        }

        return allDrugs;
    }

    public boolean deleteOne(String ID) {
        return database.getCollection("drugs").deleteOne(Filters.eq("ID", ID)).wasAcknowledged();
    }
}