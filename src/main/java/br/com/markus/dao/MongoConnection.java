package br.com.markus.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Markus on 29/07/2015.
 */
@Component
public class MongoConnection {
    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private String port;

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.usr}")
    private String user;

    @Value("${mongodb.pwd}")
    private String password;

    public MongoConnection() {
    }

    public MongoClient getConnection(){
        String addressDB = "mongodb://" + user + ":" + password + host + ":" + port;
        MongoClientURI mongoClientURI = new MongoClientURI(addressDB);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        return mongoDatabase;
    }

}
