package br.com.markus.facade.impl;

import br.com.markus.business.ILogDataBusiness;
import br.com.markus.converter.ILogDataConverter;
import br.com.markus.facade.ILogDataFacade;
import br.com.markus.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


/**
 * Classe responsável por ser a fachada para processar as solicitações de pagamento
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataFacade implements ILogDataFacade {

    private ILogDataBusiness logDataBusiness;
    private ILogDataConverter logDataConverter;

    public LogDataFacade() {
    }

    @Autowired
    public LogDataFacade(ILogDataBusiness logDataBusiness, ILogDataConverter logDataConverter) {
        this.logDataBusiness = logDataBusiness;
        this.logDataConverter = logDataConverter;
    }

    /**
     * Method responsable for insert a new LogData
     *
     * @param logString
     * @throws Exception
     */
    public void registerLog(String logString) throws Exception {
        logDataBusiness.insertLogData(logDataConverter.toLogData(logString));
    }


    /**
     * Method for search logdata by a logtype
     *
     * @param jsonQueryString
     * @return json for all logdata that fits the filter
     * @throws Exception
     */
    public String queryLog(String jsonQueryString) throws Exception {
        ArrayList<LogData> logsData = logDataBusiness.getLogData(logDataConverter.toLogDataQuery(jsonQueryString));
        return logDataConverter.fromLogData(logsData);
    }
}
