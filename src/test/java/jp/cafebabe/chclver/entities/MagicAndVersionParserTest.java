package jp.cafebabe.chclver.entities;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MagicAndVersionParserTest {
    @Test
    public void testBasic() throws IOException {
        Version version = new MagicAndVersionParser().readVersion(new ByteArrayInputStream(new byte[]{
                (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe, 0, 0, 0, 54,
        }));
        Assertions.assertEquals(Version.of(54, 0), version);
    }

    @Test
    public void testReadFailed() {
        var mvp = new MagicAndVersionParser();
        Assertions.assertThrows(IOException.class,
                () -> mvp.readVersion(new ByteArrayInputStream("not java classfile".getBytes())));
    }
}
