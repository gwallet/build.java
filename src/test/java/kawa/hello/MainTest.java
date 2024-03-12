package kawa.hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test void should_test_more_seriously() throws Exception {
    assertTrue(true, "Expected 'true' to be 'true'");
  }

  @MethodSource("even_or_odd_source")
  @ParameterizedTest(name = "should tell that {0} is {1}")
  void still_not_rigorous_test(int number, EvenOrOdd evenOrOdd) {
    assertEquals(evenOrOdd, check(number));
  }

  public static Stream<Arguments> even_or_odd_source() {
    return IntStream.range(0, 100)
                    .mapToObj(i -> Arguments.of(i, check(i)));
  }

  private static EvenOrOdd check(int number) {
    return number % 2 == 0
      ? EvenOrOdd.Even
      : EvenOrOdd.Odd;
  }

  enum EvenOrOdd {
    Even,
    Odd,
  }

}
