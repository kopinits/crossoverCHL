package br.com.markus.business;


import br.com.markus.LogDataTest;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.LogDataException;
import br.com.markus.message.MessageConstants;
import br.com.markus.model.LogData;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for LogDataBusiness
 *
 * @author Markus Kopinits
 */

public class LogDataBusinessTest extends LogDataTest{


    @Autowired
    private ILogDataBusiness logDataBusiness;

    @Test
    public void testInvalidLogData() {
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(new LogData());
        assert exceptions.size() == 4;
    }

    @Test
    public void testValidLogData() {
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(createValidLogData());
        assert exceptions.isEmpty();
    }

    @Test
    public void testAppCodeNull() {
        LogData logData = createValidLogData();
        logData.setAppCode(null);
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.APPCODE_MISSING);
    }

    @Test
    public void testTimestampNull() {
        LogData logData = createValidLogData();
        logData.setTimestamp(null);
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.TIMESTAMP_MISSING);
    }

    @Test
    public void testLogTypeNull() {
        LogData logData = createValidLogData();
        logData.setLogType(null);
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.LOGTYPE_MISSING);
    }

    @Test
    public void testLoggedDataNull() {
        LogData logData = createValidLogData();
        logData.setDataLogged(null);
        ArrayList<LogDataException> exceptions = logDataBusiness.validateLogData(logData);
        assert exceptions.size() == 1;
        assert exceptions.get(0).getMessage().equals(MessageConstants.LOGDATA_MISSING);
    }

    @Test
    public void testInsertLogData() {
        logDataBusiness.insertLogData(createValidLogData());
        List<LogData> logData = logDataBusiness.getLogData(LogTypeEnum.CSTM_PRDT_VIEW);
        assert logData.size() == 1;
    }

    @Test
    public void testInsertLogDataJsonReturn() {
        logDataBusiness.insertLogData(createValidLogData());
        String json = logDataBusiness.getJSONLogData(LogTypeEnum.CSTM_PRDT_VIEW);
        assert StringUtils.isNotBlank(json);
    }
}
