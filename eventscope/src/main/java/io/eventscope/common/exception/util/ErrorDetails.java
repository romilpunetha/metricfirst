package io.eventscope.common.exception.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.eventscope.common.constant.Constant;
import io.eventscope.common.constant.GlobalEnv;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetails {

    protected static final ErrorLevel DEFAULT_LOG_LEVEL = ErrorLevel.WARNING;
    protected static final String DEFAULT_CODE = GlobalEnv.DEFAULT_ERROR_CODE;
    protected static final String DEFAULT_NAMESPACE = GlobalEnv.ERROR_NAMESPACE;
    protected static final ErrorCode DEFAULT_STATUS_CODE = ErrorCode.BACKEND_ERROR;
    protected static final String DEFAULT_USER_MESSAGE = GlobalEnv.DEFAULT_ERROR_MESSAGE;

    protected ErrorLevel level;
    protected ErrorCode errorCode;
    protected String title;
    protected String message;
    @Getter
    protected String namespace = GlobalEnv.K8S_APP_NAME;
    protected String userMessage = Constant.DEFAULT_ERROR_MESSAGE;
    protected List<Map<String, Object>> violations;
    protected Map<String, Object> metadata;

    protected Map<String, Object> context;
    protected List<String> fingerprint;

    public ErrorType getErrorType() {
        return this.errorCode != null ? this.errorCode.getErrorType() : null;
    }
}
