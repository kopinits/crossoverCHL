package br.com.markus.converter;

import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogDataDTO;
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


    public LogData toLogData(LogDataDTO logDataDTO) {
        LogData logData = new LogData();
        logData.setAppCode(logDataDTO.getAppCode());
        logData.setCustumerID(logDataDTO.getCustumerID());
        logData.setDataLogged(logDataDTO.getDataLogged());
        logData.setLogType(LogTypeEnum.from(logDataDTO.getLogType()));
        if (StringUtils.isNotBlank(logDataDTO.getTimestamp())) {
            logData.setTimestamp(new Date(Long.valueOf(logDataDTO.getTimestamp())));
        }
        return logData;
    }

    public LogDataQuery toLogDataQuery(LogDataQueryDTO logaDataDTO) {
        LogDataQuery logDataQuery = new LogDataQuery();
        logDataQuery.setCustumerID(logaDataDTO.getCustumerID());
        logDataQuery.setAppCode(logaDataDTO.getAppCode());
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
