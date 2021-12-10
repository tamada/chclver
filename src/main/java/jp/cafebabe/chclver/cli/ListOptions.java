package jp.cafebabe.chclver.cli;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

class ListOptions {
    @Option(names = { "-v", "--verbose" }, defaultValue = "false", description = "verbose mode")
    private boolean verbose = false;

    @Parameters(paramLabel = "<JAR|CLASS_FILE|DIR...>", description = "specify the class files.")
    private List<Path> arguments;

    public boolean verbose() {
        return verbose;
    }

    public Stream<Path> stream() {
        return Optional.ofNullable(arguments)
                .map(List::stream)
                .orElse(Stream.empty());
    }
}
