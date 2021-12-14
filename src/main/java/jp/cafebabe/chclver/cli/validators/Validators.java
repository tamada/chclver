package jp.cafebabe.chclver.cli.validators;

import java.util.List;

import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.cli.Validator;

public class Validators implements Validator {
    private List<Validator> validators = List.of(new ListVersionsValidator());

    @Override
    public Arguments validate(Arguments args) throws IllegalArgumentException {
        for (Validator v : validators)
            v.validate(args);
        return args;
    }
}
