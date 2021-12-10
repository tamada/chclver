package jp.cafebabe.chclver.entities;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface Result<K, V> extends Serializable {
    void accept(BiConsumer<K, V> action);
}
