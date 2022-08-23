package home11.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    private Date start;
    private Date end;
    private Long time;
    private SimpleDateFormat sdf;
    private Map<String,Long> mapService = new HashMap<>();

    @Before("execution(* home11.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("{}",joinPoint);
        log.debug("{}",joinPoint.toLongString());
        Object[] args = joinPoint.getArgs();
        String logMessage = joinPoint.toShortString().replaceAll("execution|\\(..\\)|\\(|\\)", "")
                + Arrays.stream(args).map(String::valueOf)
                .collect(Collectors.joining(", "));
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        log.debug("{}", logMessage);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        start = now;
    }
    @After("execution(* home11.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint){
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Date now = new Date();
        end = now;
        time = end.getTime() - start.getTime();
        if (mapService.containsKey(ms.getDeclaringType().getSimpleName())){
            long l = mapService.get(ms.getDeclaringType().getSimpleName()).longValue();
            mapService.replace(ms.getDeclaringType().getSimpleName(),l+time);
        }else {
            mapService.put(ms.getDeclaringType().getSimpleName(),time);
        }
    }

    public Map<String, Long> getMapService() {
        return mapService;
    }
}
