package jp.cafebabe.chclver.entities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class MagicAndVersionParser {
    public Version readVersion(InputStream in) throws IOException {
        return parseVersion(in)
                .orElseThrow(() -> new IOException("not java class file"));
    }

    public Optional<Version> parseVersion(InputStream in) throws IOException {
        byte[] data = new byte[8];
        in.read(data);
        return Optional.ofNullable(parse(data));
    }

    private Version parse(byte[] data) {
        if (data[0] == (byte) 0xca && data[1] == (byte) 0xfe && data[2] == (byte) 0xba && data[3] == (byte) 0xbe)
            return Version.of(toInt(data[6], data[7]), toInt(data[4], data[5]));
        return null;
    }

    private int toInt(byte upper, byte lower) {
        return upper << 16 | lower;
    }
}
