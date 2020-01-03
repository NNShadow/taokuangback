package com.flying.taokuang.utils;

import com.flying.taokuang.dataobject.Result;
import org.springframework.stereotype.Component;

/**
 * @author NNShadow
 * @date 2020/1/3 22:33
 */
@Component
public class ResponseData {
    public Result write(String msg, int code, Object data) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
