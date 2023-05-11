package it.pagopa.interop.probing.response.updater.consumer;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceivedDto;
import it.pagopa.interop.probing.response.updater.util.logging.Logger;


@Component
public class PollingReceiver {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  private Logger logger;

  @SqsListener(value = "${amazon.sqs.end-point.poll-result-queue}",
      deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveStringMessage(final String message) throws IOException {

    ChangeResponseReceivedDto service = mapper.readValue(message, ChangeResponseReceivedDto.class);

    logger.logMessageReceiver(service.eserviceRecordId());
  }
}
