package it.pagopa.interop.probing.eservice.operations.integration.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceived;
import it.pagopa.interop.probing.response.updater.util.EserviceStatus;

class ChangeResponseReceivedTest {

  OffsetDateTime responseReceived = OffsetDateTime.now(ZoneOffset.UTC);
  EserviceStatus status = EserviceStatus.OK;

  private ChangeResponseReceived changeResponseReceived;

  @BeforeEach
  void setUp() {
    changeResponseReceived =
        ChangeResponseReceived.builder().responseReceived(responseReceived).status(status).build();
  }

  @Test
  @DisplayName("given valid input data ChangeResponseReceivedDto object is created")
  void testBuilder_whenGivenValidInputData_thenDtoCreatedWithCorrectValues() {
    assertNotNull(changeResponseReceived);
    assertEquals(responseReceived, changeResponseReceived.responseReceived());
    assertEquals(status, changeResponseReceived.status());
  }

}

