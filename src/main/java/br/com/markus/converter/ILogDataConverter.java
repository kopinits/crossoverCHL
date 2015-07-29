package br.com.markus.converter;

import br.com.markus.converter.impl.LogDataConverter;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Interface for convertion of JSON->Entity; Entity->JSON
 *
 * @author Markus Kopinits
 */
@Component
public interface ILogDataConverter {
    /**
     * Method responsable for convertion of a JSON data to  LogData entity
     *
     * @param logDataString json of the logdata entity
     * @return LogData entity
     * @throws Exception
     * @see LogDataConverter
     */
    LogData toLogData(String logDataString) throws Exception;

    LogTypeEnum toLogDataQuery(String logDataString) throws Exception;

    /**
     * Method responsable for convertion of a LogData entity to JSON
     *
     * @param logData entity
     * @return json of the logdata entity
     * @throws Exception
     * @see LogDataConverter
     */
    String fromLogData(LogData logData) throws Exception;

    String fromLogData(ArrayList<LogData> logsData) throws Exception;

}
