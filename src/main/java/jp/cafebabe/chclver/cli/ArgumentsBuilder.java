package jp.cafebabe.chclver.cli;

import java.io.PrintStream;
import java.io.PrintWriter;

import picocli.CommandLine;

public class ArgumentsBuilder {
    public static Arguments parse(String[] args, PrintWriter out) {
        Arguments arguments = new Arguments(out);
        new CommandLine(arguments).parseArgs(args);
        return arguments;
    }

    public static Arguments parse(String[] args, PrintStream out) {
        return parse(args, new PrintWriter(out));
    }

    public static Arguments parse(String[] args) {
        return parse(args, System.out);
    }
}
