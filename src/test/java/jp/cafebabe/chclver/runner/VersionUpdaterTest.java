package jp.cafebabe.chclver.runner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.vavr.control.Try;
import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.cli.ArgumentsBuilder;

public class VersionUpdaterTest {
    @Test
    public void testBasic() {
        List<String> results = execute(new String[]{ "src/sample/classes/java7", "--to", "Java11", "-d", "test1" });
        Assertions.assertEquals("src/sample/classes/java7 -> test1", results.get(0));
        Assertions.assertEquals("\t51.0 (Java 7) -> 55.0 (Java 11)", results.get(1));

        removeDir(Path.of("test1"));
    }

    private void removeDir(Path dir) {
        if (Files.isDirectory(dir))
            Try.run(() -> Files.list(dir)
                    .forEach(subDir -> removeDir(subDir)));
        Try.run(() -> Files.delete(dir));
    }

    private List<String> execute(String[] args) {
        StringWriter out = new StringWriter();
        Arguments arguments = ArgumentsBuilder.parse(args, new PrintWriter(out));
        Runner.build(arguments).perform();
        return Arrays.stream(out.toString().split(System.getProperty("line.separator")))
                .collect(Collectors.toList());
    }
}
