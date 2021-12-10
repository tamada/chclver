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

public class VersionsListerTest {
    @Test
    public void testResult() {
        List<String> list = execute(new StringWriter());
        Assertions.assertEquals(20, list.size());
    }

    private List<String> execute(StringWriter out) {
        Arguments args = new ArgumentsBuilder().parse(new String[]{ "--list-versions" }, new PrintWriter(out));
        Runner runner = Runner.build(args);
        runner.perform();
        args.out.flush();
        return Arrays.stream(out.toString().split(System.getProperty("line.separator")))
                .collect(Collectors.toList());
    }
}
