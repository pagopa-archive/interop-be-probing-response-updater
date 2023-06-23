package it.pagopa.interop.probing.response.updater.tracing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class TracingConfig {

  // @Value("${spring.application.name}")
  // private String awsXraySegmentName;
  //
  // @Bean
  // public Filter tracingFilter() {
  // return new AWSXRayServletFilter(awsXraySegmentName);
  // }
  //
  // @Bean
  // public HttpClientBuilder xrayHttpClientBuilder() {
  //
  // return HttpClientBuilder.create();
  // }
}
