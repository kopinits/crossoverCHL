package br.com.markus.service;


import br.com.markus.ApplicationTests;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.LogDataException;
import br.com.markus.message.MessageConstants;
import br.com.markus.model.LogData;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test cases for LogDataBusiness
 *
 * @author Markus Kopinits
 */

public class ServiceTest extends ApplicationTests{


    @Autowired
    private LogDataService dataService;

    @Test
    public void testInvalidLogData() {
        ArrayList<LogDataException> exceptions = dataService.validateLogData(new LogData());
        assert exceptions.size() == 4;
    }

    @Test
    public void testValidLogData() {
        ArrayList<LogDataException> exceptions = dataService.validateLogData(createValidLogData());
        assert exceptions.isEmpty();
    }

    @Test
    public void testAppCodeNull() {
        LogData logData = createValidLogData();
        logData.setAppCode(null);
        ArrayList<LogDataException> exceptions = dataService.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.APPCODE_MISSING);
    }

    @Test
    public void testTimestampNull() {
        LogData logData = createValidLogData();
        logData.setTimestamp(null);
        ArrayList<LogDataException> exceptions = dataService.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.TIMESTAMP_MISSING);
    }

    @Test
    public void testLogTypeNull() {
        LogData logData = createValidLogData();
        logData.setLogType(null);
        ArrayList<LogDataException> exceptions = dataService.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.LOGTYPE_MISSING);
    }

    @Test
    public void testLoggedDataNull() {
        LogData logData = createValidLogData();
        logData.setDataLogged(null);
        ArrayList<LogDataException> exceptions = dataService.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.LOGDATA_MISSING);
    }


    @Test
    public void testCustumerIDNull() {
        LogData logData = createValidLogData();
        logData.setCustumerID(null);
        ArrayList<LogDataException> exceptions = dataService.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.CUSTUMER_ID_MISSING);
    }

    @Test
    public void testInsertLogData() {
        dataService.saveLogData(createValidLogData());
        List<LogData> logData = dataService.getLogData(LogTypeEnum.CSTM_PRDT_VIEW);
        assert logData.size() == 1;
    }

    @Test
    public void testInsertLogDataJsonReturn() {
        dataService.saveLogData(createValidLogData());
        String json = dataService.getJSONLogData(LogTypeEnum.CSTM_PRDT_VIEW);
        assert StringUtils.isNotBlank(json);
    }

    private LogData createValidLogData() {
        LogData logData = new LogData();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW);
        logData.setTimestamp(new Date());
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }
}
