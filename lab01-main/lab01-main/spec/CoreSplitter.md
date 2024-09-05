# 🍌 Core Exercise: Splitter

Modify [`app/src/main/java/splitter/Splitter.java`](/app/src/main/java/splitter/Splitter.java) such that it:

- Asks the user for a message;
- Reads a line consisting of words separated by a space from `System.in`; then
- Prints out the individual words in the string.
- Passes the linter

For example:

```
Enter a sentence specified by spaces only:
Help I'm trapped in a Java program
Help
I'm
trapped
in
a
Java
program
```

You can test your implementation by manually running the test in VSCode by navigating to the [`app/src/test/java/splitter/SplitterTest.java`](/app/src/test/java/splitter/SplitterTest.java) file. Ensure that your code passes the linter as well.

> Once completed **commit** and **push** your changes to GitLab.

# Gradle Summary

## Running Gradle Tests

<table>
  <tr>
    <th>If you are working LOCALLY:</th>
    <th>If you are working on CSE:</th>
  </tr>
  <tr>
    <td><code>gradle test</code></td>
    <td><code>2511 gradle test</code></td>
  </tr>
</table>

## Running Gradle Linter

<table>
  <tr>
    <th>If you are working LOCALLY:</th>
    <th>If you are working on CSE:</th>
  </tr>
  <tr>
    <td>
      <code>gradle lint</code>
    </td>
    <td>
      <code>2511 gradle lint</code>
    </td>
  </tr>
</table>
