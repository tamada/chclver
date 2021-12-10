package jp.cafebabe.chclver.runner;

import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.entities.JvmSpecVersions;

public class VersionsLister implements Runner {
    private Arguments args;

    public VersionsLister(Arguments args) {
        this.args = args;
    }

    @Override
    public int perform() {
        args.out.println("JDK version     bytecode version (major.minor)");
        new JvmSpecVersions().accept((label, major, minor) -> args.out.printf("Java %-4s\t%d.%d%n", label, major, minor));
        return 0;
    }
}
