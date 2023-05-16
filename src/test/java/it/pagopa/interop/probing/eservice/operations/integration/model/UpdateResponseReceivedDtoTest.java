package it.pagopa.interop.probing.eservice.operations.integration.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import it.pagopa.interop.probing.response.updater.dto.impl.UpdateResponseReceivedDto;
import it.pagopa.interop.probing.response.updater.util.EserviceStatus;

class UpdateResponseReceivedDtoTest {
  Long eserviceRecordId = 123L;
  OffsetDateTime responseReceived = OffsetDateTime.now(ZoneOffset.UTC);
  EserviceStatus status = EserviceStatus.OK;

  private UpdateResponseReceivedDto updateResponseReceivedDto;

  @BeforeEach
  void setUp() {
    updateResponseReceivedDto =
        UpdateResponseReceivedDto.builder().eserviceRecordId(eserviceRecordId)
            .responseReceived(responseReceived).status(status).build();
  }

  @Test
  @DisplayName("given valid input data UpdateResponseReceivedDto object is created")
  void testBuilder_whenGivenValidInputData_thenDtoCreatedWithCorrectValues() {
    assertNotNull(updateResponseReceivedDto);
    assertEquals(eserviceRecordId, updateResponseReceivedDto.eserviceRecordId());
    assertEquals(responseReceived, updateResponseReceivedDto.responseReceived());
    assertEquals(status, updateResponseReceivedDto.status());
  }
}
