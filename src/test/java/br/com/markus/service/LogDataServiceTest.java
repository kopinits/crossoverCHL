package br.com.markus.service;


import br.com.markus.ApplicationTests;
import br.com.markus.dao.MongoConnection;
import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogaDataDTO;
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
    public void testQuery() throws UnknownHostException, ParseException {

        MongoClient mongoClient = mongoConnection.getConnection();
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase(mongoClient);

        mongoDatabase.getCollection("logdata").drop();

        SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
        Date parse = sf.parse("01/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidLogData());
        dataService.saveLogData(createValidLogData());
        parse = sf.parse("02/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidLogData());
        dataService.saveLogData(createValidLogData());
        dataService.saveLogData(createValidLogData());
        parse = sf.parse("03/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidLogData());

        parse = sf.parse("05/01/215");
        timestamp = String.valueOf(parse.getTime());
        dataService.saveLogData(createValidLogData());
        dataService.saveLogData(createValidLogData());

        LogDataQueryDTO logDataQueryDTO = new LogDataQueryDTO();
        logDataQueryDTO.setTimestampFrom(String.valueOf(sf.parse("01/01/215").getTime()));
        logDataQueryDTO.setTimestampTo(String.valueOf(sf.parse("03/01/215").getTime()));
        logDataQueryDTO.setLogType("CSTM_PRDT_VIEW");
        logDataQueryDTO.setCustumerID("10023FA34");
        ArrayList<LogData> logDatas = dataService.queryLogData(logDataQueryDTO);
        assert logDatas.size() == 6;

    }

    @Test
    public void testInvalidLogData() throws UnknownHostException {
        try {
            dataService.saveLogData(new LogaDataDTO());
        } catch (MultipleLogDataException e) {
            assert e.getExceptions().size() == 4;
        }
    }

    @Test
    public void testValidLogData() throws UnknownHostException {
        try {
            dataService.saveLogData(createValidLogData());
        } catch (MultipleLogDataException e) {
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
        logData.setTimestamp(timestamp);
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }
}
