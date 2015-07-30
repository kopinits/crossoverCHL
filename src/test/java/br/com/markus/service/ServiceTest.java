package br.com.markus.service;


import br.com.markus.ApplicationTests;
import br.com.markus.dto.LogaDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.MultipleLogDataException;
import br.com.markus.message.MessageConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * Test cases for LogDataBusiness
 *
 * @author Markus Kopinits
 */

public class ServiceTest extends ApplicationTests{


    @Autowired
    private LogDataService dataService;

    @Test
    public void testInvalidLogData() throws UnknownHostException {
       try{
           dataService.saveLogData(new LogaDataDTO());
       }catch (MultipleLogDataException e){
           assert e.getExceptions().size() == 4;
       }
    }

    @Test
    public void testValidLogData() throws UnknownHostException {
        try{
            dataService.saveLogData(createValidLogData());
        }catch (MultipleLogDataException e){
            assert e.getExceptions().isEmpty();
        }
    }

    @Test
    public void testAppCodeNull() throws UnknownHostException {
        LogaDataDTO logData = createValidLogData();
        logData.setAppCode(null);
        verifyReturnedError(logData, MessageConstants.APPCODE_MISSING);
    }

    @Test
    public void testTimestampNull() throws UnknownHostException {
        LogaDataDTO logData = createValidLogData();
        logData.setTimestamp(null);
        verifyReturnedError(logData, MessageConstants.TIMESTAMP_MISSING);
    }

    @Test
    public void testLogTypeNull() throws UnknownHostException {
        LogaDataDTO logData = createValidLogData();
        logData.setLogType(null);
        verifyReturnedError(logData, MessageConstants.LOGTYPE_MISSING);
    }

    @Test
    public void testLoggedDataNull() throws UnknownHostException {
        LogaDataDTO logData = createValidLogData();
        logData.setDataLogged(null);
        verifyReturnedError(logData, MessageConstants.LOGDATA_MISSING);
    }


    @Test
    public void testCustumerIDNull() throws UnknownHostException {
        LogaDataDTO logData = createValidLogData();
        logData.setCustumerID(null);
        verifyReturnedError(logData, MessageConstants.CUSTUMER_ID_MISSING);
    }

    private void verifyReturnedError(LogaDataDTO logData, String messageError) throws UnknownHostException {
        try {
            dataService.saveLogData(logData);
        } catch (MultipleLogDataException e) {
            assert e.getExceptions().size() == 1;
            assert e.getExceptions().iterator().next().getMessage().equals(messageError);
        }
    }
/*
    @Test
    public void testInsertLogData() throws UnknownHostException {
        dataService.saveLogData(createValidLogData());
        List<LogData> logData = dataService.queryLogData(LogTypeEnum.CSTM_PRDT_VIEW);
        assert logData.size() == 1;
    }*/



    private LogaDataDTO createValidLogData() {
        LogaDataDTO logData = new LogaDataDTO();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logData.setTimestamp(String.valueOf(new Date().getTime()));
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }
}
