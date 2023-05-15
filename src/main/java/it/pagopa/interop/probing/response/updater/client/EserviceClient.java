package it.pagopa.interop.probing.response.updater.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceived;
import it.pagopa.interop.probing.response.updater.exception.EserviceNotFoundException;

@FeignClient(name = "eserviceClient",
    url = "${api.operations.baseUrl}" + "${api.operations.eservice.basePath}")
public interface EserviceClient {

  @PostMapping("/{eserviceRecordId}/updateResponseReceived")
  ResponseEntity<Void> updateResponseReceived(
      @PathVariable("eserviceRecordId") Long eserviceRecordId,
      @RequestBody ChangeResponseReceived changeResponseReceived) throws EserviceNotFoundException;

}
