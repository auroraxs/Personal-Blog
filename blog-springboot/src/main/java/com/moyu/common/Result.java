package com.moyu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    // 返回状态码，200表示ok，400表示错误
    private String state;
    // 返回操作信息
    private String message;
    // 返回的数据
    private Map<String, Object> data = new HashMap<>();

    public Result success() {
        return new Result("200", "操作成功", this.data);
    }

    public Result set(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public Result error() {
        return new Result("400", "操作失败", this.data);
    }
}
