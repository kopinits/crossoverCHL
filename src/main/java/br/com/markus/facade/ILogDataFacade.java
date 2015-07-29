package br.com.markus.facade;

/**
 * Interface for LogDataFacade
 */
public interface ILogDataFacade {

    void registerLog(String logString) throws Exception;
    String queryLog(String jsonQueryString) throws Exception;
}
