package com.example.timer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class ExecutionTimer {
    // 조인포인트를 어노테이션으로 설정
//    @Pointcut("@annotation(com.example.timer.ExeTimer)")
//    private void timer(){};
    // 한번에 @Around("@annotation(com.example.timer.ExeTimer)") 넣어도 된다.
    // 여러 표현식을 한번에 사용할 때는 && || ! 등으로 사용가능(포인트컷 표현식 결합)

    // 메서드 실행 전,후로 시간을 공유해야 하기 때문
    @Around("@annotation(ExeTimer)")
    public Object AssumeExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!log.isEnabledForLevel(Level.DEBUG /* 사용자가 입력한 로깅 레벨... */)) {
            return joinPoint.proceed();
        }
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object proceed = joinPoint.proceed(); // 조인포인트의 메서드 실행
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        // TODO Property 생성해서 Logging Level 입력 받을 수 있게
        // 로깅 레벨을 인식해서 기본 레벨 이상이면 무시하도록
        log.debug("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);
        return proceed;
    }
}