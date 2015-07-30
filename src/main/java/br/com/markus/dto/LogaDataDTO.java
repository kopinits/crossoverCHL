package br.com.markus.dto;

/**
 * Created by Markus on 29/07/2015.
 */
public class LogaDataDTO {
    private String appCode;
    private String timestamp;
    private String logType;
    private String dataLogged;
    private String custumerID;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getDataLogged() {
        return dataLogged;
    }

    public void setDataLogged(String dataLogged) {
        this.dataLogged = dataLogged;
    }

    public String getCustumerID() {
        return custumerID;
    }

    public void setCustumerID(String custumerID) {
        this.custumerID = custumerID;
    }
}
