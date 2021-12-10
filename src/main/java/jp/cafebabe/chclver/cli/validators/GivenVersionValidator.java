package jp.cafebabe.chclver.cli.validators;

import jp.cafebabe.chclver.cli.Arguments;
import jp.cafebabe.chclver.cli.Validator;
import jp.cafebabe.chclver.entities.JvmSpecVersions;

public class GivenVersionValidator implements Validator {
    @Override
    public Arguments validate(Arguments args) throws IllegalArgumentException {
        if (args.toVersion().map(this::isInvalidVersion).orElse(false))
            throw new IllegalArgumentException(String.format("%s: unknown version", args.toVersion().get()));
        return args;
    }

    private boolean isInvalidVersion(String version) {
        return !JvmSpecVersions.isValidVersionLabel(version);
    }
}
