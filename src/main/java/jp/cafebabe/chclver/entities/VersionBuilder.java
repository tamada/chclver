package jp.cafebabe.chclver.entities;

import io.vavr.control.Try;

public class VersionBuilder {
    public static Version build(String majorAndMinorString) {
        String[] majorAndMinor = majorAndMinorString.split("\\.");
        if (majorAndMinor.length != 2)
            throw new IllegalArgumentException(majorAndMinorString + ": invalid major and minor version");
        return Try.of(() -> Version.of(Integer.parseInt(majorAndMinor[0]), Integer.parseInt(majorAndMinor[1])))
                .getOrElseThrow(() -> new IllegalArgumentException(majorAndMinorString + ": invalid major and minor version"));
    }

    public static byte[] toByte(Version version) {
        byte[] data = new byte[4];
        int minor = version.minor();
        int major = version.major();
        data[0] = (byte) (minor >> 16 & 0xff);
        data[1] = (byte) (minor >> 0 & 0xff);
        data[2] = (byte) (major >> 16 & 0xff);
        data[3] = (byte) (major >> 0 & 0xff);
        return data;
    }
}
