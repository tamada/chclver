package jp.cafebabe.chclver.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionBuilderTest {
    @Test
    public void testBasic() {
        Assertions.assertEquals(Version.of(45, 3), VersionBuilder.build("45.3"));
    }

    @Test
    public void testExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> VersionBuilder.build("no period"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> VersionBuilder.build("no.number"));
    }

    @Test
    public void testToByte() {
        Assertions.assertArrayEquals(new byte[]{ 0, 3, 0, 0x2d },
                VersionBuilder.toByte(Version.of(45, 3)));
    }
}
