package br.com.markus.webservice;

import br.com.markus.facade.ILogDataFacade;
import br.com.markus.webservice.util.TransacaoExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "ws")
public class QueryLogDataWS {
    private ILogDataFacade logDataFacade;

    @Autowired
    public QueryLogDataWS(ILogDataFacade logDataFacade) {
        this.logDataFacade = logDataFacade;
    }

    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String consultarPagamentos(@RequestBody String queryJson) {
        try {
            return logDataFacade.queryLog(queryJson);
        } catch (Exception e) {
            return TransacaoExceptionHandler.retornaExcecao(e);
        }
    }
}
