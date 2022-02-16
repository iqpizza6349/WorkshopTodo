package com.tistory.workshop6349.workshoptodo.model.response;

import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ListResult<T> extends CommonResult {

    private List<T> list;

}
