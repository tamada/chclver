package jp.cafebabe.chclver.runner;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.entities.ClassFileVersion;
import jp.cafebabe.chclver.entities.MagicAndVersionParser;
import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;

public class VersionPrinter implements Runner {
    private Arguments args;
    private ResultPrinterUtil rpu = new ResultPrinterUtil();

    public VersionPrinter(Arguments args) {
        this.args = args;
    }

    @Override
    public int perform() {
        var factory = DataSourceFactory.instance();
        MagicAndVersionParser jbc = new MagicAndVersionParser();
        args.stream()
                .forEach(path -> readAndPrint(factory, jbc, path));
        return 0;
    }

    private void readAndPrint(DataSourceFactory factory, MagicAndVersionParser jbc, Path path) {
        Try.withResources(() -> factory.build(path))
                .of(source -> performEach(source, jbc))
                .onSuccess(list -> printResult(path, list))
                .onFailure(KunaiException.class, t -> args.out.printf("%s: file or directory not found%n", t.getMessage()));
    }

    private void printResult(Path path, List<Either<Throwable, ClassFileVersion>> results) {
        if (args.isVerbose())
            printVerbose(path, results);
        else
            printDefault(path, results);
    }

    private void printVerbose(Path path, List<Either<Throwable, ClassFileVersion>> results) {
        List<ClassFileVersion> resultList = rpu.exposeEitherRight(results);
        List<Throwable> exceptionList = rpu.exposeEitherLeft(results);
        printVersions(path, resultList);
        rpu.printExceptions(args.out, path, exceptionList, true);
    }

    private void printVersions(Path path, List<ClassFileVersion> list) {
        var map = list.stream()
                .collect(Collectors.toMap(ClassFileVersion::version, cfv -> List.of(cfv.entry().className()),
                        (l1, l2) -> rpu.mergeList(l1, l2)));
        args.out.println(path);
        map.forEach((key, value) -> printEachItem(key, value));
    }

    private void printEachItem(Version version, List<ClassName> list) {
        args.out.printf("\t%s%n", version);
        list.forEach(name -> args.out.printf("\t\t%s%n", name));
    }

    private void printDefault(Path path, List<Either<Throwable, ClassFileVersion>> results) {
        List<Version> list = rpu.exposeItems(results, e -> e.isRight(), e -> e.get().version());
        args.out.printf("%s%n", path);
        list.stream()
                .distinct().forEach(v -> args.out.printf("\t%s%n", v));
    }

    private List<Either<Throwable, ClassFileVersion>> performEach(DataSource source, MagicAndVersionParser jbc) {
        return source.stream()
                .sorted((e1, e2) -> e1.className().compareTo(e2.className()))
                .filter(entry -> entry.isClass())
                .map(entry -> performEntry(entry, jbc))
                .collect(Collectors.toList());
    }

    private Either<Throwable, ClassFileVersion> performEntry(Entry entry, MagicAndVersionParser jbc) {
        return Try.withResources(() -> entry.openStream())
                .of(in -> ClassFileVersion.of(entry, jbc.readVersion(in)))
                .toEither();
    }
}
