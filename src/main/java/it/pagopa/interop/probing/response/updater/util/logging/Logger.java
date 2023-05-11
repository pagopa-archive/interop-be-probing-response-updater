package it.pagopa.interop.probing.response.updater.util.logging;

public interface Logger {

  void logMessageReceiver(Long id);

  void logMessageException(Exception exception);
}
