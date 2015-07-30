package br.com.markus.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
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


    public MongoClient getConnection(){
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(200)
                .maxWaitTime(1000 * 60 * 3)
                .minConnectionsPerHost(25)
                .build();

        String addressDB = host + ":" + port;

        MongoClient mongoClient = new MongoClient(new ServerAddress(addressDB), options);
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        return mongoDatabase;
    }
}
