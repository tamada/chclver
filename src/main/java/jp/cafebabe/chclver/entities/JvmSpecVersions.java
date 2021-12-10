package jp.cafebabe.chclver.entities;

import java.util.*;

import jp.cafebabe.chclver.VersionVisitor;

import static jp.cafebabe.chclver.entities.JvmSpecVersion.of;

public class JvmSpecVersions {
    private static final List<JvmSpecVersion> versions;
    static {
        versions = List.of(of("1.0", 45, 0),
                of("1.1", 45, 3),
                of("1.2", 46, 0),
                of("1.3", 47, 0),
                of("1.4", 48, 0),
                of("5", 49, 0),
                of("6", 50, 0),
                of("7", 51, 0),
                of("8", 52, 0),
                of("9", 53, 0),
                of("10", 54, 0),
                of("11", 55, 0),
                of("12", 56, 0),
                of("13", 57, 0),
                of("14", 58, 0),
                of("15", 59, 0),
                of("16", 60, 0),
                of("17", 61, 0),
                of("18", 62, 0));
    }

    public static String findJdkVersion(Version classFileVersion) {
        return versions.stream()
                .filter(jsv -> Objects.equals(jsv.version(), classFileVersion))
                .map(jsv -> jsv.label())
                .findFirst().map(label -> "Java " + label)
                .orElse("unknown");
    }

    public static void accept(VersionVisitor visitor) {
        versions.forEach(cfv -> cfv.accept(visitor));
    }

    public static Optional<Version> find(String label) {
        return versions.stream().filter(cfv -> cfv.match(label))
                .map(cfv -> cfv.version())
                .findFirst();
    }

    public static boolean isValidVersionLabel(String givenLabel) {
        return versions.stream().filter(cfv -> cfv.match(givenLabel))
                .findFirst().isPresent();
    }
}
