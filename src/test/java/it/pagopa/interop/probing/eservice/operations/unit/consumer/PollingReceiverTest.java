package it.pagopa.interop.probing.eservice.operations.unit.consumer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.xray.AWSXRay;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.awspring.cloud.messaging.listener.SimpleMessageListenerContainer;
import it.pagopa.interop.probing.response.updater.InteropResponseUpdaterApplication;
import it.pagopa.interop.probing.response.updater.consumer.PollingReceiver;
import it.pagopa.interop.probing.response.updater.exception.EserviceNotFoundException;
import it.pagopa.interop.probing.response.updater.service.EserviceService;


@SpringBootTest(classes = InteropResponseUpdaterApplication.class)
class PollingReceiverTest {

  @Value("classpath:data/messageChangeResponseReceivedDto.json")
  private Resource mockMessageChangeResponseReceivedDto;
  @Value("classpath:data/messageChangeResponseReceivedEmpty.json")
  private Resource mockMessageEmpty;
  @Value("classpath:data/messageChangeResponseReceivedNoEserviceRecordId.json")
  private Resource mockMessageNoEserviceRecordId;
  @Value("classpath:data/messageChangeResponseReceivedNoResponseReceived.json")
  private Resource mockMessageNoResponseReceived;
  @Value("classpath:data/messageChangeResponseReceivedNoStatus.json")
  private Resource mockMessageNoStatus;
  @Value("classpath:data/messageBadFormattedResponseReceived.json")
  private Resource mockMessageBadFormattedResponseReceived;

  @MockBean
  private EserviceService eserviceService;

  @MockBean
  private SimpleMessageListenerContainer simpleMessageListenerContainer;

  @InjectMocks
  @Autowired
  private PollingReceiver pollingReceiver;

  private Message message = new Message();
  private Map<String, String> attributes = new HashMap<>();

  @BeforeEach
  void setup() {
    AWSXRay.beginSegment("Segment-test");
    attributes.put("AWSTraceHeader", "fakeId");
  }

  @AfterEach
  void clean() {
    AWSXRay.endSegment();
  }

  @Test
  @DisplayName("given valid message method should not throw an exception")
  void testReceiveStringMessage_givenValidMessage_thenServiceDoesNotThrow()
      throws EserviceNotFoundException, IOException {
    Mockito.doNothing().when(eserviceService).updateResponseReceived(Mockito.anyLong(),
        Mockito.any());
    String messageString = getStringFromResourse(mockMessageChangeResponseReceivedDto);
    message.setBody(messageString);
    message.setAttributes(attributes);
    assertDoesNotThrow(() -> pollingReceiver.receiveStringMessage(message));
  }

  @Test
  @DisplayName("given invalid message method should throw IllegalArgumentException")
  void testReceiveStringMessage_givenInvalidMessage_thenThrowsIllegalArgumentException()
      throws IOException {
    assertThrows(NullPointerException.class, () -> pollingReceiver.receiveStringMessage(null),
        "IllegalArgumentException should be thrown");
  }

  @Test
  @DisplayName("given empty message method should throw ConstraintViolationException")
  void testReceiveStringMessage_givenEmptyMessage_thenThrowsConstraintViolationException()
      throws IOException {
    String wrongMessage = getStringFromResourse(mockMessageEmpty);
    message.setBody(wrongMessage);
    message.setAttributes(attributes);
    assertThrows(ConstraintViolationException.class,
        () -> pollingReceiver.receiveStringMessage(message),
        "ConstraintViolationException should be thrown");
  }

  @Test
  @DisplayName("when eserviceRecordId field is missing, method should throw ConstraintViolationException")
  void testReceiveStringMessage_whenEserviceRecordIdIsMissing_thenThrowsConstraintViolationException()
      throws IOException {
    String wrongMessage = getStringFromResourse(mockMessageNoEserviceRecordId);
    message.setBody(wrongMessage);
    message.setAttributes(attributes);
    assertThrows(ConstraintViolationException.class,
        () -> pollingReceiver.receiveStringMessage(message),
        "ConstraintViolationException should be thrown");
  }

  @Test
  @DisplayName("when responseReceived is missing, should throw ConstraintViolationException")
  void testReceiveStringMessage_whenResponseReceivedIsMissing_thenThrowsConstraintViolationException()
      throws IOException {
    String wrongMessage = getStringFromResourse(mockMessageNoResponseReceived);
    message.setBody(wrongMessage);
    message.setAttributes(attributes);
    assertThrows(ConstraintViolationException.class,
        () -> pollingReceiver.receiveStringMessage(message),
        "ConstraintViolationException should be thrown");
  }

  @Test
  @DisplayName("when status is missing, should throw ConstraintViolationException")
  void testReceiveStringMessage_whenStatusIsMissing_thenThrowsConstraintViolationException()
      throws IOException {
    String wrongMessage = getStringFromResourse(mockMessageNoStatus);
    message.setBody(wrongMessage);
    message.setAttributes(attributes);
    assertThrows(ConstraintViolationException.class,
        () -> pollingReceiver.receiveStringMessage(message),
        "ConstraintViolationException should be thrown");
  }

  @Test
  @DisplayName("when responseReceived is bad formatted, method should throw InvalidFormatException")
  void testReceiveStringMessage_whenResponseReceivedIsBadFormatted_thenThrowsInvalidFormatException()
      throws IOException {
    String wrongMessage = getStringFromResourse(mockMessageBadFormattedResponseReceived);
    message.setBody(wrongMessage);
    message.setAttributes(attributes);
    assertThrows(InvalidFormatException.class, () -> pollingReceiver.receiveStringMessage(message),
        "InvalidFormatException should be thrown");
  }

  private String getStringFromResourse(Resource resource) throws IOException {
    return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
  }

}
