Stupid Simple Java Build System
===============================

What if one want to quickly setup a Java project  without wanting to fire overkilled weapon
like [Maven](https://maven.apache.org), [Ant](https://ant.apache.org) or [Gradle](https://gradle.org/) ? 🤔

Is the JDK really a Software Development Kit if one need to rely on other software code a solution ? 🤔

Since Java 11: YES! 😎
-------------------

### JEP 330 is key

Starting with Java 11 and [JEP 330](https://openjdk.org/jeps/330), the runtime introduce
a mechanism allowing the `java` command line to accept a single source file as input
and running it without requiring the compilation.

Then, a shebang can be put on top of the file to make it runnable as any (shell/perl/python/ruby) script

```java
#!/usr/bin/java --source 11

public class Hello {
  public static void main(String...args) {
    System.out.println("Hello, World!");
  }
}
```

Or, if one want to be more precise:

```java
#!/usr/lib/jvm/java-21/bin/java --source 21

public class Hello {
  public static void main(String...args) {
    System.out.println("Hello, World!");
  }
}
```


### The JDK is "battery included"

The [java.lang.Runtime](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Runtime.html) allows to run command lines from the JVM.

The [java.net.http](https://docs.oracle.com/en/java/javase/21/docs/api/java.net.http/module-summary.html) module allows to make HTTP requests.

And with some elbow grease, here comes a way to manage a Java project lifecycle, including dependency management,
in a tiny portable fashion.

### Still some challenges

The more complex the lifecycle, the harder it is to hack.
First step is to list the acceptable tradeoffs, ex:
- phases are always delegating to each other in order or precedence
- there is no way to launch more than one phase at a time

Some acceptance criteria:
- it's a script, it has to be self documented
- do not need anything else than the JDK
- do not store dependencies in the source control
- allow some _local_ (per environment) customization

### Drawbacks

- unlike [Python](https://www.python.org/) scripts for example, the script can not be named `script.java` 😕
- project dependencies are only known by the script, IDEs like
  [IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/), or any power editing tool like
  [NeoVim](https://neovim.io/), [Visual Studio Code](https://code.visualstudio.com/) or [Sublime Text](https://www.sublimetext.com/)
  will be hard to sync (but it's not impossible 😉)
- because the main class is in default package, can not `import static` anything inside 😕
- it does not scale! works well on a tiny project, but you can forget multi-modules 😁

Disclaimer
----------

This project is for educational purpose only.

⚠️ DO NOT USE IN PRODUCTION ⚠️ … unless you know what you are doing.

This is just a proof of concept:
- yes, it's possible to replace Maven, Ant or Gradle
- no, it's not worth it

Sources and inspiration
-----------------------

- [nodbuild](https://github.com/tsoding/nobuild)
- [java-cli-apps-yb11](https://github.com/java-cli-apps-yb11/java-cli-apps-yb11.github.io)
