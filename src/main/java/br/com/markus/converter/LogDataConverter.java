package br.com.markus.converter;

import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogaDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import br.com.markus.model.LogDataQuery;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Converter between DTO and entity
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataConverter  {


    public LogData toLogData(LogaDataDTO logaDataDTO) {
        LogData logData = new LogData();
        logData.setAppCode(logaDataDTO.getAppCode());
        logData.setCustumerID(logaDataDTO.getCustumerID());
        logData.setDataLogged(logaDataDTO.getDataLogged());
        logData.setLogType(LogTypeEnum.from(logaDataDTO.getLogType()));
        if (StringUtils.isNotBlank(logaDataDTO.getTimestamp())) {
            logData.setTimestamp(new Date(Long.valueOf(logaDataDTO.getTimestamp())));
        }
        return logData;
    }

    public LogDataQuery toLogDataQuery(LogDataQueryDTO logaDataDTO) {
        LogDataQuery logDataQuery = new LogDataQuery();
        logDataQuery.setCustumerID(logaDataDTO.getCustumerID());
        logDataQuery.setLogType(LogTypeEnum.from(logaDataDTO.getLogType()));
        logDataQuery.setTimestampFrom(new Date(Long.valueOf(logaDataDTO.getTimestampFrom())));
        logDataQuery.setTimestampTo(new Date(Long.valueOf(logaDataDTO.getTimestampTo())));
        return logDataQuery;
    }


    public Document toBasicObject(LogData logData) {
        Document dbObject = new Document();
        dbObject.put(LogData.APP_CODE, logData.getAppCode());
        dbObject.put(LogData.TIMESTAMP, logData.getTimestamp().getTime());
        dbObject.put(LogData.LOG_TYPE, logData.getLogType().getDescription());
        dbObject.put(LogData.DATA_LOGGED, logData.getDataLogged());
        dbObject.put(LogData.CUSTUMER_ID, logData.getCustumerID());
        return dbObject;
    }
}
