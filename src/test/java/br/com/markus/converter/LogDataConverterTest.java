package br.com.markus.converter;

import br.com.markus.ApplicationTests;
import br.com.markus.dto.LogDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Markus on 30/07/2015.
 */
public class LogDataConverterTest extends ApplicationTests {
    String timestamp;
    Date date;

    @Autowired
    LogDataConverter converter;

    @Before
    public void setup() {
        date = new Date();
        timestamp = String.valueOf(date.getTime());
    }

    @Test
    public void testToLogData() throws Exception {
        LogData convertedLogData = converter.toLogData(createValidLogDataDTO());
        LogData validLogData = createValidLogData();
        assert convertedLogData.equals(validLogData);
    }


    private LogDataDTO createValidLogDataDTO() {
        LogDataDTO logData = new LogDataDTO();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logData.setTimestamp(timestamp);
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }

    private LogData createValidLogData() {
        LogData logData = new LogData();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW);
        logData.setTimestamp(date);
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        return logData;
    }
}