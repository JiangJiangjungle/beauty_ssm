package com.yingjun.ssm.entity.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class RequestObj {
    public RequestObj() {
    }
}
