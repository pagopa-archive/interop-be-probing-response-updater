package it.pagopa.interop.probing.eservice.operations.unit.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.awspring.cloud.messaging.listener.SimpleMessageListenerContainer;
import it.pagopa.interop.probing.response.updater.InteropResponseUpdaterApplication;
import it.pagopa.interop.probing.response.updater.client.EserviceClient;
import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceived;
import it.pagopa.interop.probing.response.updater.exception.EserviceNotFoundException;
import it.pagopa.interop.probing.response.updater.service.EserviceService;
import it.pagopa.interop.probing.response.updater.service.impl.EserviceServiceImpl;
import it.pagopa.interop.probing.response.updater.util.EserviceStatus;
import it.pagopa.interop.probing.response.updater.util.logging.Logger;

@SpringBootTest(classes = InteropResponseUpdaterApplication.class)
class EserviceServiceImplTest {

  @Mock
  private EserviceClient eserviceClient;

  @Mock
  private Logger logger;

  @MockBean
  private SimpleMessageListenerContainer simpleMessageListenerContainer;

  @InjectMocks
  private EserviceService eserviceService = new EserviceServiceImpl();

  private Long eserviceRecordId = 1L;
  private ChangeResponseReceived changeResponseReceived;

  @BeforeEach
  void setUp() {
    changeResponseReceived = new ChangeResponseReceived();
    changeResponseReceived.status(EserviceStatus.OK);
    changeResponseReceived.responseReceived(OffsetDateTime.now(ZoneOffset.UTC));
  }

  @Test
  @DisplayName("verify that the log is displayed")
  void testUpdateResponseReceived_whenGivenCorrectEserviceRecordIdAndChangeResponseReceived_thenLogUpdateIsShown()
      throws EserviceNotFoundException {

    eserviceService.updateResponseReceived(eserviceRecordId, changeResponseReceived);

    verify(logger).logUpdateResponseReceived(eserviceRecordId,
        changeResponseReceived.responseReceived());
  }

  @Test
  @DisplayName("finding the e-service identified by eserviceRecordId, response received is successfully updated")
  void testUpdateResponseReceived_whenGivenCorrectEserviceRecordIdAndChangeResponseReceived_thenResponseReceivedIsUpdated()
      throws EserviceNotFoundException {
    eserviceService.updateResponseReceived(eserviceRecordId, changeResponseReceived);

    verify(eserviceClient).updateResponseReceived(eserviceRecordId, changeResponseReceived);
    Assertions.assertDoesNotThrow(
        () -> eserviceService.updateResponseReceived(eserviceRecordId, changeResponseReceived),
        "e-service should be found");
  }

  @Test
  @DisplayName("not finding the e-service identified by eserviceRecordId, an EserviceNotFoundException should be thrown")
  void testUpdateResponseReceived_whenNoEServiceIsFound_thenThrowsException()
      throws EserviceNotFoundException {

    when(eserviceClient.updateResponseReceived(eserviceRecordId, changeResponseReceived))
        .thenThrow(new EserviceNotFoundException("no found e-service to update response received"));

    Assertions.assertThrows(EserviceNotFoundException.class,
        () -> eserviceService.updateResponseReceived(eserviceRecordId, changeResponseReceived),
        "e-service should not be found and an EserviceNotFoundException should be thrown");
  }
}
