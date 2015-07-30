package br.com.markus.model;

import br.com.markus.enuns.LogTypeEnum;

import java.util.Date;

/**
 * Created by Markus on 28/07/2015.
 */
public final class LogDataQuery {
    private Date timestampFrom;
    private Date timestampTo;
    private LogTypeEnum logType;
    private String custumerID;
    private String appCode;

    public Date getTimestampFrom() {
        return timestampFrom;
    }

    public void setTimestampFrom(Date timestampFrom) {
        this.timestampFrom = timestampFrom;
    }

    public Date getTimestampTo() {
        return timestampTo;
    }

    public void setTimestampTo(Date timestampTo) {
        this.timestampTo = timestampTo;
    }

    public LogTypeEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEnum logType) {
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
