package jp.cafebabe.chclver;

@FunctionalInterface
public interface VersionVisitor {
    void visit(String label, int major, int minor);
}
