package jp.cafebabe.chclver.runner.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.chclver.runner.updater.VersionUpdatingInputStream;

public class VersionUpdatingInputStreamTest {
    @Test
    public void testNotClassFile() throws IOException {
        InputStream in = new VersionUpdatingInputStream(new FileInputStream("pom.xml"), Version.of(54, 0));
        byte[] data = new byte[8];
        in.read(data);

        Assertions.assertArrayEquals(new byte[]{ '<', '?', 'x', 'm', 'l', ' ', 'v', 'e', }, data);
    }
}
