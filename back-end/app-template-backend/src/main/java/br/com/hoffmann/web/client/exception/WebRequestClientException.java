package br.com.hoffmann.web.client.exception;

public class WebRequestClientException extends RuntimeException {
    public WebRequestClientException(String erro) {
        super(erro);
    }
}
