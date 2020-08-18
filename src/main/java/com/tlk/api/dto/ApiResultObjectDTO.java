package com.tlk.api.dto;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class ApiResultObjectDTO {

    private Object result;

    private int resultCode;

    private String resultMsg;

    public ApiResultObjectDTO(){}

    public ApiResultObjectDTO(Object result, int code) {
        this.resultCode = code;
        this.result = result;
        this.resultMsg = PaaSErrCode.getPaaSErrorMessage(code) == null ? PaasCodeDefine.SUCCESS : PaaSErrCode.getPaaSErrorMessage(code);
    }
}
