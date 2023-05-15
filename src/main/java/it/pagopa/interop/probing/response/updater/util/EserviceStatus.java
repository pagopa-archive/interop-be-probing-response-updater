package it.pagopa.interop.probing.response.updater.util;

import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EserviceStatus {

  KO("KO"),

  OK("OK");

  private String value;

  EserviceStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EserviceStatus fromValue(String value) {
    return Stream.of(EserviceStatus.values()).filter(b -> b.value.equals(value)).findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unexpected value '" + value + "'"));
  }
}
