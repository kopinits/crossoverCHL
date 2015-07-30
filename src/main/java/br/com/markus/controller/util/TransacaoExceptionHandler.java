package br.com.markus.controller.util;

import br.com.markus.exception.LogDataException;
import br.com.markus.exception.MultipleLogDataException;
import br.com.markus.message.MessageConstants;

/**
 * Classe para varrer a exceção e retornar os erros
 */
public class TransacaoExceptionHandler {

    public static String getExcetionError(Exception e){
        StringBuilder excecao = new StringBuilder();
        if (e instanceof MultipleLogDataException) {
            MultipleLogDataException me = (MultipleLogDataException) e;
            for (LogDataException exception : me.getExceptions()) {
                excecao.append(exception.getMessage());
            }
        }else if (e instanceof LogDataException){
            LogDataException exception = (LogDataException) e;
            excecao.append(exception.getMessage());
        }else{
            excecao.append(MessageConstants.GENERIC_ERROR);
        }
        return excecao.toString();
    }

}
