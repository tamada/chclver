package jp.cafebabe.chclver.runner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.cli.ArgumentsBuilder;

public class VersionPrinterTest {
    @Test
    public void testJava7() {
        List<String> results = execute(new String[]{ "src/sample/classes/java7" });
        Assertions.assertEquals(2, results.size());
        Assertions.assertEquals("src/sample/classes/java7", results.get(0));
        Assertions.assertEquals("\t51.0 (Java 7)", results.get(1));
    }

    @Test
    public void testJava8() {
        List<String> results = execute(new String[]{ "src/sample/classes/java8", "--verbose" });
        Assertions.assertEquals(4, results.size());
        Assertions.assertEquals("src/sample/classes/java8", results.get(0));
        Assertions.assertEquals("\t52.0 (Java 8)", results.get(1));
        Assertions.assertEquals("\t\tFibonacci", results.get(2));
        Assertions.assertEquals("\t\tHelloWorld", results.get(3));
    }

    @Test
    public void testFileNotFound() {
        List<String> results = execute(new String[]{ "not_found", "--verbose" });
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("not_found: file or directory not found", results.get(0));
    }

    private List<String> execute(String[] args) {
        StringWriter out = new StringWriter();
        Arguments arguments = ArgumentsBuilder.parse(args, new PrintWriter(out));
        Runner.build(arguments).perform();
        return Arrays.stream(out.toString().trim().split(System.getProperty("line.separator")))
                .collect(Collectors.toList());
    }
}
