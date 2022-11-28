package min.project.tms.daodemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class LoggingAspect {

    @Around("execution(void addAccount(..))")
    public Object aroundLoggingAdvise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw e;
        }
        return result;
    }


    @Pointcut("execution(* min.project.tms.daodemo.*.*())")
    private void forDaoPackage() {}
}
