package io.github.xw.ioc.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.github.xw.model.MyBean;

/**
 * @author xw
 * @date 2023/10/9
 */
public class ClassPathXmlApplicationContextMain {
    public static void main(String[] args) {
        // 创建 ClassPathXmlApplicationContext 上下文
        ApplicationContext context = new MyClassPathXmlApplicationContext("applicationContext.xml");
        
        
        // 获取 "myBean" 实例
        MyBean myBean = context.getBean("myBean", MyBean.class);

        // 使用 "myBean"
        myBean.printMessage();


        // 关闭上下文
        ((MyClassPathXmlApplicationContext) context).close();
    }
}
