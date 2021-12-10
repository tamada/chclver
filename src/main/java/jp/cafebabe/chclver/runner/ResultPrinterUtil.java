package jp.cafebabe.chclver.runner;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vavr.control.Either;

public class ResultPrinterUtil {
    public <E> List<E> mergeList(List<E> list1, List<E> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }

    public <T> List<T> exposeEitherRight(List<Either<Throwable, T>> list) {
        return exposeItems(list, e -> e.isRight(), e -> e.get());
    }

    public <T, R> List<T> exposeEitherRight(List<Either<Throwable, R>> list, Function<Either<Throwable, R>, T> mapper) {
        return exposeItems(list, e -> e.isRight(), mapper);
    }

    public <R> List<Throwable> exposeEitherLeft(List<Either<Throwable, R>> list) {
        return exposeEitherLeft(list, e -> e.getLeft());
    }

    public <T, R> List<T> exposeEitherLeft(List<Either<Throwable, R>> list, Function<Either<Throwable, R>, T> mapper) {
        return exposeItems(list, e -> e.isLeft(), mapper);
    }

    public <T, R> List<T> exposeItems(List<Either<Throwable, R>> list, Predicate<Either<Throwable, R>> predicate, Function<Either<Throwable, R>, T> mapper) {
        return list.stream()
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.toList());
    }

    public void printExceptions(PrintWriter out, Path path, List<Throwable> exceptions, boolean stackTrace) {
        if (exceptions.size() > 0) {
            out.printf("%s Exceptions...%n", path);
            exceptions.stream()
                    .forEach(e -> printEachException(out, e, stackTrace));
        }
    }

    public void printExceptions(PrintWriter out, Path path, List<Throwable> exceptions) {
        printExceptions(out, path, exceptions, false);
    }

    private void printEachException(PrintWriter out, Throwable exception, boolean stackTrace) {
        out.printf("\t%s (%s)%n", exception.getMessage(), exception.getClass().getName());
        if (stackTrace) {
            exception.printStackTrace(out);
            if (exception.getCause() != null)
                exception.getCause().printStackTrace(out);
        }
    }
}
