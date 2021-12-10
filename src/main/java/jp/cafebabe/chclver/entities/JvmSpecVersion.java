package jp.cafebabe.chclver.entities;

import java.util.Objects;

import jp.cafebabe.chclver.VersionVisitor;

public class JvmSpecVersion {
    private Version version;
    private String label;

    private JvmSpecVersion(String label, Version version) {
        this.label = label;
        this.version = version;
    }

    public boolean match(String label) {
        return Objects.equals(label, this.label);
    }

    public void accept(VersionVisitor visitor) {
        visitor.visit(label, version.major(), version.minor());
    }

    public Version version() {
        return version;
    }

    public String label() {
        return label;
    }

    public static JvmSpecVersion of(String label, Version version) {
        return new JvmSpecVersion(label, version);
    }

    public static JvmSpecVersion of(String label, int major, int minor) {
        return of(label, Version.of(major, minor));
    }
}
