package jp.cafebabe.chclver.runner;

import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;

public class HelpPrinter implements Runner {
    private Arguments args;

    public HelpPrinter(Arguments args) {
        this.args = args;
    }

    @Override
    public int perform() {
        args.out.println("""
                chclver [OPTIONS] [JAR|CLASS_FILE|DIRECTORY...]
                OPTIONS
                  -d, --destination <DEST>      specify the destination. Default is chclver directory.
                      --list-versions           list version of class files and corresponding Java version.
                      --to <JAVA_VERSION>       specify the Java version for updating the versions of the given class files.
                                                If this option is absent, print the versions of the given class files.
                      --force-to <MAJOR.MINOR>  specify the versions of the class files directory.
                  -v, --verbose                 verbose mode.
                  -h, --help                    print this message.""");
        // new CommandLine(args).usage(args.out);
        return 0;
    }
}
