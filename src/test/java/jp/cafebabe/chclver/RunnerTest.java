package jp.cafebabe.chclver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.cafebabe.chclver.cli.ArgumentsBuilder;
import jp.cafebabe.chclver.runner.HelpPrinter;
import jp.cafebabe.chclver.runner.VersionPrinter;
import jp.cafebabe.chclver.runner.VersionUpdater;
import jp.cafebabe.chclver.runner.VersionsLister;

public class RunnerTest {
    @Test
    public void testHelpRunner() {
        Assertions.assertInstanceOf(HelpPrinter.class, buildRunner());
        Assertions.assertInstanceOf(HelpPrinter.class, buildRunner("--help"));
    }

    @Test
    public void testListVersions() {
        Assertions.assertInstanceOf(VersionsLister.class, buildRunner("--list-versions"));
    }

    @Test
    public void testVersionPrinter() {
        Assertions.assertInstanceOf(VersionPrinter.class, buildRunner("some_files"));
    }

    @Test
    public void testVersionUpdater() {
        Assertions.assertInstanceOf(VersionUpdater.class, buildRunner("some_filer", "--to", "Java15"));
    }

    private Runner buildRunner(String... args) {
        return Runner.build(ArgumentsBuilder.parse(args));
    }
}
