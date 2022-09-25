package io.eventscope.common.exception;


import io.eventscope.common.exception.util.ErrorCode;
import io.eventscope.common.exception.util.ErrorDetails;
import io.eventscope.common.exception.util.ErrorLevel;

public class IllegalArgumentException extends BaseRuntimeException {

    public IllegalArgumentException() {
        super(ErrorLevel.FATAL, ErrorCode.BAD_REQUEST, "IllegalArgumentException", "Illegal Argument Passed");
    }

    public IllegalArgumentException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public IllegalArgumentException(ErrorDetails errorDetails, Throwable throwable) {
        super(errorDetails, throwable);
    }

    public IllegalArgumentException(String message) {
        super(ErrorLevel.FATAL, ErrorCode.BAD_REQUEST, "IllegalArgumentException", message);
    }

    public IllegalArgumentException(String message, String userMessage) {
        super(ErrorLevel.FATAL, ErrorCode.BAD_REQUEST, "IllegalArgumentException", message, userMessage);
    }

}
