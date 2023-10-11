package io.github.xw.ioc.circulardependency;

public class ClassB {
    private ClassC classC;

    public void setClassC(ClassC classC) {
        this.classC = classC;
    }

    public void doSomethingB() {
        System.out.println("ClassB is doing something.");
        classC.doSomethingC();
    }
}
