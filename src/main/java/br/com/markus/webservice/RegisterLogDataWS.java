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
public class RegisterLogDataWS {
    private ILogDataFacade logDataFacade;

    @Autowired
    public RegisterLogDataWS(ILogDataFacade transacaoFacade) {
        this.logDataFacade = transacaoFacade;
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerLogData(@RequestBody String jsonLogData) {
        try {
            logDataFacade.registerLog(jsonLogData);
        } catch (Exception e) {
            return TransacaoExceptionHandler.retornaExcecao(e);
        }
        return "";
    }
}
