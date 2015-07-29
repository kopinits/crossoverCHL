package br.com.markus.converter;

/**
 * Classe responsável por converter a solicitação em formato JSON para o bean Transacao
 *
 * @author Markus Kopinits
 */

public class LogDataConverter  {

/*
    *//**
     * Method responsable for convertion of a JSON data to  LogData entity
     *
     * @param logDataString json of the logdata entity
     * @return LogData entity
     * @throws Exception
     * @see LogDataConverter
     *//*
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

    *//**
     * Method responsable for convertion of a LogData entity to JSON
     *
     * @param logData entity
     * @return json of the logdata entity
     * @throws Exception
     * @see LogDataConverter
     *//*
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

    @Override
    public BasicDBObject toBasicObject(LogData logData) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(LogData.APP_CODE, logData.getAppCode());
        dbObject.put(LogData.TIMESTAMP, logData.getTimestamp().getTime());
        dbObject.put(LogData.LOG_TYPE, logData.getLogType().getDescription());
        dbObject.put(LogData.DATA_LOGGED, logData.getDataLogged());
        dbObject.put(LogData.CUSTUMER_ID, logData.getCustumerID());
        return dbObject;
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
                logData.setCustumerID(jsonObject.get(LogData.CUSTUMER_ID).toString());
            } catch (JSONException e) {
                return null;
            }
        }
        return logData;
    }*/
}
