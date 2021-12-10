package jp.cafebabe.chclver.cli;

import io.vavr.control.Try;
import jp.cafebabe.chclver.cli.validators.Validators;
import picocli.CommandLine.Option;

class HelpOptions {
    @Option(names = { "-h", "--help" }, usageHelp = true, defaultValue = "false", description = "print this message.")
    private boolean helpFlag = false;

    public boolean isHelpFlag(Arguments args) {
        if (!helpFlag)
            Try.of(() -> new Validators().validate(args))
                    .onFailure(exception -> handleError(args, exception));
        return helpFlag;
    }

    private void handleError(Arguments args, Throwable exp) {
        helpFlag = true;
        args.out.println(exp.getMessage());
    }
}
