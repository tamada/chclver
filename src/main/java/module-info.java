module jp.cafebabe.chclver {
    requires info.picocli;
    requires jp.cafebabe.kunai;
    requires io.vavr;

    exports jp.cafebabe.chclver;
    exports jp.cafebabe.chclver.entities;
    exports jp.cafebabe.chclver.runner;

    opens jp.cafebabe.chclver.cli to info.picocli;
}
