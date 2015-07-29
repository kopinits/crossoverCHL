package br.com.markus.model;

import br.com.markus.enuns.LogTypeEnum;

import java.util.Date;

/**
 * Created by Markus on 28/07/2015.
 */
public final class LogData {
    public static final String APP_CODE = "appCode";
    public static final String TIMESTAMP = "timestamp";
    public static final String LOG_TYPE = "logType";
    public static final String DATA_LOGGED = "dataLogged";

    private String appCode;
    private Date timestamp;
    private LogTypeEnum logType;
    private String dataLogged;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public LogTypeEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEnum logType) {
        this.logType = logType;
    }

    public String getDataLogged() {
        return dataLogged;
    }

    public void setDataLogged(String dataLogged) {
        this.dataLogged = dataLogged;
    }

    @Override
    public String toString() {
        return "{" +
                "appCode='" + appCode + '\'' +
                ", timestamp=" + timestamp.getTime() +
                ", logType=" + logType.getDescription() +
                ", dataLogged='" + dataLogged + '\'' +
                '}';
    }
}
