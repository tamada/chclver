package jp.cafebabe.chclver.entities;

import java.io.Serializable;
import java.util.Objects;

public class Version implements Serializable {
    private int major;
    private int minor;

    private Version(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public int major() {
        return major;
    }

    public int minor() {
        return minor;
    }

    public byte[] toByte() {
        return VersionBuilder.toByte(this);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Version
                && equals((Version) other);
    }

    private boolean equals(Version v) {
        return v.major == major && v.minor == minor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor);
    }

    @Override
    public String toString() {
        return String.format("%d.%d (%s)", major, minor,
                JvmSpecVersions.findJdkVersion(this));
    }

    public static Version of(int major, int minor) {
        return new Version(major, minor);
    }
}
