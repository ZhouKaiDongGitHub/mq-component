package com.luban.testcache.entity;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class R {
    private Integer code;
    private Object data;
    private String msg;

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }
    public R setData(Object data){
        this.data = data;
        return this;
    }
    public R setMsg(String msg){
        this.msg = msg;
        return this;
    }
}
