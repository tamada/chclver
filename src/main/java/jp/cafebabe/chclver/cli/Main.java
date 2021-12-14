package jp.cafebabe.chclver.cli;

import jp.cafebabe.chclver.Runner;

public class Main {
    public static final String VERSION = "1.0.0-SNAPSHOT";

    private Main(Arguments args) {
        Runner.build(args)
                .perform();
        args.out.flush();
    }

    public static void main(String[] args) {
        new Main(ArgumentsBuilder.parse(args));
    }
}
