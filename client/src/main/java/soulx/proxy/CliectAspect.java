package soulx.proxy;

/**
 * @program: demo
 * @description: 模仿cliect代理，交给spring
 * @author: soulx
 * @create: 2020-08-03 11:25
 **/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class CliectAspect {
    @Pointcut("execution(* soulx..*.*(..))")
    public void point(){

    }

    @Around("point()")
    public Object rpcInvok(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before calling actual business logic"); // 在运行实际的业务逻辑之前
        // 业务逻辑方法被执行
        System.out.println("After calling actual business logic"); // 在运行实际业务逻辑之后
        return "asdas";
    }

}
