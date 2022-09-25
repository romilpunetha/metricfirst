package io.eventscope.common.exception.toresponse;

import io.eventscope.common.constant.Constant;
import io.eventscope.common.exception.util.ErrorCode;
import io.eventscope.common.exception.util.ErrorDetails;
import io.eventscope.common.exception.util.ErrorLevel;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.quarkus.arc.Priority;
import io.quarkus.logging.Log;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
@Priority(Priorities.ENTITY_CODER + 800)
public class ConstraintViolationExceptionHandler {


    @ServerExceptionMapper
    public RestResponse<ErrorDetails> toResponse(ConstraintViolationException e) {

        Span span = Span.current();
        span.recordException(e);
        span.setStatus(StatusCode.ERROR, e.getMessage());
        Log.error(e.getMessage(), e);
        Map<String, Object> violation = new HashMap<>();

        e.getConstraintViolations().forEach(constraintViolation -> {
            List<String> propertyNodes = Arrays.stream(constraintViolation.getPropertyPath().toString().split("\\."))
                    .collect(Collectors.toList());
            violation.put("field", propertyNodes.get(propertyNodes.size() - 1));
            violation.put("message", constraintViolation.getMessage());

        });

        ErrorDetails errorDetails = ErrorDetails.builder()
                .title(e.getClass().getSimpleName())
                .errorCode(ErrorCode.BAD_REQUEST)
                .violations(List.of(violation))
                .level(ErrorLevel.WARNING)
                .message(e.getMessage())
                .userMessage(Constant.DEFAULT_ERROR_MESSAGE)
                .build();

        return RestResponse.status(
                RestResponse.Status.fromStatusCode(errorDetails.getErrorCode().getErrorType().getStatusCode()),
                errorDetails);
    }
}
