package it.pagopa.interop.probing.response.updater.util.logging.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import it.pagopa.interop.probing.response.updater.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LoggerImpl implements Logger {


  @Override
  public void logMessageReceiver(Long id) {
    log.info("Writing message, id={}", id);
  }

  @Override
  public void logMessageException(Exception exception) {
    log.error(ExceptionUtils.getStackTrace(exception));
  }
}
