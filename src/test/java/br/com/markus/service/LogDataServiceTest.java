package br.com.markus.service;


import br.com.markus.ApplicationTests;
import br.com.markus.dao.MongoConnection;
import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.MultipleLogDataException;
import br.com.markus.message.MessageConstants;
import br.com.markus.model.LogData;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test cases for LogDataBusiness
 *
 * @author Markus Kopinits
 */

public class LogDataServiceTest extends ApplicationTests {


    @Autowired
    private LogDataService dataService;
    @Autowired
    MongoConnection mongoConnection;
    String timestamp;

    @Before
    public void setup(){
        timestamp = String.valueOf(new Date().getTime());
    }

    @Test
    public void testQueryCustumer() throws UnknownHostException, ParseException {

        clearDatabase();

        SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
        Date parse = sf.parse("01/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidCustumerLogData());
        dataService.saveLogData(createValidCustumerLogData());
        parse = sf.parse("02/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidCustumerLogData());
        dataService.saveLogData(createValidCustumerLogData());
        dataService.saveLogData(createValidCustumerLogData());
        parse = sf.parse("03/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidCustumerLogData());

        parse = sf.parse("05/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidCustumerLogData());
        dataService.saveLogData(createValidCustumerLogData());

        LogDataQueryDTO logDataQueryDTO = new LogDataQueryDTO();
        logDataQueryDTO.setTimestampFrom(String.valueOf(sf.parse("01/01/215").getTime()));
        logDataQueryDTO.setTimestampTo(String.valueOf(sf.parse("03/01/215").getTime()));
        logDataQueryDTO.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logDataQueryDTO.setCustumerID("10023FA34");
        ArrayList<LogDataDTO> logDatas = dataService.queryLogData(logDataQueryDTO);
        assert logDatas.size() == 6;

    }

    @Test
    public void testQueryAppLog() throws UnknownHostException, ParseException {
        clearDatabase();
        SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
        Date parse = sf.parse("01/01/215");
        timestamp = String.valueOf(parse.getTime());
        LogDataDTO validAppLogData = createValidAppLogData();
        dataService.saveLogData(validAppLogData);
        dataService.saveLogData(validAppLogData);
        LogDataQueryDTO logDataQueryDTO = new LogDataQueryDTO();
        logDataQueryDTO.setTimestampFrom(String.valueOf(sf.parse("01/01/215").getTime()));
        logDataQueryDTO.setTimestampTo(String.valueOf(sf.parse("03/01/215").getTime()));
        logDataQueryDTO.setLogType(LogTypeEnum.APPL_ERROR_LOG.getDescription());
        logDataQueryDTO.setAppCode(validAppLogData.getAppCode());
        ArrayList<LogDataDTO> logDatas = dataService.queryLogData(logDataQueryDTO);
        assert logDatas.size() == 2;

    }

    private void clearDatabase() {
        MongoClient mongoClient = mongoConnection.getConnection();
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase(mongoClient);

        mongoDatabase.getCollection("logdata").drop();
    }

    @Test
    public void testInvalidLogData() throws UnknownHostException {
        try {
            dataService.saveLogData(new LogDataDTO());
        } catch (MultipleLogDataException e) {
            assert e.getExceptions().size() == 4;
        }
    }

    @Test
    public void testValidLogData() throws UnknownHostException {
        try {
            dataService.saveLogData(createValidCustumerLogData());
        } catch (MultipleLogDataException e) {
            assert e.getExceptions().isEmpty();
        }
    }

    @Test
    public void testAppCodeNull() throws UnknownHostException {
        LogDataDTO logData = createValidCustumerLogData();
        logData.setAppCode(null);
        verifyReturnedError(logData, MessageConstants.APPCODE_MISSING);
    }

    @Test
    public void testTimestampNull() throws UnknownHostException {
        LogDataDTO logData = createValidCustumerLogData();
        logData.setTimestamp(null);
        verifyReturnedError(logData, MessageConstants.TIMESTAMP_MISSING);
    }

    @Test
    public void testLogTypeNull() throws UnknownHostException {
        LogDataDTO logData = createValidCustumerLogData();
        logData.setLogType(null);
        verifyReturnedError(logData, MessageConstants.LOGTYPE_MISSING);
    }

    @Test
    public void testLoggedDataNull() throws UnknownHostException {
        LogDataDTO logData = createValidCustumerLogData();
        logData.setDataLogged(null);
        verifyReturnedError(logData, MessageConstants.LOGDATA_MISSING);
    }


    @Test
    public void testCustumerIDNull() throws UnknownHostException {
        LogDataDTO logData = createValidCustumerLogData();
        logData.setCustumerID(null);
        verifyReturnedError(logData, MessageConstants.CUSTUMER_ID_MISSING);
    }

    private void verifyReturnedError(LogDataDTO logData, String messageError) throws UnknownHostException {
        try {
            dataService.saveLogData(logData);
        } catch (MultipleLogDataException e) {
            assert e.getExceptions().size() == 1;
            assert e.getExceptions().iterator().next().getMessage().equals(messageError);
        }
    }

    @Test
    public void testInsertLogData() throws UnknownHostException {
        clearDatabase();
        LogDataDTO validLogData = createValidCustumerLogData();
        dataService.saveLogData(validLogData);
        LogDataQueryDTO logDataQueryDTO = new LogDataQueryDTO();
        logDataQueryDTO.setTimestampFrom(validLogData.getTimestamp());
        logDataQueryDTO.setTimestampTo(validLogData.getTimestamp());
        logDataQueryDTO.setLogType(validLogData.getLogType());
        logDataQueryDTO.setCustumerID(validLogData.getCustumerID());
        List<LogDataDTO> logData = dataService.queryLogData(logDataQueryDTO);
        assert logData.size() == 1;
    }


    private LogDataDTO createValidCustumerLogData() {
        LogDataDTO logData = new LogDataDTO();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logData.setTimestamp(timestamp);
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }

    private LogDataDTO createValidAppLogData() {
        LogDataDTO logData = new LogDataDTO();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.APPL_ERROR_LOG.getDescription());
        logData.setTimestamp(timestamp);
        logData.setDataLogged("java.lang.NullPointerException");
        return logData;
    }
}
