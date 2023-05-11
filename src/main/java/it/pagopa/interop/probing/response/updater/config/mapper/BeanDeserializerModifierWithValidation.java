package it.pagopa.interop.probing.response.updater.config.mapper;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class BeanDeserializerModifierWithValidation extends BeanDeserializerModifier {

  @Override
  public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config,
      BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
    if (deserializer instanceof BeanDeserializer) {
      return new BeanDeserializerWithValidation((BeanDeserializer) deserializer);
    }
    return deserializer;
  }

}
