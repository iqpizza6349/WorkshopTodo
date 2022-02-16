package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingleResult<T> extends CommonResult {

    private T data;

}
