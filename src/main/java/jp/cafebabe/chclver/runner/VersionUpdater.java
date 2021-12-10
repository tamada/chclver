package jp.cafebabe.chclver.runner;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.chclver.Runner;
import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.entities.Pair;
import jp.cafebabe.chclver.entities.UpdateResult;
import jp.cafebabe.chclver.entities.UpdatedVersion;
import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.chclver.runner.updater.DelegateEntry;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.sink.DataSink;
import jp.cafebabe.kunai.sink.factories.DataSinkFactory;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;

public class VersionUpdater implements Runner {
    private Arguments args;
    private ResultPrinterUtil rpu = new ResultPrinterUtil();

    public VersionUpdater(Arguments args) {
        this.args = args;
    }

    @Override
    public int perform() {
        DataSourceFactory sourceFactory = DataSourceFactory.instance();
        DataSinkFactory sinkFactory = DataSinkFactory.instance();
        Try.run(() -> {
            try (DataSink sink = sinkFactory.create(Path.of(args.destination()))) {
                sinkAll(sink, sourceFactory);
            }
        }).onFailure(t -> args.out.printf("%s: %s%n", args.destination(), t.getMessage()));
        return 0;
    }

    private void sinkAll(DataSink sink, DataSourceFactory factory) {
        args.stream()
                .map(path -> Pair.of(path, consumePath(sink, path, factory)))
                .forEach(pair -> printEach(pair.left(), pair.right()));
    }

    private void printEach(Path path, List<Either<Throwable, UpdateResult>> results) {
        args.out.printf("%s -> %s%n", path, args.destination());
        if (args.isVerbose())
            printVerbose(path, results);
        else
            printDefault(path, results);
    }

    private void printVerbose(Path path, List<Either<Throwable, UpdateResult>> results) {
        Map<UpdatedVersion, List<Entry>> map = exposeToMap(results);
        map.forEach((v, entries) -> printEach(v, entries));
        rpu.printExceptions(args.out, path, rpu.exposeEitherLeft(results), true);
    }

    private void printEach(UpdatedVersion v, List<Entry> entries) {
        args.out.printf("\t%s -> %s%n", v.from(), v.to());
        entries.stream()
                .map(entry -> entry.className())
                .forEach(name -> args.out.printf("\t\t%s%n", name));
    }

    private Map<UpdatedVersion, List<Entry>> exposeToMap(List<Either<Throwable, UpdateResult>> results) {
        return rpu.exposeEitherRight(results).stream()
                .collect(Collectors.toMap(ur -> ur.map((e, v) -> v),
                        ur -> ur.map((e, v) -> List.of(e)), (l1, l2) -> rpu.mergeList(l1, l2)));
    }

    private void printDefault(Path path, List<Either<Throwable, UpdateResult>> results) {
        List<UpdatedVersion> versions = rpu.exposeEitherRight(results, e -> e.get().map((ee, vv) -> vv));
        versions.stream().distinct()
                .forEach(uv -> args.out.printf("\t%s -> %s%n", uv.from(), uv.to()));
        rpu.printExceptions(args.out, path, rpu.exposeEitherLeft(results));
    }

    private List<Either<Throwable, UpdateResult>> consumePath(DataSink sink, Path path, DataSourceFactory factory) {
        return Try.withResources(() -> factory.build(path))
                .of(source -> convertAll(source, sink))
                .onFailure(t -> args.out.printf("%s: %s%n", path, t.getMessage()))
                .get();
    }

    private List<Either<Throwable, UpdateResult>> convertAll(DataSource source, DataSink sink) {
        Version toVersion = args.version().get();
        return source.stream()
                .map(entry -> new DelegateEntry(entry, toVersion))
                .map(entry -> sinkEach(entry, sink))
                .collect(Collectors.toList());
    }

    private Either<Throwable, UpdateResult> sinkEach(DelegateEntry entry, DataSink sink) {
        return Try.of(() -> consume(entry, sink))
                .toEither();
    }

    private UpdateResult consume(DelegateEntry entry, DataSink sink) throws KunaiException {
        sink.consume(entry);
        var version = entry.version();
        if (version.isPresent()) {
            return UpdateResult.of(entry, version.get());
        }
        return UpdateResult.of(entry, null);
    }
}
