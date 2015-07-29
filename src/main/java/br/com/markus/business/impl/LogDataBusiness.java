package br.com.markus.business.impl;

import br.com.markus.business.ILogDataBusiness;
import br.com.markus.dao.ILogDataDAO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.exception.LogDataException;
import br.com.markus.exception.MultipleLogDataException;
import br.com.markus.message.MessageConstants;
import br.com.markus.model.LogData;
import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Business for LogData
 *
 * @author Markus Kopinits
 */
@Component
public class LogDataBusiness implements ILogDataBusiness {

    private ILogDataDAO logDataDAO;

    public LogDataBusiness() {
    }

    @Autowired
    public LogDataBusiness(ILogDataDAO logDataDAO) {
        this.logDataDAO = logDataDAO;
    }

    @Override
    public void insertLogData(LogData logData) {
        checkException(validateLogData(logData));
        logDataDAO.registerLog(convertLogData(logData));
    }

    @Override
    public ArrayList<LogData> getLogData(LogTypeEnum logTypeEnum) {
        return logDataDAO.retrieveLogData(logTypeEnum);
    }

    @Override
    public String getJSONLogData(LogTypeEnum logTypeEnum) {
        return logDataDAO.retrieveJsonLogData(logTypeEnum);
    }

    public ArrayList<LogDataException> validateLogData(LogData logData) {
        ArrayList<LogDataException> errors = new ArrayList<LogDataException>();
        validateAppCode(logData, errors);
        validateTimestamp(logData, errors);
        validateLogType(logData, errors);
        validateDataLogged(logData, errors);
        return errors;
    }

    private BasicDBObject convertLogData(LogData logData) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(LogData.APP_CODE, logData.getAppCode());
        dbObject.put(LogData.TIMESTAMP, logData.getTimestamp().getTime());
        dbObject.put(LogData.LOG_TYPE, logData.getLogType().getDescription());
        dbObject.put(LogData.DATA_LOGGED, logData.getDataLogged());
        return dbObject;
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

    private void validateDataLogged(LogData logData, ArrayList<LogDataException> errors) {
        if (StringUtils.isBlank(logData.getDataLogged())) {
            errors.add(new LogDataException(MessageConstants.LOGDATA_MISSING));
        }
    }
}
