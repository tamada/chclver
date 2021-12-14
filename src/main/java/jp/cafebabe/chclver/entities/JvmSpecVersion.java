package jp.cafebabe.chclver.entities;

import java.util.Objects;

import jp.cafebabe.chclver.VersionVisitor;

public enum JvmSpecVersion {
    Java1_0("1.0", 45, 0),
    Java1_1("1.1", 45, 3),
    Java1_2("1.2", 46, 0),
    Java1_3("1.3", 47, 0),
    Java1_4("1.4", 48, 0),
    Java5("5", 49, 0),
    Java6("6", 50, 0),
    Java7("7", 51, 0),
    Java8("8", 52, 0),
    Java9("9", 53, 0),
    Java10("10", 54, 0),
    Java11("11", 55, 0),
    Java12("12", 56, 0),
    Java13("13", 57, 0),
    Java14("14", 58, 0),
    Java15("15", 59, 0),
    Java16("16", 60, 0),
    Java17("17", 61, 0),
    Java18("18", 62, 0);

    private Version version;
    private String label;

    private JvmSpecVersion(String label, int major, int minor) {
        this.label = label;
        this.version = Version.of(major, minor);
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
}
