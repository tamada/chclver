package jp.cafebabe.chclver.cli.validators;

import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.cli.Validator;

public class ListVersionsValidator implements Validator {

    @Override
    public Arguments validate(Arguments args) throws IllegalArgumentException {
        if (args.stream().count() == 0 && !args.listVersions())
            throw new IllegalArgumentException("no arguments specified");
        return args;
    }
}
