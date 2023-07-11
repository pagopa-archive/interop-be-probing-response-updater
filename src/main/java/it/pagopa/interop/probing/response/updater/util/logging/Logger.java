package it.pagopa.interop.probing.response.updater.util.logging;

import java.time.OffsetDateTime;

public interface Logger {

  void logConsumerMessage(String message);

  void logMessageReceiver(Long id);

  void logUpdateResponseReceived(Long eserviceRecordId, OffsetDateTime responseReceived);

  void logMessageException(Exception exception);
}
