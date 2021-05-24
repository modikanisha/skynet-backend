package com.skynet.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {

    private boolean isSuccess;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int errorCode;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int subErrorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMsg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public GenericResponse(int errorCode, int subErrorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.subErrorCode = subErrorCode;
        this.errorMsg = errorMsg;
        this.isSuccess = false;
    }

    public GenericResponse(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.isSuccess = false;
    }

    public GenericResponse(int errorCode, String errorMsg, Object data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data=data;
        this.isSuccess = false;
    }

    public GenericResponse(Object data) {
        this.data = data;
        this.isSuccess = true;
    }

    public GenericResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
