package jp.cafebabe.chclver;

import jp.cafebabe.chclver.cli.*;
import jp.cafebabe.chclver.runner.HelpPrinter;
import jp.cafebabe.chclver.runner.VersionPrinter;
import jp.cafebabe.chclver.runner.VersionUpdater;
import jp.cafebabe.chclver.runner.VersionsLister;

public interface Runner {
    int perform();

    static Runner build(Arguments args) {
        if (args.isHelpFlag())
            return new HelpPrinter(args);
        else if (args.listVersions())
            return new VersionsLister(args);
        else if (args.version().isEmpty())
            return new VersionPrinter(args);
        return new VersionUpdater(args);
    }
}
