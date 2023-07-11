package it.pagopa.interop.probing.response.updater.util.logging.impl;

import java.time.OffsetDateTime;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import it.pagopa.interop.probing.response.updater.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LoggerImpl implements Logger {

  @Override
  public void logConsumerMessage(String message) {
    log.info("Message received : {}", message);
  }

  @Override
  public void logMessageReceiver(Long id) {
    log.info("Writing message, id={}", id);
  }

  @Override
  public void logUpdateResponseReceived(Long eserviceRecordId, OffsetDateTime responseReceived) {
    log.info("Updating e-service response received. eserviceRecordId={}, responseReceived={}",
        eserviceRecordId, responseReceived);
  }

  @Override
  public void logMessageException(Exception exception) {
    log.error(ExceptionUtils.getStackTrace(exception));
  }
}
