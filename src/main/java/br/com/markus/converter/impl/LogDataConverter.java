package br.com.markus.converter.impl;

import br.com.markus.converter.ILogDataConverter;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe responsável por converter a solicitação em formato JSON para o bean Transacao
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataConverter implements ILogDataConverter {
    public LogDataConverter() {
    }

    /**
     * Method responsable for convertion of a JSON data to  LogData entity
     *
     * @param logDataString json of the logdata entity
     * @return LogData entity
     * @throws Exception
     * @see LogDataConverter
     */
    @Override
    public LogData toLogData(String logDataString) throws Exception {
        if (StringUtils.isNotBlank(logDataString)) {
            return populateEntityValues(logDataString);
        } else {
            return null;
        }
    }

    @Override
    public LogTypeEnum toLogDataQuery(String logDataString) throws Exception {
        if (StringUtils.isNotBlank(logDataString)) {
            JSONObject jsonObject = new JSONObject(logDataString);
            return LogTypeEnum.from(jsonObject.get(LogData.LOG_TYPE).toString());
        }
        return null;
    }

    /**
     * Method responsable for convertion of a LogData entity to JSON
     *
     * @param logData entity
     * @return json of the logdata entity
     * @throws Exception
     * @see LogDataConverter
     */
    @Override
    public String fromLogData(LogData logData) throws Exception {
        return new JSONObject(logData.toString()).toString();
    }

    @Override
    public String fromLogData(ArrayList<LogData> logsData) throws Exception {
        StringBuilder result = new StringBuilder("[");
        for (LogData logData : logsData) {
            result.append(fromLogData(logData));
            result.append(",");
        }
        result.append("]");
        return result.toString();
    }

    private LogData populateEntityValues(String jsonString) throws JSONException {
        LogData logData = new LogData();
        if (StringUtils.isNotBlank(jsonString)) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                logData.setAppCode(jsonObject.get(LogData.APP_CODE).toString());
                logData.setAppCode(jsonObject.get(LogData.APP_CODE).toString());
                logData.setDataLogged(jsonObject.get(LogData.DATA_LOGGED).toString());
                logData.setLogType(LogTypeEnum.from(jsonObject.get(LogData.LOG_TYPE).toString()));
                logData.setTimestamp(new Date(Long.valueOf(jsonObject.get(LogData.TIMESTAMP).toString())));
            } catch (JSONException e) {
                return null;
            }
        }
        return logData;
    }
}
