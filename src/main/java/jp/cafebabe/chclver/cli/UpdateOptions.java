package jp.cafebabe.chclver.cli;

import java.util.Optional;

import jp.cafebabe.chclver.entities.JvmSpecVersions;
import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.chclver.entities.VersionBuilder;
import picocli.CommandLine.Option;

class UpdateOptions {
    @Option(names = { "-d", "--destination" }, defaultValue = "chclver", description = "specify the destination. Default is chclver directory.")
    private String destination;

    @Option(names = {
            "--to" }, paramLabel = "VERSION", description = "specify the class file version. If this option was not specified, print the class file version of given class files.")
    private Optional<String> toVersion;

    @Option(names = { "--force-to" }, paramLabel = "MAJOR.MINOR", description = "specify the class file version.", hidden = true)
    private Optional<String> forceVersion;

    public String destination() {
        return destination;
    }

    public Optional<String> toVersion() {
        return toVersion;
    }

    public Optional<Version> version() {
        return toVersion.map(str -> JvmSpecVersions.find(str))
                .orElseGet(() -> forceVersion.map(str -> VersionBuilder.build(str)));
    }
}
