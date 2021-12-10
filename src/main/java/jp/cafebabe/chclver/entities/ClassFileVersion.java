package jp.cafebabe.chclver.entities;

import java.util.function.BiConsumer;

import jp.cafebabe.kunai.entries.Entry;

public class ClassFileVersion implements Result<Entry, Version> {
    private Entry entry;
    private Version version;

    private ClassFileVersion(Entry entry, Version version) {
        this.entry = entry;
        this.version = version;
    }

    public Version version() {
        return version;
    }

    public Entry entry() {
        return entry;
    }

    public void accept(BiConsumer<Entry, Version> action) {
        action.accept(entry, version);
    }

    public static ClassFileVersion of(Entry entry, Version version) {
        return new ClassFileVersion(entry, version);
    }
}
