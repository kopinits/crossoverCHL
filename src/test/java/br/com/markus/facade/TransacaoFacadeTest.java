package br.com.markus.facade;


import br.com.markus.LogDataTest;
import br.com.markus.converter.ILogDataConverter;
import br.com.markus.model.LogData;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe responsável por realizar os testes da classe LogDataFacade
 *
 * @author Markus Kopinits
 */

public class TransacaoFacadeTest extends LogDataTest {

    public static final String INVALID_JSON = "INVALID JSON";
    @Autowired
    private ILogDataFacade logDataFacade;

    @Autowired
    private ILogDataConverter logDataConverter;

    @Test
    public void testInvalidJSON() throws Exception {
        try {
            logDataFacade.registerLog(INVALID_JSON);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    public void testInsertValidJSON() throws Exception {
        LogData validLogData = createValidLogData();
        logDataFacade.registerLog(logDataConverter.fromLogData(validLogData));
        String queryLog = logDataFacade.queryLog("{" + LogData.LOG_TYPE + ":" + validLogData.getLogType().getDescription() + "}");
        assert StringUtils.isNotBlank(queryLog);
    }
}
