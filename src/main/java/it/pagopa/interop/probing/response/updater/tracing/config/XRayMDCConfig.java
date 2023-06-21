package it.pagopa.interop.probing.response.updater.tracing.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.amazonaws.xray.AWSXRay;
import it.pagopa.interop.probing.response.updater.util.logging.LoggingPlaceholders;

@Aspect
@Component
public class XRayMDCConfig {

  @Before("execution(* it.pagopa.interop.probing.response.updater.service.impl..*(..))")
  public void beforeQueryOperation(JoinPoint joinPoint) {
    AWSXRay.beginSubsegment(
        LoggingPlaceholders.AURORA_SUBSEGMENT_NAME + " " + joinPoint.getSignature().getName());
  }

  @Before("execution(* it.pagopa.interop.probing.response.updater.service.impl..*(..))")
  public void afterQueryOperation(JoinPoint joinPoint) {
    AWSXRay.endSubsegment();
  }
}
