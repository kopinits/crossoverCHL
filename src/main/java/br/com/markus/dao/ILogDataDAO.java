package br.com.markus.dao;

import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface para o LogDataDAO
 */
public interface ILogDataDAO {
    void registerLog(BasicDBObject dbObject);
    String retrieveJsonLogData(LogTypeEnum logTypeEnum);
    ArrayList<LogData> retrieveLogData(LogTypeEnum logTypeEnum);
}
