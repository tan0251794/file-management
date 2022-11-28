package min.project.tms.daodemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class SecurityAspect {

    @Before("execution(void addAccount(..))")
    public void beforeAddAccountSecurityAdvise(){
        System.out.println("Security Advise Run!");
    }

}
