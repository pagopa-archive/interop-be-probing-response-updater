package it.pagopa.interop.probing.response.updater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy
public class InteropResponseUpdaterApplication {

  public static void main(String[] args) {
    SpringApplication.run(InteropResponseUpdaterApplication.class, args);
  }

}
