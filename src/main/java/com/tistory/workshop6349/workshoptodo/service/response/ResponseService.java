package com.tistory.workshop6349.workshoptodo.service.response;

import com.tistory.workshop6349.workshoptodo.controller.ListResult;
import com.tistory.workshop6349.workshoptodo.controller.SingleResult;
import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ResponseService {

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(1, "성공"),
        DEPRECATED(0, "버전 오류"),
        FAIL(-1, "실패");

        private int code;
        private String msg;
    }

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult(int code) {
        CommonResult result = new CommonResult();
        result.setSuccess(true);
        result.setCode(code);
        result.setMsg(CommonResponse.SUCCESS.getMsg());
        return result;
    }

    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    private void setFailResult(CommonResult result, String msg) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(msg);
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }


}
