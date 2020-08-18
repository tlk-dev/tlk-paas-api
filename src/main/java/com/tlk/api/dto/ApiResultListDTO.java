package com.tlk.api.dto;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import lombok.Data;

import java.util.List;

@Data
public class ApiResultListDTO {

    private List<?> result;

    private int resultCode;

    private String resultMsg;

    public ApiResultListDTO(){}

    public ApiResultListDTO(List<?>list, int code) {
        this.result = list;
        this.resultCode = code;
        this.resultMsg = PaaSErrCode.getPaaSErrorMessage(code) == null ? PaasCodeDefine.SUCCESS : PaaSErrCode.getPaaSErrorMessage(code);
    }
}
