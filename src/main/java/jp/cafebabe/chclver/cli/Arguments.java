package jp.cafebabe.chclver.cli;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import jp.cafebabe.chclver.entities.JvmSpecVersion;
import jp.cafebabe.chclver.entities.Version;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "chclver", version = "1.0.0-SNAPSHOT", description = "change class file version")
public class Arguments {
    @Mixin
    private UpdateOptions updateOptions = new UpdateOptions();

    @Mixin
    private ListOptions listOptions = new ListOptions();

    @Mixin
    private HelpOptions helpOptions = new HelpOptions();

    @Mixin
    private ListVersionsOptions versionsOptions = new ListVersionsOptions();

    public final PrintWriter out;

    Arguments() {
        this(new PrintWriter(System.out));
    }

    Arguments(PrintWriter out) {
        this.out = out;
    }

    public boolean isHelpFlag() {
        return helpOptions.isHelpFlag(this);
    }

    public String destination() {
        return updateOptions.destination();
    }

    public boolean listVersions() {
        return versionsOptions.listVersions();
    }

    public Optional<JvmSpecVersion> toVersion() {
        return updateOptions.toVersion();
    }

    public Optional<Version> version() {
        return updateOptions.version();
    }

    public boolean isVerbose() {
        return listOptions.verbose();
    }

    public Stream<Path> stream() {
        return listOptions.stream();
    }
}
