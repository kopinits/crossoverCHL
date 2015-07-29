package br.com.markus.exception;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MultipleLogDataException extends RuntimeException {

    /**
     * Serial
     */
    private static final long serialVersionUID = 7139972070262446093L;

    /**
     * Lista de excecoes
     */
    private Set<LogDataException> exceptions = new HashSet<LogDataException>();

    /**
     * Constroi uma instancia desta classe.
     *
     * @param message mensagem de erro
     */
    public MultipleLogDataException(LogDataException message) {
        exceptions.add(message);
    }

    /**
     * Constroi uma instancia desta classe.
     *
     * @param messageList mensagem de erro
     */
    public MultipleLogDataException(Collection<? extends LogDataException> messageList) {
        exceptions.addAll(messageList);
    }

    /**
     * Construtor padrao
     */
    public MultipleLogDataException() {
    }

    /**
     * Setter dos parametros para formatacao da mensagem.
     *
     * @param message a mensagem de detalhe
     */
    public void addException(LogDataException message) {
        exceptions.add(message);
    }

    /**
     * Setter dos parametros para formatacao da mensagem.
     *
     * @param messageList a mensagem de detalhe
     */
    public void addExceptionList(Collection<? extends LogDataException> messageList) {
        exceptions.addAll(messageList);
    }

    public Set<LogDataException> getExceptions() {
        return exceptions;
    }

    public boolean contains(LogDataException ex) {
        return (null != exceptions && !exceptions.isEmpty()) && exceptions.contains(ex);
    }
}
