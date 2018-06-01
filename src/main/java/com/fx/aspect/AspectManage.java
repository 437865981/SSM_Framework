package com.fx.aspect;


import org.apache.log4j.Logger;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class AspectManage {

    private static final Logger log = Logger.getLogger(AspectManage.class);

    public void  beforeMethod( ){
        log.error("beforeMethod");
    }
    public void  afterMethod(){
        log.error("afterMethod");
    }
    public void  afterReturnMethod(){
        log.error("afterReturnMethod");
    }
    public void  afterThrowingMethod(){
        log.error("afterThrowingMethod");
    }
    public void  aroundMethod(){
        log.error("aroundMethod");
    }


}
