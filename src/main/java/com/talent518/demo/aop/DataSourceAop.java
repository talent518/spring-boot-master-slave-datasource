package com.talent518.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.talent518.demo.bean.DBContextHolder;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.talent518.demo.annotation.Master) " +
            "&& (execution(* com.talent518.demo.service..*.select*(..)) " +
    		"|| execution(* com.talent518.demo.service..*.find*(..))" +
            "|| execution(* com.talent518.demo.service..*.get*(..)))" + 
            "|| @annotation(com.talent518.demo.annotation.Salve)")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.talent518.demo.annotation.Master) " +
            "|| execution(* com.talent518.demo.service..*.create*(..)) " +
            "|| execution(* com.talent518.demo.service..*.insert*(..)) " +
            "|| execution(* com.talent518.demo.service..*.add*(..)) " +
            "|| execution(* com.talent518.demo.service..*.update*(..)) " +
            "|| execution(* com.talent518.demo.service..*.edit*(..)) " +
            "|| execution(* com.talent518.demo.service..*.delete*(..)) " +
            "|| execution(* com.talent518.demo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.talent518.demo.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}