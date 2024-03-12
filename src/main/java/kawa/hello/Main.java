package kawa.hello;

public class Main {

  public static void main(String... args) {
    String name = "World";
    if (0 < args.length) {
      name = args[0];
    }
    System.out.printf("Hello, %s!%n", name);
  }

}
