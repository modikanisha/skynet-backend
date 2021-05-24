package com.skynet.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonMessageTemplateResponse extends AbstractGenericResponse {

    private boolean isSuccess;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int errorCode;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int subErrorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getSubErrorCode() {
        return subErrorCode;
    }

    public void setSubErrorCode(int subErrorCode) {
        this.subErrorCode = subErrorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
