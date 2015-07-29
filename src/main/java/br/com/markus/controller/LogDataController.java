package br.com.markus.controller;

import br.com.markus.controller.util.TransacaoExceptionHandler;
import br.com.markus.model.LogData;
import br.com.markus.service.LogDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class LogDataController {
    @Autowired
    private LogDataService service;

    @ResponseBody
    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public String registerLogData() {
        return "testandooooooooooo";
    }


    @ResponseBody
    @RequestMapping(value = "/saveLog", method = RequestMethod.POST)
    public String registerLogData(@RequestBody LogData logData) {
        try {
            service.saveLogData(logData);
        } catch (Exception e) {
            return TransacaoExceptionHandler.retornaExcecao(e);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/queryLog", method = RequestMethod.POST)
    public Collection<LogData> consultarPagamentos(@RequestBody LogData logData) {
        try {
            return service.getLogData(logData.getLogType());
        } catch (Exception e) {
            TransacaoExceptionHandler.retornaExcecao(e);
            return null;
        }
    }
}
