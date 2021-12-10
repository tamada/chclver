package jp.cafebabe.chclver.runner.updater;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Optional;

import io.vavr.control.Try;
import jp.cafebabe.chclver.entities.MagicAndVersionParser;
import jp.cafebabe.chclver.entities.UpdatedVersion;
import jp.cafebabe.chclver.entities.Version;

public class VersionUpdatingInputStream extends InputStream {
    private InputStream in;
    private Version to;
    private Version from;
    private boolean first = true;

    public VersionUpdatingInputStream(InputStream inputStream, Version to) {
        this.in = inputStream;
        this.to = to;
    }

    public Optional<UpdatedVersion> version() {
        if (first || from == null)
            return Optional.empty();
        return Optional.of(UpdatedVersion.of(from, to));
    }

    public Version to() {
        return to;
    }

    public Version from() {
        return from;
    }

    @Override
    public int read() throws IOException {
        if (first)
            updateVersion();
        return in.read();
    }

    private void updateVersion() throws IOException {
        BufferedInputStream bin = new BufferedInputStream(in);
        bin.mark(8);
        Optional<Version> version = new MagicAndVersionParser().parseVersion(bin);
        first = false;
        version.ifPresentOrElse(v -> concat(v, bin), () -> resetStream(bin));
    }

    private void concat(Version v, BufferedInputStream bin) {
        this.from = v;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(new byte[]{ (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe }, 0, 4);
        buffer.put(to.toByte());
        this.in = new SequenceInputStream(new ByteArrayInputStream(buffer.array()), bin);
    }

    private void resetStream(BufferedInputStream bin) {
        Try.run(() -> bin.reset());
        this.in = bin;
    }

    public void close() throws IOException {
        in.close();
    }
}
