package br.com.markus.dao.impl;

import br.com.markus.dao.ILogDataDAO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe responsável por "persistir" solicitações de pagamento
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataDAO implements ILogDataDAO {

    public static final String MONGODB_URL = "@ds059692.mongolab.com:59692/crossover";
    public static final String MONGODB = "mongodb://";
    public static final String CROSSOVER_PWD = "crossover_usr";
    public static final String CROSSOVER_USR = CROSSOVER_PWD;
    public static final String LOGDATA_COLLECTION_NAME = "logdata";

    public LogDataDAO() {
    }

    @Override
    public void registerLog(BasicDBObject dbObject) {
        getDbCollection().insert(dbObject);
    }


    @Override
    public String retrieveJsonLogData(LogTypeEnum logTypeEnum) {
        DBCursor logsData = findLogDatas(logTypeEnum);
        return new JSON().serialize(logsData);
    }

    @Override
    public ArrayList<LogData> retrieveLogData(LogTypeEnum logTypeEnum) {
        DBCursor logsData = findLogDatas(logTypeEnum);
        ArrayList<LogData> result = new ArrayList<LogData>();
        while (logsData.hasNext()) {
            DBObject doc = logsData.next();
            LogData logData = new LogData();
            logData.setAppCode(doc.get(LogData.APP_CODE).toString());
            logData.setDataLogged(doc.get(LogData.DATA_LOGGED).toString());
            logData.setLogType(LogTypeEnum.from(doc.get(LogData.LOG_TYPE).toString()));
            logData.setTimestamp(new Date(Long.valueOf(doc.get(LogData.TIMESTAMP).toString())));
            result.add(logData);
        }
        return result;
    }

    private DBCursor findLogDatas(LogTypeEnum logTypeEnum) {
        BasicDBObject findQuery = new BasicDBObject("logType", new BasicDBObject("$gte", logTypeEnum.getDescription()));
        BasicDBObject orderBy = new BasicDBObject("timestamp", 1);
        return getDbCollection().find(findQuery).sort(orderBy);
    }

    private DBCollection getDbCollection() {
        return getConnection().getCollection(LOGDATA_COLLECTION_NAME);
    }

    private DB getConnection() {
        MongoClientURI uri = new MongoClientURI(MONGODB
                + CROSSOVER_USR + ":" + CROSSOVER_PWD + MONGODB_URL);
        MongoClient client = new MongoClient(uri);
        return client.getDB(uri.getDatabase());
    }
}
