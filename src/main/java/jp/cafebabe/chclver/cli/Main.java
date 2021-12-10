package jp.cafebabe.chclver.cli;

import jp.cafebabe.chclver.Runner;

public class Main {
    private Main(Arguments args) {
        Runner.build(args)
                .perform();
        args.out.flush();
    }

    public static void main(String[] args) {
        new Main(ArgumentsBuilder.parse(args));
    }
}
