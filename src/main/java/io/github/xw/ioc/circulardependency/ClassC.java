package io.github.xw.ioc.circulardependency;

public class ClassC {
    private ClassA classA;

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

    public void doSomethingC() {
        System.out.println("ClassC is doing something.");
    }
}
