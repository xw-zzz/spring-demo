package io.github.xw.ioc.circulardependency;

/**
 * @author yangliu@tiduyun.com
 * @date 2023/10/10
 */
public class ClassA {
    private ClassB classB;

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public void doSomethingA() {
        System.out.println("ClassA is doing something.");
        classB.doSomethingB();
    }
}
