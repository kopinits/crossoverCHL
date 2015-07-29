package br.com.markus.dao;

import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe responsável por "persistir" solicitações de pagamento
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataDAO{

    private static final String LOGDATA_COLLECTION_NAME = "logdata";
    @Autowired
    private MongoConnection mongoConnection;


    public void registerLog(BasicDBObject dbObject) {
        MongoClient mongoClient = mongoConnection.getConnection();
        MongoDatabase mongoDatabase =  mongoConnection.getMongoDatabase(mongoClient);

        MongoCollection collection = mongoDatabase.getCollection(LOGDATA_COLLECTION_NAME);
        collection.insertOne(dbObject);
    }


    public String retrieveJsonLogData(LogTypeEnum logTypeEnum) {
        MongoCursor<Document> logsData = findLogDatas(logTypeEnum);
        return new JSON().serialize(logsData);
    }

    public ArrayList<LogData> retrieveLogData(LogTypeEnum logTypeEnum) {
        MongoCursor<Document> logsData = findLogDatas(logTypeEnum);
        return getResults(logsData);
    }

    private ArrayList<LogData> getResults(MongoCursor<Document> logsData) {
        ArrayList<LogData> result = new ArrayList<LogData>();
        while (logsData.hasNext()) {
            Document doc = logsData.next();
            LogData logData = new LogData();
            logData.setAppCode(doc.get(LogData.APP_CODE).toString());
            logData.setDataLogged(doc.get(LogData.DATA_LOGGED).toString());
            logData.setLogType(LogTypeEnum.from(doc.get(LogData.LOG_TYPE).toString()));
            logData.setTimestamp(new Date(Long.valueOf(doc.get(LogData.TIMESTAMP).toString())));
            logData.setCustumerID(doc.get(LogData.CUSTUMER_ID).toString());
            result.add(logData);
        }
        return result;
    }

    private MongoCursor<Document> findLogDatas(LogTypeEnum logTypeEnum) {
        MongoClient mongoClient = mongoConnection.getConnection();
        MongoDatabase mongoDatabase =  mongoConnection.getMongoDatabase(mongoClient);

        MongoCollection collection = mongoDatabase.getCollection(LOGDATA_COLLECTION_NAME);

        BasicDBObject query = new BasicDBObject();
        query.put(LogData.LOG_TYPE, new BasicDBObject("$regex", ".*\\Q" + logTypeEnum.getDescription() + "\\E.*")
                .append("$options", "i"));

        BasicDBObject orderBy = new BasicDBObject(LogData.TIMESTAMP, 1);
        return collection.find(query).sort(orderBy).iterator();
    }


}
