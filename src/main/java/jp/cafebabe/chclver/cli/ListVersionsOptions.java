package jp.cafebabe.chclver.cli;

import picocli.CommandLine.Option;

class ListVersionsOptions {
    @Option(names = { "--list-versions" }, defaultValue = "false", description = "list version of class files and corresponding Java version")
    private boolean listVersions;

    public boolean listVersions() {
        return listVersions;
    }
}
