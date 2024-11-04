package com.daniarques.realnaut_spaceships.domain.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Before("execution(* com.daniarques.realnaut_spaceships.controller..*(Long)) && args(id)")
    public void negativeValues(final JoinPoint point, final Long id) {

        if (id < 0) {
            log.info("negative number sent in {} with value {}", point.getSignature().getName(), id);
        }

    }
}