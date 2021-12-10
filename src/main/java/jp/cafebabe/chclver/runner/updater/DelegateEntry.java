package jp.cafebabe.chclver.runner.updater;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

import jp.cafebabe.chclver.entities.UpdatedVersion;
import jp.cafebabe.chclver.entities.Version;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.entries.Entry;

public class DelegateEntry implements Entry {
    private Entry delegates;
    private Version to;
    private VersionUpdatingInputStream in;

    public DelegateEntry(Entry entry, Version toVersion) {
        this.delegates = entry;
        this.to = toVersion;
    }

    public Optional<UpdatedVersion> version() {
        if (in != null)
            return in.version();
        return Optional.empty();
    }

    @Override
    public InputStream openStream() throws IOException {
        in = new VersionUpdatingInputStream(delegates.openStream(), to);
        return in;
    }

    @Override
    public ClassName className() {
        return delegates.className();
    }

    @Override
    public boolean endsWith(String s) {
        return delegates.endsWith(s);
    }

    @Override
    public boolean isName(String s) {
        return delegates.isName(s);
    }

    @Override
    public Path path() {
        return delegates.path();
    }
}
