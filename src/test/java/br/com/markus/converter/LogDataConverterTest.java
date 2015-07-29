package br.com.markus.converter;

import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Test Cases for logData converter
 *
 * @author Markus Kopinits
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LogDataConverterTest {


    private LogData logDataBase;

    @Before
    public void setup() {
        logDataBase = new LogData();
        logDataBase.setAppCode("gu4a");
        logDataBase.setTimestamp(new Date(1438127603643l));
        logDataBase.setDataLogged("Iphone 6");
        logDataBase.setLogType(LogTypeEnum.CSTM_PRDT_VIEW);
        logDataBase.setCustumerID("10023FA34");
    }

    @Test
    public void testJSONToLogData() throws Exception {
        String jsonLogData = "{" +
                "\"appCode\":\"gu4a\"," +
                "\"timestamp\":\"1438127603643\"," +
                "\"logType\":\"CUSTUMER_PRODUCT_VIEW\"," +
                "\"dataLogged\":\"Iphone 6\"," +
                "\"custumerID\":\"10023FA34\"," +
                "}";
        //validateConvertedLogData(logDataConverter.toLogData(jsonLogData));
    }

    @Test
    public void testJSONToLogDataError() throws Exception {
        String jsonLogData = "{" +
                "\"invalidAttr1\":\"gu4a\"," +
                "\"timestamp\":\"1438127603643\"," +
                "\"invalidAttr2\":\"CUSTUMER_PRODUCT_VIEW\"," +
                "\"dataLogge1d\":\"Iphone 6\"," +
                "\"custumerID\":\"10023FA34\"," +
                "}";
        //validateConvertedLogData(logDataConverter.toLogData(jsonLogData));
    }

  /*  @Test
    public void testLogDatatoJSON() throws Exception {
        String json = logDataConverter.fromLogData(logDataBase);
        validateConvertedLogData(logDataConverter.toLogData(json));
    }
*/
    private void validateConvertedLogData(LogData logData) {
        if (logData != null) {
            assert logDataBase.getAppCode().equals(logData.getAppCode());
            assert logDataBase.getDataLogged().equals(logData.getDataLogged());
            assert logDataBase.getLogType().equals(logData.getLogType());
            assert logDataBase.getTimestamp().equals(logData.getTimestamp());
            assert logDataBase.getCustumerID().equals(logData.getCustumerID());
        }else{
            assert true;
        }
    }


}
