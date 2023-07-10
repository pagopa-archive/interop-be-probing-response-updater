package it.pagopa.interop.probing.response.updater.tracing.config;

import java.util.UUID;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import it.pagopa.interop.probing.response.updater.util.logging.LoggingPlaceholders;

@Aspect
@Component
public class XRayMDCConfig {

  @Before("execution(* it.pagopa.interop.probing.response.updater.consumer..*(..))")
  public void beforeConsumer(JoinPoint joinPoint) {
    MDC.put(LoggingPlaceholders.TRACE_ID_PLACEHOLDER,
        "- [CID= " + UUID.randomUUID().toString().toLowerCase() + "]");

  }
}
