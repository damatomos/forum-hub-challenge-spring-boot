package br.com.damatomos.forum_hub.domain.exceptions;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message)
    {
        super(message);
    }
}
