package jp.cafebabe.chclver.entities;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jp.cafebabe.chclver.VersionVisitor;

public class JvmSpecVersions {
    private static final List<JvmSpecVersion> versions = List.of(JvmSpecVersion.values());

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
