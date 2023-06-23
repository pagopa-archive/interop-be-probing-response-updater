package it.pagopa.interop.probing.response.updater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.xray.spring.aop.XRayEnabled;
import it.pagopa.interop.probing.response.updater.client.EserviceClient;
import it.pagopa.interop.probing.response.updater.dto.impl.ChangeResponseReceived;
import it.pagopa.interop.probing.response.updater.exception.EserviceNotFoundException;
import it.pagopa.interop.probing.response.updater.service.EserviceService;
import it.pagopa.interop.probing.response.updater.util.logging.Logger;

@Service
@XRayEnabled
public class EserviceServiceImpl implements EserviceService {

  @Autowired
  private EserviceClient eserviceClient;

  @Autowired
  private Logger logger;

  @Override
  public void updateResponseReceived(Long eserviceRecordId,
      ChangeResponseReceived changeResponseReceived) throws EserviceNotFoundException {

    logger.logUpdateResponseReceived(eserviceRecordId, changeResponseReceived.responseReceived());

    eserviceClient.updateResponseReceived(eserviceRecordId, changeResponseReceived);
  }

}
