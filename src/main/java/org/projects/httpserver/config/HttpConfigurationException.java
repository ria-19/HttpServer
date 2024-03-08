package org.projects.httpserver.config;

public class HttpConfigurationException extends RuntimeException{
    public HttpConfigurationException(String message) {
        super(message);
    }

    public HttpConfigurationException() {
    }

    public HttpConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfigurationException(Throwable cause) {
        super(cause);
    }
}
