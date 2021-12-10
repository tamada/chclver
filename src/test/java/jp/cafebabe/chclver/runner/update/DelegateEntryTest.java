package jp.cafebabe.chclver.runner.update;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.cafebabe.chclver.entities.UpdatedVersion;
import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.chclver.runner.updater.DelegateEntry;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;

public class DelegateEntryTest {
    private Stream<Entry> readEntries(Path path) throws Exception {
        return DataSourceFactory.instance()
                .build(path).stream();
    }

    @Test
    public void testVersionBeforeOpenStream() throws Exception {
        Entry entry = readEntries(Path.of("src/sample/classes/java10/HelloWorld.class"))
                .findFirst().get();
        DelegateEntry de = new DelegateEntry(entry, Version.of(62, 0));
        Assertions.assertEquals(Optional.empty(), de.version());
        Assertions.assertEquals(Path.of("src/sample/classes/java10/HelloWorld.class"), de.path());
    }

    @Test
    public void testUpdate() throws Exception {
        Entry entry = readEntries(Path.of("src/sample/classes/java10/HelloWorld.class"))
                .findFirst().get();
        DelegateEntry de = new DelegateEntry(entry, Version.of(62, 0));
        try (InputStream in = de.openStream()) {
            byte[] data = new byte[8];
            in.read(data);
            Assertions.assertArrayEquals(new byte[]{ (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe, 0, 0, 0, 62 }, data);
        }
        Assertions.assertEquals(Optional.of(UpdatedVersion.of(Version.of(54, 0), Version.of(62, 0))), de.version());
    }
}
