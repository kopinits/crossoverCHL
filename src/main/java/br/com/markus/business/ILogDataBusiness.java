package br.com.markus.business;

import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.LogDataException;
import br.com.markus.model.LogData;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for LogDataBusiness
 */
public interface ILogDataBusiness {
    void insertLogData(LogData logData);
    ArrayList<LogData> getLogData(LogTypeEnum logTypeEnum);
    String getJSONLogData(LogTypeEnum logTypeEnum);
    ArrayList<LogDataException> validateLogData(LogData logData);
}
