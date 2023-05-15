
package it.pagopa.interop.probing.response.updater.service;

import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceived;
import it.pagopa.interop.probing.response.updater.exception.EserviceNotFoundException;


public interface EserviceService {

  /**
   * Updates response received of the e-service
   * 
   * @param eserviceRecordId the parameter eserviceRecordId identifies the e-service
   * @param changeResponseReceived the object changeResponseReceived containing responseReceived
   * @throws EserviceNotFoundException if the e-service isn't found
   */
  void updateResponseReceived(Long eserviceRecordId, ChangeResponseReceived changeResponseReceived)
      throws EserviceNotFoundException;
}
