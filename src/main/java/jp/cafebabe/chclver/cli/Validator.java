package jp.cafebabe.chclver.cli;

public interface Validator {
    Arguments validate(Arguments args) throws IllegalArgumentException;
}
