package jp.cafebabe.chclver.entities;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import jp.cafebabe.kunai.entries.Entry;

public class UpdateResult implements Result<Entry, UpdatedVersion> {
    private Entry entry;
    private UpdatedVersion version;

    private UpdateResult(Entry entry, UpdatedVersion version) {
        this.entry = entry;
        this.version = version;
    }

    public <T> T map(BiFunction<Entry, UpdatedVersion, T> mapper) {
        return mapper.apply(entry, version);
    }

    @Override
    public void accept(BiConsumer<Entry, UpdatedVersion> action) {
        action.accept(entry, version);
    }

    public static UpdateResult of(Entry entry, Version from, Version to) {
        return of(entry, UpdatedVersion.of(from, to));
    }

    public static UpdateResult of(Entry entry, UpdatedVersion version) {
        return new UpdateResult(entry, version);
    }
}
