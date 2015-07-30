package br.com.markus.dto;

/**
 * Created by Markus on 29/07/2015.
 */
public class LogDataQueryDTO {
    private String timestampFrom;
    private String timestampTo;
    private String logType;
    private String custumerID;
    private String appCode;

    public String getTimestampFrom() {
        return timestampFrom;
    }

    public void setTimestampFrom(String timestampFrom) {
        this.timestampFrom = timestampFrom;
    }

    public String getTimestampTo() {
        return timestampTo;
    }

    public void setTimestampTo(String timestampTo) {
        this.timestampTo = timestampTo;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getCustumerID() {
        return custumerID;
    }

    public void setCustumerID(String custumerID) {
        this.custumerID = custumerID;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
