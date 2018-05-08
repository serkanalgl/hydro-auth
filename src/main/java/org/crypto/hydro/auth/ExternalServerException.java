package org.crypto.hydro.auth;

/**
 * Created by serkanalgul on 2.05.2018.
 */
public class ExternalServerException extends RuntimeException {

    ExternalServerException(String message) {
        super(message);
    }

    ExternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
