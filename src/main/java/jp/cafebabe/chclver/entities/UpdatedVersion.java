package jp.cafebabe.chclver.entities;

import java.util.Objects;

public class UpdatedVersion {
    private Version from;
    private Version to;

    private UpdatedVersion(Version from, Version to) {
        this.from = from;
        this.to = to;
    }

    public Version from() {
        return from;
    }

    public Version to() {
        return to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UpdatedVersion uv
                && Objects.equals(to, uv.to()) && Objects.equals(from, uv.from());
    }

    public static UpdatedVersion of(Version from, Version to) {
        return new UpdatedVersion(from, to);
    }
}
