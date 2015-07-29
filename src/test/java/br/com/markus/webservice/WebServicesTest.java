package br.com.markus.webservice;

import br.com.markus.LogDataTest;
import br.com.markus.converter.ILogDataConverter;
import br.com.markus.facade.TransacaoFacadeTest;
import br.com.markus.message.MessageConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test cases for webservices
 *
 * @author Markus Kopinits
 */
public class WebServicesTest extends LogDataTest{
    @Autowired
    private RegisterLogDataWS logDataWS;
    @Autowired
    private ILogDataConverter logDataConverter;

    @Test
    public void testInvalidJSON(){
        String resposta = logDataWS.registerLogData(TransacaoFacadeTest.INVALID_JSON);
        assert resposta.equals(MessageConstants.GENERIC_ERROR);
    }

    @Test
    public void testValidJSON(){
        String json = null;
        try {
            json = logDataConverter.fromLogData(createValidLogData());
            logDataWS.registerLogData(json);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
}
