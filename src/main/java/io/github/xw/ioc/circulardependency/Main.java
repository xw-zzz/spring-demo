package io.github.xw.ioc.circulardependency;

import io.github.xw.model.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        // 创建 ClassPathXmlApplicationContext 上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("circulardepency.xml");


        ClassA myBean = context.getBean("classA", ClassA.class);


        myBean.doSomethingA();

        // 关闭上下文
        ((ClassPathXmlApplicationContext) context).close();
    }
}
