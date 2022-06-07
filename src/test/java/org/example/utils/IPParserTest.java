package org.example.utils;

import org.example.exception.FormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IPParserTest {

  private final IPParser ipParser = new IPParser();

  @Test
  public void parseToIntPositiveNumberTest() {
    int ipHash = ipParser.parseToInt("99.68.237.15");
    Assertions.assertEquals(1665461519, ipHash);
  }

  @Test
  public void parseToIntNegativeNumberTest() {
    int ipHash = ipParser.parseToInt("220.122.196.162");
    Assertions.assertEquals(-595934046, ipHash);
  }

  @Test
  public void parseToIntMaskMinMaxNumbersTest() {
    int ipHash0 = ipParser.parseToInt("0.0.0.0");
    Assertions.assertEquals(0, ipHash0);

    int ipHash255 = ipParser.parseToInt("255.255.255.255");
    Assertions.assertEquals(-1, ipHash255);

    int ipHashIntMaxValue = ipParser.parseToInt("127.255.255.255");
    Assertions.assertEquals(2147483647, ipHashIntMaxValue);

    int ipHashIntMinValue = ipParser.parseToInt("128.0.0.0");
    Assertions.assertEquals(-2147483648, ipHashIntMinValue);
  }

  @Test
  public void parseToIntNullOrEmptyTest() {
    Assertions.assertThrows(
        FormatException.class,
        () -> ipParser.parseToInt(null),
        "The ip address can not be null or empty");
    Assertions.assertThrows(
        FormatException.class,
        () -> ipParser.parseToInt(" "),
        "The ip address can not be null or empty");
  }

  @Test
  public void parseToIntInvalidStringTest() {
    String wrongIp = "123.19191919i";
    Assertions.assertThrows(
        FormatException.class,
        () -> ipParser.parseToInt(wrongIp),
        "Wrong format of ip address: " + wrongIp);
  }
}
