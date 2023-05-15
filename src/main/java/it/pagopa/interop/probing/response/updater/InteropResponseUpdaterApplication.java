package it.pagopa.interop.probing.response.updater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InteropResponseUpdaterApplication {

  public static void main(String[] args) {
    SpringApplication.run(InteropResponseUpdaterApplication.class, args);
  }

}
