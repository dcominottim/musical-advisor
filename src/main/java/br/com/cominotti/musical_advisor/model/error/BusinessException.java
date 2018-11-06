package br.com.cominotti.musical_advisor.model.error;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }
}
