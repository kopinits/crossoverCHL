package br.com.markus.dao;

import br.com.markus.converter.LogDataConverter;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import br.com.markus.model.LogDataQuery;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
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
    @Autowired
    private LogDataConverter converter;


    public void saveLogData(LogData logData) throws UnknownHostException {
        MongoCollection collection = getMongoCollection();
        collection.insertOne(converter.toBasicObject(logData));
    }


    public ArrayList<LogData> queryLogData(LogDataQuery logDataQuery) throws UnknownHostException {
        MongoCursor<Document> mongoCursor = findLogDatas(logDataQuery);
        return getResults(mongoCursor);
    }

    private ArrayList<LogData> getResults(MongoCursor<Document> mongoCursor) {
        ArrayList<LogData> result = new ArrayList<LogData>();
        while (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
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

    private MongoCursor<Document> findLogDatas(LogDataQuery logDataQuery) throws UnknownHostException {

        MongoCollection collection = getMongoCollection();

        BasicDBObject query = new BasicDBObject();
        query.put(LogData.LOG_TYPE, new BasicDBObject("$regex", ".*\\Q" + logDataQuery.getLogType().getDescription() + "\\E.*")
                .append("$options", "i"));
        query.put(LogData.CUSTUMER_ID, new BasicDBObject("$regex", ".*\\Q" + logDataQuery.getCustumerID() + "\\E.*")
                .append("$options", "i"));

        query.put(LogData.TIMESTAMP, new BasicDBObject("$elemMatch",
                new BasicDBObject(LogData.TIMESTAMP, 1)
                        .append("data", new BasicDBObject("$gte", logDataQuery.getTimestampFrom())
                                .append("$lte", logDataQuery.getTimestampTo()))));
        BasicDBObject orderBy = new BasicDBObject(LogData.TIMESTAMP, 1);

        return collection.find(query).sort(orderBy).iterator();
    }

    private MongoCollection getMongoCollection() {
        MongoClient mongoClient = mongoConnection.getConnection();
        MongoDatabase mongoDatabase =  mongoConnection.getMongoDatabase(mongoClient);

        return mongoDatabase.getCollection(LOGDATA_COLLECTION_NAME);
    }


}
