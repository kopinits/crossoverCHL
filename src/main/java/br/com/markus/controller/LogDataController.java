package br.com.markus.controller;

import br.com.markus.controller.util.TransacaoExceptionHandler;
import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogDataDTO;
import br.com.markus.model.LogData;
import br.com.markus.service.LogDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LogDataController {
    @Autowired
    private LogDataService service;

    @ResponseBody
    @RequestMapping(value = "/saveLog", method = RequestMethod.POST)
    public void saveLog(@RequestBody LogDataDTO logData) {
        try {
            service.saveLogData(logData);
        } catch (Exception e) {
            TransacaoExceptionHandler.getExcetionError(e);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/queryLog", method = RequestMethod.POST)
    public Collection<LogData> queryLog(@RequestBody LogDataQueryDTO dataQueryDTO) {
        try {
            return service.queryLogData(dataQueryDTO);
        } catch (Exception e) {
            TransacaoExceptionHandler.getExcetionError(e);
            return null;
        }
    }
}
