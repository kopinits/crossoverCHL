package br.com.markus.service;

import br.com.markus.converter.LogDataConverter;
import br.com.markus.dao.LogDataDAO;
import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogaDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.LogDataException;
import br.com.markus.exception.MultipleLogDataException;
import br.com.markus.message.MessageConstants;
import br.com.markus.model.LogData;
import br.com.markus.model.LogDataQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Service for LogData
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataService {

    @Autowired
    private LogDataDAO logDataDAO;

    @Autowired
    private LogDataConverter converter;


    public void saveLogData(LogaDataDTO logaDataDTO) throws UnknownHostException {
        LogData logData = converter.toLogData(logaDataDTO);
        checkException(validateLogData(logData));
        logDataDAO.saveLogData(logData);
    }


    public ArrayList<LogData> queryLogData(LogDataQueryDTO logDataQueryDTO) throws UnknownHostException {
        LogDataQuery logDataQuery = converter.toLogDataQuery(logDataQueryDTO);
        return logDataDAO.queryLogData(logDataQuery);
    }


    private ArrayList<LogDataException> validateLogData(LogData logData) {
        ArrayList<LogDataException> errors = new ArrayList<LogDataException>();
        validateAppCode(logData, errors);
        validateTimestamp(logData, errors);
        validateLogType(logData, errors);
        validateDataLogged(logData, errors);
        validateCustumerID(logData, errors);
        return errors;
    }

    private void checkException(ArrayList<LogDataException> exceptions) {
        if (!exceptions.isEmpty()) {
            throw new MultipleLogDataException(exceptions);
        }
    }

    private void validateTimestamp(LogData logData, ArrayList<LogDataException> errors) {
        if (logData.getTimestamp() == null) {
            errors.add(new LogDataException(MessageConstants.TIMESTAMP_MISSING));
        }
    }

    private void validateAppCode(LogData logData, ArrayList<LogDataException> errors) {
        if (StringUtils.isBlank(logData.getAppCode())) {
            errors.add(new LogDataException(MessageConstants.APPCODE_MISSING));
        }
    }

    private void validateLogType(LogData logData, ArrayList<LogDataException> errors) {
        if (logData.getLogType() == null) {
            errors.add(new LogDataException(MessageConstants.LOGTYPE_MISSING));
        }
    }

    private void validateCustumerID(LogData logData, ArrayList<LogDataException> errors) {
        if (logData.getLogType() != null) {
            if (isCustumerType(logData)) {
                if (StringUtils.isBlank(logData.getCustumerID())) {
                    errors.add(new LogDataException(MessageConstants.CUSTUMER_ID_MISSING));
                }
            }
        }
    }

    private boolean isCustumerType(LogData logData) {
        return logData.getLogType().equals(LogTypeEnum.CSTM_PRDT_VIEW) || logData.getLogType().equals(LogTypeEnum.CSTM_SRCH_DONE);
    }

    private void validateDataLogged(LogData logData, ArrayList<LogDataException> errors) {
        if (StringUtils.isBlank(logData.getDataLogged())) {
            errors.add(new LogDataException(MessageConstants.LOGDATA_MISSING));
        }
    }
}
